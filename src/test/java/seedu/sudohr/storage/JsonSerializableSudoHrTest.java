package seedu.sudohr.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.sudohr.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.sudohr.commons.exceptions.IllegalValueException;
import seedu.sudohr.commons.util.JsonUtil;
import seedu.sudohr.model.SudoHr;
import seedu.sudohr.model.employee.exceptions.DuplicateEmailException;
import seedu.sudohr.model.employee.exceptions.DuplicateEmployeeException;
import seedu.sudohr.model.employee.exceptions.DuplicatePhoneNumberException;
import seedu.sudohr.model.employee.exceptions.EmployeeNotFoundException;
import seedu.sudohr.testutil.TypicalDepartments;
import seedu.sudohr.testutil.TypicalEmployees;
import seedu.sudohr.testutil.TypicalLeave;

public class JsonSerializableSudoHrTest {

    private static final Path TEST_DATA_FOLDER = Paths
            .get("src", "test", "data", "JsonSerializableSudoHrTest");
    private static final Path TYPICAL_EMPLOYEES_FILE = TEST_DATA_FOLDER.resolve("typicalEmployeesSudoHr.json");
    private static final Path INVALID_EMPLOYEE_FILE = TEST_DATA_FOLDER.resolve("invalidEmployeeSudoHr.json");
    private static final Path DUPLICATE_EMPLOYEE_FILE = TEST_DATA_FOLDER.resolve("duplicateEmployeeSudoHr.json");
    private static final Path DUPLICATE_ID_FILE = TEST_DATA_FOLDER.resolve("duplicateIdSudoHr.json");
    private static final Path DUPLICATE_EMAIL_FILE = TEST_DATA_FOLDER.resolve("duplicateEmailSudoHr.json");
    private static final Path DUPLICATE_PHONE_FILE = TEST_DATA_FOLDER.resolve("duplicatePhoneSudoHr.json");
    private static final Path DUPLICATE_PHONE_AND_EMAIL_FILE = TEST_DATA_FOLDER.resolve(
            "duplicatePhoneEmailSudoHr.json");

    // department paths
    private static final Path TYPICAL_DEPARTMENTS_FILE = TEST_DATA_FOLDER.resolve("typicalDepartmentsSudoHr.json");
    private static final Path INVALID_DEPARTMENT_FILE = TEST_DATA_FOLDER.resolve("invalidDepartmentSudoHr.json");
    private static final Path DUPLICATE_DEPARTMENT_FILE = TEST_DATA_FOLDER.resolve("duplicateDepartmentSudoHr.json");
    private static final Path DUPLICATE_EMPLOYEE_IN_DEPARTMENT_FILE = TEST_DATA_FOLDER
            .resolve("duplicateEmployeeInDepartmentSudoHr.json");
    private static final Path CLASHING_EMPLOYEE_ID_IN_DEPARTMENT_FILE = TEST_DATA_FOLDER
            .resolve("clashingEmployeeIdInDepartmentSudoHr.json");
    private static final Path CLASHING_EMPLOYEE_PHONE_IN_DEPARTMENT_FILE = TEST_DATA_FOLDER
            .resolve("clashingEmployeePhoneInDepartmentSudoHr.json");
    private static final Path CLASHING_EMPLOYEE_EMAIL_IN_DEPARTMENT_FILE = TEST_DATA_FOLDER
            .resolve("clashingEmployeeEmailInDepartmentSudoHr.json");
    private static final Path NON_EXISTENT_EMPLOYEE_IN_DEPARTMENT_FILE = TEST_DATA_FOLDER
            .resolve("nonExistentEmployeeInDepartmentSudoHr.json");
    private static final Path INCONSISTENT_EMPLOYEE_IN_DEPARTMENT_FILE = TEST_DATA_FOLDER
            .resolve("inconsistentEmployeeInDepartmentSudoHr.json");

    // leave paths
    private static final Path TYPICAL_LEAVE_FILE = TEST_DATA_FOLDER.resolve("typicalLeavesSudoHr.json");
    private static final Path INVALID_LEAVE_FILE = TEST_DATA_FOLDER.resolve("invalidLeaveSudoHr.json");
    private static final Path DUPLICATE_LEAVE_FILE = TEST_DATA_FOLDER.resolve("duplicateLeaveSudoHr.json");
    private static final Path DUPLICATE_EMPLOYEE_IN_LEAVE_FILE = TEST_DATA_FOLDER
            .resolve("duplicateEmployeeInLeaveSudoHr.json");
    private static final Path CLASHING_EMPLOYEE_ID_IN_LEAVE_FILE = TEST_DATA_FOLDER
            .resolve("clashingEmployeeIdInLeaveSudoHr.json");
    private static final Path CLASHING_EMPLOYEE_PHONE_IN_LEAVE_FILE = TEST_DATA_FOLDER
            .resolve("clashingEmployeePhoneInLeaveSudoHr.json");
    private static final Path CLASHING_EMPLOYEE_EMAIL_IN_LEAVE_FILE = TEST_DATA_FOLDER
            .resolve("clashingEmployeeEmailInLeaveSudoHr.json");
    private static final Path NON_EXISTENT_EMPLOYEE_IN_LEAVE_FILE = TEST_DATA_FOLDER
            .resolve("nonExistentEmployeeInLeaveSudoHr.json");

