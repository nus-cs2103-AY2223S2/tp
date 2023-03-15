package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOBDESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOBDESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPERIENCE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPERIENCE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.job.Role;

/**
 * A utility class containing a list of {@code Role} objects to be used in tests.
 */
public class TypicalRoles {

    public static final Role ALICE = new RoleBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withJobDescription("Software Engineer @ Riot Games")
            .withTags("friends").withWebsite("www.google.com").withSalary("4000")
            .withDeadline("2023-10-20").withExperience("C - 1 Year").build();
    public static final Role BENSON = new RoleBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withJobDescription("Civil Engineer @ NTU")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").withWebsite("www.google.com").withSalary("4000")
            .withDeadline("2023-10-20")
            .withExperience("Python - Year 1")
            .build();
    public static final Role CARL = new RoleBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withJobDescription("Electrical Engineer @ NUS")
            .withWebsite("www.google.com").withSalary("4000")
            .withDeadline("2023-10-20").withExperience("Rust - 2 Years").build();
    public static final Role DANIEL = new RoleBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withJobDescription("Software Engineer @ Jane Street").withTags("friends").withWebsite("www.google.com")
            .withSalary("4000")
            .withDeadline("2023-10-20")
            .withExperience("CoffeeScript - 5 Years").build();
    public static final Role ELLE = new RoleBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withJobDescription("Analyst @ DBS")
            .withWebsite("www.google.com").withSalary("4000")
            .withDeadline("2023-10-20").withExperience("C - 10 Years").build();
    public static final Role FIONA = new RoleBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withJobDescription("Analyst @ Goldman")
            .withWebsite("www.google.com").withSalary("4000")
            .withDeadline("2023-10-20").withExperience("Python - 2 Years").build();
    public static final Role GEORGE = new RoleBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withJobDescription("Analyst @ CitiBank")
            .withWebsite("www.google.com").withSalary("4000")
            .withDeadline("2023-10-20").withExperience("Rust - 5 Years").build();

    // Manually added
    public static final Role HOON = new RoleBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india")
            .withJobDescription("Quantitative Trader @ Alphalabs")
            .withWebsite("www.google.com").withSalary("4000")
            .withDeadline("2023-10-20").withExperience("Rust - 5 Years").build();
    public static final Role IDA = new RoleBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withJobDescription("Analyst @ Goldman")
            .withWebsite("www.google.com").withSalary("4000")
            .withDeadline("2023-10-20").withExperience("C - 5 Years").build();

    // Manually added - Role's details found in {@code CommandTestUtil}
    public static final Role AMY = new RoleBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withJobDescription(VALID_JOBDESCRIPTION_AMY)
            .withTags(VALID_TAG_FRIEND).withWebsite("www.google.com").withSalary("4000")
            .withDeadline("2023-10-20").withExperience("Python - 5 Years").build();
    public static final Role BOB = new RoleBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withJobDescription(VALID_JOBDESCRIPTION_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withWebsite("www.google.com").withSalary("4000")
            .withDeadline("2023-10-20").withExperience("Java - 5 Years").build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalRoles() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical roles.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Role role : getTypicalRoles()) {
            ab.addRole(role);
        }
        return ab;
    }

    public static List<Role> getTypicalRoles() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
