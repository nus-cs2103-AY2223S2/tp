package taa.logic.commands;

import static java.util.Objects.requireNonNull;

import taa.logic.commands.exceptions.CommandException;
import taa.model.Model;
import taa.model.assignment.exceptions.AssignmentException;

/**
 * Resets the marks and late status of a student submission.
 */
public class UngradeCommand extends Command {

    public static final String COMMAND_WORD = "ungrade";

    public static final String MESSAGE_USAGE = "Format: ungrade n/{assignmentName} i/{studentId}";

    public static final String MESSAGE_SUCCESS = "Assignment %s, submission from student %d ungraded";
    private final String assignmentName;
    private final int studentId;

    /**
     * @param assignmentName
     * @param studentId
     */
    public UngradeCommand(String assignmentName, int studentId) {
        this.assignmentName = assignmentName;
        this.studentId = studentId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            model.ungrade(assignmentName, studentId);
        } catch (AssignmentException e) {
            throw new CommandException(
                    String.format(
                            "An error occurred when ungrading assignment %s for student %d:\n",
                            assignmentName, studentId)
                    + e.getMessage());
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, assignmentName, studentId));
    }
}
