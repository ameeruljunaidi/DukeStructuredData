import edu.duke.FileResource;

import java.util.Arrays;
import java.util.HashSet;

public class TestVigenere {
    public static String filename = "input/SecretData/secretmessage4.txt";
    public static String dictionaryFile = "input/VigenereProgram/dictionaries/English";
    public static FileResource fr = new FileResource(filename);
    public static FileResource dfr = new FileResource(dictionaryFile);
    public static String message = fr.asString();
    public static HashSet<String> dictionary = VigenereBreaker.readDictionary(dfr);

    public static void testSliceString() {
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println(vb.sliceString("abcdefghijklm", 0, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 0, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 3, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 0, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 3, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 4, 5));
    }

    public static void testTryKeyLength() {
        VigenereBreaker vg = new VigenereBreaker();
        int keyLength = 38;

        int[] keys = vg.tryKeyLength(message, keyLength, 'e');

        VigenereCipher vc = new VigenereCipher(keys);
        String decrypt = vc.decrypt(message);
        int wordCount = vg.countWords(decrypt, dictionary);

        System.out.println("Count of real words: " + wordCount);
        System.out.println(Arrays.toString(keys));
    }

    public static void breakVigenere() {
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println(vb.breakForLanguage(message, dictionary));
    }


    public static void testMostCommonChar() {
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println("Most common char is: " + vb.mostCommonCharIn(dictionary));
    }

    public static void testBreakForAllLangs() {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakForAllLangs(message, vb.getDictionaries());
    }
}
