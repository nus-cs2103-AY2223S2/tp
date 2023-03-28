package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Note;

/**
 * Allows TA to add a note by checking if the command input matches add-note
 */
public class AddNoteToEventCommand extends Command {
    public static final String COMMAND_WORD = "add-note";
    public static final String MESSAGE_SUCCESS = "Note specified has been successfully added";

    public static final String MESSAGE_DUPLICATE_NOTE = "Note has been added before";

    public static final String MESSAGE_USAGE = "Note/ -content add-your-note-here -event name-of-event";

    // JThh: below fields are temporarily not used due to code refactoring.
    public static final String MESSAGE_EVENT_TYPE_NOT_RECOGNIZED = "The event type that you have entered"
            + "cannot be recognized!";
    public static final String TUTORIAL_STRING = "tutorial";
    public static final String LAB_STRING = "lab";
    public static final String CONSULTATION_STRING = "consultation";
    private boolean attachToEvent = false;
    private Note toAdd;
    private String eventName;
    private String eventType;

    /**
     * Creates an AddNote to add the specified {@code Lab}
     */
    public AddNoteToEventCommand(Note note, String name, String type) {
        toAdd = note;
        eventName = name;
        eventType = type;
        attachToEvent = true;
    }

    /**
     * Creates an AddNote to add the specified {@code Lab}
     */
    public AddNoteToEventCommand(Note note) {
        toAdd = note;
        eventName = null;
        eventType = null;
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

        if (!(attachToEvent)) {
            model.addNote(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS), false, false, false, false);
        }

        switch (eventType) {
            case TUTORIAL_STRING:
                model.addNoteToTutorial(toAdd, eventName);
                break;
            case LAB_STRING:
                model.addNoteToLab(toAdd, this.eventName);
                break;
            case CONSULTATION_STRING:
                model.addNoteToConsultation(toAdd, this.eventName);
                break;
            default:
                throw new CommandException(MESSAGE_EVENT_TYPE_NOT_RECOGNIZED);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS), false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddNoteToEventCommand // instanceof handles nulls
                && toAdd.equals(((AddNoteToEventCommand) other).toAdd));
    }
}