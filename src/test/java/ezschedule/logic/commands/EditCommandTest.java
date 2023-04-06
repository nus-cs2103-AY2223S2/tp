package ezschedule.logic.commands;

import static ezschedule.commons.core.Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX;
import static ezschedule.logic.commands.CommandTestUtil.EDIT_DESC_A;
import static ezschedule.logic.commands.CommandTestUtil.EDIT_DESC_B;
import static ezschedule.logic.commands.CommandTestUtil.VALID_DATE_B;
import static ezschedule.logic.commands.CommandTestUtil.VALID_NAME_B;
import static ezschedule.logic.commands.CommandTestUtil.assertCommandFailure;
import static ezschedule.logic.commands.CommandTestUtil.assertCommandSuccess;
import static ezschedule.logic.commands.CommandTestUtil.showEventAtIndex;
import static ezschedule.testutil.TypicalEvents.getTypicalScheduler;
import static ezschedule.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static ezschedule.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ezschedule.commons.core.Messages;
import ezschedule.commons.core.index.Index;
import ezschedule.logic.commands.EditCommand.EditEventDescriptor;
import ezschedule.model.Model;
import ezschedule.model.ModelManager;
import ezschedule.model.Scheduler;
import ezschedule.model.UserPrefs;
import ezschedule.model.event.Event;
import ezschedule.testutil.EditEventDescriptorBuilder;
import ezschedule.testutil.EventBuilder;

/**
 * Contains integration tests (interaction with the {@code Model}) and unit tests for {@code EditCommand}.
 */
public class EditCommandTest {

    private final Model model = new ModelManager(getTypicalScheduler(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Event editedEvent = new EventBuilder().build();
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_EVENT, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new Scheduler(model.getScheduler()), new UserPrefs());
        expectedModel.setEvent(model.getFilteredEventList().get(0), editedEvent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastEvent = Index.fromOneBased(model.getFilteredEventList().size());
        Event lastEvent = model.getFilteredEventList().get(indexLastEvent.getZeroBased());

        EventBuilder eventInList = new EventBuilder(lastEvent);
        Event editedEvent = eventInList.withName(VALID_NAME_B).withDate(VALID_DATE_B).build();

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withName(VALID_NAME_B).withDate(VALID_DATE_B).build();
        EditCommand editCommand = new EditCommand(indexLastEvent, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new Scheduler(model.getScheduler()), new UserPrefs());
        expectedModel.setEvent(lastEvent, editedEvent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_EVENT, new EditEventDescriptor());
        Event editedEvent = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new Scheduler(model.getScheduler()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        Event eventInFilteredList = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        Event editedEvent = new EventBuilder(eventInFilteredList).withName(VALID_NAME_B).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_EVENT,
                new EditEventDescriptorBuilder().withName(VALID_NAME_B).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new Scheduler(model.getScheduler()), new UserPrefs());
        expectedModel.setEvent(model.getFilteredEventList().get(0), editedEvent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateEventUnfilteredList_failure() {
        Event firstEvent = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(firstEvent).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_EVENT, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    public void execute_duplicateEventFilteredList_failure() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        // edit event in filtered list into a duplicate in scheduler
        Event eventInList = model.getScheduler().getEventList().get(INDEX_SECOND_EVENT.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_EVENT,
                new EditEventDescriptorBuilder(eventInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    public void execute_eventEndTimeEarlierThanStartTime_failure() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_EVENT,
                new EditEventDescriptorBuilder().withStartTime("11:00").withEndTime("00:00").build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_EVENT_END_TIME_EARLIER_THAN_START_TIME);
    }

    @Test
    public void execute_invalidEventIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_B).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, String.format(
                MESSAGE_INVALID_EVENT_DISPLAYED_INDEX, model.getFilteredEventList().size() + 1));
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of scheduler
     */
    @Test
    public void execute_invalidEventIndexFilteredList_failure() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);
        Index outOfBoundIndex = INDEX_SECOND_EVENT;
        // ensures that outOfBoundIndex is still in bounds of scheduler list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getScheduler().getEventList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditEventDescriptorBuilder().withName(VALID_NAME_B).build());

        assertCommandFailure(editCommand, model, String.format(
                MESSAGE_INVALID_EVENT_DISPLAYED_INDEX, outOfBoundIndex.getZeroBased() + 1));
    }

    @Test
    public void equals() {
        EditCommand standardCommand = new EditCommand(INDEX_FIRST_EVENT, EDIT_DESC_A);
        EditEventDescriptor copyDescriptor = new EditEventDescriptor(EDIT_DESC_A);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_EVENT, copyDescriptor);

        // same values -> returns true
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_EVENT, EDIT_DESC_A)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_EVENT, EDIT_DESC_B)));
    }
}
