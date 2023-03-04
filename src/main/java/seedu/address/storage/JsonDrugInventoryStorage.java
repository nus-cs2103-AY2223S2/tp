package seedu.address.storage;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.readonly.ReadOnlyDrugInventory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * A class to access drug data stored as a json file on the hard disk.
 */
import static java.util.Objects.requireNonNull;

public class JsonDrugInventoryStorage implements DrugInventoryStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonDrugInventoryStorage.class);

    private Path filePath;

    public JsonDrugInventoryStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getDrugInventoryFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyDrugInventory> readDrugInventory() throws DataConversionException {
        return readDrugInventory(filePath);
    }

    /**
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyDrugInventory> readDrugInventory(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableDrugInventory> jsonDrugInventory = JsonUtil.readJsonFile(
                filePath, JsonSerializableDrugInventory.class);
        if (jsonDrugInventory.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonDrugInventory.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveDrugInventory(ReadOnlyDrugInventory drugInventory) throws IOException {
        saveDrugInventory(drugInventory, filePath);
    }

    /**
     * Similar to {@link #saveDrugInventory(ReadOnlyDrugInventory)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveDrugInventory(ReadOnlyDrugInventory drugInventory, Path filePath) throws IOException {
        requireNonNull(drugInventory);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableDrugInventory(drugInventory), filePath);
    }

}
