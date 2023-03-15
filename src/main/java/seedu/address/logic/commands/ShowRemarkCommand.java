package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;

/**
 * Shows the remark of an existing person in the address book.
 */
public class ShowRemarkCommand extends Command {

    public static final String COMMAND_WORD = "show";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the remarks added to a person. "
            + "Parameters: INDEX (must be a positive integer)";

    public static final String MESSAGE_SHOWN_REMARK_SUCCESS = "Shown: %1$s";

    private final Index index;
    //private final Remark remark;

    public ShowRemarkCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToShow = lastShownList.get(index.getZeroBased());
        Remark toBeShown = personToShow.getRemark();

        return new CommandResult(String.format(MESSAGE_SHOWN_REMARK_SUCCESS, toBeShown));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ShowRemarkCommand)) {
            return false;
        }

        // state check
        ShowRemarkCommand e = (ShowRemarkCommand) other;
        return index.equals(e.index);
    }



}
