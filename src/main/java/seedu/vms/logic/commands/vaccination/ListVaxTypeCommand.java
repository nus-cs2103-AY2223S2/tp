package seedu.vms.logic.commands.vaccination;

import java.util.List;

import seedu.vms.commons.core.Messages;
import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.Model;

/**
 * Command to list all vaccinations.
 */
public class ListVaxTypeCommand extends Command {
    @Override
    public CommandMessage execute(Model model) throws CommandException {
        model.setVaccinationFilters(List.of());
        int numListed = model.getFilteredVaxTypeMap().size();
        return new CommandMessage(String.format(Messages.MESSAGE_VACCINATION_LISTED_OVERVIEW, numListed));
    }
}
