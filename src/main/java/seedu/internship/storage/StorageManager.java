package seedu.internship.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.internship.commons.core.LogsCenter;
import seedu.internship.commons.exceptions.DataConversionException;
import seedu.internship.model.ReadOnlyInternshipCatalogue;
import seedu.internship.model.ReadOnlyUserPrefs;
import seedu.internship.model.UserPrefs;

/**
 * Manages storage of InternshipCatalogue data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private InternshipCatalogueStorage internshipCatalogueStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code InternshipCatalogueStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(InternshipCatalogueStorage internshipCatalogueStorage, UserPrefsStorage userPrefsStorage) {
        this.internshipCatalogueStorage = internshipCatalogueStorage;
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


    // ================ AddressBook methods ==============================

    @Override
    public Path getInternshipCatalogueFilePath() {
        return internshipCatalogueStorage.getInternshipCatalogueFilePath();
    }

    @Override
    public Optional<ReadOnlyInternshipCatalogue> readInternshipCatalogue() throws DataConversionException, IOException {
        return readInternshipCatalogue(internshipCatalogueStorage.getInternshipCatalogueFilePath());
    }

    @Override
    public Optional<ReadOnlyInternshipCatalogue> readInternshipCatalogue(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return internshipCatalogueStorage.readInternshipCatalogue(filePath);
    }

    @Override
    public void saveInternshipCatalogue(ReadOnlyInternshipCatalogue internshipCatalogue) throws IOException {
        saveInternshipCatalogue(internshipCatalogue, internshipCatalogueStorage.getInternshipCatalogueFilePath());
    }

    @Override
    public void saveInternshipCatalogue(ReadOnlyInternshipCatalogue internshipCatalogue, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        internshipCatalogueStorage.saveInternshipCatalogue(internshipCatalogue, filePath);
    }

}
