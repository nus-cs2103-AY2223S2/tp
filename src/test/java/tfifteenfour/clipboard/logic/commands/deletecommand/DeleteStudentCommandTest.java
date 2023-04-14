package tfifteenfour.clipboard.logic.commands.deletecommand;


import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tfifteenfour.clipboard.testutil.Assert.assertThrows;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_FIRST;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_OUT_OF_BOUND;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.testutil.TypicalModel;

class DeleteStudentCommandTest {
    private Model model;
    private Group selectedGroup;
    private Student selectedStudent;
    private CurrentSelection actualSelection;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        selectedGroup = model.getCurrentSelection().getSelectedGroup();
        selectedStudent = model.getCurrentSelection().getSelectedStudent();

        actualSelection = this.model.getCurrentSelection();
        actualSelection.setCurrentPage(PageType.STUDENT_PAGE);
    }

    @Test
    public void execute_validIndex_success() {
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteStudentCommand.MESSAGE_SUCCESS, selectedGroup,
                selectedStudent.getName());
        Model expectedModel = model.copy();
        Group expectedSelectedGroup = expectedModel.getCurrentSelection().getSelectedGroup();
        expectedSelectedGroup.deleteStudent(
                expectedSelectedGroup.getUnmodifiableStudentList().get(INDEX_FIRST.getZeroBased()));

        assertCommandSuccess(deleteStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(INDEX_OUT_OF_BOUND);
        assertThrows(CommandException.class, () -> deleteStudentCommand.execute(model));
    }

    @Test
    public void execute_wrongPage_throwsCommandException() {
        actualSelection.setCurrentPage(PageType.COURSE_PAGE);
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(INDEX_FIRST);
        assertThrows(CommandException.class, () -> deleteStudentCommand.execute(model));
    }

}
