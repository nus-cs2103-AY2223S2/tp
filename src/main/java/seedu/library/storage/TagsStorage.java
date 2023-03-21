package seedu.library.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.library.commons.exceptions.DataConversionException;
import seedu.library.model.ReadOnlyTags;
import seedu.library.model.Tags;

/**
 * Represents a storage for {@link Tags}.
 */
public interface TagsStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getTagsFilePath();

    /**
     * Returns Tags data as a {@link ReadOnlyTags}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTags> readTags() throws DataConversionException, IOException;

    /**
     * @see #getTagsFilePath()
     */
    Optional<ReadOnlyTags> readTags(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTags} to the storage.
     * @param tags cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTags(ReadOnlyTags tags) throws IOException;

    /**
     * @see #saveTags(ReadOnlyTags)
     */
    void saveTags(ReadOnlyTags tags, Path filePath) throws IOException;
}
