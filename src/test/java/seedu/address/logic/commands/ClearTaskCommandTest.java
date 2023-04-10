package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
//import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
//import seedu.address.model.person.Person;
//import seedu.address.model.person.Task;
//import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ClearTaskCommand.
 */
public class ClearTaskCommandTest {

    private static final String TASK_STUB = "Some task.";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    //    @Test
    //    public void execute_clearTaskUnfilteredList_success() {
    //        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
    //        Person editedPerson = new PersonBuilder(firstPerson).withTask(TASK_STUB).build();
    //
    //        ClearTaskCommand clearTaskCommand = new ClearTaskCommand(INDEX_FIRST_PERSON);
    //
    //        String expectedMessage = String.format(ClearTaskCommand.MESSAGE_CLEAR_TASK_SUCCESS, editedPerson);
    //
    //        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
    //        expectedModel.setPerson(firstPerson, editedPerson);
    //
    //        assertCommandSuccess(clearTaskCommand, model, expectedMessage, expectedModel);
    //    }
    //
    //    @Test
    //    public void execute_clearTaskFilteredList_success() {
    //        showPersonAtIndex(model, INDEX_FIRST_PERSON);
    //
    //        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
    //        Person editedPerson = new PersonBuilder(model.getFilteredPersonList()
    //                .get(INDEX_FIRST_PERSON.getZeroBased()))
    //                .withTask(TASK_STUB).build();
    //
    //        ClearTaskCommand clearTaskCommand = new ClearTaskCommand(INDEX_FIRST_PERSON);
    //
    //        String expectedMessage = String.format(AddTaskCommand.MESSAGE_ADD_TASK_SUCCESS, editedPerson);
    //
    //        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
    //        expectedModel.setPerson(firstPerson, editedPerson);
    //
    //        assertCommandSuccess(clearTaskCommand, model, expectedMessage, expectedModel);
    //    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ClearTaskCommand clearTaskCommand = new ClearTaskCommand(outOfBoundIndex);

        assertCommandFailure(clearTaskCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        ClearTaskCommand clearTaskCommand = new ClearTaskCommand(outOfBoundIndex);
        assertCommandFailure(clearTaskCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final ClearTaskCommand standardCommand = new ClearTaskCommand(INDEX_FIRST_PERSON);

        // same values -> returns true
        ClearTaskCommand commandWithSameValues = new ClearTaskCommand(INDEX_FIRST_PERSON);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new ClearTaskCommand(INDEX_SECOND_PERSON)));
    }
}

