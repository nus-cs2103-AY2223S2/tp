package seedu.address.logic.commands.task.note;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.NoteList;
import seedu.address.model.tag.TaskType;
import seedu.address.model.task.Note;

/**
 * Clears the note list.
 */
public class ClearNoteCommand extends Command {

    public static final String COMMAND_WORD = "clear_note";
    public static final String MESSAGE_SUCCESS = "All notes has been cleared!";
    public static final String MESSAGE_NULL = "There is nothing to clear!";

    private static final TaskType type = TaskType.NOTE;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        List<Note> lastShownList = model.getFilteredNoteList();

        if (lastShownList.size() == 0) {
            return new CommandResult(MESSAGE_NULL, type);
        }

        model.clearNote(new NoteList());

        return new CommandResult(MESSAGE_SUCCESS, type);
    }
}
