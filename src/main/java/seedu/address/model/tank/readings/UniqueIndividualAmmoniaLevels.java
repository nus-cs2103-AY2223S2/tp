package seedu.address.model.tank.readings;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.readings.exceptions.DuplicateReadingException;
import seedu.address.model.tank.readings.exceptions.ReadingNotFoundException;

/**
 * A list of Ammonia Readings of one tank
 *
 * Supports a minimal set of list operations.
 */
public class UniqueIndividualAmmoniaLevels implements Iterable<AmmoniaLevel> {
    public final ObservableList<AmmoniaLevel> internalList = FXCollections.observableArrayList();

    private final ObservableList<AmmoniaLevel> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    private Tank tank;

    /**
     * Constructor for an ammonia level list for a single tank
     * @param t Tank this list is associated to
     */
    public UniqueIndividualAmmoniaLevels(Tank t) {
        this.tank = t;
    }

    /**
     * Returns true if the list contains an equivalent reading as the given argument.
     */
    public boolean containsSameDayReading(AmmoniaLevel toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a reading to the list.
     * If a reading of the same day
     */
    public void add(AmmoniaLevel toAdd) {
        requireNonNull(toAdd);
        if (containsSameDayReading(toAdd)) {
            internalList.remove(toAdd);
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces a {@code AmmoniaLevel} in the list with {@code editedAmmoniaLevel}.
     * {@code target} must exist in the list.
     */
    public void setAmmoniaLevel(AmmoniaLevel target, AmmoniaLevel editedAmmoniaLevel) {
        requireAllNonNull(target, editedAmmoniaLevel);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ReadingNotFoundException();
        }

        internalList.set(index, editedAmmoniaLevel);
    }

    /**
     * Removes the equivalent reading from the list.
     * The reading must exist in the list.
     */
    public void remove(AmmoniaLevel toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ReadingNotFoundException();
        }
    }

    public void setAmmoniaLevels(UniqueIndividualAmmoniaLevels replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code ammoniaLevels}.
     * {@code ammoniaLevels} must not contain duplicate ammoniaLevels.
     */
    public void setAmmoniaLevels(List<AmmoniaLevel> ammoniaLevels) {
        requireAllNonNull(ammoniaLevels);
        if (!ammoniaLevelsAreUnique(ammoniaLevels)) {
            throw new DuplicateReadingException();
        }

        internalList.setAll(ammoniaLevels);
    }

    /**
     * Returns true if {@code Readings} contains only unique Readings.
     */
    private boolean ammoniaLevelsAreUnique(List<AmmoniaLevel> ammoniaLevels) {
        for (int i = 0; i < ammoniaLevels.size() - 1; i++) {
            for (int j = i + 1; j < ammoniaLevels.size(); j++) {
                if (ammoniaLevels.get(i).equals(ammoniaLevels.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<AmmoniaLevel> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Setter for tank
     * @param t Tank to be set to
     */
    public void setTank(Tank t) {
        this.tank = t;
    }

    /**
     * Getter for tank
     * @return tank of this list
     */
    public Tank getTank() {
        return this.tank;
    }

    @Override
    public Iterator<AmmoniaLevel> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueIndividualAmmoniaLevels // instanceof handles nulls
                && tank.equals(((UniqueIndividualAmmoniaLevels) other).tank));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    public int size() {
        return internalList.size();
    }
}
