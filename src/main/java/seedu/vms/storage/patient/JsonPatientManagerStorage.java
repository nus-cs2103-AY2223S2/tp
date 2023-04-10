package seedu.vms.storage.patient;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.commons.util.FileUtil;
import seedu.vms.commons.util.JsonUtil;
import seedu.vms.model.patient.ReadOnlyPatientManager;

/**
 * A class to access PatientManager data stored as a json file on the hard disk.
 */
public class JsonPatientManagerStorage implements PatientManagerStorage {
    private static final Path USER_PATIENT_FILE_PATH = Path.of("data", "patientmanager.json");

    private final Path filePath;

    public JsonPatientManagerStorage() {
        this(USER_PATIENT_FILE_PATH);
    }

    public JsonPatientManagerStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public ReadOnlyPatientManager readPatientManager() throws IOException {
        try {
            return JsonUtil
                    .deserializeFromFile(filePath, JsonSerializablePatientManager.class)
                    .toModelType();
        } catch (IllegalValueException ive) {
            throw new IOException("Illegal values present", ive);
        }
    }

    @Override
    public void savePatientManager(ReadOnlyPatientManager patientManager) throws IOException {
        requireNonNull(patientManager);

        FileUtil.createIfMissing(filePath);
        JsonUtil.serializeToFile(filePath, new JsonSerializablePatientManager(patientManager));
    }
}
