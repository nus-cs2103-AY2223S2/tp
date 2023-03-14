package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyPetPal;

/**
 * A class to access PetPal data stored as a json file on the hard disk.
 */
public class JsonPetPalStorage implements PetPalStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPetPalStorage.class);

    private Path filePath;

    public JsonPetPalStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getPetPalFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyPetPal> readPetPal() throws DataConversionException {
        return readPetPal(filePath);
    }

    /**
     * Similar to {@link #readPetPal()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyPetPal> readPetPal(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializablePetPal> jsonPetPal = JsonUtil.readJsonFile(
                filePath, JsonSerializablePetPal.class);
        if (!jsonPetPal.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonPetPal.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void savePetPal(ReadOnlyPetPal petPal) throws IOException {
        savePetPal(petPal, filePath);
    }

    /**
     * Similar to {@link #savePetPal(ReadOnlyPetPal)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void savePetPal(ReadOnlyPetPal petPal, Path filePath) throws IOException {
        requireNonNull(petPal);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePetPal(petPal), filePath);
    }

}
