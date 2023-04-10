package seedu.vms.logic.commands.vaccination;

import java.util.List;
import java.util.function.Predicate;

import seedu.vms.commons.core.Messages;
import seedu.vms.commons.util.StringUtil;
import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.Model;
import seedu.vms.model.vaccination.VaxType;

/**
 * Command to filter vaccination types.
 */
public class FilterVaxTypeCommand extends Command {
    private final List<String> namePatterns;


    public FilterVaxTypeCommand(List<String> namePatterns) {
        this.namePatterns = namePatterns;
    }


    @Override
    public CommandMessage execute(Model model) throws CommandException {
        Predicate<VaxType> nameFilter =
                vaxType -> StringUtil.isMatching(vaxType.getName(), namePatterns);
        model.setVaccinationFilters(List.of(nameFilter));
        int numListed = model.getFilteredVaxTypeMap().size();
        return new CommandMessage(String.format(Messages.MESSAGE_VACCINATION_LISTED_OVERVIEW,
                numListed));
    }
}
