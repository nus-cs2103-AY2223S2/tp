package seedu.address.logic.commands.task.note;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.todo.Note;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Clears the address book.
 */
public class ClearNoteCommand extends Command {

    public static final String COMMAND_WORD = "clear note";
    public static final String MESSAGE_SUCCESS = "All notes has been cleared!";
    public static final String MESSAGE_NULL = "There is nothing to clear!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        List<Note> lastShownList = model.getFilteredNoteList();

        if (lastShownList.size() == 0) {
            return new CommandResult(MESSAGE_NULL);
        }

        model.clearNote(new AddressBook());

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
