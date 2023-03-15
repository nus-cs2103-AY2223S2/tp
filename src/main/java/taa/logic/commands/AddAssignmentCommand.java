package taa.logic.commands;

import static java.util.Objects.requireNonNull;

import taa.logic.commands.exceptions.CommandException;
import taa.model.Model;

/**
 * Adds a new assignment of {assignmentName} into AssignmentList.  .
 */
public class AddAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "asgn_add";

    public static final String MESSAGE_SUCCESS = "Assignment %s added.";

    public static final String MESSAGE_USAGE = "Format: asgn_add n/{name}";
    private final String toAdd;

    /**
     * @param assignmentName
     */
    public AddAssignmentCommand(String assignmentName) {
        requireNonNull(assignmentName);
        toAdd = assignmentName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.addAssignment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
