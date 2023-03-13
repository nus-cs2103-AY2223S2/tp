package seedu.careflow.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.careflow.commons.exceptions.DataConversionException;
import seedu.careflow.model.ReadOnlyUserPrefs;
import seedu.careflow.model.UserPrefs;
import seedu.careflow.model.readonly.ReadOnlyDrugInventory;
import seedu.careflow.model.readonly.ReadOnlyPatientRecord;

/**
 * Represents a storage for {@link seedu.careflow.model.CareFlow}.
 */
public interface CareFlowStorage extends PatientRecordStorage, DrugInventoryStorage, UserPrefsStorage {
    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getPatientRecordFilePath();

    @Override
    Path getDrugInventoryFilePath();

    @Override
    Optional<ReadOnlyDrugInventory> readDrugInventory() throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyPatientRecord> readPatientRecord() throws DataConversionException, IOException;

    @Override
    void savePatientRecord(ReadOnlyPatientRecord patientRecord) throws IOException;

    @Override
    void saveDrugInventory(ReadOnlyDrugInventory drugInventory) throws IOException;

}
