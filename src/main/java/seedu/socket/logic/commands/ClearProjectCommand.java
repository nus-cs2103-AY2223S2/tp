package seedu.socket.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.socket.model.Model;

/**
 * Clears the project list.
 */
public class ClearProjectCommand extends Command {
    public static final String COMMAND_WORD = "clearpj";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears all projects.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Project list has been cleared!";
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setProjects(new ArrayList<>());
        model.commitSocket();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
