package seedu.internship.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.internship.commons.exceptions.DataConversionException;
import seedu.internship.model.ReadOnlyInternshipCatalogue;
import seedu.internship.model.ReadOnlyUserPrefs;
import seedu.internship.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends InternshipCatalogueStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getInternshipCatalogueFilePath();

    @Override
    Optional<ReadOnlyInternshipCatalogue> readInternshipCatalogue() throws DataConversionException, IOException;

    @Override
    void saveInternshipCatalogue(ReadOnlyInternshipCatalogue internshipCatalogue) throws IOException;

}
