package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedEmployee.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEmployees.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.employee.Address;
import seedu.address.model.employee.Department;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.EmployeeId;
import seedu.address.model.employee.LeaveCounter;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.Payroll;
import seedu.address.model.employee.Phone;
import seedu.address.model.employee.PicturePath;

public class JsonAdaptedEmployeeTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_EMPLOYEE_ID = "abc";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_DEPARTMENT = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_PICTURE_PATH = "src/main/resources/doesntexist.jpg";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_EMPLOYEE_ID = BENSON.getEmployeeId().toString();
    private static final String VALID_DEPARTMENT = BENSON.getDepartment().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final JsonAdaptedPayroll VALID_PAYROLL = new JsonAdaptedPayroll(BENSON.getPayroll().toString());
    private static final JsonAdaptedLeaveCounter VALID_LEAVE_COUNT =
            new JsonAdaptedLeaveCounter(BENSON.getLeaveCounter().toString());
    private static final String VALID_DATE_OF_BIRTH = BENSON.getDateOfBirth().toString();
    private static final String VALID_DATE_OF_JOINING = BENSON.getDateOfJoining().toString();


    private static final String VALID_PICTURE_PATH = BENSON.getPicturePath().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final JsonAdaptedPayroll INVALID_PAYROLL = new JsonAdaptedPayroll("-1 10");
    private static final JsonAdaptedLeaveCounter INVALID_LEAVE_COUNT = new JsonAdaptedLeaveCounter("-3");

    @Test
    public void toModelType_validEmployeeDetails_returnsEmployee() throws Exception {
        JsonAdaptedEmployee person = new JsonAdaptedEmployee(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedEmployee person =
                new JsonAdaptedEmployee(INVALID_NAME, VALID_EMPLOYEE_ID, VALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_DEPARTMENT, VALID_PAYROLL, VALID_LEAVE_COUNT,
                        VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_PICTURE_PATH, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedEmployee person = new JsonAdaptedEmployee(null, VALID_EMPLOYEE_ID,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DEPARTMENT, VALID_PAYROLL, VALID_LEAVE_COUNT,
                VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_PICTURE_PATH, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmployeeID_throwsIllegalValueException() {
        JsonAdaptedEmployee person =
                new JsonAdaptedEmployee(VALID_NAME, INVALID_EMPLOYEE_ID, VALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_DEPARTMENT, VALID_PAYROLL, VALID_LEAVE_COUNT,
                        VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_PICTURE_PATH, VALID_TAGS);
        String expectedMessage = EmployeeId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmployeeId_throwsIllegalValueException() {
        JsonAdaptedEmployee person = new JsonAdaptedEmployee(VALID_NAME, null,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DEPARTMENT, VALID_PAYROLL, VALID_LEAVE_COUNT,
                VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_PICTURE_PATH, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EmployeeId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedEmployee person =
                new JsonAdaptedEmployee(VALID_NAME, VALID_EMPLOYEE_ID, INVALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_DEPARTMENT, VALID_PAYROLL, VALID_LEAVE_COUNT,
                        VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_PICTURE_PATH, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedEmployee person = new JsonAdaptedEmployee(VALID_NAME, VALID_EMPLOYEE_ID, null,
                VALID_EMAIL, VALID_ADDRESS, VALID_DEPARTMENT, VALID_PAYROLL, VALID_LEAVE_COUNT,
                VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_PICTURE_PATH, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedEmployee person =
                new JsonAdaptedEmployee(VALID_NAME, VALID_EMPLOYEE_ID, VALID_PHONE, INVALID_EMAIL,
                        VALID_ADDRESS, VALID_DEPARTMENT, VALID_PAYROLL, VALID_LEAVE_COUNT,
                        VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_PICTURE_PATH, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedEmployee person = new JsonAdaptedEmployee(VALID_NAME, VALID_EMPLOYEE_ID,
                VALID_PHONE, null, VALID_ADDRESS, VALID_DEPARTMENT, VALID_PAYROLL, VALID_LEAVE_COUNT,
                VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_PICTURE_PATH, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedEmployee person =
                new JsonAdaptedEmployee(VALID_NAME, VALID_EMPLOYEE_ID, VALID_PHONE, VALID_EMAIL,
                        INVALID_ADDRESS, VALID_DEPARTMENT, VALID_PAYROLL, VALID_LEAVE_COUNT,
                        VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_PICTURE_PATH, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedEmployee person = new JsonAdaptedEmployee(VALID_NAME, VALID_EMPLOYEE_ID,
                VALID_PHONE, VALID_EMAIL, null, VALID_DEPARTMENT, VALID_PAYROLL, VALID_LEAVE_COUNT,
                VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_PICTURE_PATH, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDepartment_throwsIllegalValueException() {
        JsonAdaptedEmployee person =
                new JsonAdaptedEmployee(VALID_NAME, VALID_EMPLOYEE_ID, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, INVALID_DEPARTMENT, VALID_PAYROLL, VALID_LEAVE_COUNT,
                        VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_PICTURE_PATH, VALID_TAGS);
        String expectedMessage = Department.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDepartment_throwsIllegalValueException() {
        JsonAdaptedEmployee person = new JsonAdaptedEmployee(VALID_NAME, VALID_EMPLOYEE_ID,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, null, VALID_PAYROLL, VALID_LEAVE_COUNT,
                VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_PICTURE_PATH, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Department.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPayroll_throwsIllegalValueException() {
        JsonAdaptedEmployee person =
                new JsonAdaptedEmployee(VALID_NAME, VALID_EMPLOYEE_ID, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_DEPARTMENT, INVALID_PAYROLL, VALID_LEAVE_COUNT,
                        VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_PICTURE_PATH, VALID_TAGS);
        String expectedMessage = Payroll.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPayroll_throwsIllegalValueException() {
        JsonAdaptedEmployee person = new JsonAdaptedEmployee(VALID_NAME, VALID_EMPLOYEE_ID,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DEPARTMENT, null, VALID_LEAVE_COUNT,
                VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_PICTURE_PATH, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Payroll.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidLeaveCounter_throwsIllegalValueException() {
        JsonAdaptedEmployee person =
                new JsonAdaptedEmployee(VALID_NAME, VALID_EMPLOYEE_ID, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_DEPARTMENT, VALID_PAYROLL, INVALID_LEAVE_COUNT,
                        VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_PICTURE_PATH, VALID_TAGS);
        String expectedMessage = LeaveCounter.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullLeaveCounter_throwsIllegalValueException() {
        JsonAdaptedEmployee person = new JsonAdaptedEmployee(VALID_NAME, VALID_EMPLOYEE_ID,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DEPARTMENT, VALID_PAYROLL, null,
                VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_PICTURE_PATH, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LeaveCounter.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPicturePath_throwsIllegalValueException() {
        JsonAdaptedEmployee person =
                new JsonAdaptedEmployee(VALID_NAME, VALID_EMPLOYEE_ID, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_DEPARTMENT, VALID_PAYROLL, VALID_LEAVE_COUNT,
                        VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, INVALID_PICTURE_PATH, VALID_TAGS);
        String expectedMessage = PicturePath.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPicturePath_throwsIllegalValueException() {
        JsonAdaptedEmployee person = new JsonAdaptedEmployee(VALID_NAME, VALID_EMPLOYEE_ID,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DEPARTMENT, VALID_PAYROLL, VALID_LEAVE_COUNT,
                VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PicturePath.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedEmployee person =
                new JsonAdaptedEmployee(VALID_NAME, VALID_EMPLOYEE_ID, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_DEPARTMENT, VALID_PAYROLL, VALID_LEAVE_COUNT,
                        VALID_DATE_OF_BIRTH, VALID_DATE_OF_JOINING, VALID_PICTURE_PATH, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }



}
