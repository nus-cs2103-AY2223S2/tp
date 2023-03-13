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

}
