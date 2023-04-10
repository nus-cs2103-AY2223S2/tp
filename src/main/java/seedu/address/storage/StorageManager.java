package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyPetPal;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of PetPal data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private PetPalStorage petPalStorage;
    private UserPrefsStorage userPrefsStorage;
    private PetPalStorage petPalArchiveStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code PetPalStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(PetPalStorage petPalStorage, UserPrefsStorage userPrefsStorage) {
        this.petPalStorage = petPalStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ PetPal methods ==============================

    @Override
    public Path getPetPalFilePath() {
        return petPalStorage.getPetPalFilePath();
    }

    @Override
    public Optional<ReadOnlyPetPal> readPetPal() throws DataConversionException, IOException {
        return readPetPal(petPalStorage.getPetPalFilePath());
    }

    @Override
    public Optional<ReadOnlyPetPal> readPetPal(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return petPalStorage.readPetPal(filePath);
    }

    @Override
    public void savePetPal(ReadOnlyPetPal petPal) throws IOException {
        savePetPal(petPal, petPalStorage.getPetPalFilePath());
    }

    @Override
    public void savePetPal(ReadOnlyPetPal petPal, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        petPalStorage.savePetPal(petPal, filePath);
    }

    // ================================= Archive Methods ============================================================

    @Override
    public Path getPetPalArchiveFilePath() {
        return petPalStorage.getPetPalArchiveFilePath();
    }
    @Override
    public Optional<ReadOnlyPetPal> readPetPalArchive() throws DataConversionException, IOException {
        return readPetPalArchive(petPalStorage.getPetPalArchiveFilePath());
    }
    @Override
    public Optional<ReadOnlyPetPal> readPetPalArchive(Path filePath) throws IOException, DataConversionException {
        logger.fine("Attempting to read archive data from file: " + filePath);
        return petPalStorage.readPetPalArchive(filePath);
    }

    @Override
    public void savePetPalArchive(ReadOnlyPetPal petPal) throws IOException {
        savePetPalArchive(petPal, petPalStorage.getPetPalArchiveFilePath());
    }

    @Override
    public void savePetPalArchive(ReadOnlyPetPal petPal, Path filePath) throws IOException {
        logger.fine("Attempting to write to archive data file: " + filePath);
        petPalStorage.savePetPalArchive(petPal, filePath);
    }
}
