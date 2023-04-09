package tfifteenfour.clipboard.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_FIRST;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.task.Task;
import tfifteenfour.clipboard.testutil.TypicalModel;

class SelectCommandTest {
    private Model model;
    private Course selectedCourse;
    private Group selectedGroup;
    private Session selectedSession;
    private Student selectedStudent;
    private Task selectedTask;
    private CurrentSelection actualSelection;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        this.model.getCurrentSelection().setCurrentPage(PageType.SESSION_STUDENT_PAGE);
        selectedCourse = model.getCurrentSelection().getSelectedCourse();
        selectedGroup = model.getCurrentSelection().getSelectedGroup();
        selectedSession = model.getCurrentSelection().getSelectedSession();
        selectedTask = model.getCurrentSelection().getSelectedTask();
        selectedStudent = model.getCurrentSelection().getSelectedStudent();

        actualSelection = this.model.getCurrentSelection();
    }

    @Test
    public void execute_selectCourse_success() {
        actualSelection.setCurrentPage(PageType.COURSE_PAGE);
        SelectCommand selectCommand = new SelectCommand(INDEX_FIRST);

        String expectedMessage = String.format(SelectCommand.MESSAGE_SUCCESS_COURSE, selectedCourse);
        Model expectedModel = model.copy();

        assertCommandSuccess(selectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_selectGroup_success() {
        actualSelection.setCurrentPage(PageType.GROUP_PAGE);
        SelectCommand selectCommand = new SelectCommand(INDEX_FIRST);

        String expectedMessage = String.format(SelectCommand.MESSAGE_SUCCESS_GROUP, selectedGroup, selectedCourse);
        Model expectedModel = model.copy();

        assertCommandSuccess(selectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_selectSession_success() {
        actualSelection.setCurrentPage(PageType.SESSION_PAGE);
        SelectCommand selectCommand = new SelectCommand(INDEX_FIRST);

        String expectedMessage = String.format(SelectCommand.MESSAGE_SUCCESS_SESSION, selectedSession);
        Model expectedModel = model.copy();

        assertCommandSuccess(selectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_selectTask_success() {
        actualSelection.setCurrentPage(PageType.TASK_PAGE);
        SelectCommand selectCommand = new SelectCommand(INDEX_FIRST);

        String expectedMessage = String.format(SelectCommand.MESSAGE_SUCCESS_TASK, selectedTask);
        Model expectedModel = model.copy();

        assertCommandSuccess(selectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_selectStudent_success() {
        actualSelection.setCurrentPage(PageType.STUDENT_PAGE);
        SelectCommand selectCommand = new SelectCommand(INDEX_FIRST);

        String expectedMessage = String.format(SelectCommand.MESSAGE_SUCCESS_STUDENT, selectedStudent);
        Model expectedModel = model.copy();

        assertCommandSuccess(selectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        // same object -> returns true
        SelectCommand selectCommand = new SelectCommand(INDEX_FIRST);
        assertEquals(selectCommand, selectCommand);

        // same values -> returns true
        SelectCommand selectCommandCopy = new SelectCommand(INDEX_FIRST);
        assertEquals(selectCommand, selectCommandCopy);

        // different types -> returns false
        assertNotEquals(1, selectCommand);

        // null -> returns false
        assertNotEquals(null, selectCommand);

        // different index -> returns false
        SelectCommand selectCommandDiffIndex = new SelectCommand(INDEX_SECOND);
        assertNotEquals(selectCommand, selectCommandDiffIndex);
    }

}
