/**
 * Write a description of class LogAnalyzer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        this.records = new ArrayList<>();
    }

    /**
     * Read a file line by line and add all the log entries into the array
     *
     * @param filename the name of the file to read from
     */
    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);

        for (String line : fr.lines()) {
            LogEntry le = WebLogParser.parseEntry(line);
            this.records.add(le);
        }
    }

    /**
     * Print all the LogEntry in the log entry ArrayList
     */
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    /**
     * Get the number of unique IP addresses, assumes member variable records already filled in
     *
     * @return size of a local ArrayList within method
     */
    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<>();

        for (LogEntry logEntry : records) {
            if (!uniqueIPs.contains(logEntry.getIpAddress())) {
                uniqueIPs.add(logEntry.getIpAddress());
            }
        }

        return uniqueIPs.size();
    }

    /**
     * Examine all the web log entries in records and print those LogEntry that have a status code greater than num
     *
     * @param num the lowe bound (non-inclusive)
     */
    public void printAllHigherThanNum(int num) {
        for (LogEntry logEntry : records) {
            if (logEntry.getStatusCode() > num) {
                System.out.println(logEntry);
            }
        }
    }

    /**
     * Access the web logs in records and return an ArrayList of Strings of unique IP address
     * that has had access on any given day
     *
     * @param someday format is "MMM DD" where
     *                MMM is the first three characters of the month with the first letter capitalized
     *                DD is the day in two digits
     *                <p>
     *                Example: "Dec 05" and "Apr 22"
     * @return the ArrayList of unique IP visits
     */
    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPs = new ArrayList<>();

        for (LogEntry logEntry : records) {
            if (logEntry.getAccessTime().toString().contains(someday) && !uniqueIPs.contains(logEntry.getIpAddress())) {
                uniqueIPs.add(logEntry.getIpAddress());
            }
        }

        return uniqueIPs;
    }

    /**
     * Ge the number of unique IP addresses in records that have a status code in the range from low to high, inclusive
     *
     * @param low  the left boundary, inclusive
     * @param high the  right boundary, inclusive
     * @return the size of the local ArrayList
     */
    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPs = new ArrayList<>();

        for (LogEntry logEntry : records) {
            if (logEntry.getStatusCode() >= low && logEntry.getStatusCode() <= high &&
                    !uniqueIPs.contains(logEntry.getIpAddress())) {
                uniqueIPs.add(logEntry.getIpAddress());
            }
        }

        return uniqueIPs.size();
    }

    /**
     * Get a HashMap that maps na IP address to the number of times that IP address appears in records
     * The number times this IP address visited the website
     *
     * @return a HashMap of String and Integer with IP address as key and count as value
     */
    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> visits = new HashMap<>();

        for (LogEntry le : records) {
            String ipAddress = le.getIpAddress();

            if (!visits.containsKey(ipAddress)) {
                visits.put(ipAddress, 1);
            } else {
                visits.put(ipAddress, visits.get(ipAddress) + 1);
            }
        }

        return visits;
    }

    /**
     * Takes a map and map an IP address to the number of times that IP address appears in the web log file
     *
     * @param map the HashMap that contains the IP address as its key and count as value
     * @return the max number in the value field of the map
     */
    public int mostNumberVisitsByIP(HashMap<String, Integer> map) {
        int maxCount = 0;

        for (String ipAddress : map.keySet()) {
            int currentCount = map.get(ipAddress);
            if (currentCount > maxCount) {
                maxCount = currentCount;
            }
        }

        return maxCount;
    }

    /**
     * Get a list of IP addresses that all have the maximum number of visit to the website
     *
     * @param map is the map that contains the IP address as its key and count for its value
     * @return an ArrayList of IP address that has the max number of visits to the website
     */
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map) {
        ArrayList<String> ipAddresses = new ArrayList<>();

        int maxCount = mostNumberVisitsByIP(map);

        for (String ipAddress : map.keySet()) {
            if (map.get(ipAddress) == maxCount) {
                ipAddresses.add(ipAddress);
            }
        }

        return ipAddresses;
    }

    /**
     * Get a map to see all the IP addresses that shows up during that day
     * Format for the day is "MMM DD"
     *
     * @return a HashMap with key as value and ArrayList of IP addresses as the value
     */
    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> dayMap = new HashMap<>();

        for (LogEntry le : records) {
            String date = le.getAccessTime().toString().substring(4, 10);
            ArrayList<String> ipAddresses;

            if (!dayMap.containsKey(date)) {
                ipAddresses = new ArrayList<>();

            } else {
                ipAddresses = dayMap.get(date);
            }

            ipAddresses.add(le.getIpAddress());
            dayMap.put(date, ipAddresses);
        }

        return dayMap;
    }

    /**
     * Get the day with the most visitors during that day
     *
     * @param dayMap the map that contains the day and the list of IP addresses that visits on that day
     * @return the ArrayList in the map with the largest size
     */
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dayMap) {
        String dayMostVisited = "";
        int maxVisit = 0;

        for (String day : dayMap.keySet()) {
            int currentVisit = dayMap.get(day).size();

            if (currentVisit > maxVisit) {
                maxVisit = currentVisit;
                dayMostVisited = day;
            }
        }

        return dayMostVisited;
    }

    /**
     * Find the user that visited the website the most during that day
     *
     * @param dayMap the HashMap that has the day as key and ArrayList of IP addresses as value
     * @param day    the day String formatted as "MMM DD"
     * @return an ArrayList with IP address that has accessed the site the most
     */
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dayMap, String day) {
        ArrayList<String> ipAddresses = new ArrayList<>();
        HashMap<String, Integer> ipAddressesCount = new HashMap<>();
        int maxCount = 0;

        for (String ipAddress : dayMap.get(day)) {
            if (!ipAddressesCount.containsKey(ipAddress)) {
                ipAddressesCount.put(ipAddress, 1);
            } else {
                ipAddressesCount.put(ipAddress, ipAddressesCount.get(ipAddress) + 1);
            }

            if (ipAddressesCount.get(ipAddress) > maxCount) {
                maxCount = ipAddressesCount.get(ipAddress);
            }
        }

        for (String ipAddress : ipAddressesCount.keySet()) {
            if (ipAddressesCount.get(ipAddress) == maxCount && !ipAddresses.contains(ipAddress)) {
                ipAddresses.add(ipAddress);
            }
        }

        return ipAddresses;
    }
}
