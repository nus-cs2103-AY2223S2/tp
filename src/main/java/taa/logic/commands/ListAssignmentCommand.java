package taa.logic.commands;

import taa.model.Model;

import static java.util.Objects.requireNonNull;

public class ListAssignmentCommand extends Command {
    public static final String COMMAND_WORD = "list_asgn";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(model.listAssignments());
    }
}
