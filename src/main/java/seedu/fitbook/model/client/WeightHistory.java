package seedu.fitbook.model.client;

import java.time.LocalDateTime;
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
     */
    public WeightHistory(Weight initialWeight) {
        weights = new ArrayList<>();
        weights.add(initialWeight);
    }

    /**
     * Constructs a {@code WeightHistory} with a copy of a list of weights.
     */
    public WeightHistory(List<Weight> weightHistory) {
        weights = weightHistory;
    }

    /**
     * Returns the last entry of {@code weight}.
     */
    public Weight getLastEntry() {
        int lastEntry = weights.size() - 1;
        if (weights.isEmpty()) {
            //throw an exception
            return null;
        }
        return weights.get(lastEntry);
    }

    /**
     * Returns a {@code Dates} with a copy of
     */
    public List<Date> getListDates() {
        List<Date> dates = new ArrayList<>();
        weights.forEach(weight -> dates.add(weight.date));
        return dates;
    }

    /**
     * Adds a weight to the history.
     *
     * @param weight The weight to add.
     * @param date The date to associate with the weight.
     */
    public void addWeight(Date date, String weight) {
        Weight dateWeight = new Weight(date, weight);
        weights.add(dateWeight);
    }

    /**
     * Sorts the weights in ascending order of date and time.
     */
    public WeightHistory sortByDate() {
        List<Weight> sortWeights = new ArrayList<>(weights);
        sortWeights.sort((w1, w2) -> {
            LocalDateTime d1 = w1.getDateTime();
            LocalDateTime d2 = w2.getDateTime();
            return d1.compareTo(d2);
        });
        return new WeightHistory(sortWeights);
    }

    /**
     * Remove the weights dated one month ago.
     */
    public void removeOldWeights() {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        List<Weight> weightsToRemove = new ArrayList<>();
        for (Weight weight : weights) {
            LocalDateTime weightDate = weight.getDateTime();
            if (weightDate.isBefore(oneMonthAgo)) {
                weightsToRemove.add(weight);
            }
        }
        weights.removeAll(weightsToRemove);
    }

    /**
     * Refines the {@code weight} of the client.
     */
    public WeightHistory refineGraphWeightHistory() {
        sortByDate();
        removeOldWeights();
        return this;
    }

    public List<Weight> getList() {
        return weights;
    }

    public int getListSize() {
        return weights.size();
    }

    public String getWeightValue(int index) {
        return weights.get(index).value;
    }

    public Date getDateValue(int index) {
        return weights.get(index).date;
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
