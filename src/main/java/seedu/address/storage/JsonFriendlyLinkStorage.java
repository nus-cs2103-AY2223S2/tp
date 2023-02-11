package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyFriendlyLink;

/**
 * A class to access FriendlyLink data stored as a json file on the hard disk.
 */
public class JsonFriendlyLinkStorage implements FriendlyLinkStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFriendlyLinkStorage.class);

    private final Path filePath;

    public JsonFriendlyLinkStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFriendlyLinkFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyFriendlyLink> readFriendlyLink() throws DataConversionException {
        return readFriendlyLink(filePath);
    }

    /**
     * Similar to {@link #readFriendlyLink()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyFriendlyLink> readFriendlyLink(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFriendlyLink> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableFriendlyLink.class);
        if (jsonAddressBook.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFriendlyLink(ReadOnlyFriendlyLink friendlyLink) throws IOException {
        saveFriendlyLink(friendlyLink, filePath);
    }

    /**
     * Similar to {@link #saveFriendlyLink(ReadOnlyFriendlyLink)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFriendlyLink(ReadOnlyFriendlyLink friendlyLink, Path filePath) throws IOException {
        requireNonNull(friendlyLink);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFriendlyLink(friendlyLink), filePath);
    }

}
