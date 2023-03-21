package seedu.vms.storage.patient;

import java.io.IOException;

import seedu.vms.commons.exceptions.DataConversionException;
import seedu.vms.model.patient.ReadOnlyPatientManager;

/**
 * Represents a storage for {@link seedu.vms.model.patient.PatientManager}.
 */
public interface PatientManagerStorage {
    /**
     * Returns PatientManager data as a {@link ReadOnlyPatientManager}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    ReadOnlyPatientManager readPatientManager() throws IOException;

    /**
     * Saves the given {@link ReadOnlyPatientManager} to the storage.
     * @param patientManager cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePatientManager(ReadOnlyPatientManager patientManager) throws IOException;
}
