package seedu.address.storage.volunteer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyVolunteer;

/**
 * Storage class for serializing and unserializing of the volunteer entity.
 */
public interface VolunteerStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getVolunteerFilePath();

    /**
     * Returns FriendlyLink data as a {@link ReadOnlyVolunteer}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyVolunteer> readVolunteer() throws DataConversionException, IOException;

    /**
     * @see #getVolunteerFilePath()
     */
    Optional<ReadOnlyVolunteer> readVolunteer(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyVolunteer} to the storage.
     * @param volunteer cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveVolunteer(ReadOnlyVolunteer volunteer) throws IOException;

    /**
     * @see #saveVolunteer(ReadOnlyVolunteer)
     */
    void saveVolunteer(ReadOnlyVolunteer volunteer, Path filePath) throws IOException;
}
