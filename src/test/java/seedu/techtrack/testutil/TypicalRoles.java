package seedu.techtrack.testutil;

import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_COMPANY_AMY;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_CONTACT_AMY;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_CONTACT_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_EXPERIENCE_AMY;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_EXPERIENCE_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_JOBDESCRIPTION_AMY;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_JOBDESCRIPTION_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.techtrack.model.RoleBook;
import seedu.techtrack.model.role.Role;

/**
 * A utility class containing a list of {@code Role} objects to be used in tests.
 */
public class TypicalRoles {

    public static final Role ALICE = new RoleBuilder().withName("Alice Pauline")
            .withCompany("Google").withEmail("alice@example.com")
            .withPhone("94351253").withJobDescription("Software Engineer @ Riot Games")
            .withTags("friends").withWebsite("www.google.com").withSalary("4000")
            .withDeadline("2023-10-20").withExperience("Javascript - 2 Years").build();
    public static final Role BENSON = new RoleBuilder().withName("Benson Meier")
            .withCompany("Google").withJobDescription("Civil Engineer @ NTU")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").withWebsite("www.google.com").withSalary("4000")
            .withDeadline("2023-10-20")
            .withExperience("C - 1 Year")
            .build();
    public static final Role CARL = new RoleBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withCompany("NUS").withJobDescription("Electrical Engineer @ NUS")
            .withWebsite("www.google.com").withSalary("4000")
            .withTags("Tech")
            .withDeadline("2023-10-20").withExperience("Python - 10 Years").build();
    public static final Role DANIEL = new RoleBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withCompany("Jane Street")
            .withJobDescription("Software Engineer @ Jane Street").withTags("friends").withWebsite("www.google.com")
            .withSalary("4000")
            .withDeadline("2023-10-20")
            .withExperience("Rust - 6 Years").build();
    public static final Role ELLE = new RoleBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withCompany("NUS").withJobDescription("Analyst @ DBS")
            .withWebsite("www.google.com").withSalary("4000")
            .withTags("Tech")
            .withDeadline("2023-10-20").withExperience("Lua - 2 Years").build();
    public static final Role FIONA = new RoleBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withCompany("NUS").withJobDescription("Analyst @ Goldman")
            .withWebsite("www.google.com").withSalary("4000")
            .withTags("Tech")
            .withDeadline("2023-10-20").withExperience("Coffeescript - 3 Years").build();
    public static final Role GEORGE = new RoleBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withCompany("Mary Street").withJobDescription("Analyst @ CitiBank")
            .withWebsite("www.google.com").withSalary("4000")
            .withDeadline("2023-10-20").withExperience("Go - 5 Years").build();

    // Manually added
    public static final Role HOON = new RoleBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withCompany("little india")
            .withJobDescription("Quantitative Trader @ Alphalabs")
            .withWebsite("www.google.com").withSalary("4000")
            .withDeadline("2023-10-20").withExperience("Rust - 5 Years").build();
    public static final Role IDA = new RoleBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withCompany("chicago ave").withJobDescription("Analyst @ Goldman")
            .withWebsite("www.google.com").withSalary("4000")
            .withDeadline("2023-10-20").withExperience("C - 5 Years").build();

    // Manually added - Role's details found in {@code CommandTestUtil}
    public static final Role AMY = new RoleBuilder().withName(VALID_NAME_AMY).withPhone(VALID_CONTACT_AMY)
            .withEmail(VALID_EMAIL_AMY).withCompany(VALID_COMPANY_AMY).withJobDescription(VALID_JOBDESCRIPTION_AMY)
            .withTags(VALID_TAG_FRIEND).withWebsite("www.google.com").withSalary("4000")
            .withDeadline("2023-10-20").withExperience(VALID_EXPERIENCE_AMY).build();
    public static final Role BOB = new RoleBuilder().withName(VALID_NAME_BOB).withPhone(VALID_CONTACT_BOB)
            .withEmail(VALID_EMAIL_BOB).withCompany(VALID_COMPANY_BOB).withJobDescription(VALID_JOBDESCRIPTION_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withWebsite("www.google.com").withSalary("4000")
            .withDeadline("2023-10-20").withExperience(VALID_EXPERIENCE_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalRoles() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical roles.
     */
    public static RoleBook getTypicalRoleBook() {
        RoleBook ab = new RoleBook();
        for (Role role : getTypicalRoles()) {
            ab.addRole(role);
        }
        return ab;
    }

    public static List<Role> getTypicalRoles() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
