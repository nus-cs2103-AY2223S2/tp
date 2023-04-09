package seedu.internship.logic.commands.event;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.internship.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internship.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;
import static seedu.internship.testutil.TypicalEvents.EM11;
import static seedu.internship.testutil.TypicalEvents.EM12;
import static seedu.internship.testutil.TypicalEvents.EM21;
import static seedu.internship.testutil.TypicalEvents.getTypicalEventCatalogue;
import static seedu.internship.testutil.TypicalInternships.getTypicalInternshipCatalogue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.internship.logic.commands.CommandResult;
import seedu.internship.logic.commands.ResultType;
import seedu.internship.model.EventCatalogue;
import seedu.internship.model.InternshipCatalogue;
import seedu.internship.model.Model;
import seedu.internship.model.ModelManager;
import seedu.internship.model.UserPrefs;
import seedu.internship.model.event.End;
import seedu.internship.model.event.Name;
import seedu.internship.model.event.Start;


/**
 * Contains integration tests (interaction with the Model) and unit tests for FindCommand.
 */
public class EventFindCommandTest {

    private Model model = new ModelManager(getTypicalInternshipCatalogue(),
            new EventCatalogue(getTypicalEventCatalogue()), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        EventFindCommand.FilterEventDescriptor descriptor = new EventFindCommand.FilterEventDescriptor();
        descriptor.setName(new Name("Interview"));
        descriptor.setStart(new Start(LocalDateTime.parse("04/04/2023 1500",
                Start.NUMERIC_DATE_TIME_FORMATTER)));
        descriptor.setEnd(new End(LocalDateTime.parse("04/04/2023 1800",
                End.NUMERIC_DATE_TIME_FORMATTER)));
        EventFindCommand eventFindCommand = new EventFindCommand(descriptor);

        Model expectedModel = new ModelManager(new InternshipCatalogue(model.getInternshipCatalogue()),
                new EventCatalogue(model.getEventCatalogue()), new UserPrefs());

        expectedModel.updateFilteredEventList(x -> x.equals(EM11));

        CommandResult expectedCommandResult = new CommandResult(
                String.format(EventFindCommand.MESSAGE_SUCCESS, 1),
                ResultType.FIND_EVENT, expectedModel.getFilteredEventList());

        assertCommandSuccess(eventFindCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        EventFindCommand.FilterEventDescriptor descriptor = new EventFindCommand.FilterEventDescriptor();
        descriptor.setName(new Name("HR Meeting"));
        EventFindCommand findCommand = new EventFindCommand(descriptor);

        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
        Model expectedModel = new ModelManager(new InternshipCatalogue(model.getInternshipCatalogue()),
                new EventCatalogue(model.getEventCatalogue()), new UserPrefs());

        expectedModel.updateFilteredEventList(x -> x.equals(EM12) || x.equals(EM21));

        CommandResult expectedCommandResult = new CommandResult(
                String.format(EventFindCommand.MESSAGE_SUCCESS, 2),
                ResultType.FIND_EVENT, expectedModel.getFilteredEventList());

        assertCommandSuccess(findCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        EventFindCommand.FilterEventDescriptor descriptor = new EventFindCommand.FilterEventDescriptor();
        EventFindCommand eventFindCommand = new EventFindCommand(descriptor);

        assertCommandFailure(eventFindCommand,
                model, String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void equals() {
        final EventFindCommand standardCommand = new EventFindCommand(new EventFindCommand.FilterEventDescriptor());

        // same values -> returns true
        EventFindCommand.FilterEventDescriptor copyDescriptor = new EventFindCommand.FilterEventDescriptor();
        EventFindCommand commandWithSameValues = new EventFindCommand(copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different descriptor -> returns false
        EventFindCommand.FilterEventDescriptor descriptor = new EventFindCommand.FilterEventDescriptor();
        descriptor.setName(new Name("Interview"));
        assertFalse(standardCommand.equals(new EventFindCommand(descriptor)));
    }



}

