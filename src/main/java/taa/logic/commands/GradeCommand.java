package taa.logic.commands;

import taa.logic.commands.exceptions.CommandException;
import taa.model.Model;

import static java.util.Objects.requireNonNull;

/**
 * Grades a student submission of an assignment.
 */
public class GradeCommand extends Command {

    public static final String COMMAND_WORD = "grade";

    public static final String MESSAGE_USAGE = "Format: grade n/{assignmentName} i/{studentId} m/{marks}";

    public static final String MESSAGE_SUCCESS = "Assignment %s, submission from student %d graded: %d marks";
    private final String assignmentName;
    private final int studentId;
    private final int marks;

    /**
     * @param assignmentName
     * @param studentId
     * @param marks
     */
    public GradeCommand(String assignmentName, int studentId, int marks) {
        this.assignmentName = assignmentName;
        this.studentId = studentId;
        this.marks = marks;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException{
        requireNonNull(model);
        model.grade(assignmentName, studentId, marks);
        return new CommandResult(String.format(MESSAGE_SUCCESS,assignmentName,studentId,marks));
    }
}
