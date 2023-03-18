package seedu.address.logic.commands.task.note;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.todo.Note;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Adds an application to the internship tracker.
 */
public class NoteCommand extends Command {

    public static final String COMMAND_WORD = "note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a note.\n"
            + "Parameters: "
            + PREFIX_NOTE_CONTENT + "NOTE_CONTENT "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NOTE_CONTENT + "Focus on software engineering jobs!";

    public static final String MESSAGE_SUCCESS = "New note added: %1$s";
    public static final String MESSAGE_DUPLICATE_NOTE = "This note already exists in the todo list";

    private final Note note;

    /**
     * Creates an AddCommand to add the specified {@code InternshipApplication}
     */
    public NoteCommand(Note noteContent) {
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
        return new CommandResult(String.format(MESSAGE_SUCCESS, note));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NoteCommand // instanceof handles nulls
                && note.equals(((NoteCommand) other).note));
    }
}
