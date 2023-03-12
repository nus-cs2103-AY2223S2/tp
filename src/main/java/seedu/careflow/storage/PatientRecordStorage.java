package seedu.careflow.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.careflow.commons.exceptions.DataConversionException;
import seedu.careflow.model.readonly.ReadOnlyPatientRecord;

/**
 * Represents a storage for patient record
 */
public interface PatientRecordStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getPatientRecordFilePath();

    /**
     * Returns patient record data as a {@link ReadOnlyPatientRecord}.
     * Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyPatientRecord> readPatientRecord() throws DataConversionException, IOException;

    /**
     * @see #getPatientRecordFilePath()
     */
    Optional<ReadOnlyPatientRecord> readPatientRecord(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyPatientRecord} to the storage.
     * @param patientRecord cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePatientRecord(ReadOnlyPatientRecord patientRecord) throws IOException;

    /**
     * @see #savePatientRecord(ReadOnlyPatientRecord)
     */
    void savePatientRecord(ReadOnlyPatientRecord patientRecord, Path filePath) throws IOException;


}
