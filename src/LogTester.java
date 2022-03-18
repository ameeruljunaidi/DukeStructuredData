/**
 * Write a description of class Tester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class LogTester {
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);

        System.out.println(le);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("input/UniqueIPData/short-test_log");
        la.printAll();
    }

    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("input/UniqueIPData/weblog2_log");
        System.out.println(la.countUniqueIPs());
    }

    public void testPrintFilterByNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("input/UniqueIPData/weblog-short_log");
        la.printAllHigherThanNum(400);
    }

    public void testPrintFilterByDate() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("input/UniqueIPData/weblog2_log");

        ArrayList<String> ipAddresses = la.uniqueIPVisitsOnDay("Sep 24");

        for (String ipAddress : ipAddresses) {
            System.out.println(ipAddress);
        }

        System.out.println("Count: " + ipAddresses.size());
    }

    public void testUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("input/UniqueIPData/weblog2_log");

        System.out.println(la.countUniqueIPsInRange(200, 299));
    }

    public void testCountVisitPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("input/UniqueIPData/weblog1_log");

        System.out.println(la.countVisitsPerIP());
    }

    public void testMaxNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("input/UniqueIPData/weblog2_log");

        System.out.println(la.mostNumberVisitsByIP(la.countVisitsPerIP()));
    }

    public void testIpsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("input/UniqueIPData/weblog2_log");

        System.out.println(la.iPsMostVisits(la.countVisitsPerIP()));
    }

    public void testIpForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("input/CountingVisitsData/weblog3-short_log");
        System.out.println(la.iPsForDays());
    }

    public void testDaysWithMostVisit() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("input/UniqueIPData/weblog2_log");
        System.out.println(la.dayWithMostIPVisits(la.iPsForDays()));
    }

    public void testIpsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("input/UniqueIPData/weblog2_log");
        System.out.println(la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Sep 29"));
    }
}
