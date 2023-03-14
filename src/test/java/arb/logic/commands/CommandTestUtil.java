package arb.logic.commands;

import static arb.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static arb.logic.parser.CliSyntax.PREFIX_EMAIL;
import static arb.logic.parser.CliSyntax.PREFIX_NAME;
import static arb.logic.parser.CliSyntax.PREFIX_OPTION;
import static arb.logic.parser.CliSyntax.PREFIX_PHONE;
import static arb.logic.parser.CliSyntax.PREFIX_TAG;
import static arb.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import arb.commons.core.index.Index;
import arb.logic.commands.client.EditClientCommand;
import arb.logic.commands.exceptions.CommandException;
import arb.logic.commands.project.EditProjectCommand;
import arb.model.AddressBook;
import arb.model.ListType;
import arb.model.Model;
import arb.model.client.Client;
import arb.model.client.NameContainsKeywordsPredicate;
import arb.model.project.Project;
import arb.model.project.TitleContainsKeywordsPredicate;
import arb.testutil.EditClientDescriptorBuilder;
import arb.testutil.EditProjectDescriptorBuilder;

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
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String VALID_TITLE_SKY_PAINTING = "Sky Painting";
    public static final String VALID_TITLE_OIL_PAINTING = "Oil Painting";
    public static final String VALID_DEADLINE_SKY_PAINTING = "6pm 2023-02-02";
    public static final String VALID_DEADLINE_OIL_PAINTING = "midnight 2023-05-05";

    public static final String VALID_SORTING_OPTION_DEADLINE = "deadline";
    public static final String VALID_SORTING_OPTION_TITLE = "name";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String TITLE_DESC_SKY_PAINTING = " " + PREFIX_NAME + VALID_TITLE_SKY_PAINTING;
    public static final String TITLE_DESC_OIL_PAINTING = " " + PREFIX_NAME + VALID_TITLE_OIL_PAINTING;
    public static final String DEADLINE_DESC_SKY_PAINTING = " " + PREFIX_DEADLINE + VALID_DEADLINE_SKY_PAINTING;
    public static final String DEADLINE_DESC_OIL_PAINTING = " " + PREFIX_DEADLINE + VALID_DEADLINE_OIL_PAINTING;

    public static final String SORTING_OPTION_DESC = " " + PREFIX_OPTION + VALID_SORTING_OPTION_DEADLINE;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String INVALID_TITLE_DESC = " " + PREFIX_NAME
            + "watercolour painting&"; // '&' not allowed in titles
    public static final String INVALID_DEADLINE_DESC = " " + PREFIX_DEADLINE
            + "ocean"; // 'ocean' is not able to be parsed into a date

    public static final String INVALID_SORTING_OPTION_DESC = " " + PREFIX_OPTION
            + "deadline&"; // does not match 'deadline' or 'name'

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditClientCommand.EditClientDescriptor DESC_AMY;
    public static final EditClientCommand.EditClientDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditClientDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditClientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    public static final EditProjectCommand.EditProjectDescriptor DESC_SKY_PAINTING;
    public static final EditProjectCommand.EditProjectDescriptor DESC_OIL_PAINTING;

    static {
        DESC_SKY_PAINTING = new EditProjectDescriptorBuilder().withTitle(VALID_TITLE_SKY_PAINTING)
                .withDeadline(VALID_DEADLINE_SKY_PAINTING).build();

        DESC_OIL_PAINTING = new EditProjectDescriptorBuilder().withTitle(VALID_TITLE_OIL_PAINTING)
                .withDeadline(VALID_DEADLINE_OIL_PAINTING)
                .build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, ListType currentListBeingShown, ListType listToBeShown,
            Model actualModel, CommandResult expectedCommandResult, Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel, currentListBeingShown);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, ListType, ListType, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}, {@code currentListBeingShown} and {@code listToBeShown}.
     */
    public static void assertCommandSuccess(Command command, ListType currentListBeingShown, ListType listToBeShown,
            Model actualModel, String expectedMessage, Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, listToBeShown);
        assertCommandSuccess(command, currentListBeingShown, listToBeShown, actualModel, expectedCommandResult,
                expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered client list and selected client in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, ListType currentListBeingShown, Model actualModel,
            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Client> expectedFilteredList = new ArrayList<>(actualModel.getFilteredClientList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel,
                currentListBeingShown));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredClientList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the client at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showClientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredClientList().size());

        Client client = model.getFilteredClientList().get(targetIndex.getZeroBased());
        final String[] splitName = client.getName().fullName.split("\\s+");
        model.updateFilteredClientList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredClientList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the project at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showProjectAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredProjectList().size());

        Project project = model.getFilteredProjectList().get(targetIndex.getZeroBased());
        final String[] splitName = project.getTitle().fullTitle.split("\\s+");
        model.updateFilteredProjectList(new TitleContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredProjectList().size());
    }

}
