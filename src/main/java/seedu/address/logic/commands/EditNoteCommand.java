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
    public static final String COMMAND_WORD = "editNote";

    public static final String MESSAGE_USAGE = "Edit syntax: editNote index/INDEX (must be a positive integer)"
            + " content/updated-note name/name-of-event type/type-of-event";
    public static final String MESSAGE_EXAMPLE = "editNote index/1 content/this is my updated notes name/"
            + "tutorial 1 type/Tutorial";
    public static final String MESSAGE_NOTE_INDEX_NOT_FOUND = "The event %2$s itself or its note indexed %1$s is "
            + "not found!";
    public static final String MESSAGE_EVENT_TYPE_NOT_RECOGNIZED = "The event type that you have entered"
            + " cannot be recognized! Please enter type such as Tutorial, Lab, or Consultation";
    public static final String MESSAGE_EDIT_NOTE_SUCCESS = "Edited note: %1$s";

    public static final String TUTORIAL_STRING = "Tutorial";
    public static final String LAB_STRING = "Lab";
    public static final String CONSULTATION_STRING = "Consultation";

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
        boolean editResult;
        if (eventType.toLowerCase().contains(TUTORIAL_STRING.toLowerCase())) {
            editResult = model.editNoteFromTutorial(targetIndex, note, eventName);
        } else if (eventType.toLowerCase().contains(LAB_STRING.toLowerCase())) {
            editResult = model.editNoteFromLab(targetIndex, note, eventName);
        } else if (eventType.toLowerCase().contains(CONSULTATION_STRING.toLowerCase())) {
            editResult = model.editNoteFromConsultation(targetIndex, note, eventName);
        } else {
            throw new CommandException(MESSAGE_EVENT_TYPE_NOT_RECOGNIZED);
        }
        if (!(editResult)) {
            throw new CommandException(String.format(MESSAGE_NOTE_INDEX_NOT_FOUND, targetIndex.getOneBased(),
                    eventName));
        } else {
            return new CommandResult(String.format(MESSAGE_EDIT_NOTE_SUCCESS, note.getContent()), false, false,
                    false, true);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditNoteCommand // instanceof handles nulls
                && targetIndex.equals(((EditNoteCommand) other).targetIndex)); // state check
    }
}
