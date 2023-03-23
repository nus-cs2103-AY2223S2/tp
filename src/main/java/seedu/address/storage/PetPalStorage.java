package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyPetPal;

/**
 * Represents a storage for {@link seedu.address.model.PetPal}.
 */
public interface PetPalStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getPetPalFilePath();

    /**
     * @return the file path for the archive file.
     */
    Path getPetPalArchiveFilePath();

    /**
     * Returns PetPal data as a {@link ReadOnlyPetPal}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyPetPal> readPetPal() throws DataConversionException, IOException;

    /**
     * @see #getPetPalFilePath()
     */
    Optional<ReadOnlyPetPal> readPetPal(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyPetPal} to the storage.
     * @param petPal cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePetPal(ReadOnlyPetPal petPal) throws IOException;

    /**
     * @see #savePetPal(ReadOnlyPetPal)
     */
    void savePetPal(ReadOnlyPetPal petPal, Path filePath) throws IOException;

    Optional<ReadOnlyPetPal> readPetPalArchive() throws DataConversionException, IOException;

    Optional<ReadOnlyPetPal> readPetPalArchive(Path filePath) throws DataConversionException, IOException;

    void savePetPalArchive(ReadOnlyPetPal petPal) throws IOException;

    void savePetPalArchive(ReadOnlyPetPal petPal, Path filePath) throws IOException;

}
