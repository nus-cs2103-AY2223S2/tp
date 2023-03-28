package seedu.address.model.tank.readings;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;

/**
 * Wraps all data at the Ammonia list within tank level
 * Duplicates will replace existing entry (readings of same dates are duplicates)
 */
public class FullReadingLevels implements ReadOnlyReadingLevels {

    private final UniqueFullReadingLevels fullReadingLevels;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        fullReadingLevels = new UniqueFullReadingLevels();
    }

    public FullReadingLevels() {}

    /**
     * Creates an FullReadingLevels using the AmmoniaLevels in the {@code toBeCopied}
     */
    public FullReadingLevels(ReadOnlyReadingLevels toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the ammonia level list with {@code fullReadingLevels}.
     * {@code fullReadingLevels} must not contain duplicate fullReadingLevels.
     */
    public void setFullReadingLevels(List<UniqueIndividualReadingLevels> fullReadingLevels) {
        this.fullReadingLevels.setReadingLevels(fullReadingLevels);
    }

    /**
     * Resets the existing data of this {@code AmmoniaLevelLists} with {@code newData}.
     * @param newData
     */
    public void resetData(ReadOnlyReadingLevels newData) {
        requireNonNull(newData);

        setFullReadingLevels(newData.getFullReadingLevels());
    }

    //// ammoniaLevel-level operations

    /**
     * Returns true if a reading Level with the same identity as {@code UniqueIndividualReadingLevels} exists
     * in the reading level list. They are same identity if they are of the same tank.
     */
    public boolean hasIndividualReadingLevels(UniqueIndividualReadingLevels ammoniaLevelList) {
        requireNonNull(ammoniaLevelList);
        return fullReadingLevels.containsSameTankReading(ammoniaLevelList);
    }

    /**
     * Adds an individual reading level to the FullReadingLevels.
     * Readings of the same day will replace the other one on the same day
     */
    public void addIndividualReadingLevel(UniqueIndividualReadingLevels individualReadingLevels) {
        fullReadingLevels.add(individualReadingLevels);
    }

    /**
     * Replaces the given ammonia level {@code target} in the list with {@code editedReadingLevel}.
     * {@code target} must exist in the FullReadingLevels.
     */
    public void setIndividualReadingLevel(UniqueIndividualReadingLevels target,
                                          UniqueIndividualReadingLevels editedReadingLevel) {
        requireNonNull(editedReadingLevel);

        fullReadingLevels.setUniqueIndividualReadingLevels(target, editedReadingLevel);
    }

    /**
     * Removes {@code key} from this {@code FullReadingLevels}.
     * {@code key} must exist in the FullReadingLevels.
     */
    public void removeIndividualReadingLevel(UniqueIndividualReadingLevels key) {
        fullReadingLevels.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return fullReadingLevels.asUnmodifiableObservableList().size() + " individual tank readings";
    }

    @Override
    public ObservableList<UniqueIndividualReadingLevels> getFullReadingLevels() {
        return fullReadingLevels.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FullReadingLevels // instanceof handles nulls
                && fullReadingLevels.equals(((FullReadingLevels) other).fullReadingLevels));
    }

    @Override
    public int hashCode() {
        return fullReadingLevels.hashCode();
    }

    public int size() {
        return fullReadingLevels.size();
    }
}
