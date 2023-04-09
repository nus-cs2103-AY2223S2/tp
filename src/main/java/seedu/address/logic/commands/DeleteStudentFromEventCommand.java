package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Deletes a student from an event.
 */
public class DeleteStudentFromEventCommand extends Command {
    public static final String COMMAND_WORD = "deleteStudent";
    public static final String MESSAGE_SUCCESS = "Student at specified index deleted from event";
    public static final String MESSAGE_EVENT_TYPE_NOT_RECOGNIZED = "Either you did not enter an "
            + "event type or the event type that you have entered "
            + "cannot be recognized!\n";
    public static final String MESSAGE_STUDENT_INDEX_TOO_SMALL = "The student index you "
            + "have entered cannot be 0 or less";
    public static final String MESSAGE_STUDENT_INDEX_TOO_BIG = "The student index you have entered cannot be bigger "
            + "than the size of the student list within the event";
    public static final String MESSAGE_STUDENT_INDEX_INVALID = "The student index "
            + "needs to be a non-zero integer";
    public static final String MESSAGE_EVENT_INDEX_TOO_SMALL = "The event index you have entered cannot be "
            + "0 or less";
    public static final String MESSAGE_EVENT_INDEX_TOO_BIG = "The event index you have entered cannot be "
            + "bigger than the size of the specified event list";
    public static final String MESSAGE_EVENT_INDEX_INVALID = "The event index "
            + "needs to be a non-zero integer";

    public static final String MESSAGE_USAGE = "Delete Student from Event Syntax: "
            + COMMAND_WORD + " "
            + "STUDENT_INDEX EVENT_TYPE/EVENT_INDEX\n"
            + "Parameters: STUDENT_INDEX (must be a valid positive integer), "
            + "EVENT_TYPE (Only Tutorial or Consultation or Lab case-sensitive is allowed)\n"
            + "EVENT_INDEX (must be a valid positive integer)\n"
            + "Example: " + COMMAND_WORD + " deleteStudent 1 Tutorial/1";

    public static final String TUTORIAL_STRING = "Tutorial/";
    public static final String LAB_STRING = "Lab/";
    public static final String CONSULTATION_STRING = "Consultation/";
    private final Index studentIndex;
    private final Index eventIndex;
    private final String eventType;

    /**
     * Constructs a DeleteStudentFromEventCommand object to delete a student from an event.
     *
     * @param studentIndex the index of the student within the event's student list to be deleted.
     * @param eventIndex the index of the event the student will be deleted from.
     * @param type the type of the event the student will be deleted from.
     */
    public DeleteStudentFromEventCommand(Index studentIndex, Index eventIndex, String type) {
        this.studentIndex = studentIndex;
        this.eventIndex = eventIndex;
        this.eventType = type;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, ParseException {
        requireNonNull(model);
        assert(studentIndex != null);
        assert(eventIndex != null);
        assert(eventType != null);

        if (studentIndex.getZeroBased() < 0) {
            throw new CommandException(MESSAGE_STUDENT_INDEX_TOO_SMALL);
        }
        if (eventIndex.getZeroBased() < 0) {
            throw new CommandException(MESSAGE_EVENT_INDEX_TOO_SMALL);
        }

        switch (eventType) {
        case TUTORIAL_STRING:
            if (eventIndex.getZeroBased() >= model.getFilteredTutorialList().size()) {
                throw new CommandException(MESSAGE_EVENT_INDEX_TOO_BIG);
            }
            if (studentIndex.getZeroBased() >= model.getFilteredTutorialList()
                    .get(eventIndex.getZeroBased()).getStudents().size()) {
                throw new CommandException(MESSAGE_STUDENT_INDEX_TOO_BIG);
            }
            break;
        case LAB_STRING:
            if (eventIndex.getZeroBased() >= model.getFilteredLabList().size()) {
                throw new CommandException(MESSAGE_EVENT_INDEX_TOO_BIG);
            }
            if (studentIndex.getZeroBased() >= model.getFilteredLabList()
                    .get(eventIndex.getZeroBased()).getStudents().size()) {
                throw new CommandException(MESSAGE_STUDENT_INDEX_TOO_BIG);
            }
            break;
        case CONSULTATION_STRING:
            if (eventIndex.getZeroBased() >= model.getFilteredConsultationList().size()) {
                throw new CommandException(MESSAGE_EVENT_INDEX_TOO_BIG);
            }
            if (studentIndex.getZeroBased() >= model.getFilteredConsultationList()
                    .get(eventIndex.getZeroBased()).getStudents().size()) {
                throw new CommandException(MESSAGE_STUDENT_INDEX_TOO_BIG);
            }
            break;
        default:
            throw new CommandException(MESSAGE_EVENT_TYPE_NOT_RECOGNIZED
            + MESSAGE_USAGE);
        }

        model.deleteStudentFromEvent(this.studentIndex, this.eventIndex, this.eventType);

        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}
