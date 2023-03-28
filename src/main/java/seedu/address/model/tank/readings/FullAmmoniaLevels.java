package seedu.address.model.tank.readings;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;

/**
 * Wraps all data at the Ammonia list within tank level
 * Duplicates will replace existing entry (readings of same dates are duplicates)
 */
public class FullAmmoniaLevels implements ReadOnlyAmmoniaLevels {

    private final UniqueFullAmmoniaLevels ammoniaLevelLists;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        ammoniaLevelLists = new UniqueFullAmmoniaLevels();
    }

    public FullAmmoniaLevels() {}

    /**
     * Creates an FullAmmoniaLevels using the AmmoniaLevels in the {@code toBeCopied}
     */
    public FullAmmoniaLevels(ReadOnlyAmmoniaLevels toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the ammonia level list with {@code ammoniaLevelLists}.
     * {@code ammoniaLevelLists} must not contain duplicate ammoniaLevelLists.
     */
    public void setAmmoniaLevelLists(List<UniqueIndividualAmmoniaLevels> ammoniaLevelLists) {
        this.ammoniaLevelLists.setAmmoniaLevelLists(ammoniaLevelLists);
    }

    /**
     * Resets the existing data of this {@code AmmoniaLevelLists} with {@code newData}.
     * @param newData
     */
    public void resetData(ReadOnlyAmmoniaLevels newData) {
        requireNonNull(newData);

        setAmmoniaLevelLists(newData.getAmmoniaLevelLists());
    }

    //// ammoniaLevel-level operations

    /**
     * Returns true if a ammoniaLevel with the same identity as {@code UniqueIndividualAmmoniaLevels} exists
     * in the ammonia level list.
     */
    public boolean hasAmmoniaLevelList(UniqueIndividualAmmoniaLevels ammoniaLevelList) {
        requireNonNull(ammoniaLevelList);
        return ammoniaLevelLists.containsSameDayReading(ammoniaLevelList);
    }

    /**
     * Adds an ammonia level to the FullAmmoniaLevels.
     * Readings of the same day will replace the other one on the same day
     */
    public void addAmmoniaLevelList(UniqueIndividualAmmoniaLevels ammoniaLevelList) {
        ammoniaLevelLists.add(ammoniaLevelList);
    }

    /**
     * Replaces the given ammonia level {@code target} in the list with {@code editedAmmoniaLevel}.
     * {@code target} must exist in the FullAmmoniaLevels.
     */
    public void setAmmoniaLevelList(UniqueIndividualAmmoniaLevels target,
                                    UniqueIndividualAmmoniaLevels editedAmmoniaLevel) {
        requireNonNull(editedAmmoniaLevel);

        ammoniaLevelLists.setUniqueIndividualAmmoniaLevelList(target, editedAmmoniaLevel);
    }

    /**
     * Removes {@code key} from this {@code FullAmmoniaLevels}.
     * {@code key} must exist in the FullAmmoniaLevels.
     */
    public void removeAmmoniaLevelList(UniqueIndividualAmmoniaLevels key) {
        ammoniaLevelLists.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return ammoniaLevelLists.asUnmodifiableObservableList().size() + " individual tank ammonia level readings";
        // TODO: refine later
    }

    @Override
    public ObservableList<UniqueIndividualAmmoniaLevels> getAmmoniaLevelLists() {
        return ammoniaLevelLists.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FullAmmoniaLevels // instanceof handles nulls
                && ammoniaLevelLists.equals(((FullAmmoniaLevels) other).ammoniaLevelLists));
    }

    @Override
    public int hashCode() {
        return ammoniaLevelLists.hashCode();
    }

    public int size() {
        return ammoniaLevelLists.size();
    }
}
