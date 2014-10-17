package org.dungeon.benchmarks.mafagafogigante;

/**
 * Compares two different methods of inserting a string before another.
 * 
 * By default, these methods take two strings of input and insert the smaller one before the larger one.
 * The lengths do not need to be verified, as one of the parameters would take a smaller string that the other.
 * Instead of having the strings as parameters, it just accesses two static final String field declared in this class.
 * 
 * Results:
 *   Method A took 1733.0276 ms.    // Pass the longer string to the StringBuilder then insert the shorter one at 0.
 *   Method B took 1263.6386 ms.    // Pass the shorter string to the StringBuilder then append the longer one.
 * 
 * @author Bernardo Sulzbach
 * @date 17/10/2014
 */
public class InsertBeforeLinesBenchmark extends Benchmark {
    
    // Two string variables used by the methods in this benchmark.
    protected static final String TEXT = "This is a reasonably long string.";
    protected static final String PREFIX = "mafagafo@mint $ ";

    public InsertBeforeLinesBenchmark(int runs) {
        super(runs);
    }

    public InsertBeforeLinesBenchmark(String message, int runs) {
        super(message, runs);
    }
    
    @Override
    protected void methodA() {
        if (TEXT.isEmpty()) {
            throw new IllegalArgumentException("text should be a non-empty String.");
        }
        if (PREFIX.isEmpty()) {
            throw new IllegalArgumentException("word should be a non-empty String.");
        }
        StringBuilder builder = new StringBuilder(TEXT);
        builder.insert(0, PREFIX);
        int index = builder.indexOf("\n");
        while (index != -1) {
            builder.insert(index + 1, PREFIX);
            index = builder.indexOf("\n", index + PREFIX.length() + 1);
        }
        builder.toString();
    }

    @Override
    protected void methodB() {
        if (TEXT.isEmpty()) {
            throw new IllegalArgumentException("text should be a non-empty String.");
        }
        if (PREFIX.isEmpty()) {
            throw new IllegalArgumentException("word should be a non-empty String.");
        }
        StringBuilder builder = new StringBuilder(PREFIX);
        builder.append(TEXT);
        int index = builder.indexOf("\n");
        while (index != -1) {
            builder.insert(index + 1, PREFIX);
            index = builder.indexOf("\n", index + PREFIX.length() + 1);
        }
        builder.toString();
    }
    
}