    //// employee-level tests

    @Test
    public void toModelType_typicalEmployeesFile_success() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(TYPICAL_EMPLOYEES_FILE,
                JsonSerializableSudoHr.class).get();
        SudoHr sudoHrFromFile = dataFromFile.toModelType();
        SudoHr typicalEmployeesSudoHr = TypicalEmployees.getTypicalSudoHr();
        assertEquals(sudoHrFromFile, typicalEmployeesSudoHr);
    }

    @Test
    public void toModelType_invalidEmployeeFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(INVALID_EMPLOYEE_FILE,
                JsonSerializableSudoHr.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEmployees_throwsIllegalValueException() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EMPLOYEE_FILE,
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

    @Test
    public void toModelType_nonExistentEmployeeInDepartment_throwsEmployeeNotFoundException() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(NON_EXISTENT_EMPLOYEE_IN_DEPARTMENT_FILE,
                JsonSerializableSudoHr.class).get();
        assertThrows(EmployeeNotFoundException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_inconsistentEmployeeInDepartment_throwsEmployeeNotFoundException() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(INCONSISTENT_EMPLOYEE_IN_DEPARTMENT_FILE,
                JsonSerializableSudoHr.class).get();
        assertThrows(EmployeeNotFoundException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEmployeeInDepartment_throwsDuplicateEmployeeException() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EMPLOYEE_IN_DEPARTMENT_FILE,
                JsonSerializableSudoHr.class).get();
        assertThrows(DuplicateEmployeeException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_clashingEmployeeIdInDepartment_throwsDuplicateEmployeeException() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(CLASHING_EMPLOYEE_ID_IN_DEPARTMENT_FILE,
                JsonSerializableSudoHr.class).get();
        assertThrows(DuplicateEmployeeException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_clashingEmployeePhoneInDepartment_throwsDuplicatePhoneNumberException() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(CLASHING_EMPLOYEE_PHONE_IN_DEPARTMENT_FILE,
                JsonSerializableSudoHr.class).get();
        assertThrows(DuplicatePhoneNumberException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_clashingEmployeeEmailInDepartment_throwsDuplicateEmailException() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(CLASHING_EMPLOYEE_EMAIL_IN_DEPARTMENT_FILE,
                JsonSerializableSudoHr.class).get();
        assertThrows(DuplicateEmailException.class, dataFromFile::toModelType);
    }

    //// leave-level tests
    @Test
    public void toModelType_typicalLeaveFile_success() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(TYPICAL_LEAVE_FILE,
                JsonSerializableSudoHr.class).get();
        SudoHr sudoHrFromFile = dataFromFile.toModelType();
        SudoHr typicalLeavesSudoHr = TypicalLeave.getTypicalSudoHr();
        assertEquals(sudoHrFromFile, typicalLeavesSudoHr);
    }

    @Test
    public void toModelType_invalidLeaveFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(INVALID_LEAVE_FILE,
                JsonSerializableSudoHr.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateLeave_throwsIllegalValueException() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(DUPLICATE_LEAVE_FILE,
                JsonSerializableSudoHr.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSudoHr.MESSAGE_DUPLICATE_LEAVES,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_nonExistentEmployeeInLeave_throwsEmployeeNotFoundException() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(NON_EXISTENT_EMPLOYEE_IN_LEAVE_FILE,
                JsonSerializableSudoHr.class).get();
        assertThrows(EmployeeNotFoundException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEmployeeInLeave_throwsDuplicateEmployeeException() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EMPLOYEE_IN_LEAVE_FILE,
                JsonSerializableSudoHr.class).get();
        assertThrows(DuplicateEmployeeException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_clashingEmployeeIdInLeave_throwsDuplicateEmployeeException() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(CLASHING_EMPLOYEE_ID_IN_LEAVE_FILE,
                JsonSerializableSudoHr.class).get();
        assertThrows(DuplicateEmployeeException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_clashingEmployeePhoneInLeave_throwsDuplicatePhoneNumberException() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(CLASHING_EMPLOYEE_PHONE_IN_LEAVE_FILE,
                JsonSerializableSudoHr.class).get();
        assertThrows(DuplicatePhoneNumberException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_clashingEmployeeEmailInLeave_throwsDuplicateEmailException() throws Exception {
        JsonSerializableSudoHr dataFromFile = JsonUtil.readJsonFile(CLASHING_EMPLOYEE_EMAIL_IN_LEAVE_FILE,
                JsonSerializableSudoHr.class).get();
        assertThrows(DuplicateEmailException.class, dataFromFile::toModelType);
    }
}
