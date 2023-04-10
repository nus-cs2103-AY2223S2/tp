package tfifteenfour.clipboard.logic.commands.attendancecommand;

import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tfifteenfour.clipboard.testutil.Assert.assertThrows;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.testutil.TypicalModel;

public class AttendanceCommandTest {
    private Model model;
    private Course selectedCourse;
    private Group selectedGroup;
    private Session selectedSession;
    private Student selectedStudent;
    private CurrentSelection actualSelection;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        selectedCourse = model.getCurrentSelection().getSelectedCourse();
        selectedGroup = model.getCurrentSelection().getSelectedGroup();
        selectedSession = model.getCurrentSelection().getSelectedSession();
        selectedStudent = model.getCurrentSelection().getSelectedStudent();

        actualSelection = this.model.getCurrentSelection();
        actualSelection.setCurrentPage(PageType.SESSION_STUDENT_PAGE);
    }

    @Test
    public void execute_attendancePage_returnsCommandResult() throws CommandException {
        AttendanceCommand attendanceCommand = new AttendanceCommand();

        Map<Student, Integer> attendance = selectedSession.getAttendance();
        int numOfTotalStudents = attendance.keySet().size();
        int numOfPresentStudents = (int) attendance.values().stream().filter(x -> x == 1).count();

        String expectedMessage = String.format(AttendanceCommand.MESSAGE_SUCCESS_SESSION,
                selectedCourse, selectedGroup, selectedSession, numOfPresentStudents, numOfTotalStudents);
        Model expectedModel = model.copy();

        assertCommandSuccess(attendanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_studentPage_returnsCommandResult() throws CommandException {
        actualSelection.setCurrentPage(PageType.STUDENT_PAGE);
        AttendanceCommand attendanceCommand = new AttendanceCommand();

        List<Session> sessions = selectedGroup.getUnmodifiableSessionList();
        int numOfTotalSession = sessions.size();
        int numOfAttendedSession = 0;

        for (Session session : sessions) {
            if (session.getAttendance().get(selectedStudent) == 1) {
                numOfAttendedSession++;
            }
        }

        String expectedMessage = String.format(AttendanceCommand.MESSAGE_SUCCESS_STUDENT,
                selectedStudent.getName(), selectedCourse, selectedGroup, numOfAttendedSession, numOfTotalSession);
        Model expectedModel = model.copy();

        assertCommandSuccess(attendanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noSelectedStudent_throwCommandException() {
        actualSelection.setCurrentPage(PageType.STUDENT_PAGE);
        actualSelection.emptySelectedStudent();

        AttendanceCommand command = new AttendanceCommand();
        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void execute_onGroupPage_throwCommandException() {
        actualSelection.setCurrentPage(PageType.GROUP_PAGE);
        actualSelection.emptySelectedStudent();

        AttendanceCommand command = new AttendanceCommand();
        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void execute_onSessionPage_throwCommandException() {
        actualSelection.setCurrentPage(PageType.SESSION_PAGE);
        AttendanceCommand command = new AttendanceCommand();
        assertThrows(CommandException.class, () -> command.execute(model));
    }
}
