package dna;


/**
 * Simple but inefficient implementation of IDnaStrand. This implementation uses
 * Strings to represent genomic/DNA data. A StringBuilder is used where
 * appropriate for concatenation efficiency.
 * @author ola
 * @date January 2008, modified and commented September 2008
 * 
 * @author Barbara Lerner
 * @date March 2010, some modifications
 */

public class StringDNAStrand implements DnaStrand {
	// The DNA in the strand
    private String dnaSequence;
    
    // The number of cuts that have been made in the strand.
    private int numBreaks;

    /**
     * Create a strand representing an empty DNA strand, length of zero.
     */
    public StringDNAStrand() {
        this("");
    }

    /**
     * Create a strand representing s. No error checking is done to see if s represents
     * valid genomic/DNA data.
     * @param s is the source of cgat data for this strand
     */
    public StringDNAStrand(String s) {
        dnaSequence = s;
    }

    /**
     * Cut this strand at every occurrence of enzyme, replacing
     * every occurrence of enzyme with splicee.
     * @param enzyme is the pattern/strand searched for and replaced
     * @param splicee is the pattern/strand replacing each occurrence of enzyme
     * @return the new strand leaving the original strand unchanged.  If the original
     *    strand does not contain the enzyme, the empty strand is returned.
     */
	public DnaStrand cutAndSplice(String enzyme, String splicee) {
		// Find the first occurrence of the enzyme
		int enzymeStart = dnaSequence.indexOf(enzyme);
		
		// If the enzyme did not appear in the DNA, return an empty DNA strand.
		if (enzymeStart == -1) {
			return new StringDNAStrand();
		}
		
		// StringBuilder is a class that lets us concatenate strings efficiently.
		StringBuilder recombStrand = new StringBuilder();
		
		// Search for all occurrences of the enzyme.  Replace each with the splicee dna.
		int enzymeEnd = 0;
		while (enzymeStart != -1) {
			numBreaks++;
			
			// Part before the enzyme
			recombStrand.append(dnaSequence.substring(enzymeEnd, enzymeStart));
			
			// Splicee
			recombStrand.append(splicee);
			
			// Skip over the enzyme for the next search
			enzymeEnd = enzymeStart + enzyme.length();
			enzymeStart = dnaSequence.indexOf(enzyme, enzymeEnd);
		}
		
		// Attach what is left over of the dna
		recombStrand.append(dnaSequence.substring(enzymeEnd));
		
		// Return a new strand containing the recombinant dna.
		return new StringDNAStrand (recombStrand.toString());
	}

    /**
     * Returns the number of nucleotides/base-pairs in this strand.
     * @return the number of base-pairs in this IDnaStrand
     */
    public long size() {
        return dnaSequence.length();
    }
    
    /**
     * @return the number of times the strand was cut.
     */
    public int getNumBreaks() {
    	return numBreaks;
    }

    @Override
    public String toString() {
        return dnaSequence;
    }

}

