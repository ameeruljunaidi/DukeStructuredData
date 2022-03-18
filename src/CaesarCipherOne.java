public class CaesarCipherOne {
    private final String alphabet;
    private final String shiftedAlphabet;
    private final int key;

    public CaesarCipherOne(int key) {
        this.alphabet = "abcdefghijklmnopqrstuvwxyz";
        this.shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        this.key = key;
    }

    /**
     * @param input the original string itself
     * @return a string that has been encrypted using the caesar cipher algorithm
     */
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);

        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int index = alphabet.indexOf(Character.toLowerCase(currChar));

            if (index != -1) {
                char lowerCaseReplace = shiftedAlphabet.charAt(index);
                char upperCaseReplace = Character.toUpperCase(lowerCaseReplace);
                char newChar = Character.isLowerCase(encrypted.charAt(i)) ? lowerCaseReplace : upperCaseReplace;

                encrypted.setCharAt(i, newChar);
            }
        }

        return encrypted.toString();
    }

    /**
     * Decrypt the input given by creating a new object within a class
     *
     * @param input the encrypted message to decrypt
     * @return the decrypted message
     */
    public String decrypt(String input) {
        CaesarCipherOne cc = new CaesarCipherOne(26 - key);
        return cc.encrypt(input);
    }

    /**
     * TODO: This needs to change if we're doing this in an oop way
     *
     * @param input the string to modify
     * @param key1  key to modify odd character
     * @param key2  key to modify even character
     * @return a string that has been encrypted using some algorithm
     */
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);

        String odd = encrypt(encrypted.toString());
        String even = encrypt(encrypted.toString());

        for (int i = 0; i < encrypted.length(); i++) {
            if ((i + 1) % 2 == 0) {
                encrypted.setCharAt(i, even.charAt(i));
            } else {
                encrypted.setCharAt(i, odd.charAt(i));
            }
        }

        return encrypted.toString();
    }

}

