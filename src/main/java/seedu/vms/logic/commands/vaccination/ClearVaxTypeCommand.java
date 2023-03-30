package seedu.vms.logic.commands.vaccination;

import java.util.List;
import java.util.stream.Collectors;

import seedu.vms.commons.core.Messages;
import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.commons.exceptions.UnexpectedChangeException;
import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.GroupName;
import seedu.vms.model.Model;


/**
 * Command to clear all vaccinations.
 */
public class ClearVaxTypeCommand extends Command {
    private final boolean isForce;


    public ClearVaxTypeCommand(boolean isForce) {
        this.isForce = isForce;
    }


    @Override
    public CommandMessage execute(Model model) throws CommandException {
        List<GroupName> names = model.getVaxTypeManager()
                .asUnmodifiableObservableMap()
                .values()
                .stream()
                .map(vaxType -> vaxType.getGroupName())
                .collect(Collectors.toList());

        int totalToClear = names.size();
        int numCleared = 0;
        try {
            for (GroupName name : names) {
                model.deleteVaccination(name, isForce);
                numCleared++;
            }
        } catch (IllegalValueException ive) {
            return new CommandMessage(ive.getMessage(), CommandMessage.State.DEATH);
        } catch (UnexpectedChangeException uce) {
            String errMessage = String.format("%d of %d vaccination cleared.\n%s\n%s",
                    numCleared, totalToClear,
                    uce.getMessage(),
                    Messages.MESSAGE_USE_FORCE);
            throw new CommandException(errMessage);
        }

        return new CommandMessage("Vaccinations successfully cleared");
    }
}
