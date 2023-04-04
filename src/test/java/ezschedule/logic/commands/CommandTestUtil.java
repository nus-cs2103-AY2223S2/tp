package ezschedule.logic.commands;

import static ezschedule.logic.parser.CliSyntax.PREFIX_DATE;
import static ezschedule.logic.parser.CliSyntax.PREFIX_END;
import static ezschedule.logic.parser.CliSyntax.PREFIX_NAME;
import static ezschedule.logic.parser.CliSyntax.PREFIX_START;
import static ezschedule.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ezschedule.commons.core.index.Index;
import ezschedule.logic.commands.exceptions.CommandException;
import ezschedule.model.Model;
import ezschedule.model.Scheduler;
import ezschedule.model.event.Event;
import ezschedule.model.event.EventContainsKeywordsPredicate;
import ezschedule.testutil.EditEventDescriptorBuilder;


/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_A = "Event A";
    public static final String VALID_NAME_B = "Event B";
    public static final String VALID_DATE_A = "2023-05-01";
    public static final String VALID_DATE_B = "2023-06-01";
    public static final String VALID_START_TIME_A = "12:00";
    public static final String VALID_START_TIME_B = "18:00";
    public static final String VALID_END_TIME_A = "15:00";
    public static final String VALID_END_TIME_B = "22:00";
    public static final String VALID_END_TIME_BEFORE_START_TIME_B = "00:00";

    public static final String NAME_DESC_A = " " + PREFIX_NAME + VALID_NAME_A;
    public static final String NAME_DESC_B = " " + PREFIX_NAME + VALID_NAME_B;
    public static final String DATE_DESC_A = " " + PREFIX_DATE + VALID_DATE_A;
    public static final String DATE_DESC_B = " " + PREFIX_DATE + VALID_DATE_B;
    public static final String START_TIME_DESC_A = " " + PREFIX_START + VALID_START_TIME_A;
    public static final String START_TIME_DESC_B = " " + PREFIX_START + VALID_START_TIME_B;
    public static final String END_TIME_DESC_A = " " + PREFIX_END + VALID_END_TIME_A;
    public static final String END_TIME_DESC_B = " " + PREFIX_END + VALID_END_TIME_B;
    public static final String END_TIME_BEFORE_START_TIME_DESC_B =
            " " + PREFIX_END + VALID_END_TIME_BEFORE_START_TIME_B;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "Event@"; // '@' not allowed in names
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + "2023-01-05a"; // 'a' not allowed in phones
    public static final String INVALID_START_TIME_DESC = " " + PREFIX_START + "1800"; // missing ':' symbol
    public static final String INVALID_END_TIME_DESC = " " + PREFIX_END + "22:00*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditEventDescriptor DESC_A;
    public static final EditCommand.EditEventDescriptor DESC_B;

    static {
        DESC_A = new EditEventDescriptorBuilder().withName(VALID_NAME_A).withDate(VALID_DATE_A)
                .withStartTime(VALID_START_TIME_A).withEndTime(VALID_END_TIME_A).build();
        DESC_B = new EditEventDescriptorBuilder().withName(VALID_NAME_B).withDate(VALID_DATE_B)
                .withStartTime(VALID_START_TIME_B).withEndTime(VALID_END_TIME_B).build();
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
     * - the scheduler, filtered event list and selected event in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Scheduler expectedAddressBook = new Scheduler(actualModel.getScheduler());
        List<Event> expectedFilteredList = new ArrayList<>(actualModel.getFilteredEventList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getScheduler());
        assertEquals(expectedFilteredList, actualModel.getFilteredEventList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the event at the given {@code targetIndex} in the
     * {@code model}'s scheduler.
     */
    public static void showEventAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredEventList().size());

        Event event = model.getFilteredEventList().get(targetIndex.getZeroBased());
        final String[] splitName = event.getName().fullName.split("\\s+");
        model.updateFilteredEventList(new EventContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredEventList().size());
    }
}
