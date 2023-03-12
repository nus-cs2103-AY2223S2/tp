package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyInternshipCatalogue;
import seedu.address.model.ReadOnlyUserPrefs1;
import seedu.address.model.UserPrefs1;

/**
 * Manages storage of InternshipCatalogue data in local storage.
 */
public class StorageManager1 implements Storage1 {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private InternshipCatalogueStorage internshipCatalogueStorage;
    private UserPrefsStorage1 userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code InternshipCatalogueStorage} and {@code UserPrefStorage}.
     */
    public StorageManager1(InternshipCatalogueStorage internshipCatalogueStorage, UserPrefsStorage1 userPrefsStorage) {
        this.internshipCatalogueStorage = internshipCatalogueStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs1> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs1 userPrefs) throws IOException {
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
