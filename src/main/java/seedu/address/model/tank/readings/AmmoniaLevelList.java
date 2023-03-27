package seedu.address.model.tank.readings;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;

/**
 * Wraps all data at the Ammonia list within tank level
 * Duplicates will replace existing entry (readings of same dates are duplicates)
 */
public class AmmoniaLevelList implements ReadOnlyAmmoniaLevelList {

    private final UniqueAmmoniaLevelList ammoniaLevels;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        ammoniaLevels = new UniqueAmmoniaLevelList();
    }

    public AmmoniaLevelList() {}

    /**
     * Creates an AmmoniaLevelList using the AmmoniaLevels in the {@code toBeCopied}
     */
    public AmmoniaLevelList(ReadOnlyAmmoniaLevelList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the ammonia level list with {@code ammoniaLevels}.
     * {@code ammoniaLevels} must not contain duplicate ammoniaLevels.
     */
    public void setAmmoniaLevels(List<AmmoniaLevel> ammoniaLevels) {
        this.ammoniaLevels.setAmmoniaLevels(ammoniaLevels);
    }

    /**
     * Resets the existing data of this {@code AmmoniaLevelList} with {@code newData}.
     * @param newData
     */
    public void resetData(ReadOnlyAmmoniaLevelList newData) {
        requireNonNull(newData);

        setAmmoniaLevels(newData.getAmmoniaLevelList());
    }

    //// ammoniaLevel-level operations

    /**
     * Returns true if a ammoniaLevel with the same identity as {@code ammoniaLevel} exists in the ammonia level list.
     */
    public boolean hasAmmoniaLevel(AmmoniaLevel ammoniaLevel) {
        requireNonNull(ammoniaLevel);
        return ammoniaLevels.containsSameDayReading(ammoniaLevel);
    }

    /**
     * Adds an ammonia level to the AmmoniaLevelList.
     * Readings of the same day will replace the other one on the same day
     */
    public void addAmmoniaLevel(AmmoniaLevel ammoniaLevel) {
        ammoniaLevels.add(ammoniaLevel);
    }

    /**
     * Replaces the given ammonia level {@code target} in the list with {@code editedAmmoniaLevel}.
     * {@code target} must exist in the AmmoniaLevelList.
     */
    public void setAmmoniaLevel(AmmoniaLevel target, AmmoniaLevel editedAmmoniaLevel) {
        requireNonNull(editedAmmoniaLevel);

        ammoniaLevels.setAmmoniaLevel(target, editedAmmoniaLevel);
    }

    /**
     * Removes {@code key} from this {@code AmmoniaLevelList}.
     * {@code key} must exist in the AmmoniaLevelList.
     */
    public void removeAmmoniaLevel(AmmoniaLevel key) {
        ammoniaLevels.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return ammoniaLevels.asUnmodifiableObservableList().size() + " ammonia level readings";
        // TODO: refine later
    }

    @Override
    public ObservableList<AmmoniaLevel> getAmmoniaLevelList() {
        return ammoniaLevels.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AmmoniaLevelList // instanceof handles nulls
                && ammoniaLevels.equals(((AmmoniaLevelList) other).ammoniaLevels));
    }

    @Override
    public int hashCode() {
        return ammoniaLevels.hashCode();
    }

    public int size() {
        return ammoniaLevels.size();
    }
}
