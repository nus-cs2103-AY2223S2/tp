package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;



/**
 * Abstract DataStorage class for other data storage classes to inherit from. Currently, not in use as the individual
 * data storages have too different behavior to abstract out.
 */
public abstract class DataStorage<T extends ReadOnlyData> {

    protected Path filePath;


    /**
     * Returns the file path of the data file.
     */
    public Path getFilePath() {
        return this.filePath;
    }

    public Optional<T> readData() throws DataConversionException, IOException {
        return this.readData(this.filePath);
    }

    public abstract Optional<T> readData(Path filePath) throws DataConversionException;

    public void saveData(T data) throws IOException {
        this.saveData(data, filePath);
    }

    public abstract void saveData(T data, Path filePath) throws IOException;

}
