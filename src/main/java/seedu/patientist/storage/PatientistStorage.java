package seedu.patientist.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.patientist.commons.exceptions.DataConversionException;
import seedu.patientist.model.Patientist;
import seedu.patientist.model.ReadOnlyPatientist;

/**
 * Represents a storage for {@link Patientist}.
 */
public interface PatientistStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getPatientistFilePath();

    /**
     * Returns Patientist data as a {@link ReadOnlyPatientist}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyPatientist> readPatientist() throws DataConversionException, IOException;

    /**
     * @see #getPatientistFilePath()
     */
    Optional<ReadOnlyPatientist> readPatientist(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyPatientist} to the storage.
     * @param patientist cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePatientist(ReadOnlyPatientist patientist) throws IOException;

    /**
     * @see #savePatientist(ReadOnlyPatientist)
     */
    void savePatientist(ReadOnlyPatientist patientist, Path filePath) throws IOException;

}
