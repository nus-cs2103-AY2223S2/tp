package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Delete note command class
 */
public class DeleteNoteCommand extends Command {
    public static final String COMMAND_WORD = "deleteNote";

    public static final String MESSAGE_USAGE = "Delete syntax: deleteNote index/INDEX (must be a positive integer) "
            + "name/name-of-event type/type-of-event";
    public static final String MESSAGE_EXAMPLE = "deleteNote index/1 name/t1 type/Tutorial";
    public static final String MESSAGE_EVENT_TYPE_NOT_RECOGNIZED = "The event type that you have entered "
            + "cannot be recognized! Please enter type such as Tutorial, Lab, or Consultation";
    public static final String MESSAGE_NOTE_NOT_FOUND = "The event %2$s itself or its note indexed %1$s is not found!";
    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Deleted note indexed %1$s from event %2$s";

    public static final String TUTORIAL_STRING = "Tutorial";
    public static final String LAB_STRING = "Lab";
    public static final String CONSULTATION_STRING = "Consultation";

    private final Index targetIndex;
    private final String eventName;
    private final String eventType;

    /**
     * Creates an AddNote to add the specified {@code Lab}
     */
    public DeleteNoteCommand(Index index, String name, String type) {
        targetIndex = index;
        eventName = name;
        eventType = type;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        boolean rmResult;
        if (eventType.toLowerCase().contains(TUTORIAL_STRING.toLowerCase())) {
            rmResult = model.removeNoteFromTutorial(targetIndex, eventName);
        } else if (eventType.toLowerCase().contains(LAB_STRING.toLowerCase())) {
            rmResult = model.removeNoteFromLab(targetIndex, this.eventName);
        } else if (eventType.toLowerCase().contains(CONSULTATION_STRING.toLowerCase())) {
            rmResult = model.removeNoteFromConsultation(targetIndex, this.eventName);
        } else {
            throw new CommandException(MESSAGE_EVENT_TYPE_NOT_RECOGNIZED);
        }
        if (!(rmResult)) {
            throw new CommandException(String.format(MESSAGE_NOTE_NOT_FOUND, targetIndex.getOneBased(),
                    eventName));
        } else {
            return new CommandResult(String.format(MESSAGE_DELETE_NOTE_SUCCESS, targetIndex.getOneBased(),
                    eventName), false, false,
                    false, true);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteNoteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteNoteCommand) other).targetIndex)); // state check
    }
}
