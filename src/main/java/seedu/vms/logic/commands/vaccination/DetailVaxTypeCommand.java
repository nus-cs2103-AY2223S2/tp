package seedu.vms.logic.commands.vaccination;

import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.GroupName;
import seedu.vms.model.Model;
import seedu.vms.model.vaccination.VaxType;

public class DetailVaxTypeCommand extends Command {
    private final GroupName vaxName;


    public DetailVaxTypeCommand(GroupName vaxName) {
        this.vaxName = vaxName;
    }


    @Override
    public CommandMessage execute(Model model) throws CommandException {
        VaxType vaxType = model.getVaxTypeManager().get(vaxName.getName())
                .orElseThrow(() -> new CommandException("Vaccination does not exist"));
        model.setDetailedVaxType(vaxType);
        return new CommandMessage("Detailing vaccination");
    }
}
