package seedu.vms.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.vms.commons.exceptions.DataConversionException;
import seedu.vms.model.ReadOnlyUserPrefs;
import seedu.vms.model.UserPrefs;
import seedu.vms.model.patient.ReadOnlyPatientManager;
import seedu.vms.storage.vaccination.VaxTypeStorage;

/**
 * API of the Storage component
 */
public interface Storage extends PatientManagerStorage, UserPrefsStorage, VaxTypeStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getPatientManagerFilePath();

    @Override
    Optional<ReadOnlyPatientManager> readPatientManager() throws DataConversionException, IOException;

    @Override
    void savePatientManager(ReadOnlyPatientManager patientManager) throws IOException;

}
