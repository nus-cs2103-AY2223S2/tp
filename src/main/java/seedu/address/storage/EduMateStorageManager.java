package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.EduMateHistory;
import seedu.address.model.ReadOnlyEduMate;
import seedu.address.model.ReadOnlyEduMateHistory;

/**
 * A class to access EduMate data stored as a json file on the hard disk.
 */
public class EduMateStorageManager implements EduMateStorage {

    private static final Logger logger = LogsCenter.getLogger(EduMateStorageManager.class);

    private Path eduMateFilePath;

    private Path eduMateHistoryFilePath;

    /**
     * Constructor for the EduMateStorageManager.
     * @param eduMateFilePath location of data.
     * @param eduMateHistoryFilePath location of command history data.
     */
    public EduMateStorageManager(Path eduMateFilePath, Path eduMateHistoryFilePath) {
        this.eduMateFilePath = eduMateFilePath;
        this.eduMateHistoryFilePath = eduMateHistoryFilePath;
    }

    public Path getEduMateFilePath() {
        return eduMateFilePath;
    }

    @Override
    public Path getEduMateHistoryFilePath() {
        return eduMateHistoryFilePath;
    }

    @Override
    public Optional<ReadOnlyEduMate> readEduMate() throws DataConversionException {
        return readEduMate(eduMateFilePath);
    }

    /**
     * Similar to {@link #readEduMate()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyEduMate> readEduMate(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableEduMate> jsonEduMate = JsonUtil.readJsonFile(
                filePath, JsonSerializableEduMate.class);
        if (!jsonEduMate.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonEduMate.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public Optional<ReadOnlyEduMateHistory> readEduMateHistory() throws IOException {
        requireNonNull(eduMateHistoryFilePath);
        ArrayList<String> history = StringUtil.readStringFile(eduMateHistoryFilePath);
        EduMateHistory eduMateHistory = new EduMateHistory();
        eduMateHistory.setList(history);
        return Optional.of(eduMateHistory);
    }

    @Override
    public void saveEduMate(ReadOnlyEduMate eduMate) throws IOException {
        FileUtil.createIfMissing(eduMateFilePath);
        saveEduMate(eduMate, eduMateFilePath);
    }


    /**
     * Similar to {@link #saveEduMate(ReadOnlyEduMate)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveEduMate(ReadOnlyEduMate eduMate, Path filePath) throws IOException {
        requireNonNull(eduMate);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableEduMate(eduMate), filePath);
    }

    @Override
    public void saveEduMateHistory(String input) throws IOException {
        FileUtil.createIfMissing(eduMateHistoryFilePath);
        StringUtil.saveStringFile(input, eduMateHistoryFilePath);
    }

}
