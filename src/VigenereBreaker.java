import java.util.*;

import com.sun.jdi.event.ClassUnloadEvent;
import edu.duke.*;

public class VigenereBreaker {
    /**
     * @param message     represents the encrypted message
     * @param whichSlice  indicates the index the slice should start form
     * @param totalSlices the length of the key
     * @return a String consisting of every totalSlices-th character from message, starting at the whichSLide-th char
     */
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder ret = new StringBuilder();

        for (int i = whichSlice; i < message.length(); i++) {
            int charIndex = i % totalSlices;
            if (charIndex == whichSlice) {
                ret.append(message.charAt(i));
            }
        }

        return ret.toString();
    }

    /**
     * Use the CaesarCracker class, as well as the sliceString method to find the shift for each index in the key
     * Fill in the key (which is an Array of integers) and return it
     *
     * @param encrypted  represents the encrypted message
     * @param klength    represents the key length
     * @param mostCommon indicates the most common character in the language of the message
     * @return the shift for each index in the key
     */
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];

        for (int i = 0; i < klength; i++) {
            String currentSlice = sliceString(encrypted, i, klength);

            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[i] = cc.getKey(currentSlice);
        }

        return key;
    }

    /**
     * Get a set containing all words in any language
     *
     * @param fr the file to read in
     * @return a set containing all the words in the dictionary
     */
    public static HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> wordSet = new HashSet<>();

        for (String word : fr.lines()) {
            wordSet.add(word.toLowerCase());
        }

        return wordSet;
    }

    /**
     * Split the message into words (use .split("\\W+", which returns a String array)
     *
     * @param message    is the message to read in and count words
     * @param dictionary the dictionary in whatever language we pass in
     * @return the integer representing the number of valid word found
     */
    public int countWords(String message, HashSet<String> dictionary) {
        int counter = 0;

        for (String word : message.split("\\W+")) {
            if (dictionary.contains(word.toLowerCase())) {
                counter++;
            }
        }

        return counter;
    }


    /**
     * Try all key lengths from 1 to 100 to obtain the best decryption for each key length in that range
     * For each key length, decrypt the message using VigenereCipher's decrypt method
     * Count how many words in it are real words in English, based on the dictionary passed in
     *
     * @param encrypted  the message that we want to decrypt
     * @param dictionary the dictionary that contains all the valid word in any one language
     * @return the String that is already decrypted
     */
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        String decryptedMessage = "";
        int maxRealWord = 0;
        int keyLength = 0;

        System.out.print("Breaking Vigenere ..");
        for (int i = 1; i < 100; i++) {
            VigenereCipher vc = new VigenereCipher(tryKeyLength(encrypted, i, mostCommonCharIn(dictionary)));
            String currentDecryptedMessage = vc.decrypt(encrypted);
            int currentRealWord = countWords(currentDecryptedMessage, dictionary);

            if (currentRealWord > maxRealWord) {
                maxRealWord = currentRealWord;
                decryptedMessage = currentDecryptedMessage;
                keyLength = i;
            }

            if (i % 5 == 0) {
                System.out.print(".");
            }
        }
        System.out.print(" finished.\n");

        int[] keys = tryKeyLength(encrypted, keyLength, mostCommonCharIn(dictionary));
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        // System.out.println("Keys indices are: " + Arrays.toString(keys));
        System.out.print("Keywords is: ");
        for (int i = 0; i < keys.length; i++) {
            System.out.print(alphabet.charAt(keys[i]));
        }
        System.out.println("\nNumber of real words: " + maxRealWord);

        return decryptedMessage;
    }

    /**
     * Get the most common character in any language
     *
     * @param dictionary a HashSet of all the words in that language
     * @return the most common character in the charCount HashMa
     */
    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> charCount = new HashMap<>();

        for (String currentWord : dictionary) {
            for (char currentChar : currentWord.toCharArray()) {
                if (!charCount.containsKey(currentChar)) {
                    charCount.put(currentChar, 1);
                } else {
                    int currentCount = charCount.get(currentChar);
                    charCount.put(currentChar, currentCount + 1);
                }
            }
        }

        char mostCommonChar = 'a';
        int highestCount = 0;

        for (char currentCharacter : charCount.keySet()) {
            int currentCount = charCount.get(currentCharacter);
            if (currentCount > highestCount) {
                highestCount = currentCount;
                mostCommonChar = currentCharacter;
            }
        }

        return mostCommonChar;
    }

    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        for (String currentLanguage : languages.keySet()) {
            System.out.println("Current language: " + currentLanguage);
            String decrypt = breakForLanguage(encrypted, languages.get(currentLanguage));
            System.out.print("First line: ");
            for (char currentCharacter : decrypt.toCharArray()) {
                if (currentCharacter == '\n') {
                    break;
                }
                System.out.print(currentCharacter);
            }
            System.out.println("\n\n");
        }
    }

    public HashMap<String, HashSet<String>> getDictionaries() {
        HashMap<String, HashSet<String>> dictionaries = new HashMap<>();

        String[] languages = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};

        for (String language : languages) {
            System.out.print("Reading dictionary for " + language + "...");
            String filename = "input/VigenereProgram/dictionaries/" + language;
            FileResource fr = new FileResource(filename);

            if (!dictionaries.containsKey(language)) {
                HashSet<String> dictionary = readDictionary(fr);
                dictionaries.put(language, dictionary);
            }
            System.out.println(" completed reading " + language + ".");
        }

        System.out.println("Completed reading dictionaries.\n");

        return dictionaries;
    }
}
