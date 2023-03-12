package seedu.address.storage;

import java.nio.file.Path;

/**
 * Abstract DataStorage class for other data storage classes to inherit from. Currently, not in use as the individual
 * data storages have too different behavior to abstract out.
 */
public abstract class DataStorage {

    protected Path filePath;

    /**
     * Returns the file path of the data file.
     */
    public Path getFilePath() {
        return this.filePath;
    }
}
