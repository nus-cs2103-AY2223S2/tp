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
import seedu.address.model.ReadOnlyMathutoring;

/**
 * A class to access Mathutoring data stored as a json file on the hard disk.
 */
public class JsonMathutoringStorage implements MathutoringStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonMathutoringStorage.class);

    private Path filePath;

    public JsonMathutoringStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getMathutoringFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyMathutoring> readMathutoring() throws DataConversionException {
        return readMathutoring(filePath);
    }

    /**
     * Similar to {@link #readMathutoring()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyMathutoring> readMathutoring(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableMathutoring> jsonMathutoring = JsonUtil.readJsonFile(
                filePath, JsonSerializableMathutoring.class);
        if (!jsonMathutoring.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonMathutoring.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveMathutoring(ReadOnlyMathutoring mathutoring) throws IOException {
        saveMathutoring(mathutoring, filePath);
    }

    /**
     * Similar to {@link #saveMathutoring(ReadOnlyMathutoring)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveMathutoring(ReadOnlyMathutoring mathutoring, Path filePath) throws IOException {
        requireNonNull(mathutoring);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableMathutoring(mathutoring), filePath);
    }

}
