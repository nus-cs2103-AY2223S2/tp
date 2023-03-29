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
    public static final String MESSAGE_USAGE = "add-note -content add-your-note-here -name name-of-event "
            + "-type type-of-event";
    public static final String MESSAGE_EXAMPLE = "add-note note -content this is a new note -name dijkstraReview "
            + "-type Tutorial";

    // JThh: below fields are temporarily not used due to code refactoring.
    public static final String MESSAGE_EVENT_TYPE_NOT_RECOGNIZED = "The event type that you have entered"
            + " cannot be recognized!";
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
     * Executes the addition of note and saves it into the model state
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult A command result that flags successful message
     * @throws CommandException Throws command exception when event type is not recognized
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

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
