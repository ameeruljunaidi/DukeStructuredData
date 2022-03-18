/**
 * This class encrypts a message with two keys
 */
public class CaesarCipherTwo {
    private final String alphabet;
    private final String shiftedAlphabet1;
    private final String shiftedAlphabet2;
    private final int key1;
    private final int key2;

    public CaesarCipherTwo(int key1, int key2) {
        this.alphabet = "abcdefghijklmnopqrstuvwxyz";

        this.shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        this.shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);

        this.key1 = key1;
        this.key2 = key2;
    }

    public String encrypt(String input) {
        StringBuilder encryptedMessage = new StringBuilder(input);

        CaesarCipherOne cc1 = new CaesarCipherOne(key1);
        CaesarCipherOne cc2 = new CaesarCipherOne(key2);

        String first = cc1.encrypt(input);
        String second = cc2.encrypt(input);

        for (int i = 0; i < encryptedMessage.length(); i++) {
            if (i % 2 == 0) {
                encryptedMessage.setCharAt(i, first.charAt(i));
            } else {
                encryptedMessage.setCharAt(i, second.charAt(i));
            }
        }

        return encryptedMessage.toString();
    }

    public String decrypt(String input) {
        StringBuilder decryptedMessage = new StringBuilder(input);

        CaesarCipherOne cc1 = new CaesarCipherOne(key1);
        CaesarCipherOne cc2 = new CaesarCipherOne(key2);

        String first = cc1.decrypt(input);
        String second = cc2.decrypt(input);

        for (int i = 0; i < decryptedMessage.length(); i++) {
            if (i % 2 == 0) {
                decryptedMessage.setCharAt(i, first.charAt(i));
            } else {
                decryptedMessage.setCharAt(i, second.charAt(i));
            }
        }

        return decryptedMessage.toString();
    }
}
