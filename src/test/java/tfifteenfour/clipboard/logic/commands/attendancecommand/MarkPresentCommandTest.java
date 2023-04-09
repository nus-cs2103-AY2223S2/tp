package tfifteenfour.clipboard.logic.commands.attendancecommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tfifteenfour.clipboard.testutil.Assert.assertThrows;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_FIRST;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_OUT_OF_BOUND;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_SECOND;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.student.StudentWithAttendance;
import tfifteenfour.clipboard.testutil.TypicalModel;

public class MarkPresentCommandTest {
    private Model model;
    private Session selectedSession;
    private CurrentSelection actualSelection;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        this.model.getCurrentSelection().setCurrentPage(PageType.SESSION_STUDENT_PAGE);
        selectedSession = model.getCurrentSelection().getSelectedSession();

        actualSelection = this.model.getCurrentSelection();
        actualSelection.setCurrentPage(PageType.SESSION_STUDENT_PAGE);
    }

    @Test
    public void execute_validSingleIndex_success() {
        List<StudentWithAttendance> lastShownList = selectedSession.getUnmodifiableStudentList();
        Student firstStudent = lastShownList.get(INDEX_FIRST.getZeroBased());
        MarkPresentCommand markCommand = new MarkPresentCommand(INDEX_FIRST);

        String expectedMessage = String.format(MarkPresentCommand.MESSAGE_SUCCESS,
                selectedSession, firstStudent.getName());
        Model expectedModel = model.copy();

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validMultipleIndex_success() {
        List<StudentWithAttendance> lastShownList = selectedSession.getUnmodifiableStudentList();
        Index[] indices = new Index[]{INDEX_FIRST, INDEX_SECOND};
        MarkPresentCommand markCommand = new MarkPresentCommand(indices);

        Student firstStudent = lastShownList.get(INDEX_FIRST.getZeroBased());
        Student secondStudent = lastShownList.get(INDEX_SECOND.getZeroBased());


        String expectedMessage = String.format(MarkPresentCommand.MESSAGE_SUCCESS,
                selectedSession, firstStudent.getName() + ", " + secondStudent.getName());
        Model expectedModel = model.copy();

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_onSessionPage_throwCommandException() {
        actualSelection.setCurrentPage(PageType.SESSION_PAGE);
        MarkPresentCommand markCommand = new MarkPresentCommand(INDEX_FIRST);
        assertThrows(CommandException.class, () -> markCommand.execute(model));
    }

    @Test
    public void execute_onGroupPage_throwCommandException() {
        actualSelection.setCurrentPage(PageType.GROUP_PAGE);
        MarkPresentCommand markCommand = new MarkPresentCommand(INDEX_FIRST);
        assertThrows(CommandException.class, () -> markCommand.execute(model));
    }

    @Test
    public void execute_indexOutOfBound_throwCommandException() {
        actualSelection.setCurrentPage(PageType.SESSION_STUDENT_PAGE);
        MarkPresentCommand markCommand = new MarkPresentCommand(INDEX_OUT_OF_BOUND);
        assertThrows(CommandException.class, () -> markCommand.execute(model));
    }

    @Test
    public void equals() {
        Index[] indexArray1 = new Index[] {Index.fromOneBased(1), Index.fromOneBased(2)};
        Index[] indexArray2 = new Index[] {Index.fromOneBased(2), Index.fromOneBased(1)};
        Index[] indexArray3 = new Index[] {Index.fromOneBased(1), Index.fromOneBased(2), Index.fromOneBased(3)};
        Index[] indexArray4 = new Index[] {Index.fromOneBased(1)};

        MarkPresentCommand command1 = new MarkPresentCommand(indexArray1);
        MarkPresentCommand command2 = new MarkPresentCommand(indexArray2);
        MarkPresentCommand command3 = new MarkPresentCommand(indexArray3);
        MarkPresentCommand command4 = new MarkPresentCommand(indexArray4);

        MarkAbsentCommand command5 = new MarkAbsentCommand(indexArray1);

        // Test for equality
        assertEquals(command1, command2);
        assertEquals(command2, command1);
        assertNotEquals(command1, command3);
        assertNotEquals(command3, command1);
        assertNotEquals(command1, command4);
        assertNotEquals(command4, command1);
        assertNotEquals(command1, command5);

    }


}
