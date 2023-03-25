package seedu.fitbook.model.client;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Client's list of weights in FitBook.
 * Guarantees: immutable
 */
public class WeightHistory {

    public final List<Weight> weights;

    /**
     * Constructs a {@code WeightHistory} with an initial weight.
     *
     * @param initialWeight The initial weight to add to the history.
     */
    public WeightHistory(Weight initialWeight) {
        weights = new ArrayList<>();
        weights.add(initialWeight);
    }

    public Weight getLastEntry() {
        if (weights.isEmpty()) {
            //throw an exception
            return null;
        }
        return weights.get(weights.size() - 1);
    }

    /**
     * Adds a weight to the history.
     *
     * @param weight The weight to add.
     * @param date The date to associate with the weight.
     */
    public void addWeight(String date, String weight) {
        Weight dateWeight = new Weight(date, weight);
        weights.add(dateWeight);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < weights.size(); i++) {
            sb.append(weights.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WeightHistory // instanceof handles nulls
                && weights.equals(((WeightHistory) other).weights)); // state check
    }

    @Override
    public int hashCode() {
        return weights.hashCode();
    }
}
