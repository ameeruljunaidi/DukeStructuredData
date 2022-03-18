import edu.duke.*;

import java.util.*;

public class GladLib {
    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruitList;

    private ArrayList<String> usedWords;
    private int wordsReplaced;

    private final Random myRandom;

    private static final String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static final String dataSourceDirectory = "input/GladLibData/datalong";

    /**
     * Initializer (one of two, no parameters passed):
     * Store ArrayList of lines from source file into the private variables declared in this class
     * Note, since the source is not passed, need to make sure dataSourceDirectory is referring to the right location
     * dataSourceDirectory needs to refer to relative path of data in GladLibData
     * Create a new instance of the object Random
     */
    public GladLib() {
        usedWords = new ArrayList<>();
        wordsReplaced = 0;

        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    /**
     * Initializer (two of two, passes a source location):
     * The source location seems to be designed to date dateSourceURL
     * Store ArrayList of lines from source file into the private variables declared in this class
     * Create a new instance of the object Random
     *
     * @param source the source location of the file, either local or online
     */
    public GladLib(String source) {
        usedWords = new ArrayList<>();

        initializeFromSource(source);
        myRandom = new Random();
    }

    /**
     * Initialize the private variables that are set up in this class
     *
     * @param source the source of the file, could be either local or online
     */
    private void initializeFromSource(String source) {
        adjectiveList = readIt(source + "/adjective.txt");
        nounList = readIt(source + "/noun.txt");
        colorList = readIt(source + "/color.txt");
        countryList = readIt(source + "/country.txt");
        nameList = readIt(source + "/name.txt");
        animalList = readIt(source + "/animal.txt");
        timeList = readIt(source + "/timeframe.txt");
        verbList = readIt(source + "/verb.txt");
        fruitList = readIt(source + "/fruit.txt");
    }

    /**
     * Get a random from a source of type ArrayList
     *
     * @param source the ArrayList that contains all the random words
     * @return a randomly picked word from the arrayList
     */
    private String randomFrom(ArrayList<String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    /**
     * 'If the current word is supposed to be substituted (wrapped with angle brackets), replace that word
     *
     * @param label type of word to be replaced with
     * @return a String of the replacement word
     */
    private String getSubstitute(String label) {
        if (label.equals("country")) {
            return randomFrom(countryList);
        }
        if (label.equals("color")) {
            return randomFrom(colorList);
        }
        if (label.equals("noun")) {
            return randomFrom(nounList);
        }
        if (label.equals("name")) {
            return randomFrom(nameList);
        }
        if (label.equals("adjective")) {
            return randomFrom(adjectiveList);
        }
        if (label.equals("animal")) {
            return randomFrom(animalList);
        }
        if (label.equals("timeframe")) {
            return randomFrom(timeList);
        }
        if (label.equals("number")) {
            return "" + myRandom.nextInt(50) + 5;
        }
        if (label.equals("verb")) {
            return randomFrom(verbList);
        }
        if (label.equals("fruit")) {
            return randomFrom(fruitList);
        }
        return "**UNKNOWN**";
    }

    /**
     * Takes in a word and see if the word is supposed to be replaced with another word
     *
     * @param w the word current being processed
     * @return a String with either the same word, or replaced by a random word
     */
    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);

        if (first == -1 || last == -1) {
            return w;
        }

        String prefix = w.substring(0, first);
        String suffix = w.substring(last + 1);
        String sub = getSubstitute(w.substring(first + 1, last));

        while (usedWords.contains(sub)) {
            sub = getSubstitute(w.substring(first + 1, last));
        }

        usedWords.add(sub);
        wordsReplaced++;

        return prefix + sub + suffix;
    }

    /**
     * Utility function to print out a story to the console, within a fixed lineWidth
     *
     * @param s         the string (story) to print out to the console
     * @param lineWidth the max with of the string to be printed out to the console
     */
    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for (String w : s.split("\\s+")) {
            if (charsWritten + w.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w + " ");
            charsWritten += w.length() + 1;
        }
    }

    /**
     * Create a story from a source (works with a local or online source)
     *
     * @param source the source location of the story
     * @return a String with the story to be printed
     */
    private String fromTemplate(String source) {
        StringBuilder story = new StringBuilder();

        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);

            for (String word : resource.words()) {
                story.append(processWord(word)).append(" ");
            }
        } else {
            FileResource resource = new FileResource(source);

            for (String word : resource.words()) {
                story.append(processWord(word)).append(" ");
            }
        }

        return story.toString();
    }

    /**
     * Get an ArrayList of all the lines in a source
     * Takes in a source location and determine if it is from a local or online source
     *
     * @param source the location of the file, either local or online
     * @return ArrayList with all the lines in that file
     */
    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        }
        return list;
    }

    /**
     * This is the root of the function calls, most likely
     * Prints out a new line and create a new random story using a utility function
     */
    public void makeStory() {
        System.out.println("\n");
        String story = fromTemplate("input/GladLibData/data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n\nTotal number of words replaced: " + wordsReplaced);
    }
}
