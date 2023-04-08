package tfifteenfour.clipboard.logic.commands.attendancecommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tfifteenfour.clipboard.testutil.Assert.assertThrows;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_FIRST;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_OUT_OF_BOUND;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.SelectCommand;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.testutil.TypicalModel;

public class SessionCommandTest {
    private Model model;
    private Course selectedCourse;
    private Group selectedGroup;
    private Session selectedSession;
    private Student selectedStudent;
    private CurrentSelection actualSelection;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        this.model.getCurrentSelection().setCurrentPage(PageType.SESSION_STUDENT_PAGE);
        selectedCourse = model.getCurrentSelection().getSelectedCourse();
        selectedGroup = model.getCurrentSelection().getSelectedGroup();
        selectedSession = model.getCurrentSelection().getSelectedSession();
        selectedStudent = model.getCurrentSelection().getSelectedStudent();

        actualSelection = this.model.getCurrentSelection();
        actualSelection.setCurrentPage(PageType.GROUP_PAGE);
    }

    @Test
    public void execute_validIndex_success() {
        SessionCommand sessionCommand = new SessionCommand(INDEX_FIRST);

        String expectedMessage = String.format(SessionCommand.MESSAGE_SUCCESS, selectedGroup);
        Model expectedModel = model.copy();

        assertCommandSuccess(sessionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        SessionCommand sessionCommand = new SessionCommand(INDEX_OUT_OF_BOUND);
        assertThrows(CommandException.class, () -> sessionCommand.execute(model));
    }

    @Test
    public void execute_wrongPage_throwsCommandException() {
        actualSelection.setCurrentPage(PageType.COURSE_PAGE);
        SessionCommand sessionCommand = new SessionCommand(INDEX_FIRST);
        assertThrows(CommandException.class, () -> sessionCommand.execute(model));
    }

    @Test
    public void equals() {
        SessionCommand command1 = new SessionCommand(INDEX_FIRST);
        SessionCommand command2 = new SessionCommand(INDEX_FIRST);
        SessionCommand command3 = new SessionCommand(INDEX_SECOND);

        SelectCommand differentCommand = new SelectCommand(INDEX_FIRST);
        //MarkPresentCommand differentCommand = new MarkPresentCommand(Index.fromZeroBased(0));




        // Test for equality
        assertEquals(command1, command2);
        assertEquals(command2, command1);

        assertNotEquals(command3, command1);
        assertNotEquals(command1, command3);
        assertNotEquals(command1, differentCommand);

    }
    /*
    @Test
    public void equals_sameObject_returnsTrue() {
        SessionCommand sessionCommand = new SessionCommand(Index.fromZeroBased(0));

        assertTrue(sessionCommand.equals(sessionCommand));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        SessionCommand sessionCommand1 = new SessionCommand(Index.fromZeroBased(0));
        SessionCommand sessionCommand2 = new SessionCommand(Index.fromZeroBased(0));

        assertTrue(sessionCommand1.equals(sessionCommand2));
    }

    @Test
    public void equals_differentTypes_returnsFalse() {
        SessionCommand sessionCommand = new SessionCommand(Index.fromZeroBased(0));
        DeleteCommand deleteCommand = new DeleteCommand(Index.fromZeroBased(0));

        assertFalse(sessionCommand.equals(deleteCommand));
    }

    @Test
    public void equals_null_returnsFalse() {
        SessionCommand sessionCommand = new SessionCommand(Index.fromZeroBased(0));

        assertFalse(sessionCommand.equals(null));
    }

    @Test
    public void equals_differentValues_returnsFalse() {
        SessionCommand sessionCommand1 = new SessionCommand(Index.fromZeroBased(0));
        SessionCommand sessionCommand2 = new SessionCommand(Index.fromZeroBased(1));

        assertFalse(sessionCommand1.equals(sessionCommand2));
    }
*/
}

