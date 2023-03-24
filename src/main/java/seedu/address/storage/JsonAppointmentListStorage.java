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
import seedu.address.model.ReadOnlyAppointmentList;

/**
 * A class to access appointment list data stored as a json file on the hard disk.
 */
public class JsonAppointmentListStorage implements AppointmentListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAppointmentListStorage.class);

    private Path filePath;

    public JsonAppointmentListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAppointmentListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAppointmentList> readAppointmentList() throws DataConversionException {
        return readAppointmentList(filePath);
    }

    /**
     * Similar to {@link #readAppointmentList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyAppointmentList> readAppointmentList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAppointmentList> jsonAppointmentList = JsonUtil.readJsonFile(
            filePath, JsonSerializableAppointmentList.class);
        if (!jsonAppointmentList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAppointmentList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAppointmentList(ReadOnlyAppointmentList addressBook) throws IOException {
        saveAppointmentList(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveAppointmentList(ReadOnlyAppointmentList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAppointmentList(ReadOnlyAppointmentList appointmentList, Path filePath) throws IOException {
        requireNonNull(appointmentList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAppointmentList(appointmentList), filePath);
    }
}
