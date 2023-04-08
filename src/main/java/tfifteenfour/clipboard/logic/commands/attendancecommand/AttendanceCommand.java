package tfifteenfour.clipboard.logic.commands.attendancecommand;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.Command;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.SessionWithAttendance;
import tfifteenfour.clipboard.model.student.Student;


/**
 * Command that allows the user to view the sessions of a selected group
 */
public class AttendanceCommand extends Command {
    public static final String COMMAND_WORD = "attendance";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the overall attendance of current session or student.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS_SESSION = "Attendance for %s %s %s:\n %d / %d";
    public static final String MESSAGE_SUCCESS_STUDENT = "Attendance for %s in %s %s:\n %d / %d";

    public AttendanceCommand() {
        super(true);
    }

    /**
     * Executes the command to select a group and view its sessions
     *
     * @param model the model to execute the command on
     * @return a CommandResult indicating the result of the command
     * @throws CommandException if there is an error executing the command
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CurrentSelection currentSelection = model.getCurrentSelection();

        if (currentSelection.getCurrentPage() == PageType.SESSION_PAGE) {
            throw new CommandException("Please select a session to view overall attendance.");
        } else if (currentSelection.getCurrentPage() == PageType.STUDENT_PAGE
                && currentSelection.getSelectedStudent().equals(CurrentSelection.NON_EXISTENT_STUDENT)) {
            throw new CommandException("Please select a student to view his / her overall attendance.");
        } else if (currentSelection.getCurrentPage() != PageType.SESSION_STUDENT_PAGE
                && currentSelection.getCurrentPage() != PageType.STUDENT_PAGE) {
            throw new CommandException("Wrong page! \n"
                    + "Navigate to session page and select a session to view overall attendance of the session.\n"
                    + "Navigate to student page and select a student to view overall attendance of the student.");
        }

        Course course = currentSelection.getSelectedCourse();
        Group group = currentSelection.getSelectedGroup();


        if (currentSelection.getCurrentPage() == PageType.SESSION_STUDENT_PAGE) {
            Session session = currentSelection.getSelectedSession();
            Map<Student, Integer> attendance = session.getAttendance();

            int numOfTotalStudents = attendance.keySet().size();
            int numOfPresentStudents = (int) attendance.values().stream().filter(x -> x == 1).count();

            return new CommandResult(this, String.format(
                    MESSAGE_SUCCESS_SESSION,
                    course, group, session, numOfPresentStudents, numOfTotalStudents), willModifyState);
        } else if (currentSelection.getCurrentPage() == PageType.STUDENT_PAGE) {
            Student student = currentSelection.getSelectedStudent();
            List<Session> sessions = group.getUnmodifiableSessionList();
            int numOfTotalSession = sessions.size();
            int numOfAttendedSession = 0;
            List<SessionWithAttendance> sessionsWithAttendance = new ArrayList<>();

            for (Session session : sessions) {
                if (session.getAttendance().get(student) == 1) {
                    numOfAttendedSession++;
                    sessionsWithAttendance.add(new SessionWithAttendance(session, 1));
                } else {
                    sessionsWithAttendance.add(new SessionWithAttendance(session, 0));
                }
            }

            student.setSessionAttendanceList(sessionsWithAttendance);

            return new CommandResult(this, String.format(
                    MESSAGE_SUCCESS_STUDENT,
                    student.getName() , course, group, numOfAttendedSession, numOfTotalSession), willModifyState);
        } else {
            throw new CommandException("should not reach here!");
        }
    }

}
