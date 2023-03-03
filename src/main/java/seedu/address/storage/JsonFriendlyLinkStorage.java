package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.FriendlyLink;
import seedu.address.model.ReadOnlyFriendlyLink;

/**
 * A class to access FriendlyLink data stored as a json file on the hard disk.
 */
public class JsonFriendlyLinkStorage extends JsonAppStorage<ReadOnlyFriendlyLink, FriendlyLink,
        JsonSerializableFriendlyLink> implements FriendlyLinkStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFriendlyLinkStorage.class);

    public JsonFriendlyLinkStorage(Path filePath) {
        super(filePath);
    }

    public Path getFriendlyLinkFilePath() {
        return super.getFilePath();
    }

    public Optional<ReadOnlyFriendlyLink> readFriendlyLink() throws DataConversionException {
        return super.read(JsonSerializableFriendlyLink.class, logger);
    }

    /**
     * Similar to {@link #readFriendlyLink()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyFriendlyLink> readFriendlyLink(Path filePath) throws DataConversionException {
        return super.read(filePath, JsonSerializableFriendlyLink.class, logger);
    }

    @Override
    public void saveFriendlyLink(ReadOnlyFriendlyLink entity) throws IOException {
        saveFriendlyLink(entity, super.getFilePath());
    }

    @Override
    public void saveFriendlyLink(ReadOnlyFriendlyLink entity, Path filePath) throws IOException {
        requireNonNull(entity);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFriendlyLink(entity), filePath);
    }
}
