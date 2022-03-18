import edu.duke.FileResource;

public class CaesarBreaker {

    /**
     * Count the number of counts each letter has in the phrase
     *
     * @param phrase to count the letters in
     * @param counts array to update
     */
    public static void countLetters(String phrase, int[] counts) {
        assert counts.length == 26;

        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < phrase.length(); i++) {
            if (!Character.isLetter(phrase.charAt(i))) {
                continue;
            }

            int charIndex = alphabet.indexOf(Character.toLowerCase(phrase.charAt(i)));
            counts[charIndex]++;
        }
    }

    /**
     * A way to return an encrypted message back to normal
     *
     * @param encryptedMessage is the message to decrypt
     * @param key              the key for the decryption
     * @return a decrypted message
     */
    public static String decrypt(String encryptedMessage, int key) {
        return "TODO";
    }

    /**
     * Get every other char from
     *
     * @param message of the original message to get the new string from
     * @param start   what index should the message start from
     * @return a string with every other character from the index
     */
    public static String halfOfString(String message, int start) {
        StringBuilder ret = new StringBuilder();

        for (int i = start; i < message.length(); i += 2) {
            ret.append(message.charAt(i));
        }

        return ret.toString();
    }

    /**
     * Get the key of the encrypted message by looking at the most frequent char in the encrypted message
     *
     * @param s the message to look the frequency for
     * @return the key (an int) for the encrypted message
     */
    public static int getKey(String s) {
        int[] counts = new int[26];

        countLetters(s, counts);

        int ret;

        if (WordLengths.maxIndex(counts) - 4 >= 0) {
            ret = WordLengths.maxIndex(counts) - 4;
        } else {
            ret = 22 - WordLengths.maxIndex(counts);
        }

        return ret;
    }

    /**
     * Decrypt a message that was encrypted using two keys
     *
     * @param s the encrypted message
     * @return a string of the encrypted message decrypted
     */
    public static String decryptTwoKeys(String s) {
        StringBuilder ret = new StringBuilder(s);

        String first = halfOfString(s, 0);
        String second = halfOfString(s, 1);

        int firstKey = getKey(first);
        int secondKey = getKey(second);

        String firstDecrypted = decrypt(first, firstKey);
        String secondDecrypted = decrypt(second, secondKey);

        {
            int firstIndex = 0;
            int secondIndex = 0;

            for (int i = 0; i < ret.length(); i++) {
                if (i % 2 == 0) {
                    ret.setCharAt(i, firstDecrypted.charAt(firstIndex));
                    firstIndex++;
                } else {
                    ret.setCharAt(i, secondDecrypted.charAt(secondIndex));
                    secondIndex++;
                }
            }
        }

        return ret.toString();
    }

}
