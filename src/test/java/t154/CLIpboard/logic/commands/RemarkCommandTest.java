package t154.CLIpboard.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static t154.CLIpboard.logic.commands.CommandTestUtil.assertCommandFailure;
import static t154.CLIpboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static t154.CLIpboard.logic.commands.CommandTestUtil.showStudentAtIndex;
import static t154.CLIpboard.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static t154.CLIpboard.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static t154.CLIpboard.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import t154.CLIpboard.commons.core.Messages;
import t154.CLIpboard.commons.core.index.Index;
import t154.CLIpboard.model.AddressBook;
import t154.CLIpboard.model.Model;
import t154.CLIpboard.model.ModelManager;
import t154.CLIpboard.model.UserPrefs;
import t154.CLIpboard.model.student.Remark;
import t154.CLIpboard.model.student.Student;
import t154.CLIpboard.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
class RemarkCommandTest {
    private static final String REMARK_STUB = "Some remark";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_addRemarkUnfilteredList_success() {
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student editedStudent = new StudentBuilder(firstStudent).withRemark(REMARK_STUB).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_PERSON,
                new Remark(editedStudent.getRemark().value));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addRemarkFilteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON);

        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student editedStudent = new StudentBuilder(model.getFilteredStudentList()
                .get(INDEX_FIRST_PERSON.getZeroBased())).withRemark(REMARK_STUB).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_PERSON,
                new Remark(editedStudent.getRemark().value));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        RemarkCommand remarkCommand = new RemarkCommand(outOfBoundIndex, new Remark(REMARK_STUB));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());

        RemarkCommand remarkCommand = new RemarkCommand(outOfBoundIndex, new Remark(REMARK_STUB));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_deleteRemarkUnfilteredList_success() {
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student editedStudent = new StudentBuilder(firstStudent).withRemark("").build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_PERSON,
                new Remark(editedStudent.getRemark().value));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_DELETE_REMARK_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteRemarkFilteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON);

        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student editedStudent = new StudentBuilder(model.getFilteredStudentList()
                .get(INDEX_FIRST_PERSON.getZeroBased())).withRemark("").build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_PERSON,
                new Remark(editedStudent.getRemark().value));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_DELETE_REMARK_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        Remark remark = new Remark(REMARK_STUB);
        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_PERSON, remark);

        // same object -> returns true
        assertTrue(remarkCommand.equals(remarkCommand));

        // same index and remark -> returns true
        RemarkCommand remarkCommandCopy = new RemarkCommand(INDEX_FIRST_PERSON, remark);
        assertTrue(remarkCommand.equals(remarkCommandCopy));

        // same index but different remark -> returns false
        assertFalse(remarkCommand.equals(new RemarkCommand(INDEX_FIRST_PERSON, new Remark("Different remark"))));

        // different index but same remark -> returns false
        assertFalse(remarkCommand.equals(new RemarkCommand(INDEX_SECOND_PERSON, remark)));

        // different types -> returns false
        assertFalse(remarkCommand.equals(1));

        // null -> returns false
        assertFalse(remarkCommand.equals(null));

        // different index -> returns false
        RemarkCommand differentIndexRemarkCommand = new RemarkCommand(INDEX_SECOND_PERSON, remark);
        assertFalse(remarkCommand.equals(differentIndexRemarkCommand));
    }
}
