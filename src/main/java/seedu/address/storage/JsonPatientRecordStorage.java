package seedu.address.storage;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.readonly.ReadOnlyPatientRecord;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;
/**
 * A class to access patient data stored as a json file on the hard disk.
 */
public class JsonPatientRecordStorage implements PatientRecordStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonPatientRecordStorage.class);

    private Path filePath;

    public JsonPatientRecordStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getPatientRecordFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyPatientRecord> readPatientRecord() throws DataConversionException, IOException {
        return readPatientRecord(filePath);
    }

    /**
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlyPatientRecord> readPatientRecord(Path filePath) throws DataConversionException, IOException {
        requireNonNull(filePath);
        Optional<JsonSerializablePatientRecord> jsonPatientRecord = JsonUtil.readJsonFile(
                filePath, JsonSerializablePatientRecord.class);
        if (!jsonPatientRecord.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonPatientRecord.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void savePatientRecord(ReadOnlyPatientRecord patientRecord) throws IOException {
        savePatientRecord(patientRecord, filePath);
    }

    @Override
    public void savePatientRecord(ReadOnlyPatientRecord patientRecord, Path filePath) throws IOException {

        requireNonNull(patientRecord);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePatientRecord(patientRecord), filePath);
    }
}
