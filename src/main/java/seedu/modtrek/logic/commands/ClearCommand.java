package seedu.modtrek.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.modtrek.model.DegreeProgression;
import seedu.modtrek.model.Model;

/**
 * Clears the degree progression
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Degree Progress has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setDegreeProgression(new DegreeProgression());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
