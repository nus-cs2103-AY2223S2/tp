package seedu.address.model.score;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.text.DecimalFormat;
import java.util.DoubleSummaryStatistics;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.score.exceptions.DuplicateScoreException;
import seedu.address.model.score.exceptions.ScoreNotFoundException;

/**
 * A list of scores that enforces uniqueness between its elements and does not allow nulls.
 * A score is considered unique by comparing using {@code score#isSameScore(otherScore)}. As such, adding and updating
 * of scores uses Score#isSameScore(otherScore) for equality so as to ensure that the score being added or updated is
 * unique in terms of identity in the ScoreList. However, the removal of a score uses score#equals(Object) so
 * as to ensure that the score with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Score#isSameScore(Score)
 */
public class ScoreList implements Iterable<Score> {
    private final ObservableList<Score> internalList = FXCollections.observableArrayList();
    private final ObservableList<Score> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent score as the given argument.
     */
    public boolean contains(Score toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameScore);
    }

    /**
     * Adds a score to the list.
     * The score must not already exist in the list.
     */
    public void add(Score toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateScoreException();
        }
        internalList.add(toAdd);
        internalList.sort((s1, s2) -> s1.getLocalDate().compareTo(s2.getLocalDate()));
        FXCollections.reverse(internalList);
    }

    /**
     * Replaces the score {@code target} in the list with {@code editedScore}.
     * {@code target} must exist in the list.
     * The score identity of {@code editedScore} must not be the same as another existing score in the list.
     */
    public void setScore(Score target, Score editedScore) {
        requireAllNonNull(target, editedScore);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ScoreNotFoundException();
        }

        if (!target.equals(editedScore) && contains(editedScore)) {
            throw new DuplicateScoreException();
        }

        internalList.set(index, editedScore);
    }

    /**
     * Removes the equivalent score from the list.
     * The score must exist in the list.
     */
    public void remove(Score toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ScoreNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code scores}.
     * {@code scores} must not contain duplicate scores.
     */
    public void setScores(ScoreList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code scores}.
     * {@code scores} must not contain duplicate scores.
     */
    public void setScores(List<Score> scores) throws DuplicateScoreException {
        requireNonNull(scores);
        if (!scoresAreUnique(scores)) {
            throw new DuplicateScoreException();
        }
        internalList.setAll(scores);
    }

    /**
     * Gets the internal list for score list panel to get all scores for a specific student.
     * @return All tasks in list.
     */
    public ObservableList<Score> getInternalList() {
        return internalList;
    }

    /**
     * Returns the backing score list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Score> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Gets the recent 5 scores with recent score at back.
     * @return A view of list of recent 5 scores.
     */
    public ObservableList<Score> getRecentScoreList() {
        if (internalList.size() < 5) {
            ObservableList<Score> recentScoreList = FXCollections.observableArrayList(internalList.stream()
                    .limit(internalList.size()).collect(java.util.stream.Collectors.toList()));
            FXCollections.reverse(recentScoreList);
            return recentScoreList;
        }
        ObservableList<Score> recentScoreList = FXCollections.observableArrayList(internalList.stream()
                        .limit(5).collect(java.util.stream.Collectors.toList()));
        FXCollections.reverse(recentScoreList);
        return recentScoreList;
    }

    /**
     * Gets the summary statistic of recent 5 scores.
     * @return A view of list of recent 5 scores' summary statistic.
     */
    public ObservableList<ScoreSummary> getScoreSummary() {
        DecimalFormat df = new DecimalFormat("#.##");
        ObservableList<Score> recentScoreList = getRecentScoreList();
        DoubleSummaryStatistics scoreSummary = new DoubleSummaryStatistics();
        for (int i = 0; i < recentScoreList.size(); i++) {
            scoreSummary.accept(recentScoreList.get(i).getValue().value);
        }
        Double average = scoreSummary.getAverage();
        Double maxValue = scoreSummary.getMax();
        Double minValue = scoreSummary.getMin();
        Double oldValue = recentScoreList.get(0).getValue().value;
        Double newValue = recentScoreList.get(recentScoreList.size() - 1).getValue().value;
        Double percentage = newValue - oldValue == 0 ? 0 : oldValue == 0 ? (newValue - oldValue) / 1   : (newValue - oldValue) / oldValue * 100; 
        System.out.println("new value " + newValue.toString() + " old value " + oldValue.toString() + " percentage " + percentage.toString());
        average = Double.valueOf(df.format(average));
        percentage = Double.valueOf(df.format(percentage));
        ScoreSummary ss = new ScoreSummary(maxValue, minValue, average, percentage);
        ObservableList<ScoreSummary> summary = FXCollections.observableArrayList();
        summary.add(ss);
        return summary;
    }

    /**
     * Inner class
     */
    public class ScoreSummary {

        private Double maxScore;
        private Double minScore;
        private Double average;
        private Double percentage;

        /**
         * Class constructor
         */
        public ScoreSummary(Double max, Double min, Double average, Double percentage) {
            this.maxScore = max;
            this.minScore = min;
            this.average = average;
            this.percentage = percentage;
        }

        public Double getMaxScore() {
            return this.maxScore;
        }

        public Double getMinScore() {
            return this.minScore;
        }

        public Double getAverage() {
            return this.average;
        }

        public Double getPercentage() {
            return this.percentage;
        }
    }

    @Override
    public Iterator<Score> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScoreList // instanceof handles nulls
                && internalList.equals(((ScoreList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code scores} contains only unique scores.
     */
    private boolean scoresAreUnique(List<Score> scores) {
        for (int i = 0; i < scores.size() - 1; i++) {
            for (int j = i + 1; j < scores.size(); j++) {
                if (scores.get(i).isSameScore(scores.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public int size() {
        return internalList.size();
    }
    public Score get(int index) {
        return internalList.get(index);
    }

}
