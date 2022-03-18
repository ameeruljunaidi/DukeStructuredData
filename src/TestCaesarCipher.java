public class TestCaesarCipher {
    /**
     * Test to break an encrypted message
     * Figure out which key was used to encrypt this message
     *
     * @param input in the encrypted message to break
     * @return the decrypted message
     */
    public String breakCaesarCipher(String input) {
        int keyFound = CaesarBreaker.getKey(input);

        CaesarCipherOne cc = new CaesarCipherOne(keyFound);

        return cc.decrypt(input);
    }

    public void simpleTests() {
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";

        CaesarCipherOne cc = new CaesarCipherOne(15);
        String encryptedMessage = cc.encrypt(message);
        String decryptedMessage = breakCaesarCipher(encryptedMessage);

        System.out.println(encryptedMessage);
        System.out.println(decryptedMessage);
    }

}
