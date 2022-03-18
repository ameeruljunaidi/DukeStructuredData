import java.util.*;

/**
 * Class implemented by Duke
 */
public class VigenereCipher {
    CaesarCipher[] ciphers;

    /**
     * Constructor take an array of ints that is the keys for the encrypted message
     *
     * @param key an array of ints representing the keys to decrypt the encrypted message
     */
    public VigenereCipher(int[] key) {
        ciphers = new CaesarCipher[key.length];
        for (int i = 0; i < key.length; i++) {
            ciphers[i] = new CaesarCipher(key[i]);
        }
    }

    /**
     * Encrypt a String using the VigenereCipher
     * This method encrypts the String character by character using the encryptLetter method from CaesarCipher
     *
     * @param input the String to encrypt
     * @return the encrypted String
     */
    public String encrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.encryptLetter(c));
            i++;
        }
        return answer.toString();
    }

    /**
     * Decrypt a Vigenere encrypted message
     * Essentially the same as encrypting function, the only difference is the function call
     *
     * @param input the message to decrypt
     * @return the decrypted message
     */
    public String decrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.decryptLetter(c));
            i++;
        }
        return answer.toString();
    }

    /**
     * toString implementation
     *
     * @return the String implementation
     */
    public String toString() {
        return Arrays.toString(ciphers);
    }

}
