package seedu.fitbook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.fitbook.model.FitBookExerciseRoutine;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.client.Client;

/**
 * Clears the FitBook routine list.
 */
public class ClearRoutinesCommand extends Command {

    public static final String COMMAND_WORD = "clearRoutines";
    public static final String MESSAGE_SUCCESS = "Routines in FitBook has been cleared!";

    @Override
    public CommandResult execute(FitBookModel model) {
        requireNonNull(model);
        model.setFitBookExerciseRoutine(new FitBookExerciseRoutine());
        updateClientExercise(model);
        return new CommandResult((MESSAGE_SUCCESS),
                null, false, false, false, true, false);
    }

    /**
     * Updates the {@code clients} with the cleared {@code Routine}.
     */
    private void updateClientExercise(FitBookModel model) {
        List<Client> clientList = model.getFitBook().getClientList();
        clientList.forEach(client -> client.clearRoutines());
    }
}
