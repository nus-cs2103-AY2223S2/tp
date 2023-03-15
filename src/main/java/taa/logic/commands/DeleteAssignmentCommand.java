package taa.logic.commands;

import static java.util.Objects.requireNonNull;

import taa.model.Model;

/**
 * Deletes an Assignment of {assignmentName} from AssignmentList.
 */
public class DeleteAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "asgn_delete";
    public static final String MESSAGE_INVALID_NAME = "Assignment name not found.";
    public static final String MESSAGE_DELETE_ASSIGNMENT_SUCCESS = "Assignment: %s Deleted.";
    private final String assignmentName;

    public DeleteAssignmentCommand(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.deleteAssignment(assignmentName);
        return new CommandResult(String.format(MESSAGE_DELETE_ASSIGNMENT_SUCCESS, assignmentName));
    }
}
