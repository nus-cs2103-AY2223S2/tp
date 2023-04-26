package taa.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

/**
 * Contains the statistics of a list of integers.
 */
public class IntStatistics {
    private final double mean;
    private final double variance;

    /**
     * Initialises the statistics of a given list of Integers.
     */
    public IntStatistics(List<Integer> numbers) {
        requireNonNull(numbers);
        long sum = calcSum(numbers);
        this.mean = (double) sum / numbers.size();
        this.variance = calcVariance(numbers, this.mean);
    }

    /**
     * Returns the mean of the list of Integers passed in during construction.
     */
    public double getMean() {
        return this.mean;
    }

    /**
     * Returns the variance of the list of Integers passed in during construction.
     */
    public double getVariance() {
        return this.variance;
    }

    /**
     * Returns the standard deviation of the list of Integers passed in during construction.
     */
    public double getStdDev() {
        return Math.sqrt(this.variance);
    }
    private long calcSum(List<Integer> numbers) {
        long result = 0;
        for (int number : numbers) {
            result += number;
        }
        return result;
    }

    private double calcVariance(List<Integer> numbers, double mean) {
        double result = 0;

        for (int number : numbers) {
            result += Math.pow(number - mean, 2);
        }

        return result;
    }
}
