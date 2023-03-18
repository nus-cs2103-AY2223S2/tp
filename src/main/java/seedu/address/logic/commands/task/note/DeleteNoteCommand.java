package seedu.address.logic.commands.task.note;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.todo.Note;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Deletes a note identified using it's displayed index from the note list.
 */
public class DeleteNoteCommand extends Command {

    public static final String COMMAND_WORD = "delete note";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the specified note from the list of todo.\n"
            + "Deletes note at the specified INDEX.\n"
            + "The index refers to the index number shown in the displayed note list.\n"
            + "Parameters: INDEX (must be a positive integer 1, 2, 3, ...)\n"
            + "Example: " + COMMAND_WORD + " 2";

    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Deleted note: %1$s";

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

        return new CommandResult(String.format(MESSAGE_DELETE_NOTE_SUCCESS, noteToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteNoteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteNoteCommand) other).targetIndex)); // state check
    }
}
