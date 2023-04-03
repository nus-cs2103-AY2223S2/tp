package taa.logic.commands;

import static java.util.Objects.requireNonNull;

import taa.model.ClassList;
import taa.model.Model;

/**
 * Clears the entire class list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "All students have been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTaaData(new ClassList("no name"));
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
