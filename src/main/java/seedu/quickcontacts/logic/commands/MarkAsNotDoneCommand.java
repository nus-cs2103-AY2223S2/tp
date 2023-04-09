package seedu.quickcontacts.logic.commands;

import java.util.List;
import java.util.stream.Collectors;

import seedu.quickcontacts.commons.core.index.Index;
import seedu.quickcontacts.logic.commands.exceptions.CommandException;
import seedu.quickcontacts.model.Model;

/**
 * Creates a new command that marks a meeting as done
 */
public class MarkAsNotDoneCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks a meeting as not done\n" + "Parameters: m/ "
            + "index of meeting";
    public static final String INDEX_NOT_FOUND = "One of the provided indices is not found";
    public static final String COMMAND_DESCRIPTION = "Marks a meeting as not done";
    public static final String SUCCESS_FORMAT = "Meetings %s marked as undone";
    private final List<Index> indexes;

    /**
     * Creates a new mark as not done command
     *
     * @param indexes indexes to mark as undone
     */
    public MarkAsNotDoneCommand(List<Index> indexes) {
        this.indexes = indexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            model.markMeetingsAsNotDone(indexes);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(INDEX_NOT_FOUND);
        }
        return new CommandResult(String.format(SUCCESS_FORMAT,
                indexes.stream().map(Index::getOneBased).collect(Collectors.toList())));
    }

    @Override
    public boolean equals(Object o) {
        return o == this // short circuit if same object
                || (o instanceof MarkAsNotDoneCommand
                && indexes.equals(((MarkAsNotDoneCommand) o).indexes));
    }
}
