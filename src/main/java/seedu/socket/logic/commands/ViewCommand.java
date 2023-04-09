package seedu.socket.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.socket.commons.core.Messages;
import seedu.socket.commons.core.index.Index;
import seedu.socket.logic.commands.exceptions.CommandException;
import seedu.socket.model.Model;
import seedu.socket.model.person.Person;

/**
 * Views the details of a person in the filtered list.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the person identified by the index number used in the contact list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";;

    public static final String MESSAGE_VIEW_PERSON_SUCCESS = "Viewed Person: %1$s";
    private final Index index;
    /**
     * @param index of the person in the filtered person list to view
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
        Person viewedPerson = lastShownList.get(index.getZeroBased());
        model.updateViewedPerson(viewedPerson);
        return new CommandResult(String.format(MESSAGE_VIEW_PERSON_SUCCESS, viewedPerson));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && index.equals(((ViewCommand) other).index)); // state check
    }
}
