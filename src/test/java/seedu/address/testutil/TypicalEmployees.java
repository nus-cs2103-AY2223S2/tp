package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.ExecutiveProDb;
import seedu.address.model.employee.Employee;

/**
 * A utility class containing a list of {@code Employee} objects to be used in tests.
 */
public class TypicalEmployees {

    public static final Employee ALICE = new EmployeeBuilder().withName("Alice Pauline").withPhone("94351253")
            .withEmail("alice@example.com").withAddress("123, Jurong West Ave 6, #08-111")
            .withDepartment("Sales").withPicturePath("src/main/resources/employeepictures/default_employee.png")
            .withTags("friends").build();
    public static final Employee BENSON = new EmployeeBuilder().withName("Benson Meier").withPhone("98765432")
            .withEmail("johnd@example.com").withAddress("311, Clementi Ave 2, #02-25")
            .withDepartment("Sales").withPicturePath("src/main/resources/employeepictures/default_employee.png")
            .withTags("owesMoney", "friends").build();
    public static final Employee CARL = new EmployeeBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withDepartment("Sales").withPicturePath("src/main/resources/employeepictures/default_employee.png").build();
    public static final Employee DANIEL = new EmployeeBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withDepartment("Marketing").withPicturePath("src/main/resources/employeepictures/default_employee.png")
            .withTags("friends").build();
    public static final Employee ELLE = new EmployeeBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withDepartment("Marketing").withPicturePath("src/main/resources/employeepictures/default_employee.png").build();
    public static final Employee FIONA = new EmployeeBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withDepartment("Marketing").withPicturePath("src/main/resources/employeepictures/default_employee.png").build();
    public static final Employee GEORGE = new EmployeeBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withDepartment("Marketing").withPicturePath("src/main/resources/employeepictures/default_employee.png").build();

    // Manually added
    public static final Employee HOON = new EmployeeBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india")
            .withDepartment("Sales").withPicturePath("src/main/resources/employeepictures/default_employee.png").build();
    public static final Employee IDA = new EmployeeBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withDepartment("Marketing").withPicturePath("src/main/resources/employeepictures/default_employee.png").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Employee AMY = new EmployeeBuilder().withName(CommandTestUtil.VALID_NAME_AMY)
            .withEmployeeId(CommandTestUtil.VALID_EMPLOYEE_ID_AMY).withPhone(CommandTestUtil.VALID_PHONE_AMY)
            .withEmail(CommandTestUtil.VALID_EMAIL_AMY).withAddress(CommandTestUtil.VALID_ADDRESS_AMY)
            .withDepartment(CommandTestUtil.VALID_DEPARTMENT_AMY)
            .withPicturePath(CommandTestUtil.VALID_PICTURE_PATH_AMY)
            .withTags(CommandTestUtil.VALID_TAG_FRIEND).build();
    public static final Employee BOB = new EmployeeBuilder().withName(CommandTestUtil.VALID_NAME_BOB)
            .withEmployeeId(CommandTestUtil.VALID_EMPLOYEE_ID_BOB).withPhone(CommandTestUtil.VALID_PHONE_BOB)
            .withEmail(CommandTestUtil.VALID_EMAIL_BOB).withAddress(CommandTestUtil.VALID_ADDRESS_BOB)
            .withDepartment(CommandTestUtil.VALID_DEPARTMENT_BOB)
            .withPicturePath(CommandTestUtil.VALID_PICTURE_PATH_BOB)
            .withTags(CommandTestUtil.VALID_TAG_HUSBAND, CommandTestUtil.VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalEmployees() {} // prevents instantiation

    /**
     * Returns an {@code ExecutiveProDb} with all the typical persons.
     */
    public static ExecutiveProDb getTypicalExecutiveProDb() {
        ExecutiveProDb ab = new ExecutiveProDb();
        for (Employee employee : getTypicalEmployees()) {
            ab.addEmployee(employee);
        }
        return ab;
    }

    public static List<Employee> getTypicalEmployees() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Employee> getRemainingEmployees() {

        return new ArrayList<>(Arrays.asList(HOON, IDA));
    }
}
