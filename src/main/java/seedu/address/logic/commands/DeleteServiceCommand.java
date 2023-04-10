package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a service identified using its id.
 */
public class DeleteServiceCommand extends Command {

    public static final String COMMAND_WORD = "deleteservice";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the service identified by the id number displayed by the list command.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_SERVICE_SUCCESS = "Deleted Service %d";

    private final int id;

    public DeleteServiceCommand(int id) {
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.getShop().removeService(id);
            model.resetSelected();
            return new CommandResult(String.format(MESSAGE_DELETE_SERVICE_SUCCESS, id), Tab.SERVICES);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteServiceCommand // instanceof handles nulls
            && id == (((DeleteServiceCommand) other).id)); // state check
    }
}
