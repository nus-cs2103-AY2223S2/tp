package seedu.internship.logic.commands.event;

import static seedu.internship.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.internship.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internship.logic.commands.event.EventDeleteCommand.MESSAGE_DELETE_EVENT_SUCCESS;
import static seedu.internship.testutil.Assert.assertThrows;
import static seedu.internship.testutil.TypicalEvents.getTypicalEventCatalogue;
import static seedu.internship.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.internship.testutil.TypicalInternships.getTypicalInternshipCatalogue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internship.commons.core.Messages;
import seedu.internship.commons.core.index.Index;
import seedu.internship.logic.commands.CommandResult;
import seedu.internship.logic.commands.ResultType;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.model.Model;
import seedu.internship.model.ModelManager;
import seedu.internship.model.UserPrefs;
import seedu.internship.model.event.Event;
import seedu.internship.model.event.EventByInternship;
import seedu.internship.model.internship.Internship;

public class EventDeleteCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternshipCatalogue(), getTypicalEventCatalogue(), new UserPrefs());
    }
    @Test
    public void execute_validIndexUnfilteredList_success() {
        Event eventToDelete = model.getFilteredEventList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        model.updateSelectedInternship(eventToDelete.getInternship());
        EventDeleteCommand eventDeleteCommand = new EventDeleteCommand(INDEX_FIRST_INTERNSHIP);

        ModelManager expectedModel = new ModelManager(model.getInternshipCatalogue(), model.getEventCatalogue(),
                new UserPrefs());

        expectedModel.deleteEvent(eventToDelete);
        Internship selectedInternship = eventToDelete.getInternship();
        expectedModel.updateFilteredEventList(new EventByInternship(selectedInternship));
        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_DELETE_EVENT_SUCCESS, eventToDelete) , ResultType.SHOW_INFO,
                selectedInternship, expectedModel.getFilteredEventList());

        assertCommandSuccess(eventDeleteCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_eventWithoutSelecting_throwsCommandException() {
        EventDeleteCommand deleteCommand = new EventDeleteCommand(INDEX_FIRST_INTERNSHIP);
        ModelManager expectedModel = new ModelManager(model.getInternshipCatalogue(), model.getEventCatalogue(),
                new UserPrefs());
        assertThrows(CommandException.class, EventDeleteCommand.MESSAGE_NO_INTERNSHIP_SELECTED, () ->
                deleteCommand.execute(expectedModel));
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        ModelManager expectedModel = new ModelManager(model.getInternshipCatalogue(), model.getEventCatalogue(),
                new UserPrefs());
        Event eventToDelete = expectedModel.getFilteredEventList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        expectedModel.updateSelectedInternship(eventToDelete.getInternship());
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        EventDeleteCommand eventDeleteCommand = new EventDeleteCommand(outOfBoundIndex);

        assertCommandFailure(eventDeleteCommand, expectedModel, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }


}
