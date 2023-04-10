package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Finds and selects a specified part identified by its name
 */
public class ViewPartCommand extends Command {

    public static final String COMMAND_WORD = "viewpart";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the part identified by the part name.\n"
            + "Parameters: STRING\n"
            + "Example: " + COMMAND_WORD + " CS-2103-T";

    private final String userString;

    public ViewPartCommand(String userString) {
        this.userString = userString;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.getShop().getPartQty(userString);
            model.updateFilteredPartMap(e -> e.getKey().equalsIgnoreCase(userString));
            return new CommandResult(String.format(Messages.MESSAGE_PART_VIEW_OVERVIEW), Tab.PARTS);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewPartCommand // instanceof handles nulls
                && userString.equals(((ViewPartCommand) other).userString)); // state check
    }
}
