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
    private Path archiveFilePath;

    /**
     * Constructs a {@code JsonPetPalStorage} with the given {@code data file Path} and {@code archive file Path}
     */
    public JsonPetPalStorage(Path filePath, Path archiveFilePath) {
        this.filePath = filePath;
        this.archiveFilePath = archiveFilePath;
    }

    public Path getPetPalFilePath() {
        return filePath;
    }

    @Override
    public Path getPetPalArchiveFilePath() {
        return archiveFilePath;
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
        System.out.println(filePath);
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

    @Override
    public Optional<ReadOnlyPetPal> readPetPalArchive() {
        return Optional.empty();
    }

    @Override
    public Optional<ReadOnlyPetPal> readPetPalArchive(Path filePath) throws DataConversionException {
        requireNonNull(filePath);
        System.out.println(filePath);
        Optional<JsonSerializablePetPal> jsonPetPalArchive = JsonUtil.readJsonFile(
                filePath, JsonSerializablePetPal.class);

        if (jsonPetPalArchive.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonPetPalArchive.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void savePetPalArchive(ReadOnlyPetPal petPal) throws IOException {
        savePetPal(petPal, archiveFilePath);
    }

    @Override
    public void savePetPalArchive(ReadOnlyPetPal petPal, Path filePath) throws IOException {
        requireNonNull(petPal);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePetPal(petPal), filePath);
    }
}
