package tfifteenfour.clipboard.logic.commands.taskcommand;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import tfifteenfour.clipboard.commons.core.Messages;
import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.Command;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.student.StudentWithGrades;
import tfifteenfour.clipboard.model.task.Task;

/**
 * Command to assign a grade to student for a task.
 */
public class AssignCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Assigns a mark to the selected student. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "+ MARK_TO_ASSIGN (must be a positive integer between 0 and 100)\n"
            + "Example: " + COMMAND_WORD + " 1" + " 50";

    public static final String MESSAGE_SUCCESS = "Assigned student %2$s with grade for task %1$s";

    private final Index targetIndex;
    private final int grade;

    /**
     * Creates a AssignCommand to assign a mark to the student for the current task.
     */
    public AssignCommand(Index targetIndex, int grade) {
        super(true);
        this.targetIndex = targetIndex;
        this.grade = grade;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CurrentSelection currentSelection = model.getCurrentSelection();

        if (currentSelection.getCurrentPage() != PageType.TASK_STUDENT_PAGE) {
            throw new CommandException("Wrong page. Navigate to task page to assign grade");
        }

        Task task = currentSelection.getSelectedTask();
        ObservableList<StudentWithGrades> studentList = task.getUnmodifiableStudentList();
        StringBuilder studentAssigned = new StringBuilder();

        if (targetIndex.getZeroBased() >= studentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } else {
            Student studentToAssign = studentList.get(targetIndex.getZeroBased());
            task.assignGrade(studentToAssign, grade);
            studentAssigned.append(studentToAssign.getName());
        }

        return new CommandResult(this, String.format(MESSAGE_SUCCESS, task, studentAssigned), willModifyState);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AssignCommand)) {
            return false;
        }

        AssignCommand e = (AssignCommand) other;

        return targetIndex == e.targetIndex;
    }
}
