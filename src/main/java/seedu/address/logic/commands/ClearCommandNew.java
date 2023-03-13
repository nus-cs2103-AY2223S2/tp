package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.ModelNew;
import seedu.address.model.Ultron;

/**
 * Clears the openings list.
 */
public class ClearCommandNew extends CommandNew {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Openings list has been cleared!";


    @Override
    public CommandResultNew execute(ModelNew model) {
        requireNonNull(model);
        model.setUltron(new Ultron());
        // model.setOpeningsList(new OpeningsList());
        return new CommandResultNew(MESSAGE_SUCCESS);
    }
}
