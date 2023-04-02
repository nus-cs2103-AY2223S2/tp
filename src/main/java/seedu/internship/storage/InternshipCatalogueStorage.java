package seedu.internship.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.internship.commons.exceptions.DataConversionException;
import seedu.internship.model.ReadOnlyInternshipCatalogue;

/**
 * Represents a storage for {@link seedu.internship.model.InternshipCatalogue}.
 */
public interface InternshipCatalogueStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getInternshipCatalogueFilePath();

    /**
     * Returns InternshipCatalogue data as a {@link ReadOnlyInternshipCatalogue}.
     * Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyInternshipCatalogue> readInternshipCatalogue() throws DataConversionException, IOException;

    /**
     * @see #getInternshipCatalogueFilePath()
     */
    Optional<ReadOnlyInternshipCatalogue> readInternshipCatalogue(Path filePath) throws DataConversionException,
            IOException;

    /**
     * Saves the given {@link ReadOnlyInternshipCatalogue} to the storage.
     * @param internshipCatalogue cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveInternshipCatalogue(ReadOnlyInternshipCatalogue internshipCatalogue) throws IOException;

    /**
     * @see #saveInternshipCatalogue(ReadOnlyInternshipCatalogue)
     */
    void saveInternshipCatalogue(ReadOnlyInternshipCatalogue internshipCatalogue, Path filePath) throws IOException;

}
