package seedu.address.storage.pair;

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
import seedu.address.model.ReadOnlyPair;
import seedu.address.storage.JsonAppStorage;

/**
 * A class to access pair data stored as a json file on the hard disk.
 */
public class JsonPairStorage extends JsonAppStorage<ReadOnlyPair, FriendlyLink,
        JsonSerializablePair> implements PairStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPairStorage.class);

    /**
     * Creates a {@code JsonPairStorage} to access pair file data.
     *
     * @param filePath Pair data file path.
     */
    public JsonPairStorage(Path filePath) {
        super(filePath);
    }

    @Override
    public Path getPairFilePath() {
        return super.getFilePath();
    }

    @Override
    public Optional<ReadOnlyPair> readPair(FriendlyLink friendlyLink) throws DataConversionException {
        return super.read(JsonSerializablePair.class, logger, friendlyLink);
    }

    @Override
    public Optional<ReadOnlyPair> readPair(Path filePath, FriendlyLink friendlyLink)
            throws DataConversionException {
        return super.read(filePath, JsonSerializablePair.class, logger, friendlyLink);
    }

    @Override
    public void savePair(ReadOnlyPair entity) throws IOException {
        savePair(entity, super.getFilePath());
    }

    @Override
    public void savePair(ReadOnlyPair entity, Path filePath) throws IOException {
        requireNonNull(entity);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePair(entity), filePath);
    }
}
