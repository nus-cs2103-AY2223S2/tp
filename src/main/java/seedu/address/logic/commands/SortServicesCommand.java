package seedu.address.logic.commands;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.service.Service;
import seedu.address.model.service.Vehicle;

/**
 * Manages Sorting of services
 */
public class SortServicesCommand extends Command{
    public static final String COMMAND_WORD = "sortservices";
    public static final String MESSAGE_SUCCESS = "Sorted services";

    private final Comparator<Service> cmp;

    public SortServicesCommand(Comparator<Service> cmp) {
        this.cmp = cmp;
    }
    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateServiceComparator(cmp);
        return new CommandResult(MESSAGE_SUCCESS, Tab.SERVICES);
    }
}
