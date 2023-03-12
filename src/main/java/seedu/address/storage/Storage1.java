package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyInternshipCatalogue;
import seedu.address.model.ReadOnlyUserPrefs1;
import seedu.address.model.UserPrefs1;

/**
 * API of the Storage component
 */
public interface Storage1 extends InternshipCatalogueStorage, UserPrefsStorage1 {

    @Override
    Optional<UserPrefs1> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs1 userPrefs) throws IOException;

    @Override
    Path getInternshipCatalogueFilePath();

    @Override
    Optional<ReadOnlyInternshipCatalogue> readInternshipCatalogue() throws DataConversionException, IOException;

    @Override
    void saveInternshipCatalogue(ReadOnlyInternshipCatalogue internshipCatalogue) throws IOException;

}
