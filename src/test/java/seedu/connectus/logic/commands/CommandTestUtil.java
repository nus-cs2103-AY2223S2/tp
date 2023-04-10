package seedu.connectus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.connectus.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.connectus.commons.core.index.Index;
import seedu.connectus.logic.commands.exceptions.CommandException;
import seedu.connectus.model.ConnectUs;
import seedu.connectus.model.Model;
import seedu.connectus.model.person.FieldsContainKeywordsPredicate;
import seedu.connectus.model.person.Person;
import seedu.connectus.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_REMARK_HUSBAND = "husband";
    public static final String VALID_REMARK_FRIEND = "friend";
    public static final String VALID_MODULE_CS2103T = "CS2103T";
    public static final String VALID_MODULE_CS2101 = "CS2101";
    public static final String VALID_CCA_ICS = "ICS";
    public static final String VALID_CCA_NES = "NES";
    public static final String VALID_MAJOR_COMPUTER_SCIENCE = "COMPUTER SCIENCE";
    public static final String VALID_MAJOR_BBA = "BBA";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String REMARK_DESC_FRIEND = " " + PREFIX_REMARK + VALID_REMARK_FRIEND;
    public static final String REMARK_DESC_HUSBAND = " " + PREFIX_REMARK + VALID_REMARK_HUSBAND;
    public static final String MODULE_DESC_CS2103T = " " + PREFIX_MODULE + VALID_MODULE_CS2103T;
    public static final String MODULE_DESC_CS2101 = " " + PREFIX_MODULE + VALID_MODULE_CS2101;
    public static final String CCA_DESC_ICS = " " + PREFIX_CCA + VALID_CCA_ICS;
    public static final String CCA_DESC_NES = " " + PREFIX_CCA + VALID_CCA_NES;
    public static final String MAJOR_DESC_COMPUTER_SCIENCE = " " + PREFIX_MAJOR + VALID_MAJOR_COMPUTER_SCIENCE;
    public static final String MAJOR_DESC_BBA = " " + PREFIX_MAJOR + VALID_MAJOR_BBA;
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_REMARK_DESC = " " + PREFIX_REMARK + "hubby*"; // '*' not allowed in REMARKs
    public static final String INVALID_MODULE_DESC = " " + PREFIX_MODULE + "CS2!03T"; //! not allowed in modules
    public static final String INVALID_CCA_DESC = " " + PREFIX_CCA + "*ICS*"; //* not allowed in ccas
    public static final String INVALID_MAJOR_DESC = " "
            + PREFIX_MAJOR + "BBA!"; //! not allowed in majors

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    // For help command tests
    public static final String VALID_HELP_COMMAND_ADD = "add";
    public static final String VALID_HELP_COMMAND_ADD_T = "add-t";
    public static final String VALID_HELP_COMMAND_CHAT = "chat";
    public static final String VALID_HELP_COMMAND_CLEAR = "clear";
    public static final String VALID_HELP_COMMAND_DELETE = "delete";
    public static final String VALID_HELP_COMMAND_DELETE_T = "delete-t";
    public static final String VALID_HELP_COMMAND_EDIT = "edit";
    public static final String VALID_HELP_COMMAND_EXIT = "exit";
    public static final String VALID_HELP_COMMAND_HELP = "help";
    public static final String VALID_HELP_COMMAND_LIST = "list";
    public static final String VALID_HELP_COMMAND_OPEN = "open";
    public static final String VALID_HELP_COMMAND_SEARCH = "search";
    public static final String VALID_HELP_COMMAND_UPCOMING_B = "upcoming-b";
    public static final String VALID_HELP_COMMAND_EMPTY = "";
    public static final String VALID_HELP_COMMAND_CLEAR_WITH_WHITESPACE = "\t   \n clear   ";
    public static final String VALID_HELP_COMMAND_WHITESPACE = "              ";
    public static final String INVALID_COMMAND_DESC = "Invalid Command.";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).build();
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
     * - the ConnectUS, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ConnectUs expectedConnectUS = new ConnectUs(actualModel.getConnectUs());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedConnectUS, actualModel.getConnectUs());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s ConnectUS.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String name = person.getName().fullName;
        FieldsContainKeywordsPredicate predicate = new FieldsContainKeywordsPredicate();
        predicate.setName(name);
        model.updateFilteredPersonList(predicate);

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
