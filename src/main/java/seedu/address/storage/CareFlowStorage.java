package seedu.address.storage;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.readonly.ReadOnlyDrugInventory;
import seedu.address.model.readonly.ReadOnlyPatientRecord;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

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
