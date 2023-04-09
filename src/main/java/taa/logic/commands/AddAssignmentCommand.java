package taa.logic.commands;

import static java.util.Objects.requireNonNull;

import taa.logic.commands.exceptions.CommandException;
import taa.model.Model;
import taa.model.assignment.exceptions.DuplicateAssignmentException;

/**
 * Adds a new assignment of {assignmentName} into AssignmentList.  .
 */
public class AddAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "add_asgn";
    public static final String MESSAGE_SUCCESS = "Assignment %s with total marks %s added.";
    public static final String MESSAGE_USAGE = "Format: asgn_add n/{name} (optional: m/{total marks})";
    private final String toAdd;
    private final int totalMarks;

    /**
     * Constructor for our command
     * @param assignmentName
     * @param totalMarks
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
