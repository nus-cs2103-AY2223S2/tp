package seedu.address.logic.commands.task.note;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.TodoType;
import seedu.address.model.task.Note;

/**
 * Deletes a note identified using it's displayed index from the note list.
 */
public class DeleteNoteCommand extends Command {

    public static final String COMMAND_WORD = "delete_note";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the specified note from the list of notes.\n"
            + "Deletes note at the specified INDEX.\n"
            + "The index refers to the index number shown in the displayed note list.\n"
            + "Parameters: INDEX (must be a positive integer 1, 2, 3, ...)\n"
            + "Example: " + COMMAND_WORD + " 2";

    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Deleted note: %1$s";

    private static final TodoType type = TodoType.NOTE;

    private final Index targetIndex;

    /**
     * Creates an DeleteCommand to delete the specified {@code targetIndex} note
     */
    public DeleteNoteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Note> lastShownList = model.getFilteredNoteList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        Note noteToDelete = lastShownList.get(targetIndex.getZeroBased());

        model.deleteNote(noteToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_NOTE_SUCCESS, noteToDelete), type);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteNoteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteNoteCommand) other).targetIndex)); // state check
    }
}
