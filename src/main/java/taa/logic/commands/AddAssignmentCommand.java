package taa.logic.commands;

import static java.util.Objects.requireNonNull;

import taa.model.Model;

/**
 * Adds a new assignment of {assignmentName} into AssignmentList.  .
 */
public class AddAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "asgn_add";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "This assignment name already exists.";
    public static final String MESSAGE_SUCCESS = "Assignment Added.";
    private final String toAdd;

    /**
     * @param assignmentName
     */
    public AddAssignmentCommand(String assignmentName) {
        requireNonNull(assignmentName);
        toAdd = assignmentName;
    }

    // TODO: handle exceptions.
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.addAssignment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

}
