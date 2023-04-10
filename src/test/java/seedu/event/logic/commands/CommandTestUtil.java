package seedu.event.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.event.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.event.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.event.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.event.logic.parser.CliSyntax.PREFIX_RATE;
import static seedu.event.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.event.logic.parser.CliSyntax.PREFIX_TIME_END;
import static seedu.event.logic.parser.CliSyntax.PREFIX_TIME_START;
import static seedu.event.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.event.commons.core.index.Index;
import seedu.event.logic.commands.exceptions.CommandException;
import seedu.event.model.EventBook;
import seedu.event.model.Model;
import seedu.event.model.event.Event;
import seedu.event.model.event.NameContainsKeywordsPredicate;
import seedu.event.testutil.EditEventDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_ALICE = "Alice Pauline";
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_ALICE = "94351253";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_START_TIME_AMY = "11-03-2023 11:00";
    public static final String VALID_END_TIME_AMY = "11-03-2023 12:00";
    public static final String VALID_START_TIME_BOB = "04-04-2023 11:00";
    public static final String VALID_END_TIME_BOB = "04-05-2023 13:00";
    public static final String VALID_MARK_AMY = "[ ]";
    public static final String VALID_MARK_BOB = "[ ]";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_ALICE = " " + PREFIX_NAME + VALID_NAME_ALICE;
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_ALICE = " " + PREFIX_PHONE + VALID_PHONE_ALICE;
    public static final String PHONE_DESC_AMY = " " + PREFIX_RATE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_RATE + VALID_PHONE_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String START_TIME_DESC_AMY = " " + PREFIX_TIME_START + VALID_START_TIME_AMY;
    public static final String START_TIME_DESC_BOB = " " + PREFIX_TIME_START + VALID_START_TIME_BOB;
    public static final String END_TIME_DESC_AMY = " " + PREFIX_TIME_END + VALID_END_TIME_AMY;
    public static final String END_TIME_DESC_BOB = " " + PREFIX_TIME_END + VALID_END_TIME_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_RATE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    // string not formatted correctly not allowed for time
    public static final String INVALID_START_TIME_DESC = " " + PREFIX_TIME_START + " ";
    public static final String INVALID_END_TIME_DESC = " " + PREFIX_TIME_END + " ";
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditEventDescriptor DESC_AMY;
    public static final EditCommand.EditEventDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditEventDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withAddress(VALID_ADDRESS_AMY)
                .withStartTime(VALID_START_TIME_AMY)
                .withEndTime(VALID_END_TIME_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditEventDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withAddress(VALID_ADDRESS_BOB)
                .withStartTime(VALID_START_TIME_BOB)
                .withEndTime(VALID_END_TIME_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
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
     * - the event book, filtered event list and selected event in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        EventBook expectedEventBook = new EventBook(actualModel.getEventBook());
        List<Event> expectedFilteredList = new ArrayList<>(actualModel.getFilteredEventList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedEventBook, actualModel.getEventBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredEventList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the event at the given {@code targetIndex} in the
     * {@code model}'s event book.
     */
    public static void showEventAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredEventList().size());

        Event event = model.getFilteredEventList().get(targetIndex.getZeroBased());
        final String[] splitName = event.getName().fullName.split("\\s+");
        model.updateFilteredEventList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredEventList().size());
    }

}
