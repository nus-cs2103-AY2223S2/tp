package seedu.medinfo.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.medinfo.model.MedInfo;
import seedu.medinfo.model.Model;

/**
 * Clears the medinfo book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "MedInfo has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setMedInfo(new MedInfo());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
