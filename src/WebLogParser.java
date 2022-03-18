import java.text.*;
import java.util.*;

public class WebLogParser {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy:kk:mm:ss Z", Locale.US);

    /**
     * Trim the surrounding delimiter of the string
     *
     * @param sb    the string to trim
     * @param delim the delimiter to look for
     * @return a String object that is already trimmed
     */
    private static String munchTo(StringBuilder sb, String delim) {
        int x = sb.indexOf(delim);

        if (x == -1) {
            x = sb.length();
        }

        String ans = sb.substring(0, x);
        sb.delete(0, x + delim.length());

        return ans;
    }

    /**
     * Assumes line is valid and in this format:
     * 110.76.104.12 - - [30/Sep/2015:07:47:11 -0400] "GET //favicon.ico HTTP/1.1" 200 3426
     *
     * @param line the represented as a string in the log file
     * @return the LogEntry object
     */
    public static LogEntry parseEntry(String line) {
        StringBuilder sb = new StringBuilder(line);
        String ip = munchTo(sb, " ");

        munchTo(sb, " "); //ignore -
        munchTo(sb, " ["); //ignore -, and eat the leading [

        String dateStr = munchTo(sb, "] \""); //]-space is intentional: eat both
        Date date = parseDate(dateStr);
        String request = munchTo(sb, "\" "); // quote-space is intentional: eat both
        String statusStr = munchTo(sb, " ");
        int status = Integer.parseInt(statusStr);
        String byteStr = munchTo(sb, " ");
        int bytes = Integer.parseInt(byteStr);

        return new LogEntry(ip, date, request, status, bytes);
    }

    /**
     * Take a string that is a date and parse it into a date object
     *
     * @param dateStr the string to parse
     * @return a Date object
     */
    public static Date parseDate(String dateStr) {
        ParsePosition pp = new ParsePosition(0);
        return dateFormat.parse(dateStr, pp);
    }

}
