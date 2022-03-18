import edu.duke.FileResource;

import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Determine the word that occurs the most often in a file
 * If more than one word occurs as the most often, return the fist such word found
 * ! Make all words lowercase before counting them
 * ! Punctuation should be ignored - "end" and "end," are different words
 */

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        this.myWords = new ArrayList<>();
        this.myFreqs = new ArrayList<>();
    }

    /**
     * Get the size of the word array
     *
     * @return length of myWords/myFreqs array
     */
    public int getSize() {
        assert myWords.size() == myFreqs.size();

        return this.myWords.size();
    }

    /**
     * Get the words at a certain index
     *
     * @param index the index to access in the myWords array
     * @return the word that is at that index
     */
    public String getWord(int index) {
        return myWords.get(index);
    }

    /**
     * Get the count of certain words at a particular index
     *
     * @param index the index to access the myFreqs array
     * @return the count that is at that index
     */
    public int getFreq(int index) {
        return myFreqs.get(index);
    }

    /**
     * Clear both myWords and myFreqs, using the .clear() method
     * Then selects a file and iterates over every word in the file
     * Putting the unique words found into myWords
     * For each world in the kth position, count how much occurrence into myFreqs
     */
    public void findUnique(String filename) {
        this.myWords.clear();
        this.myFreqs.clear();

        FileResource fr = new FileResource(filename);

        for (String word : fr.words()) {
            String lowerCaseWord = word.toLowerCase();

            if (!myWords.contains(lowerCaseWord)) {
                this.myWords.add(lowerCaseWord);
                this.myFreqs.add(1);
            } else {
                int index = this.myWords.indexOf(lowerCaseWord);
                int newCount = this.myFreqs.get(index) + 1;
                this.myFreqs.set(index, newCount);
            }
        }
    }


    /**
     * Get the index of the word with the highest frequency
     *
     * @return the index with the highest frequency
     */
    public int findIndexOfMax() {
        int maxIndex = 0;
        int maxValue = 0;

        for (int i = 0; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > maxValue) {
                maxValue = myFreqs.get(i);
                maxIndex = i;
            }
        }

        return maxIndex;
    }
}
