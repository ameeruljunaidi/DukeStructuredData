import edu.duke.*;

public class CaesarCracker {
    char mostCommon;

    /**
     * Default constructor without parameter passed will take 'e' as most common (English)
     */
    public CaesarCracker() {
        mostCommon = 'e';
    }

    /**
     * If parameter passed, language is not English
     *
     * @param c the most common character in the language chosen
     */
    public CaesarCracker(char c) {
        mostCommon = c;
    }

    /**
     * Get an array of 26 ints each representing the number of times a character showed up in the String
     *
     * @param message is the message that we want to count the characters
     * @return the array with the counts
     */
    public int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++) {
            int dex = alph.indexOf(Character.toLowerCase(message.charAt(k)));
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }

    /**
     * Get the index of the highest count
     *
     * @param vals the array of ints (representing the counts of letters)
     * @return the index of the int in the array with the highest value
     */
    public int maxIndex(int[] vals) {
        int maxDex = 0;
        for (int k = 0; k < vals.length; k++) {
            if (vals[k] > vals[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }

    /**
     * Get the key for an encrypted message
     *
     * @param encrypted the message to decrypt
     * @return the key for that encrypted message
     */
    public int getKey(String encrypted) {
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int mostCommonPos = mostCommon - 'a';
        int dkey = maxDex - mostCommonPos;
        if (maxDex < mostCommonPos) {
            dkey = 26 - (mostCommonPos - maxDex);
        }
        return dkey;
    }

    /**
     * Decrypt a message
     *
     * @param encrypted the message to decrypt
     * @return the decrypted message
     */
    public String decrypt(String encrypted) {
        int key = getKey(encrypted);
        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(encrypted);

    }

}
