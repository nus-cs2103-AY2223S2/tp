package tfifteenfour.clipboard.logic.commands;

import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tfifteenfour.clipboard.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.Roster;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.testutil.TypicalModel;

public class ClearCommandTest {
    private Model model;
    private Model expectedModel;
    private Course selectedCourse;
    private Group selectedGroup;
    private Session selectedSession;
    private Student selectedStudent;
    private CurrentSelection actualSelection;
    private String clearedObject;
    private String section;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        expectedModel = model.copy();
        selectedCourse = model.getCurrentSelection().getSelectedCourse();
        selectedGroup = model.getCurrentSelection().getSelectedGroup();
        selectedSession = model.getCurrentSelection().getSelectedSession();
        selectedStudent = model.getCurrentSelection().getSelectedStudent();
        clearedObject = "";
        section = "";

        actualSelection = this.model.getCurrentSelection();
    }

    @Test
    public void execute_clearCourses_success() {
        actualSelection.setCurrentPage(PageType.COURSE_PAGE);
        expectedModel.setRoster(new Roster());

        clearedObject = "Courses";
        section = "CLIpboard";
        String expectedMessage = String.format(ClearCommand.MESSAGE_SUCCESS,
                clearedObject, section);

        assertCommandSuccess(new ClearCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_clearGroups_success() {
        actualSelection.setCurrentPage(PageType.GROUP_PAGE);
        selectedCourse = actualSelection.getSelectedCourse();

        clearedObject = "Groups";
        section = selectedCourse.getCourseCode();
        String expectedMessage = String.format(ClearCommand.MESSAGE_SUCCESS, clearedObject, section);

        assertCommandSuccess(new ClearCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_clearSessions_success() {
        actualSelection.setCurrentPage(PageType.SESSION_PAGE);
        selectedGroup = actualSelection.getSelectedGroup();

        clearedObject = "Sessions";
        section = selectedGroup.getGroupName();
        String expectedMessage = String.format(ClearCommand.MESSAGE_SUCCESS, clearedObject, section);

        assertCommandSuccess(new ClearCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_clearTasks_success() {
        actualSelection.setCurrentPage(PageType.TASK_PAGE);
        selectedGroup = actualSelection.getSelectedGroup();

        clearedObject = "Tasks";
        section = selectedGroup.getGroupName();
        String expectedMessage = String.format(ClearCommand.MESSAGE_SUCCESS, clearedObject, section);

        assertCommandSuccess(new ClearCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_clearStudents_success() {
        actualSelection.setCurrentPage(PageType.STUDENT_PAGE);
        selectedGroup = actualSelection.getSelectedGroup();

        clearedObject = "Students";
        section = selectedGroup.getGroupName();
        String expectedMessage = String.format(ClearCommand.MESSAGE_SUCCESS, clearedObject, section);

        assertCommandSuccess(new ClearCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_clearTaskStudentPage_throwsCommandException() {
        actualSelection.setCurrentPage(PageType.TASK_STUDENT_PAGE);

        assertThrows(CommandException.class, () -> new ClearCommand().execute(model));
    }

    @Test
    public void execute_clearSessionStudentPage_throwsCommandException() {
        actualSelection.setCurrentPage(PageType.SESSION_STUDENT_PAGE);

        assertThrows(CommandException.class, () -> new ClearCommand().execute(model));

    }

    @Test
    public void execute_clearInvalidPage_throwsCommandException() {
        actualSelection.setCurrentPage(PageType.INVALID_PAGE);

        assertThrows(CommandException.class, () -> new ClearCommand().execute(model));

    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClearCommand().execute(null));
    }


}
