package seedu.careflow.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.careflow.commons.exceptions.DataConversionException;
import seedu.careflow.model.readonly.ReadOnlyDrugInventory;


/**
 * Represents a storage for drug inventory
 */
public interface DrugInventoryStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getDrugInventoryFilePath();

    /**
     * Returns drug inventory data as a {@link ReadOnlyDrugInventory}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyDrugInventory> readDrugInventory() throws DataConversionException, IOException;

    /**
     * @see #getDrugInventoryFilePath()
     */
    Optional<ReadOnlyDrugInventory> readDrugInventory(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyDrugInventory} to the storage.
     * @param drugInventory cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveDrugInventory(ReadOnlyDrugInventory drugInventory) throws IOException;

    /**
     * @see #saveDrugInventory(ReadOnlyDrugInventory)
     */
    void saveDrugInventory(ReadOnlyDrugInventory drugInventory, Path filePath) throws IOException;

}
