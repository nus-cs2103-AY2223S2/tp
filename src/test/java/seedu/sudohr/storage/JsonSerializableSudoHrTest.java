package seedu.sudohr.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.sudohr.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.sudohr.commons.exceptions.IllegalValueException;
import seedu.sudohr.commons.util.JsonUtil;
import seedu.sudohr.model.SudoHr;
import seedu.sudohr.testutil.TypicalDepartments;
import seedu.sudohr.testutil.TypicalPersons;

public class JsonSerializableSudoHrTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableSudoHrTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsSudoHr.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonSudoHr.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonSudoHr.json");
    private static final Path DUPLICATE_ID_FILE = TEST_DATA_FOLDER.resolve("duplicateIdSudoHr.json");
    private static final Path DUPLICATE_EMAIL_FILE = TEST_DATA_FOLDER.resolve("duplicateEmailSudoHr.json");
    private static final Path DUPLICATE_PHONE_FILE = TEST_DATA_FOLDER.resolve("duplicatePhoneSudoHr.json");
    private static final Path DUPLICATE_PHONE_AND_EMAIL_FILE = TEST_DATA_FOLDER.resolve("duplicatePhoneEmailSudoHr.json");
    private static final Path TYPICAL_DEPARTMENTS_FILE = TEST_DATA_FOLDER.resolve("typicalDepartmentsSudoHr.json");
    private static final Path INVALID_DEPARTMENT_FILE = TEST_DATA_FOLDER.resolve("invalidDepartmentSudoHr.json");
    private static final Path DUPLICATE_DEPARTMENT_FILE = TEST_DATA_FOLDER.resolve("duplicateDepartmentSudoHr.json");

    //// employee-level tests

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableSudoHr.class).get();
        SudoHr sudoHrFromFile = dataFromFile.toModelType();
        SudoHr typicalPersonsSudoHr = TypicalPersons.getTypicalSudoHr();
        assertEquals(sudoHrFromFile, typicalPersonsSudoHr);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableSudoHr.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableSudoHr.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSudoHr.MESSAGE_DUPLICATE_EMPLOYEE,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateIds_throwsIllegalValueException() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ID_FILE,
                JsonSerializableSudoHr.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSudoHr.MESSAGE_DUPLICATE_EMPLOYEE,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEmails_throwsIllegalValueException() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EMAIL_FILE,
                JsonSerializableSudoHr.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSudoHr.MESSAGE_DUPLICATE_EMAIL,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePhoneNumbers_throwsIllegalValueException() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PHONE_FILE,
                JsonSerializableSudoHr.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSudoHr.MESSAGE_DUPLICATE_PHONE,
                dataFromFile::toModelType);
    }

    // note the checks for duplicate phone comes before email
    @Test
    public void toModelType_duplicatePhonesAndEmails_throwsIllegalValueException() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PHONE_AND_EMAIL_FILE,
                JsonSerializableSudoHr.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSudoHr.MESSAGE_DUPLICATE_PHONE,
                dataFromFile::toModelType);
    }

    //// department-level tests
    @Test
    public void toModelType_typicalDepartmentsFile_success() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(TYPICAL_DEPARTMENTS_FILE,
                JsonSerializableSudoHr.class).get();
        SudoHr sudoHrFromFile = dataFromFile.toModelType();
        SudoHr typicalDepartmentsSudoHr = TypicalDepartments.getTypicalSudoHr();
        assertEquals(sudoHrFromFile, typicalDepartmentsSudoHr);
    }

    @Test
    public void toModelType_invalidDepartmentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(INVALID_DEPARTMENT_FILE,
                JsonSerializableSudoHr.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateDepartments_throwsIllegalValueException() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(DUPLICATE_DEPARTMENT_FILE,
                JsonSerializableSudoHr.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSudoHr.MESSAGE_DUPLICATE_DEPARTMENTS,
                dataFromFile::toModelType);
    }

}
