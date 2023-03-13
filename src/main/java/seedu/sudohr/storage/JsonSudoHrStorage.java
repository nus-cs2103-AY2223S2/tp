package seedu.sudohr.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.sudohr.commons.core.LogsCenter;
import seedu.sudohr.commons.exceptions.DataConversionException;
import seedu.sudohr.commons.exceptions.IllegalValueException;
import seedu.sudohr.commons.util.FileUtil;
import seedu.sudohr.commons.util.JsonUtil;
import seedu.sudohr.model.ReadOnlySudoHr;

/**
 * A class to access SudoHr data stored as a json file on the hard disk.
 */
public class JsonSudoHrStorage implements SudoHrStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonSudoHrStorage.class);

    private Path filePath;

    public JsonSudoHrStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getSudoHrFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlySudoHr> readSudoHr() throws DataConversionException {
        return readSudoHr(filePath);
    }

    /**
     * Similar to {@link #readSudoHr()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlySudoHr> readSudoHr(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableSudoHr> jsonSudoHr = JsonUtil.readJsonFile(
                filePath, JsonSerializableSudoHr.class);
        if (!jsonSudoHr.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonSudoHr.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveSudoHr(ReadOnlySudoHr sudoHr) throws IOException {
        saveSudoHr(sudoHr, filePath);
    }

    /**
     * Similar to {@link #saveSudoHr(ReadOnlySudoHr)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveSudoHr(ReadOnlySudoHr sudoHr, Path filePath) throws IOException {
        requireNonNull(sudoHr);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableSudoHr(sudoHr), filePath);
    }

}
