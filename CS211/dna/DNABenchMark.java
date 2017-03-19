package dna;


import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 * A benchmark program for DNA splicing.
 * 
 * @author Owen Astrachan
 * @author Barbara Lerner Added comments and did some refactoring.
 * 
 */
public class DNABenchMark extends JPanel {
	// Number of splice trials to benchmark. Each trial uses a different length
	// splice
	private static final int NUM_TRIALS = 32;

	// Number of times to repeat at each length.
	private static final int NUM_REPEATS = 3;

	// The minimum length of the dna being spliced in.
	private static final int MINIMUM_SPLICE_STRING_LENGTH = 256;

	// The DNA sequence for the restriction enzyme used to split the DNA.
	private static final String ENZYME = "gaattc";

	/**
	 * Creates and runs a benchmark.
	 */
	public DNABenchMark() {
		// Let the user pick the DNA file.
		JFileChooser dnaFileChooser = new JFileChooser(System.getProperty("user.dir"));
		if (dnaFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			try {
				// Read DNA from a file
				String dnaSource = dnaFromScanner(new Scanner(dnaFileChooser
						.getSelectedFile()));
				System.out.println("dna length = " + dnaSource.length());

				// Construct the minimum length splice string
				String spliceString = "";
				for (int i = 0; i < MINIMUM_SPLICE_STRING_LENGTH; i++) {
					spliceString = spliceString + "c";
				}
				
				// Run the experiment
				runSpliceLengthExperiment(spliceString, dnaSource);
				//runEnzymeLengthExperiment(spliceString, dnaSource);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * Runs an experiment in which the only change is that the splice string gets longer with each trial.
	 * @param spliceString the string to splice in.
	 * @param strand the DNA strand to splice into.
	 */
	private void runSpliceLengthExperiment(String spliceString, String dnaSource) {
		
		// Repeatedly splice in strings of c's in lengths from 256 up to
		// 2^32 and take benchmarks.
		for (int trial = 1; trial <= NUM_TRIALS; trial++) {
			// each time this loop run, a new strand is created
			// could it be too many strand created here?
			for (int repeats = 1; repeats <= NUM_REPEATS; repeats++) {
				// Create the strand to operate on
				DnaStrand strand;
				// strand of type StringDNAStrand
				//strand = new StringDNAStrand(dnaSource);
				// strand of type LinkedListDNAStrand
				//strand = new LinkedListDNAStrand(dnaSource);
				// strand of type SlowStringDNAStrand
				strand = new SlowStringDNAStrand(dnaSource);
				// calculate the time
				strandSpliceBenchmark(ENZYME, spliceString, strand);
			}
			System.out.println();

			// Double the length of the string
			spliceString = spliceString + spliceString;
		}
	}
	
	/**
	 * Runs an experiment in which the only change is that the enzyme string gets longer with each trial.
	 * @param enzyme the string to detect the position to splice in .
	 * @param strand the DNA strand to splice into.
	 */
	 
	private void runEnzymeLengthExperiment(String spliceString, String dnaSource) {
		// the original 
		String enzyme = ENZYME;		
		
		// Repeatedly splice in strings of c's in lengths from 256 up to
		// 2^32 and take benchmarks.
		for (int trial = 1; trial <= NUM_TRIALS; trial++) {
			// each time this loop run, a new strand is created
			// could it be too many strand created here?
			for (int repeats = 1; repeats <= NUM_REPEATS; repeats++) {
				// Create the strand to operate on
				DnaStrand strand;
				//strand = new StringDNAStrand(dnaSource);
				 strand = new LinkedListDNAStrand(dnaSource);
				// calculate the time
				strandSpliceBenchmark(enzyme, spliceString, strand);
			}
			System.out.println();

			// Double the length of the string
			enzyme = enzyme + enzyme;
		}
	}

	/**
	 * Performs a strand splice and times how long it takes.
	 * 
	 * @param enzyme
	 *            the DNA of the enzyme that splits the long strand of DNA
	 * @param splicee
	 *            the DNA to splice into the split
	 * @param strand
	 *            the DNA strand being manipulated
	 */
	private void strandSpliceBenchmark(String enzyme, String splicee, DnaStrand strand) {
		// Remember the length of the strand before we start
		long length = strand.size();

		// Start a timer
		double start = System.currentTimeMillis();

		// Do the experiment
		DnaStrand recomb = strand.cutAndSplice(enzyme, splicee);

		// Stop the timer and determine how long it took in seconds.
		double end = System.currentTimeMillis();
		double time = (end - start) / 1000.0;

		// Return information about the experiment.
		long length2 = strand.size();
		long recLength = recomb.size();
		// this is the print statement that I have been looking for
		// % will be the numbers. Sweet. 
		System.out.println(String
				.format(
						"%s:\t splicee %,d\t time %1.3f\tbefore %,d\tafter %,d\trecomb %,d\tbreaks %d",
						strand.getClass().getName(), splicee.length(), time,
						length, length2, recLength, strand.getNumBreaks()));

	}

	/**
	 * Return a string representing the DNA read from the scanner, ignoring any
	 * characters that can't be part of DNA and converting all characters to lower
	 * case.
	 * 
	 * @param s
	 *            is the Scanner read from
	 * @return a string representing the DNA read, characters in the returned
	 *         string are restricted to 'c', 'g', 't', 'a'
	 */
	private String dnaFromScanner(Scanner s) {
		assert s != null;
		
		// StringBuilder is more efficient than String when building up
		// a String value from smaller parts.
		StringBuilder buf = new StringBuilder();
		while (s.hasNextLine()) {
			String line = s.nextLine().toLowerCase();
			for (int k = 0; k < line.length(); k++) {
				char ch = line.charAt(k);

				// Only include the character if it is a valid DNA character 
				//(a,c, g or t)
				if ("acgt".indexOf(ch) != -1) {
					buf.append(ch);
				}
			}
		}

		// Returns the string that was built.
		return buf.toString();
	}
	
	/**
	 * Runs the benchmark program.
	 * @param args
	 */
	public static void main(String[] args) {
		new DNABenchMark();

	}

}
