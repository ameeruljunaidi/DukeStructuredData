import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Determine the characters in one of Shakespeare's plays that has the most speaking parts
 * The method used is to go character by character and look for the first period
 * Assume that everything up to the first period is the name of a character and count how many  times that occurs
 * Printing out only those characters that appear a lot, we will get rid of most of the errors
 */
public class CharactersInPlay {
    private ArrayList<String> characterName;
    private ArrayList<Integer> characterCount;

    public CharactersInPlay() {
        this.characterName = new ArrayList<>();
        this.characterCount = new ArrayList<>();
    }

    /**
     * Get a character at a particular index
     *
     * @param index of the character to get
     * @return the name of the character at that particular index
     */
    public String getCharacterName(int index) {
        return this.characterName.get(index);
    }

    /**
     * Get a count of a character at a particular index
     *
     * @param index of the character we are looking at
     * @return the count of the character's frequency
     */
    public int getCharacterCount(int index) {
        return this.characterCount.get(index);
    }

    public int getCharacterCountAtRank(int rank) {
        ArrayList<Integer> sortedCount = new ArrayList<>(this.characterCount);

        sortedCount.sort(Collections.reverseOrder());

        return sortedCount.get(rank);
    }

    /**
     * Get the size of the current class
     *
     * @return size of the array, should be the same for both
     */
    public int getSize() {
        assert this.characterName.size() == this.characterCount.size();

        return characterName.size();
    }

    /**
     * Update the two ArrayList, adding the character's name if it is not already there
     * Counting this line as one speaking part of this person
     *
     * @param person is the name of the character that we are counting
     */
    public void update(String person) {
        if (!this.characterName.contains(person)) {
            this.characterName.add(person);
            this.characterCount.add(1);
        } else {
            int index = this.characterName.indexOf(person);
            int newCount = this.characterCount.get(index) + 1;
            this.characterCount.set(index, newCount);
        }
    }

    /**
     * Opens a file, read line-by-line, if there is a period on the line
     * Extract the possible name of the speaking part
     * Call update to count it as an occurrence for this person
     * Clear the appropriate instance variables before each new file
     */
    public void findAllCharacters(String filename) {
        this.characterName.clear();
        this.characterCount.clear();

        FileResource fr = new FileResource(filename);

        for (String line : fr.lines()) {
            String lineTrimmed = line.trim();
            StringBuilder potentialCharacter = new StringBuilder();

            for (int i = 0; i < lineTrimmed.length(); i++) {
                Character currentCharacter = Character.toLowerCase(lineTrimmed.charAt(i));

                if (currentCharacter.equals('.')) {
                    this.update(potentialCharacter.toString());

                    break;
                } else {
                    potentialCharacter.append(currentCharacter);
                }
            }
        }
    }

    /**
     * Filter the character based on occurence
     * num1 should be less than or equal num2
     * Print out the names of all those characters that have exactly number speaking parts
     * Where number is greater than or equal to num1 and less than or equal to num2
     *
     * @param num1 the left boundary
     * @param num2 the right boundary
     */
    public void characterWithNumParts(int num1, int num2) {
        assert num1 <= num2;

        for (int i = 0; i < this.getSize(); i++) {
            int count = this.getCharacterCount(i);
            if (count >= num1 && count <= num2) {
                System.out.println(this.getCharacterName(i));
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

        for (int i = 0; i < characterCount.size(); i++) {
            if (characterCount.get(i) > maxValue) {
                maxValue = characterCount.get(i);
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    public void findMaxFilteredByPartCount(int num1, int num2) {
        ArrayList<Integer> characters = new ArrayList<>();

        assert num1 <= num2;

        for (int i = 0; i < this.getSize(); i++) {
            int count = this.getCharacterCount(i);

            if (count >= num1 && count <= num2) {
                if (!characters.contains(i)) {
                    characters.add(i);
                }
            }
        }

        int maxCount = 0;
        int maxIndex = 0;

        for (int ci : characters) {
            if (this.getCharacterCount(ci) > maxCount) {
                maxCount = this.getCharacterCount(ci);
                maxIndex = ci;
            }
        }

        System.out.println(this.getCharacterName(maxIndex));
    }
}
