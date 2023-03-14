package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBDESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEBSITE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.job.NameContainsKeywordsPredicate;
import seedu.address.model.job.Role;
import seedu.address.testutil.EditRoleDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_WEB_AMY = "www.google.com";
    public static final String VALID_WEB_BOB = "www.google.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_JOBDESCRIPTION_AMY = "Software Engineer Intern @ DBS";
    public static final String VALID_JOBDESCRIPTION_BOB = "Civil Engineer Intern @ HDB";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_WEB_FRIEND = "www.google.com";
    public static final String VALID_SALARY_AMY = "4000";
    public static final String VALID_SALARY_BOB = "4000";
    public static final String VALID_DEADLINE_AMY = "2023-10-20";
    public static final String VALID_DEADLINE_BOB = "2023-10-20";

    public static final String NAME_DESC_AMY = " " + PREFIX_ROLE + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_ROLE + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_CONTACT + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_CONTACT + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String JOBDESCRIPTION_DESC_AMY = " " + PREFIX_JOBDESCRIPTION + VALID_JOBDESCRIPTION_AMY;
    public static final String JOBDESCRIPTION_DESC_BOB = " " + PREFIX_JOBDESCRIPTION + VALID_JOBDESCRIPTION_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String WEBSITE = " " + PREFIX_WEBSITE + "www.google.com";
    public static final String SALARY_DESC_AMY = " " + PREFIX_SALARY + VALID_SALARY_AMY;
    public static final String SALARY_DESC_BOB = " " + PREFIX_SALARY + VALID_SALARY_BOB;
    public static final String DEADLINE_DESC_AMY = " " + PREFIX_DEADLINE + VALID_DEADLINE_AMY;
    public static final String DEADLINE_DESC_BOB = " " + PREFIX_DEADLINE + VALID_DEADLINE_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_ROLE + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_CONTACT + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_JOBDESCRIPTION_DESC = " " + PREFIX_JOBDESCRIPTION; // empty string not allowed
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_WEBSITE = " " + PREFIX_WEBSITE + "ww.com";
    public static final String INVALID_SALARY_DESC = " " + PREFIX_SALARY + "dn1"; // Characters not allowed in salaries
    public static final String INVALID_DEADLINE_DESC = " " + PREFIX_DEADLINE
            + "2023-10-2a"; // Characters not allowed in deadline
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditRoleDescriptor DESC_AMY;
    public static final EditCommand.EditRoleDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditRoleDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).withWebsite(VALID_WEB_AMY).withJobDescription(VALID_JOBDESCRIPTION_AMY)
                .withTags(VALID_TAG_FRIEND).withSalary(VALID_SALARY_AMY)
                .withDeadline(VALID_DEADLINE_AMY).build();
        DESC_BOB = new EditRoleDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withWebsite(VALID_WEB_BOB)
                .withJobDescription(VALID_JOBDESCRIPTION_AMY).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .withSalary(VALID_SALARY_BOB).withDeadline(VALID_DEADLINE_BOB).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered role list and selected role in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Role> expectedFilteredList = new ArrayList<>(actualModel.getFilteredRoleList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredRoleList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the role at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showRoleAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredRoleList().size());

        Role role = model.getFilteredRoleList().get(targetIndex.getZeroBased());
        final String[] splitName = role.getName().fullName.split("\\s+");
        model.updateFilteredRoleList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredRoleList().size());
    }

}
