package tfifteenfour.clipboard.logic.commands.attendancecommand;

import static java.util.Objects.requireNonNull;

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
import tfifteenfour.clipboard.model.student.Student;


/**
 * Command that allows the user to view the sessions of a selected group
 */
public class AttendanceCommand extends Command {
    public static final String COMMAND_WORD = "attendance";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the attendance of current session.\n"
            + "Example: " + COMMAND_WORD;


    public AttendanceCommand() {
        super(false);
    }

    /**
     * Executes the command to select a group and view its sessions
     *
     * @param model the model to execute the command on
     * @param currentSelection the current selection of the user
     * @return a CommandResult indicating the result of the command
     * @throws CommandException if there is an error executing the command
     */
    @Override
    public CommandResult execute(Model model, CurrentSelection currentSelection) throws CommandException {
        requireNonNull(model);

        if (currentSelection.getCurrentPage() == PageType.SESSION_PAGE) {
            throw new CommandException("Please select a session to view overall attendance.");
        } else if (currentSelection.getCurrentPage() != PageType.SESSION_STUDENT_PAGE) {
            throw new CommandException("Wrong page. Navigate to session page and select a session to view attendance.");
        }

        Course course = currentSelection.getSelectedCourse();
        Group group = currentSelection.getSelectedGroup();
        Session session = currentSelection.getSelectedSession();
        Map<Student, Integer> attendance = session.getAttendance();

        int numOfTotalStudents = attendance.keySet().size();
        int numOfPresentStudents = (int) attendance.values().stream().filter(x -> x == 1).count();

        return new CommandResult(this, String.format(
                "Attendance for %s %s %s:\n %d / %d",
                course, group, session, numOfPresentStudents, numOfTotalStudents), willModifyState);
    }

}
