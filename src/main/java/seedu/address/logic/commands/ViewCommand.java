package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.List;

/**
 * Loads specific person to the right info panel.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View details of the person identified "
            + "by the index number used in the displayed person list "
            + "or by different tab names (c/m/s)\n";

    public static final String MESSAGE_SUCCESS = "Viewing %1$d";

    private final Index index;

    private final String tab;

    public ViewCommand(Index index) {
        requireNonNull(index);
        this.index = index;
        this.tab = "c";
    }

    public ViewCommand(String tab) {
        requireNonNull(tab);
        this.index = Index.fromZeroBased(-1);
        this.tab = tab;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() == -1) {
            return new CommandResult(String.format(MESSAGE_SUCCESS, index.getOneBased()));
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person protagonist = lastShownList.get(index.getZeroBased());
        model.setProtagonist(protagonist);

        return new CommandResult(String.format(MESSAGE_SUCCESS, index.getOneBased()));
    }
}
