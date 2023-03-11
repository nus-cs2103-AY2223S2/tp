package seedu.patientist.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.patientist.model.Model;
import seedu.patientist.model.Patientist;

/**
 * Clears the patientist book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Patientist has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setPatientist(new Patientist());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
