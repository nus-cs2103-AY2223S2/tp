package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;



public class DeleteCommandsTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void execute_validIndexsUnfilteredList_success() {

        Person personToDelete1 = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person personToDelete2 = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        ArrayList<Index> temp = new ArrayList<>();
        temp.add(INDEX_FIRST_PERSON);
        temp.add(INDEX_SECOND_PERSON);
        DeleteCommands deleteCommands = new DeleteCommands(temp);

        String people = personToDelete2 + " " + personToDelete1 + " ";
        String expectedMessage = String.format(DeleteCommands.MESSAGE_DELETE_PERSON_SUCCESS, people);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete1);
        expectedModel.deletePerson(personToDelete2);

        assertCommandSuccess(deleteCommands, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexsUnfilteredList_throwsCommandException() {
        Index normalIndex = Index.fromOneBased(model.getFilteredPersonList().size() - 1);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ArrayList<Index> temp = new ArrayList<>();
        temp.add(outOfBoundIndex);
        temp.add(normalIndex);
        DeleteCommands deleteCommand = new DeleteCommands(temp);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexsFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        ArrayList<Index> temp = new ArrayList<>();
        temp.add(outOfBoundIndex);
        DeleteCommands deleteCommand = new DeleteCommands(temp);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ArrayList<Index> temp = new ArrayList<>();
        temp.add(INDEX_SECOND_PERSON);
        temp.add(INDEX_FIRST_PERSON);
        DeleteCommands deleteCommands = new DeleteCommands(temp);

        // same object -> returns true
        assertTrue(deleteCommands.equals(deleteCommands));

        ArrayList<Index> temp2 = new ArrayList<>();
        temp2.add(INDEX_SECOND_PERSON);
        temp2.add(INDEX_FIRST_PERSON);

        // same values -> returns true
        DeleteCommands deleteCommandsCopy = new DeleteCommands(temp2);
        assertTrue(deleteCommands.equals(deleteCommandsCopy));

        // same values but different order -> returns true
        ArrayList<Index> temp3 = new ArrayList<>();
        temp3.add(INDEX_FIRST_PERSON);
        temp3.add(INDEX_SECOND_PERSON);

        DeleteCommands deleteCommandsCopy2 = new DeleteCommands(temp3);
        assertTrue(deleteCommands.equals(deleteCommandsCopy2));

        // different types -> returns false
        assertFalse(deleteCommands.equals(1));

        // null -> returns false
        assertFalse(deleteCommands.equals(null));

        temp3.add(INDEX_THIRD_PERSON);
        DeleteCommands deleteCommandsCopy3 = new DeleteCommands(temp3);

        // different person -> returns false
        assertFalse(deleteCommands.equals(deleteCommandsCopy3));
    }

}
