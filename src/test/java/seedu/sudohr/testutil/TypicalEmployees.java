package seedu.sudohr.testutil;

import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.sudohr.model.SudoHr;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.Id;

/**
 * A utility class containing a list of {@code Employee} objects to be used in tests.
 */
public class TypicalEmployees {

    public static final Id ID_NOT_EXIST = new Id("999");
    public static final Id ALICE_ID = new Id("101");
    public static final Id BENSON_ID = new Id("102");
    public static final Id ALICE_ID_COPY = new Id("101");

    public static final Employee ALICE = new EmployeeBuilder().withId("101")
            .withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Employee BENSON = new EmployeeBuilder().withId("102")
            .withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Employee CARL = new EmployeeBuilder().withId("103")
            .withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Employee DANIEL = new EmployeeBuilder().withId("104")
            .withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build();
    public static final Employee ELLE = new EmployeeBuilder().withId("105")
            .withName("Elle Meyer").withPhone("94822241")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Employee FIONA = new EmployeeBuilder().withId("106")
            .withName("Fiona Kunz").withPhone("94824271")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Employee GEORGE = new EmployeeBuilder().withId("107")
            .withName("George Best").withPhone("94824421")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Employee HOON = new EmployeeBuilder().withId("108")
            .withName("Hoon Meier").withPhone("84824243")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Employee IDA = new EmployeeBuilder().withId("109")
            .withName("Ida Mueller").withPhone("84821314")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Employee's details found in {@code CommandTestUtil}
    public static final Employee AMY = new EmployeeBuilder().withId(VALID_ID_AMY).withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Employee BOB = new EmployeeBuilder().withId(VALID_ID_BOB).withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalEmployees() {} // prevents instantiation

    /**
     * Returns an {@code SudoHr} with all the typical employees.
     */
    public static SudoHr getTypicalSudoHr() {
        SudoHr sudoHr = new SudoHr();
        for (Employee employee : getTypicalEmployees()) {
            sudoHr.addEmployee(new EmployeeBuilder(employee).build());
        }
        return sudoHr;
    }

    public static List<Employee> getTypicalEmployees() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
