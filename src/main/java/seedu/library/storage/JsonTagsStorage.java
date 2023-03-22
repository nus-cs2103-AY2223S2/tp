package seedu.library.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.library.commons.core.LogsCenter;
import seedu.library.commons.exceptions.DataConversionException;
import seedu.library.commons.exceptions.IllegalValueException;
import seedu.library.commons.util.FileUtil;
import seedu.library.commons.util.JsonUtil;
import seedu.library.model.ReadOnlyTags;

/**
 * A class to access tag list data stored as a json file on the hard disk.
 */
public class JsonTagsStorage implements TagsStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonLibraryStorage.class);

    private Path filePath;

    public JsonTagsStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTagsFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTags> readTags() throws DataConversionException {
        return readTags(filePath);
    }

    /**
     * Similar to {@link #readTags()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTags> readTags(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTags> jsonTags = JsonUtil.readJsonFile(
                filePath, JsonSerializableTags.class);
        if (!jsonTags.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTags.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTags(ReadOnlyTags tags) throws IOException {
        saveTags(tags, filePath);
    }

    /**
     * Similar to {@link #saveTags(ReadOnlyTags)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTags(ReadOnlyTags tags, Path filePath) throws IOException {
        requireNonNull(tags);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTags(tags), filePath);
    }
}
