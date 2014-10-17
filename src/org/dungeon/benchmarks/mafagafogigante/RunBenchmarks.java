package org.dungeon.benchmarks.mafagafogigante;

/**
 * Driver class for the benchmarks package.
 * 
 * @author Bernardo
 */
public class RunBenchmarks {
    
    public static void main(String[] args) {
        InsertBeforeLinesBenchmark iblb = new InsertBeforeLinesBenchmark((int) Math.pow(10, 7));
        iblb.run();
    }
    
}
