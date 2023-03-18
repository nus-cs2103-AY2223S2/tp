package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showEventAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_EVENT;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.person.EventSetContainsEventPredicate;

public class ListEventContactCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredListAndMultiplePersonListed_success() {
        Event targetEvent = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        ListEvContactCommand listEvContactCommand = new ListEvContactCommand(INDEX_FIRST_EVENT);

        String expectedMessage = String.format(ListEvContactCommand.MESSAGE_LIST_EVENT_CONTACT_SUCCESS, targetEvent);

        EventSetContainsEventPredicate predicate = new EventSetContainsEventPredicate(targetEvent);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(listEvContactCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_validIndexUnfilteredListAndNoPersonListed_success() {
        Event targetEvent = model.getFilteredEventList().get(INDEX_THIRD_EVENT.getZeroBased());
        ListEvContactCommand listEvContactCommand = new ListEvContactCommand(INDEX_THIRD_EVENT);

        String expectedMessage = String.format(ListEvContactCommand.MESSAGE_LIST_EVENT_CONTACT_SUCCESS, targetEvent);

        EventSetContainsEventPredicate predicate = new EventSetContainsEventPredicate(targetEvent);
        expectedModel.updateFilteredPersonList(predicate);
        showNoPerson(expectedModel);

        assertCommandSuccess(listEvContactCommand, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        ListEvContactCommand listEvContactCommand = new ListEvContactCommand(outOfBoundIndex);

        assertCommandFailure(listEvContactCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredListAndMultiplePersonListed_success() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);
        showEventAtIndex(expectedModel, INDEX_FIRST_EVENT);

        Event targetEvent = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        ListEvContactCommand listEvContactCommand = new ListEvContactCommand(INDEX_FIRST_EVENT);

        String expectedMessage = String.format(ListEvContactCommand.MESSAGE_LIST_EVENT_CONTACT_SUCCESS, targetEvent);

        EventSetContainsEventPredicate predicate = new EventSetContainsEventPredicate(targetEvent);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(listEvContactCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        Index outOfBoundIndex = INDEX_SECOND_EVENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getEventList().size());

        ListEvContactCommand listEvContactCommand = new ListEvContactCommand(outOfBoundIndex);

        assertCommandFailure(listEvContactCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ListEvContactCommand listEvContactFirstCommand = new ListEvContactCommand(INDEX_FIRST_EVENT);
        ListEvContactCommand listEvContactSecondCommand = new ListEvContactCommand(INDEX_SECOND_EVENT);

        // same object -> returns true
        assertTrue(listEvContactFirstCommand.equals(listEvContactFirstCommand));

        // same values -> returns true
        ListEvContactCommand listEvContactFirstCommandCopy = new ListEvContactCommand(INDEX_FIRST_EVENT);
        assertTrue(listEvContactFirstCommand.equals(listEvContactFirstCommandCopy));

        // different types -> returns false
        assertFalse(listEvContactFirstCommand.equals(1));

        // null -> returns false
        assertFalse(listEvContactFirstCommand.equals(null));

        // different event and person -> returns false
        assertFalse(listEvContactFirstCommand.equals(listEvContactSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no event.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
