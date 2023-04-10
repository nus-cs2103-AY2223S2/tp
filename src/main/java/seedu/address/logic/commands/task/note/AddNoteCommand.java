package seedu.address.logic.commands.task.note;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_CONTENT;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.TaskType;
import seedu.address.model.task.Note;

/**
 * Adds a quick note to the note list.
 */
public class AddNoteCommand extends Command {

    public static final String COMMAND_WORD = "add_note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a note.\n"
            + "Parameters: "
            + PREFIX_NOTE_CONTENT + "NOTE_CONTENT "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NOTE_CONTENT + "Focus on software engineering jobs!";

    public static final String MESSAGE_SUCCESS = "New note added: %1$s";
    public static final String MESSAGE_DUPLICATE_NOTE = "This note already exists in the todo list";

    private static final TaskType type = TaskType.NOTE;

    private final Note note;

    /**
     * Creates an AddNoteCommand to add the specified {@code Note}.
     */
    public AddNoteCommand(Note noteContent) {
        requireNonNull(noteContent);
        note = noteContent;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasNote(note)) {
            throw new CommandException(MESSAGE_DUPLICATE_NOTE);
        }

        model.addNote(note);
        return new CommandResult(String.format(MESSAGE_SUCCESS, note), type);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddNoteCommand // instanceof handles nulls
                && note.equals(((AddNoteCommand) other).note));
    }
}
