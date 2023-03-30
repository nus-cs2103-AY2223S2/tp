package tfifteenfour.clipboard.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.Roster;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.task.Task;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";

    public ClearCommand() {
        super(false);
    }

    @Override
    public CommandResult execute(Model model, CurrentSelection currentSelection) throws CommandException {
        requireNonNull(model);

        PageType currentPage = currentSelection.getCurrentPage();
        Course selectedCourse = currentSelection.getSelectedCourse();
        Group selectedGroup = currentSelection.getSelectedGroup();




        switch (currentPage) {
        case COURSE_PAGE:
            model.setRoster(new Roster());
            break;
        case GROUP_PAGE:
            List<Group> groups = selectedCourse.getModifiableGroupList();
            while (!groups.isEmpty()) {
                groups.remove(0);
            }
            break;
        case SESSION_PAGE:
            List<Session> sessions = selectedGroup.getModifiableSessionList();
            while (!sessions.isEmpty()) {
                sessions.remove(0);
            }
            break;
        case TASK_PAGE:
            List<Task> tasks = selectedGroup.getModifiableTaskList();
            while (!tasks.isEmpty()) {
                tasks.remove(0);
            }
            break;
        case STUDENT_PAGE:
            List<Student> students = selectedGroup.getModifiableStudentList();
            while (!students.isEmpty()) {
                students.remove(0);
            }
            break;
        default:
            throw new CommandException("Can't clear current page!\n"
                    + "Navigate to the following pages to use clear command:"
                    + "Course page, Group page, Session page, Task page, Student page");
        }
        return new CommandResult(this, MESSAGE_SUCCESS, willModifyState);
    }
}
