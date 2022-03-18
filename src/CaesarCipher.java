/**
 * CaesarCipher class provided by Duke
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int theKey;

    public CaesarCipher(int key) {
        theKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        alphabet = alphabet + alphabet.toLowerCase();
        shiftedAlphabet = shiftedAlphabet + shiftedAlphabet.toLowerCase();
    }

    /**
     * Transform a character provided into its encrypted version
     *
     * @param c    the character to change
     * @param from the original (right) set of 26 characters
     * @param to   the encrypted (shifted) set of 26 characters
     * @return the character that it changed to
     */
    private char transformLetter(char c, String from, String to) {
        int idx = from.indexOf(c);
        if (idx != -1) {
            return to.charAt(idx);
        }
        return c;
    }

    /**
     * Encrypt a character that is provided
     *
     * @param c the character to encrypt
     * @return the encrypted version of the character
     */
    public char encryptLetter(char c) {
        return transformLetter(c, alphabet, shiftedAlphabet);
    }

    /**
     * Decrypt a letter that is provided
     *
     * @param c the character to decrypt
     * @return the character that is decrypted
     */
    public char decryptLetter(char c) {
        return transformLetter(c, shiftedAlphabet, alphabet);
    }

    /**
     * Similar to transform letter, but this time for a whole String rather than a character
     *
     * @param input is the String to transform
     * @param from  the original (right) set of 26 characters
     * @param to    the encrypted (shifted) set of 26 characters
     * @return the transformed set of characters (string)
     */
    private String transform(String input, String from, String to) {
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            c = transformLetter(c, from, to);
            sb.setCharAt(i, c);
        }
        return sb.toString();
    }

    /**
     * Encrypt a String
     *
     * @param input the String to encrypt
     * @return the encrypted String
     */
    public String encrypt(String input) {
        return transform(input, alphabet, shiftedAlphabet);
    }

    /**
     * Decrypt a string
     *
     * @param input the String to decrypt
     * @returna the decrypted String
     */
    public String decrypt(String input) {
        return transform(input, shiftedAlphabet, alphabet);
    }

    /**
     * Implementation of toString
     *
     * @return the String implementation
     */
    public String toString() {
        return "" + theKey;
    }
}
