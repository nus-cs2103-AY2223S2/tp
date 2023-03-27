package seedu.vms.logic.commands.vaccination;

import java.util.List;
import java.util.stream.Collectors;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.GroupName;
import seedu.vms.model.Model;


/**
 * Command to clear all vaccinations.
 */
public class ClearVaxTypeCommand extends Command {
    @Override
    public CommandMessage execute(Model model) throws CommandException {
        List<GroupName> names = model.getVaxTypeManager()
                .asUnmodifiableObservableMap()
                .values()
                .stream()
                .map(vaxType -> vaxType.getGroupName())
                .collect(Collectors.toList());
        try {
            for (GroupName name : names) {
                model.deleteVaccination(name);
            }
        } catch (IllegalValueException ive) {
            return new CommandMessage(ive.getMessage(), CommandMessage.State.DEATH);
        }
        return new CommandMessage("Vaccinations successfully cleared");
    }
}
