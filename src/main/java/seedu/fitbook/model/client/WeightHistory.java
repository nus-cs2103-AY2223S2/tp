package seedu.fitbook.model.client;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a Client's list of weights in FitBook.
 * Guarantees: immutable
 */
public class WeightHistory {

    public final List<Pair<String, String>> weights;

    /**
     * Constructs a {@code WeightHistory} with an initial weight.
     *
     * @param initialWeight The initial weight to add to the history.
     */
    public WeightHistory(Pair<String, String> initialWeight) {
        weights = new ArrayList<>();
        weights.add(initialWeight);
    }

    /**
     * Returns an unmodifiable list of weights in the history.
     */
    public List<Pair<String, String>> getWeights() {
        return Collections.unmodifiableList(weights);
    }

    public Pair<String, String> getLastEntry() {
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
        Pair<String, String> weightPair = new Pair<>(date, weight);
        weights.add(weightPair);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < weights.size(); i++) {
            sb.append(weights.get(i).getValue()).append(":").append(weights.get(i).getKey()).append("\n");
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
