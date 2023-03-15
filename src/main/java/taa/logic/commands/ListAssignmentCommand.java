package taa.logic.commands;

import static java.util.Objects.requireNonNull;

import taa.model.Model;

/**
 * Command that lists out all assignments.
 */
public class ListAssignmentCommand extends Command {
    public static final String COMMAND_WORD = "list_asgn";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(model.listAssignments());
    }
}
