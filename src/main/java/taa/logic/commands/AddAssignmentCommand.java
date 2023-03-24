package taa.logic.commands;

import static java.util.Objects.requireNonNull;

import taa.logic.commands.exceptions.CommandException;
import taa.model.Model;

/**
 * Adds a new assignment of {assignmentName} into AssignmentList.  .
 */
public class AddAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "add_asgn";
    public static final String MESSAGE_SUCCESS = "Assignment %s added.";
    public static final String MESSAGE_USAGE = "Format: asgn_add n/{name} (optional: m/{marks})";
    private final String toAdd;
    private final int marks;

    /**
     * @param assignmentName
     */
    public AddAssignmentCommand(String assignmentName, int marks) {
        requireNonNull(assignmentName);
        toAdd = assignmentName;
        this.marks = marks;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.addAssignment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
