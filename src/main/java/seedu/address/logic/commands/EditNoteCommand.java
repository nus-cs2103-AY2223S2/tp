package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class EditNoteCommand extends Command {
    public static final String COMMAND_WORD = "edit-note";

    public static final String MESSAGE_USAGE = "Edit syntax: edit-note -index INDEX (must be a positive integer) -name name-of-event -type type-of-event";

    public static final String MESSAGE_EVENT_TYPE_NOT_RECOGNIZED = "The event type that you have entered"
            + "cannot be recognized!";
    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Deleted note: %1$s";

    public static final String TUTORIAL_STRING = "tutorial";
    public static final String LAB_STRING = "lab";
    public static final String CONSULTATION_STRING = "consultation";

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

        switch (eventType) {
            case TUTORIAL_STRING:
                model.removeNoteFromTutorial(targetIndex, eventName);
                break;
            case LAB_STRING:
                model.removeNoteFromLab(targetIndex, this.eventName);
                break;
            case CONSULTATION_STRING:
                model.removeNoteFromConsultation(targetIndex, this.eventName);
                break;
            default:
                throw new CommandException(MESSAGE_EVENT_TYPE_NOT_RECOGNIZED);
        }
        return new CommandResult(String.format(MESSAGE_DELETE_NOTE_SUCCESS), false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
