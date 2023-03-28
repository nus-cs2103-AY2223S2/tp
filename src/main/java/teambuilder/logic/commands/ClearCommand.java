package teambuilder.logic.commands;

import static java.util.Objects.requireNonNull;

import teambuilder.commons.core.Memento;
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

        Memento old = model.save();
        HistoryUtil.getInstance().storePast(old, COMMAND_WORD);

        model.setTeamBuilder(new TeamBuilder());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
