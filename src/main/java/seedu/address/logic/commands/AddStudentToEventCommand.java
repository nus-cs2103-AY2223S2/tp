package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds a student to an event.
 */
public class AddStudentToEventCommand extends Command {
    public static final String COMMAND_WORD = "addStudent";
    public static final String MESSAGE_SUCCESS = "Student at specified index added to event";
    public static final String MESSAGE_EVENT_TYPE_NOT_RECOGNIZED = "Either you did not enter an "
            + "event type or the event type that you have entered "
            + "cannot be recognized!\n";
    public static final String MESSAGE_STUDENT_INDEX_TOO_BIG = "The student index you have entered cannot be bigger "
            + "than the size of the student list within the event";
    public static final String MESSAGE_STUDENT_INDEX_INVALID = "The student index "
            + "needs to be a non-zero integer!";
    public static final String MESSAGE_EVENT_INDEX_TOO_BIG = "The event index you have entered cannot be "
            + "bigger than the size of the specified event list";
    public static final String MESSAGE_EVENT_INDEX_INVALID = "The event index "
            + "needs to be a non-zero integer!";
    public static final String MESSAGE_USAGE = "Add Student to Event Syntax: "
            + COMMAND_WORD + " " + "STUDENT_INDEX EVENT_TYPE/EVENT_INDEX\n"
            + "Parameters: STUDENT_INDEX within the student list (must be a valid positive integer), "
            + "EVENT_TYPE (Only Tutorial or Consultation or Lab case-sensitive is allowed)\n"
            + "EVENT_INDEX within the event list of the specified event (must be a valid positive integer)\n"
            + "Example: " + COMMAND_WORD + " addStudent 1 Tutorial/1";
    public static final String TUTORIAL_STRING = "Tutorial/";
    public static final String LAB_STRING = "Lab/";
    public static final String CONSULTATION_STRING = "Consultation/";
    private final Index studentIndex;
    private final Index eventIndex;
    private final String eventType;

    /**
     * Constructs an AddStudentToEventCommand object to add the student at the specified index
     * to an event of the specified type and name.
     *
     * @param studentIndex the index of the student within the student list to be added.
     * @param eventIndex the index of the event the student will be added into.
     * @param type the type of the event the student will be added into.
     */
    public AddStudentToEventCommand(Index studentIndex, Index eventIndex, String type) {
        this.studentIndex = studentIndex;
        this.eventIndex = eventIndex;
        this.eventType = type;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (studentIndex.getZeroBased() >= model.getFilteredPersonList().size()) {
            throw new CommandException(MESSAGE_STUDENT_INDEX_TOO_BIG);
        }

        switch (eventType) {
        case TUTORIAL_STRING:
            if (eventIndex.getZeroBased() >= model.getFilteredTutorialList().size()) {
                throw new CommandException(MESSAGE_EVENT_INDEX_TOO_BIG);
            }
            model.addStudentToTutorial(this.studentIndex, this.eventIndex);
            break;
        case LAB_STRING:
            if (eventIndex.getZeroBased() >= model.getFilteredLabList().size()) {
                throw new CommandException(MESSAGE_EVENT_INDEX_TOO_BIG);
            }
            model.addStudentToLab(this.studentIndex, this.eventIndex);
            break;
        case CONSULTATION_STRING:
            if (eventIndex.getZeroBased() >= model.getFilteredConsultationList().size()) {
                throw new CommandException(MESSAGE_EVENT_INDEX_TOO_BIG);
            }
            model.addStudentToConsultation(this.studentIndex, this.eventIndex);
            break;
        default:
            throw new CommandException(MESSAGE_EVENT_TYPE_NOT_RECOGNIZED
            + MESSAGE_USAGE);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS),
                false, false, false, true);
    }
}
