package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Note;

/**
 * Allows TA to add a note by checking if the command input matches add-note.
 *
 */
public class AddNoteToEventCommand extends Command {
    public static final String COMMAND_WORD = "addNote";
    public static final String MESSAGE_SUCCESS = "A new note has been successfully added";
    public static final String MESSAGE_USAGE = "addNote content/add-your-note-here name/name-of-event "
            + "type/type-of-event";
    public static final String MESSAGE_EXAMPLE = "addNote content/this is a new note name/dijkstraReview "
            + "type/Tutorial";

    public static final String MESSAGE_EVENT_TYPE_NOT_RECOGNIZED = "The event type that you have entered"
            + " cannot be recognized! Please enter type such as Tutorial, Lab, or Consultation";
    public static final String MESSAGE_EVENT_NAME_NOT_FOUND = "The event name %1$s is not found in the list!";

    public static final String TUTORIAL_STRING = "Tutorial";
    public static final String LAB_STRING = "Lab";
    public static final String CONSULTATION_STRING = "Consultation";
    private final Note toAdd;
    private final String eventName;
    private final String eventType;

    /**
     * Creates an AddNote to add the specified {@code Lab}
     */
    public AddNoteToEventCommand(Note note, String name, String type) {
        toAdd = note;
        eventName = name;
        eventType = type;
    }

    /**
     * Executes the addition of note and saves it into the model state.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult A command result that flags successful message
     * @throws CommandException Throws command exception when event type is not recognized
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        boolean addResult;

        if (eventType.toLowerCase().contains(TUTORIAL_STRING.toLowerCase())) {
            addResult = model.addNoteToTutorial(toAdd, eventName);
        } else if (eventType.toLowerCase().contains(LAB_STRING.toLowerCase())) {
            addResult = model.addNoteToLab(toAdd, this.eventName);
        } else if (eventType.toLowerCase().contains(CONSULTATION_STRING.toLowerCase())) {
            addResult = model.addNoteToConsultation(toAdd, this.eventName);
        } else {
            throw new CommandException(MESSAGE_EVENT_TYPE_NOT_RECOGNIZED);
        }

        if (!(addResult)) {
            throw new CommandException(String.format(MESSAGE_EVENT_NAME_NOT_FOUND, eventName));
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
