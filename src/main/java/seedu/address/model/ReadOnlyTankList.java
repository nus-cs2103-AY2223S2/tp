package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.tank.Tank;

/**
 * Unmodifiable view of a Tank List
 */
public interface ReadOnlyTankList {
    /**
            * Returns an unmodifiable view of the tanks list.
            * This list will not contain any duplicate tanks.
            */
    ObservableList<Tank> getTankList();
}
