package expresslibrary.logic.commands;

import static expresslibrary.logic.commands.CommandTestUtil.DESC_AMY;
import static expresslibrary.logic.commands.CommandTestUtil.DESC_BOB;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static expresslibrary.logic.commands.CommandTestUtil.assertCommandFailure;
import static expresslibrary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static expresslibrary.logic.commands.CommandTestUtil.showPersonAtIndex;
import static expresslibrary.testutil.TypicalExpressLibrary.getTypicalExpressLibrary;
import static expresslibrary.testutil.TypicalIndexes.INDEX_FIRST;
import static expresslibrary.testutil.TypicalIndexes.INDEX_SECOND;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import expresslibrary.commons.core.Messages;
import expresslibrary.commons.core.index.Index;
import expresslibrary.logic.commands.EditPersonCommand.EditPersonDescriptor;
import expresslibrary.model.ExpressLibrary;
import expresslibrary.model.Model;
import expresslibrary.model.ModelManager;
import expresslibrary.model.UserPrefs;
import expresslibrary.model.person.Person;
import expresslibrary.testutil.EditPersonDescriptorBuilder;
import expresslibrary.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * EditCommand.
 */
public class EditPersonCommandTest {

    private Model model = new ModelManager(getTypicalExpressLibrary(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditPersonCommand editPersonCommand = new EditPersonCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new ExpressLibrary(model.getExpressLibrary()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editPersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditPersonCommand editPersonCommand = new EditPersonCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new ExpressLibrary(model.getExpressLibrary()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editPersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditPersonCommand editPersonCommand = new EditPersonCommand(INDEX_FIRST, new EditPersonDescriptor());
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new ExpressLibrary(model.getExpressLibrary()), new UserPrefs());

        assertCommandSuccess(editPersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        EditPersonCommand editCommand = new EditPersonCommand(INDEX_FIRST,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new ExpressLibrary(model.getExpressLibrary()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        EditPersonCommand editPersonCommand = new EditPersonCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editPersonCommand, model, EditPersonCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST);

        // edit person in filtered list into a duplicate in express library
        Person personInList = model.getExpressLibrary().getPersonList().get(INDEX_SECOND.getZeroBased());
        EditPersonCommand editPersonCommand = new EditPersonCommand(INDEX_FIRST,
                new EditPersonDescriptorBuilder(personInList).build());

        assertCommandFailure(editPersonCommand, model, EditPersonCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditPersonCommand editPersonCommand = new EditPersonCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editPersonCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of express library
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of express library list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getExpressLibrary().getPersonList().size());

        EditPersonCommand editPersonCommand = new EditPersonCommand(outOfBoundIndex,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editPersonCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditPersonCommand standardCommand = new EditPersonCommand(INDEX_FIRST, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditPersonCommand commandWithSameValues = new EditPersonCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditPersonCommand(INDEX_SECOND, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditPersonCommand(INDEX_FIRST, DESC_BOB)));
    }

}
