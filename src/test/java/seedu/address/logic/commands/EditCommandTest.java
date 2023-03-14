package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalEduMate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.EduMate;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;
import seedu.address.model.person.User;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {
    private static final EditPersonDescriptor EDIT_PERSON_DESCRIPTOR = new EditPersonDescriptor();

    private Model model = new ModelManager(getTypicalEduMate(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(new ContactIndex(INDEX_FIRST_PERSON.getOneBased()), descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new EduMate(model.getEduMate()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());
        ContactIndex contactIndex = lastPerson.getContactIndex();
        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withName(NAME_BEN).withPhone(PHONE_BEN)
                .withGroupTags(VALID_GROUP_1).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(NAME_BEN)
                .withPhone(PHONE_BEN).withGroupTags(VALID_GROUP_1).build();
        EditCommand editCommand = new EditCommand(contactIndex, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new EduMate(model.getEduMate()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(new ContactIndex(INDEX_FIRST_PERSON.getOneBased()),
                new EditPersonDescriptor());
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new EduMate(model.getEduMate()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nullIndex_success() {
        EditCommand editCommand = new EditCommand(null, new EditPersonDescriptor());
        User originalUser = model.getUser();

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_USER_SUCCESS, originalUser);

        Model expectedModel = new ModelManager(new EduMate(model.getEduMate()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);

        model.setUser(originalUser);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(NAME_BEN)
                .build();
        EditCommand editCommand = new EditCommand(new ContactIndex(INDEX_FIRST_PERSON.getOneBased()),
                new EditPersonDescriptorBuilder().withName(NAME_BEN).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new EduMate(model.getEduMate()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        EditCommand editCommand = new EditCommand(new ContactIndex(INDEX_SECOND_PERSON.getOneBased()), descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Person personInList = model.getEduMate().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(new ContactIndex(INDEX_FIRST_PERSON.getOneBased()),
                new EditPersonDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(NAME_BEN).build();
        EditCommand editCommand = new EditCommand(new ContactIndex(1000), descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
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
        assertTrue(outOfBoundIndex.getZeroBased() < model.getEduMate().getPersonList().size());

        EditCommand editCommand = new EditCommand(new ContactIndex(1000),
                new EditPersonDescriptorBuilder().withName(NAME_BEN).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(new ContactIndex(INDEX_FIRST_PERSON.getOneBased()),
                DESC_ALEX);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_ALEX);
        EditCommand commandWithSameValues = new EditCommand(new ContactIndex(INDEX_FIRST_PERSON.getOneBased()),
                copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(new ContactIndex(INDEX_ALEX),
                DESC_ALEX)));
        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(new ContactIndex(INDEX_FIRST_PERSON.getOneBased()),
                DESC_BEN)));
    }

    @Test
    public void equals_sameObject_true() {
        assertEquals(EDIT_PERSON_DESCRIPTOR, EDIT_PERSON_DESCRIPTOR);
    }

    @Test
    public void equals_notEditCommand_false() {
        assertNotEquals(EDIT_PERSON_DESCRIPTOR, 34);
    }

    @Test
    public void isAnyFieldEdited_noneEdited_false() {
        assertFalse(EDIT_PERSON_DESCRIPTOR.isAnyFieldEdited());
    }

}
