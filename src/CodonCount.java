import edu.duke.FileResource;

import java.util.HashMap;

/**
 * Find out how many times each codon occurs in a strand of DNA based on reading frames
 * A codon is three consecutive symbols in a strand o fDNA such as ATT or TCC
 */
public class CodonCount {
    private final String dnaStrand;
    private final HashMap<String, Integer> dnaMap;

    CodonCount(String filename) {
        this.dnaStrand = new FileResource(filename).asString().trim();
        this.dnaMap = new HashMap<>();
    }

    /**
     * Get number of unique codons in the current frame
     *
     * @return the size of the HashMap that stores the codon
     */
    public int getMapSize() {
        return this.dnaMap.size();
    }

    /**
     * Get the number of times that codon showed up
     *
     * @param codon the particular codon we're looking for
     * @return the size of value of the key (codon)
     */
    public int getOccurrenceCount(String codon) {
        return dnaMap.get(codon);
    }

    /**
     * Build a new map of codons mapped to their counts from the string dna with the reading frame with the position
     * start (a value of 0, 1, or 2)
     *
     * @param start starting position for the frame (0, 1, or 2)
     */
    public void buildCodonMap(int start) {
        dnaMap.clear();

        for (int i = start + 3; i <= CodonCount.this.dnaStrand.length(); i += 3) {
            String currentCodon = CodonCount.this.dnaStrand.substring(i - 3, i);

            if (!dnaMap.containsKey(currentCodon)) {
                dnaMap.put(currentCodon, 1);
            } else {
                dnaMap.put(currentCodon, dnaMap.get(currentCodon) + 1);
            }
        }
    }

    /**
     * Get the codon in the current frame with the largest count
     *
     * @return the most common codon
     */
    public String getMostCommonCodon() {
        String mostCommonCodon = "";
        int mostCommonCount = 0;

        for (String currentCodon : dnaMap.keySet()) {
            int currentCodonCount = dnaMap.get(currentCodon);

            if (currentCodonCount > mostCommonCount) {
                mostCommonCount = currentCodonCount;
                mostCommonCodon = currentCodon;
            }
        }

        return mostCommonCodon;
    }

    /**
     * Print all the codons in the HashMap along with their counts if their counts is between start and end inclusive
     *
     * @param start the start index inclusive
     * @param end   the end index inclusive
     */
    public void printCodonCounts(int start, int end) {
        for (String currentCodon : dnaMap.keySet()) {
            if (dnaMap.get(currentCodon) >= start && dnaMap.get(currentCodon) <= end) {
                System.out.println(currentCodon + "\t" + dnaMap.get(currentCodon));
            }
        }
    }
}
