package teambuilder.logic.commands;

import static java.util.Objects.requireNonNull;

import teambuilder.commons.core.Momento;
import teambuilder.commons.util.HistoryUtil;
import teambuilder.model.Model;
import teambuilder.model.TeamBuilder;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        Momento old = model.save();
        old.setDescription(COMMAND_WORD);
        HistoryUtil.getInstance().store(old);

        model.setAddressBook(new TeamBuilder());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
