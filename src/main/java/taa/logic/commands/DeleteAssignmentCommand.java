package taa.logic.commands;

import static java.util.Objects.requireNonNull;

import taa.logic.commands.exceptions.CommandException;
import taa.model.Model;
import taa.model.assignment.exceptions.AssignmentException;

/**
 * Deletes an Assignment of {assignmentName} from AssignmentList.
 */
public class DeleteAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "delete_asgn";

    public static final String MESSAGE_USAGE = "Format: delete_asgn n/{name}";
    public static final String MESSAGE_DELETE_ASSIGNMENT_SUCCESS = "Assignment: %s Deleted.";
    private final String assignmentName;

    public DeleteAssignmentCommand(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            model.deleteAssignment(assignmentName);
        } catch (AssignmentException e) {
            throw new CommandException(String.format(
                "An error occurred when deleting assignment %s:\n%s",
                assignmentName,
                e.getMessage()));
        }

        return new CommandResult(String.format(MESSAGE_DELETE_ASSIGNMENT_SUCCESS, assignmentName));
    }
}
