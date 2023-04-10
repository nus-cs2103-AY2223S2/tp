package seedu.address.storage;

import javafx.beans.Observable;

/**
 * Marker interface for ReadOnlyData types to inherit from.
 */
public interface ReadOnlyData {
    Observable getData();
}
