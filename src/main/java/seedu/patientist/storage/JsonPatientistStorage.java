package seedu.patientist.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.patientist.commons.core.LogsCenter;
import seedu.patientist.commons.exceptions.DataConversionException;
import seedu.patientist.commons.exceptions.IllegalValueException;
import seedu.patientist.commons.util.FileUtil;
import seedu.patientist.commons.util.JsonUtil;
import seedu.patientist.model.ReadOnlyPatientist;

/**
 * A class to access Patientist data stored as a json file on the hard disk.
 */
public class JsonPatientistStorage implements PatientistStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPatientistStorage.class);

    private Path filePath;

    public JsonPatientistStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getPatientistFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyPatientist> readPatientist() throws DataConversionException {
        return readPatientist(filePath);
    }

    /**
     * Similar to {@link #readPatientist()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyPatientist> readPatientist(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializablePatientist> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializablePatientist.class);
        if (!jsonAddressBook.isPresent()) {
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
    public void savePatientist(ReadOnlyPatientist patientist) throws IOException {
        savePatientist(patientist, filePath);
    }

    /**
     * Similar to {@link #savePatientist(ReadOnlyPatientist)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void savePatientist(ReadOnlyPatientist patientist, Path filePath) throws IOException {
        requireNonNull(patientist);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePatientist(patientist), filePath);
    }

}
