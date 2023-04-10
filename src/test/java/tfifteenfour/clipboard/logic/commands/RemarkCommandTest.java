package tfifteenfour.clipboard.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tfifteenfour.clipboard.testutil.Assert.assertThrows;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_FIRST;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_OUT_OF_BOUND;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.student.Remark;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.testutil.StudentBuilder;
import tfifteenfour.clipboard.testutil.TypicalModel;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
class RemarkCommandTest {
    private static final Remark REMARK_STUB = new Remark("Some remark");
    private Model model;
    private Student selectedStudent;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        this.model.getCurrentSelection().setCurrentPage(PageType.TASK_STUDENT_PAGE);
        selectedStudent = model.getCurrentSelection().getSelectedStudent();
    }


    @Test
    public void execute_validIndexUnfilteredList_success() {

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST, REMARK_STUB);

        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS,
                selectedStudent.getName().fullName, REMARK_STUB.value);

        Model expectedModel = model.copy();

        Group expectedSelectedGroup = expectedModel.getCurrentSelection().getSelectedGroup();
        expectedSelectedGroup.setStudent(selectedStudent,
                new StudentBuilder(selectedStudent).withRemark(REMARK_STUB.value).build());

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        RemarkCommand remarkCommand = new RemarkCommand(INDEX_OUT_OF_BOUND, REMARK_STUB);
        assertThrows(CommandException.class, () -> remarkCommand.execute(model));
    }

    @Test
    void equals() {
        RemarkCommand remarkCommand1 = new RemarkCommand(INDEX_FIRST, REMARK_STUB);
        RemarkCommand remarkCommand2 = new RemarkCommand(INDEX_FIRST, REMARK_STUB);
        RemarkCommand remarkCommand3 = new RemarkCommand(INDEX_SECOND, REMARK_STUB);
        SelectCommand selectCommand = new SelectCommand(INDEX_SECOND);

        // Same object -> returns true
        assertEquals(remarkCommand1, remarkCommand1);

        // Same values -> returns true
        assertEquals(remarkCommand1, remarkCommand2);

        // Different types -> returns false
        assertNotEquals(1, remarkCommand1);

        // Different index -> returns false
        assertNotEquals(remarkCommand1, remarkCommand3);

        assertNotEquals(remarkCommand1, selectCommand);
    }

}
