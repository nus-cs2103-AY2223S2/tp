package seedu.medinfo.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.medinfo.commons.core.LogsCenter;
import seedu.medinfo.commons.exceptions.DataConversionException;
import seedu.medinfo.commons.exceptions.IllegalValueException;
import seedu.medinfo.commons.util.FileUtil;
import seedu.medinfo.commons.util.JsonUtil;
import seedu.medinfo.logic.commands.Command;
import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.model.ReadOnlyMedInfo;

/**
 * A class to access MedInfo data stored as a json file on the hard disk.
 */
public class JsonMedInfoStorage implements MedInfoStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonMedInfoStorage.class);

    private Path filePath;

    public JsonMedInfoStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getMedInfoFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyMedInfo> readMedInfo() throws DataConversionException, CommandException {
        return readMedInfo(filePath);
    }

    /**
     * Similar to {@link #readMedInfo()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyMedInfo> readMedInfo(Path filePath) throws DataConversionException, CommandException {
        requireNonNull(filePath);

        Optional<JsonSerializableMedInfo> jsonMedInfo = JsonUtil.readJsonFile(
                filePath, JsonSerializableMedInfo.class);
        if (!jsonMedInfo.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonMedInfo.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveMedInfo(ReadOnlyMedInfo medInfo) throws IOException {
        saveMedInfo(medInfo, filePath);
    }

    /**
     * Similar to {@link #saveMedInfo(ReadOnlyMedInfo)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveMedInfo(ReadOnlyMedInfo medInfo, Path filePath) throws IOException {
        requireNonNull(medInfo);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableMedInfo(medInfo), filePath);
    }

}
