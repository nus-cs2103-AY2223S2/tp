package seedu.vms.logic.commands.patient;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;

import seedu.vms.commons.core.Messages;
import seedu.vms.commons.exceptions.UnexpectedChangeException;
import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.Model;

/**
 * Clears the patient manager.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Patients successfully cleared";

    private final boolean isForce;


    /**
     * Constructs a {@code ClearCommand} that forces its changes.
     */
    public ClearCommand() {
        // TODO: This should be removed once additoinal test cases are added.
        this(true);
    }


    /**
     * Constructs a {@code ClearCommand}.
     *
     * @param isForce - {@code true} if the chances should be forced and
     *      {@code false} otherwise.
     */
    public ClearCommand(boolean isForce) {
        this.isForce = isForce;
    }

    @Override
    public CommandMessage execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Integer> ids = model.getPatientManager()
                .getMapView()
                .values()
                .stream()
                .map(patient -> patient.getId())
                .collect(Collectors.toList());

        int totalToClear = ids.size();
        int numCleared = 0;
        try {
            for (Integer id : ids) {
                model.deletePatient(id, isForce);
                numCleared++;
            }
        } catch (UnexpectedChangeException uce) {
            String errMessage = String.format("%d of %d patients cleared.\n%s\n%s",
                    numCleared, totalToClear,
                    uce.getMessage(),
                    Messages.MESSAGE_USE_FORCE);
            throw new CommandException(errMessage);
        }

        model.resetPatientIds();
        return new CommandMessage(MESSAGE_SUCCESS);
    }
}
