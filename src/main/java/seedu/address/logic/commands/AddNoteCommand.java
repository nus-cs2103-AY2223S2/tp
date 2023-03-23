package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Note;

/**
 * Allows TA to add a note by checking if the command input matches add-note
 */
public class AddNoteCommand extends Command {
    public static final String COMMAND_WORD = "add-note";
    public static final String MESSAGE_SUCCESS = "Note specified has been successfully added";

    public static final String MESSAGE_DUPLICATE_NOTE = "Note has been added before";

    public static final String MESSAGE_USAGE = "Note/ -content add-your-note-here";

    // JThh: below fields are temporarily not used due to code refactoring.
    public static final String MESSAGE_EVENT_TYPE_NOT_RECOGNIZED = "The event type that you have entered"
            + "cannot be recognized!";
    public static final String TUTORIAL_STRING = "tutorial";
    public static final String LAB_STRING = "lab";
    public static final String CONSULTATION_STRING = "consultation";
    private Note toAdd;
    private Index index;
    private String eventName;
    private String eventType;

    /**
     * Creates an AddLab to add the specified {@code Lab}
     */
    public AddNoteCommand(Note note) {
        requireNonNull(note);
        toAdd = note;
    }

    /**
     * Executes the addition of note and saves it into the model state
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasNote(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_NOTE);
        }

        model.addNote(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddNoteCommand // instanceof handles nulls
                && toAdd.equals(((AddNoteCommand) other).toAdd));
    }
}
