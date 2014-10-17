package org.dungeon.benchmarks.mafagafogigante;

/**
 * Generic benchmark abstract class that declares two abstract methods and a third concrete method that compares the
 * other methods.
 *
 * @author Bernardo Sulzbach
 */
public abstract class Benchmark implements Runnable {

    private final int runs;

    public Benchmark(int runs) {
        if (runs < 0) {
            throw new IllegalArgumentException("runs must be a nonnegative integer.");
        }
        this.runs = runs;
    }

    public Benchmark(String message, int runs) {
        this(runs);
        System.out.println(message);
    }

    /**
     * Returns the time passed since a System.nanoTime() call.
     *
     * @param start a value returned by System.nanoTime();
     * @return a double representing the number of milliseconds.
     */
    protected static double evaluateTimePassed(double start) {
        return (System.nanoTime() - start) / Math.pow(10, 6);
    }

    protected abstract void methodA();

    protected abstract void methodB();

    @Override
    public void run() {
        double startingTime;

        System.out.println("Benchmarking using " + runs + " runs.");

        startingTime = System.nanoTime();
        for (int i = 0; i < runs; i++) {
            methodA();
        }
        System.out.println("Method A took " + evaluateTimePassed(startingTime) + " ms.");

        startingTime = System.nanoTime();
        for (int i = 0; i < runs; i++) {
            methodB();
        }
        System.out.println("Method B took " + evaluateTimePassed(startingTime) + " ms.");
    }

}
