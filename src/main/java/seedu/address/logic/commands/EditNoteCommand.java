package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Note;

/**
 * Command class for editing notes
 */
public class EditNoteCommand extends Command {
    public static final String COMMAND_WORD = "edit-note";

    public static final String MESSAGE_USAGE = "Edit syntax: edit-note -index INDEX (must be a positive integer)"
            + " -content updated-note -name name-of-event -type type-of-event";
    public static final String MESSAGE_EXAMPLE = "edit-note -index 1 -content this is my updated notes -name "
            + "tutorial 1 -type tutorial";
    public static final String MESSAGE_EVENT_TYPE_NOT_RECOGNIZED = "The event type that you have entered"
            + " cannot be recognized!";
    public static final String MESSAGE_EDIT_NOTE_SUCCESS = "Edited note: %1$s";

    public static final String TUTORIAL_STRING = "tutorial";
    public static final String LAB_STRING = "lab";
    public static final String CONSULTATION_STRING = "consultation";

    private final Index targetIndex;
    private final Note note;
    private final String eventName;
    private final String eventType;

    /**
     * Creates an Edit Note to edit the specified {@code Index} note
     */
    public EditNoteCommand(Index index, Note newNote, String name, String type) {
        targetIndex = index;
        note = newNote;
        eventName = name;
        eventType = type;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        switch (eventType) {
        case TUTORIAL_STRING:
            model.editNoteFromTutorial(targetIndex, note, eventName);
            break;
        case LAB_STRING:
            model.editNoteFromLab(targetIndex, note, eventName);
            break;
        case CONSULTATION_STRING:
            model.editNoteFromConsultation(targetIndex, note, eventName);
            break;
        default:
            throw new CommandException(MESSAGE_EVENT_TYPE_NOT_RECOGNIZED);
        }
        return new CommandResult(String.format(MESSAGE_EDIT_NOTE_SUCCESS, note), false, false,
                false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditNoteCommand // instanceof handles nulls
                && targetIndex.equals(((EditNoteCommand) other).targetIndex)); // state check
    }
}
