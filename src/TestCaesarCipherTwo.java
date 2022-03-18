public class TestCaesarCipherTwo {
    /**
     * Try to break the cipher with two keys
     *
     * @param input is the encrypted message
     * @return the decrypted message
     */
    public String breakCaesarCipher(String input) {
        StringBuilder decryptedMessage = new StringBuilder(input);

        String first = CaesarBreaker.halfOfString(input, 0);
        String second = CaesarBreaker.halfOfString(input, 1);

        int firstKey = CaesarBreaker.getKey(first);
        int secondKey = CaesarBreaker.getKey(second);

        CaesarCipherOne ccFirst = new CaesarCipherOne(firstKey);
        CaesarCipherOne ccSecond = new CaesarCipherOne(secondKey);

        String firstDecrypted = ccFirst.decrypt(first);
        String secondDecrypted = ccSecond.decrypt(second);

        int firstIndex = 0;
        int secondIndex = 0;

        for (int i = 0; i < decryptedMessage.length(); i++) {
            if (i % 2 == 0) {
                decryptedMessage.setCharAt(i, firstDecrypted.charAt(firstIndex));
                firstIndex++;
            } else {
                decryptedMessage.setCharAt(i, secondDecrypted.charAt(secondIndex));
                secondIndex++;
            }
        }

        return decryptedMessage.toString();
    }

    public void simpleTests() {
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";

        CaesarCipherTwo cct = new CaesarCipherTwo(21, 3);
        String encryptedMessage = cct.encrypt(message);
        String decryptedMessage = breakCaesarCipher(encryptedMessage);

        System.out.println(encryptedMessage);
        System.out.println(decryptedMessage);
    }
}
