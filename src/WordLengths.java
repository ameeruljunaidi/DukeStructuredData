import edu.duke.FileResource;

public class WordLengths {

    /**
     * Read in the words from resource and count the number of words of each length for all the words in resource
     * Store it in the array counts
     *
     * @param resource file to be read
     * @param counts   reference to array of word counts
     */
    public static void countWordLengths(FileResource resource, int[] counts) {

        // counts[k] should contain the number of words of length k
        // Trim start and end for non-character
        // Hyphens in the middle of a string should be considered as a character
        // If the count is larger than the last index of the array, then assume it's the last index

        for (String word : resource.words()) {
            int charCount = 0;

            for (int i = 0; i < word.length(); i++) {
                if (Character.isLetter(word.charAt(i))) {
                    charCount++;
                }
            }

            if (charCount < counts.length) {
                counts[charCount]++;
            } else {
                counts[counts.length - 1]++;
            }
        }
    }

    /**
     * Get the index of the max value in the array
     *
     * @param values array contains values to compare
     * @return index of highers count, returns -1 if for some reason cannot find
     */
    public static int maxIndex(int[] values) {
        int maxValue = 0;
        int maxIndex = -1;

        for (int i = 0; i < values.length; i++) {
            if (values[i] > maxValue) {
                maxValue = values[i];
                maxIndex = i;
            }
        }

        return maxIndex;
    }
}
