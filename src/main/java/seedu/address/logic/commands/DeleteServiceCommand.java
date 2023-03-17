package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.idgen.IdGenerator;
import seedu.address.model.Model;
import seedu.address.model.service.Service;

/**
 * Deletes a vehicle identified using it's displayed index from viewvehicle.
 */
public class DeleteServiceCommand extends RedoableCommand {

    public static final String COMMAND_WORD = "deletevehicle";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the vehicle identified by the id number displayed by the list command.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_SERVICE_SUCCESS = "Deleted Service: %1$s";

    private final Index targetIndex;

    public DeleteServiceCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult executeUndoableCommand(Model model) throws CommandException {
        requireNonNull(model);
        List<Service> lastShownList = model.getFilteredServiceList();

        Service serviceToDeletes = lastShownList.get(targetIndex.getZeroBased());
        int id = serviceToDeletes.getId();
        model.deleteService(serviceToDeletes);
        IdGenerator.setServiceIdUnused(id);
        return new CommandResult(String.format(MESSAGE_DELETE_SERVICE_SUCCESS, serviceToDeletes));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteServiceCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteServiceCommand) other).targetIndex)); // state check
    }
}
