package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Shows the details of an existing person inside TAB
 */
public class ShowDetailCommand extends Command {
    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the details of the person selected. \n "
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SHOW_PERSON_SUCCESS = "Details displayed successfully!";

    private final Index targetIndex;

    /**
     * @param index of the person in the displayed persons list to show details of
     */
    public ShowDetailCommand(Index index) {
        requireNonNull(index);
        this.targetIndex = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> currentList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= currentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToShow = currentList.get(targetIndex.getZeroBased());
        return new CommandResult(MESSAGE_SHOW_PERSON_SUCCESS, false, personToShow, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowDetailCommand // instanceof handles nulls
                && targetIndex.equals(((ShowDetailCommand) other).targetIndex)); // state check
    }
}
