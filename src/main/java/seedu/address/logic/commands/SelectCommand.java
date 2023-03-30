package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.ui.enums.LightDarkMode;
import seedu.address.ui.result.ResultDisplay;


/**
 * Selects person in address book to display details
 */
public class SelectCommand extends Command {

    public static final String COMMAND_WORD = "select";

    public static final String MESSAGE_USAGE =
            ResultDisplay.formatMessage(COMMAND_WORD,
                    "Displays the details of the selected contact specified by the given index.")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_PARAMETERS, "INDEX (must be a positive integer)")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_EXAMPLE, COMMAND_WORD, "1");

    public static final String MESSAGE_SELECT_PERSON_SUCCESS = "Selected contact: %1$s";

    public static final String MESSAGE_NO_CHANGE = "Already selected contact: %s";
    private final Index index;

    /**
     * @param index Index of the contact to display.
     */
    public SelectCommand(Index index) {
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
        Person prevPerson = model.getSelectedPerson().get();
        model.setSelectedPerson(index);
        Person currPerson = model.getSelectedPerson().get();
        if (Objects.equals(prevPerson, currPerson)) {
            return new CommandResult(String.format(MESSAGE_NO_CHANGE, currPerson), false, false,
                    LightDarkMode.NO_CHANGE);
        } else {
            return new CommandResult(String.format(MESSAGE_SELECT_PERSON_SUCCESS, currPerson),
                    false, false, LightDarkMode.NO_CHANGE);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SelectCommand)) {
            return false;
        }

        // state check
        SelectCommand e = (SelectCommand) other;
        return index.equals(e.index);
    }
}
