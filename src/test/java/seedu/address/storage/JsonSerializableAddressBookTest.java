package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalDoctors.getTypicalAddressBook;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src",
            "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_DOCTORS_FILE =
            TEST_DATA_FOLDER.resolve("typicalDoctorsAddressBook.json");
    private static final Path INVALID_DOCTOR_FILE =
            TEST_DATA_FOLDER.resolve("invalidDoctorAddressBook.json");
    private static final Path DUPLICATE_DOCTOR_FILE =
            TEST_DATA_FOLDER.resolve("duplicateDoctorAddressBook.json");

    private static final Path INVALID_PATIENT_FILE =
            TEST_DATA_FOLDER.resolve("invalidPatientAddressBook.json");
    private static final Path DUPLICATE_UNASSIGNED_PATIENT_FILE =
            TEST_DATA_FOLDER.resolve("duplicateUnassignedPatientAddressBook.json");
    private static final Path DUPLICATE_ASSIGNED_PATIENT_FILE =
            TEST_DATA_FOLDER.resolve("duplicateAssignedPatientAddressBook.json");
    private static final Path PATIENT_ASSIGNED_AND_UNASSIGNED_FILE =
            TEST_DATA_FOLDER.resolve("patientAssignedAndUnassignedAddressBook.json");

    @Test
    public void toModelType_typicalDoctorsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_DOCTORS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalDoctorsAddressBook = getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalDoctorsAddressBook);
    }

    @Test
    public void toModelType_invalidDoctorFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_DOCTOR_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateDoctors_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_DOCTOR_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType,
                JsonSerializableAddressBook.MESSAGE_DUPLICATE_DOCTOR);
    }

    @Test
    public void toModelType_invalidPatientFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_PATIENT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateAssignedPatients_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(
                DUPLICATE_ASSIGNED_PATIENT_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalDoctorsAddressBook = getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalDoctorsAddressBook);
    }

    @Test
    public void toModelType_duplicateUnassignedPatients_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(
                DUPLICATE_UNASSIGNED_PATIENT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType,
                JsonSerializableAddressBook.MESSAGE_DUPLICATE_PATIENT);
    }

    @Test
    public void toModelType_patientAssignedAndUnassigned_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(
                PATIENT_ASSIGNED_AND_UNASSIGNED_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

}
