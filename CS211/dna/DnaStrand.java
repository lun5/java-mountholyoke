package dna;

/**
 * Interface for DNA/strand experiments
 * 
 * @author Owen Astrachan
 * @date February, 2008
 * @author Barbara Lerner Small modifications
 * @date March 25, 2010
 */
public interface DnaStrand {

    /**
     * Cut this strand at every occurrence of enzyme, replacing
     * every occurrence of enzyme with splicee.
     * @param enzyme is the pattern/strand searched for and replaced
     * @param splicee is the pattern/strand replacing each occurrence of enzyme
     * @return the new strand leaving the original strand unchanged.
     */
    public DnaStrand cutAndSplice(String enzyme, String splicee);

    /**
     * Returns the number of elements/base-pairs/nucleotides in this strand.
     * @return the number of base-pairs in this strand
     */
    public long size();

    /**
     * @return the number of cuts in the dna
     */
	public int getNumBreaks();

}