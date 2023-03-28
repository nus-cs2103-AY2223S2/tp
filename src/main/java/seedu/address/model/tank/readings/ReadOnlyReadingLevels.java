package seedu.address.model.tank.readings;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a list of ammonia readings
 */
public interface ReadOnlyReadingLevels {
    /**
     * Returns an unmodifiable view of the Fishes list.
     * This list will not contain any duplicate Fishes.
     */
    ObservableList<UniqueIndividualReadingLevels> getAmmoniaLevelLists();
}
