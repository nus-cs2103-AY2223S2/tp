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
    public static final String MESSAGE_SUCCESS = "%1$s of %2$s have been cleared!";

    public ClearCommand() {
        super(true);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CurrentSelection currentSelection = model.getCurrentSelection();

        PageType currentPage = currentSelection.getCurrentPage();
        Course selectedCourse = currentSelection.getSelectedCourse();
        Group selectedGroup = currentSelection.getSelectedGroup();

        String clearedObject;
        String section;

        switch (currentPage) {
        case COURSE_PAGE:
            clearedObject = "Courses";
            section = "CLIpboard";
            model.setRoster(new Roster());
            break;
        case GROUP_PAGE:
            clearedObject = "Groups";
            section = selectedCourse.getCourseCode();
            List<Group> groups = selectedCourse.getModifiableGroupList();
            while (!groups.isEmpty()) {
                groups.remove(0);
            }
            break;
        case SESSION_PAGE:
            clearedObject = "Sessions";
            section = selectedGroup.getGroupName();
            List<Session> sessions = selectedGroup.getModifiableSessionList();
            while (!sessions.isEmpty()) {
                sessions.remove(0);
            }
            break;
        case TASK_PAGE:
            clearedObject = "Tasks";
            section = selectedGroup.getGroupName();
            List<Task> tasks = selectedGroup.getModifiableTaskList();
            while (!tasks.isEmpty()) {
                tasks.remove(0);
            }
            break;
        case STUDENT_PAGE:
            clearedObject = "Students";
            section = selectedGroup.getGroupName();
            List<Student> students = selectedGroup.getModifiableStudentList();
            while (!students.isEmpty()) {
                students.remove(0);
            }
            break;
        case TASK_STUDENT_PAGE:
            throw new CommandException("Can't clear current page!\n"
                    + "Unselect a task by 'back' to clear tasks.\n");
        case SESSION_STUDENT_PAGE:
            throw new CommandException("Can't clear current page!\n"
                    + "Unselect a session by 'back' to clear sessions.\n");
        default:
            // Should be unreachable
            throw new CommandException("Can't clear current page!\n"
                    + "Navigate to the following pages to use clear command:\n"
                    + "Course page, Group page, Session page, Task page, Student page");
        }
        return new CommandResult(this,
                String.format(MESSAGE_SUCCESS, clearedObject, section), willModifyState);
    }
}
