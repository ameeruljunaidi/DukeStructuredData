/**
 * Transform words from a file into another form
 *
 * @author AJ Junaidi
 * @version 1.0
 */
public class WordPlay {
    /**
     * Check if character is a vowel
     *
     * @param ch check if this character is a vowel
     * @return true if ch is a vowel (one of 'a', 'e', 'i', 'o', 'u') including uppercase
     */
    public boolean isVowel(char ch) {
        if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z') {
            char[] lowerVowel = {'a', 'e', 'i', 'o', 'u'};
            char[] upperVowel = {'A', 'E', 'I', 'O', 'U'};

            for (char currChar : lowerVowel) {
                if (currChar == ch) return true;
            }

            for (char currChar : upperVowel) {
                if (currChar == ch) return true;
            }
        }

        return false;
    }

    /**
     * @param phrase the input phrase to operate on
     * @param ch     the char to replace by
     * @return a string that is the string phrase with all the vowels replaced by ch
     */
    public String replaceVowels(String phrase, char ch) {
        StringBuilder ret = new StringBuilder(phrase);

        for (int i = 0; i < phrase.length(); i++) {
            if (isVowel(ret.charAt(i))) ret.setCharAt(i, ch);
        }

        return ret.toString();
    }

    /**
     * @param phrase the input phrase to operate on
     * @param ch     the char to replace by
     * @return a string of phrase with the ch (upper or lower case) replaced by something else
     */
    public String emphasize(String phrase, char ch) {
        StringBuilder ret = new StringBuilder(phrase);

        for (int i = 0; i < phrase.length(); i++) {
            if (String.valueOf(ret.charAt(i)).equalsIgnoreCase(String.valueOf(ch))) {
                if ((i + 1) % 2 == 0) {
                    ret.setCharAt(i, '+');
                } else {
                    ret.setCharAt(i, '*');
                }
            }
        }

        return ret.toString();
    }
}
