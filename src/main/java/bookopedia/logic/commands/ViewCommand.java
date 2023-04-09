package bookopedia.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import bookopedia.commons.core.Messages;
import bookopedia.commons.core.index.Index;
import bookopedia.logic.commands.exceptions.CommandException;
import bookopedia.model.Model;
import bookopedia.model.person.Person;

/**
 * View more information about a delivery.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_USAGE = "Invalid index";
    public static final String MESSAGE_SUCCESS = "Successfully viewed a delivery!";

    private final Index index;

    /**
     * Creates a View Command to View the delivery at {@code Index}
     * @param index Index of delivery of interest.
     */
    public ViewCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person deliveryToView = lastShownList.get(index.getZeroBased());
        return new CommandResult(MESSAGE_SUCCESS, true, deliveryToView, index.getZeroBased());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && index.equals(((ViewCommand) other).index));
    }
}
