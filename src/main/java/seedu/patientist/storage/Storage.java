package seedu.patientist.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.patientist.commons.exceptions.DataConversionException;
import seedu.patientist.model.ReadOnlyPatientist;
import seedu.patientist.model.ReadOnlyUserPrefs;
import seedu.patientist.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends PatientistStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getPatientistFilePath();

    @Override
    Optional<ReadOnlyPatientist> readPatientist() throws DataConversionException, IOException;

    @Override
    void savePatientist(ReadOnlyPatientist patientist) throws IOException;

}
