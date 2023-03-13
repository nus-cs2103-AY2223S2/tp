package taa.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import taa.commons.core.Messages;
import taa.commons.core.index.Index;
import taa.model.ClassList;
import taa.model.Model;
import taa.model.ModelManager;
import taa.model.UserPrefs;
import taa.model.student.Student;
import taa.testutil.EditPersonDescriptorBuilder;
import taa.testutil.PersonBuilder;
import taa.testutil.TypicalIndexes;
import taa.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Student editedStudent = new PersonBuilder().build();
        EditCommand.EditStudentDescriptor descriptor = new EditPersonDescriptorBuilder(editedStudent).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new ClassList(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredStudentList().size());
        Student lastStudent = model.getFilteredStudentList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastStudent);
        Student editedStudent = personInList.withName(CommandTestUtil.VALID_NAME_BOB)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();

        EditCommand.EditStudentDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_BOB)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new ClassList(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(lastStudent, editedStudent);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                new EditCommand.EditStudentDescriptor());
        Student editedStudent = model.getFilteredStudentList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new ClassList(model.getAddressBook()), new UserPrefs());

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Student studentInFilteredList = model.getFilteredStudentList()
                .get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        Student editedStudent = new PersonBuilder(studentInFilteredList)
                .withName(CommandTestUtil.VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new ClassList(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Student firstStudent = model.getFilteredStudentList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        EditCommand.EditStudentDescriptor descriptor = new EditPersonDescriptorBuilder(firstStudent).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_SECOND_PERSON, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        // edit student in filtered list into a duplicate in address book
        Student studentInList = model.getAddressBook().getStudentList()
                .get(TypicalIndexes.INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder(studentInList).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        EditCommand.EditStudentDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);
        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditPersonDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                CommandTestUtil.DESC_AMY);

        // same values -> returns true
        EditCommand.EditStudentDescriptor copyDescriptor = new EditCommand
                .EditStudentDescriptor(CommandTestUtil.DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(TypicalIndexes.INDEX_SECOND_PERSON,
                CommandTestUtil.DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                CommandTestUtil.DESC_BOB)));
    }

}
