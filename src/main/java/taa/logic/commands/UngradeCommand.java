package taa.logic.commands;

import taa.logic.commands.exceptions.CommandException;
import taa.model.Model;

import static java.util.Objects.requireNonNull;

/**
 * Grades a student submission of an assignment.
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
        model.ungrade(assignmentName, studentId);
        return new CommandResult(String.format(MESSAGE_SUCCESS, assignmentName, studentId));
    }
}
