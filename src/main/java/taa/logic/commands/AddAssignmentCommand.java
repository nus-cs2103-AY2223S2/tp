package taa.logic.commands;

import static java.util.Objects.requireNonNull;

import taa.assignment.exceptions.DuplicateAssignmentException;
import taa.logic.commands.exceptions.CommandException;
import taa.model.Model;

/**
 * Adds a new assignment of {assignmentName} into AssignmentList.  .
 */
public class AddAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "add_asgn";
    public static final String MESSAGE_SUCCESS = "Assignment %s with %s marks added.";
    public static final String MESSAGE_USAGE = "Format: asgn_add n/{name} (optional: m/{marks}) "
            + "(optional: cl/{class_list}";
    private final String toAdd;
    private final int totalMarks;

    /**
     * @param assignmentName
     */
    public AddAssignmentCommand(String assignmentName, int totalMarks) {
        requireNonNull(assignmentName);
        toAdd = assignmentName;
        this.totalMarks = totalMarks;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            model.addAssignment(toAdd, totalMarks);
        } catch (DuplicateAssignmentException e) {
            throw new CommandException(e.getMessage());
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd, totalMarks));
    }
}
