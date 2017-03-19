package dna;
import java.util.*;
/**
 * Implementation of a DNA strand where the DNA sequence 
 * may be broken across multiple nodes
 * @author Luong Nguyen
 * @date 04/12/11
 */ 

public class LinkedListDNAStrand implements DnaStrand
{
	// The DNA in the strand
	private LinkedList<String> dnaSequence = new LinkedList<String>();
	// the number of break
	int numBreaks;
	
	
	 /**
     * Create a strand representing an empty DNA strand, length of zero.
     */
	public LinkedListDNAStrand()
	{
		dnaSequence.addFirst("");
	}
	
	/**
     * Create a strand representing s. No error checking is done to see if s represents
     * valid genomic/DNA data.
     * @param s is the source of cgat data for this strand
     * initially the entire DNA seq will be in a single node
     */
    public LinkedListDNAStrand(String s) {
        dnaSequence.addFirst(s);
    }
    	
	 /**
     * Cut this strand at every occurrence of enzyme, replacing
     * every occurrence of enzyme with splicee.
     * @param enzyme is the pattern/strand searched for and replaced
     * @param splicee is the pattern/strand replacing each occurrence of enzyme
     * @return the new strand leaving the original strand unchanged.
     */
    public DnaStrand cutAndSplice(String enzyme, String splicee) {
		// Find the first occurrence of the enzyme
		int enzymeStart = dnaSequence.getFirst().indexOf(enzyme);
		// If the enzyme did not appear in the DNA, return an empty DNA strand.
		if (enzymeStart == -1) {
			return new LinkedListDNAStrand();
		}		
				
		// Search for all occurrences of the enzyme.  Replace each with the splicee dna.
		int enzymeEnd = 0;
		while (enzymeStart != -1) {
			numBreaks++;			
			// Part before the enzyme
			append(dnaSequence.getFirst().substring(enzymeEnd, enzymeStart));
			// Splicee
			append(splicee);
			// Skip over the enzyme for the next search
			enzymeEnd = enzymeStart + enzyme.length();
			enzymeStart = dnaSequence.getFirst().indexOf(enzyme, enzymeEnd);
			}
		// Attach what is left over of the dna
		append(dnaSequence.getFirst().substring(enzymeEnd));
		// remove the first node which have the whole original string
		dnaSequence.removeFirst();
		// Return a new strand containing the recombinant dna.
		return this;//new LinkedListDNAStrand (toString());
	}

    
    /**
     * append places a String in a new node and puts it at the end of the linked list
     * @param String
     * @return
     */
    private void append(String s)
    {
    	dnaSequence.addLast(s);
    }
    
    
    /**
     * Returns the number of elements/base-pairs/nucleotides in this strand.
     * @return the number of base-pairs in this strand
     */
    public long size()
    {
    	int s=0;// keep track of the size of the dna
    	for (int i=0; i< dnaSequence.size();i++)
    	{
    		s = s+ dnaSequence.get(i).length();
    	}
    	return s;
    }

      
    /**
     * @return the number of cuts in the dna
     */
	public int getNumBreaks()
	{
		return numBreaks;
	}
	
	/**
	 * toString walks linked list
	 * and produces the string value
	 */
	public String toString()
	{
		// the return string
		String s = "";
		for(int i=0; i<size(); i++){
			s = s+ dnaSequence.get(i);			
		}
		return s;
	}
		
}
