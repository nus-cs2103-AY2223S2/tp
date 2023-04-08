package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONSULTATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Allows the TA to add student details to an event within one command instead of multiple commands
 */
public class AddStudentToEventCommand extends Command {
    public static final String COMMAND_WORD = "addStudent";
    public static final String MESSAGE_SUCCESS = "Student at specified index added to event";
    public static final String MESSAGE_EVENT_TYPE_NOT_RECOGNIZED = "Either you did not enter an "
            + "event type or the event type that you have entered "
            + "cannot be recognized!\n";
    public static final String MESSAGE_STUDENT_INDEX_TOO_SMALL = "The student index you "
            + "have entered cannot be 0 or less";
    public static final String MESSAGE_STUDENT_INDEX_TOO_BIG = "The student index you have entered cannot be bigger "
            + "than the size of the student list within the event";
    public static final String MESSAGE_EVENT_INDEX_TOO_SMALL = "The event index you have entered cannot be "
            + "0 or less";
    public static final String MESSAGE_EVENT_INDEX_TOO_BIG = "The event index you have entered cannot be "
            + "bigger than the size of the specified event list";
    public static final String MESSAGE_USAGE = "Add Student to Event Syntax: "
            + COMMAND_WORD + " " + "STUDENT_INDEX EVENT_TYPE/EVENT_INDEX\n"
            + "Parameters: STUDENT_INDEX within the student list (must be a valid positive integer), "
            + "EVENT_TYPE (Only Tutorial or Consultation or Lab case-sensitive is allowed)\n"
            + "EVENT_INDEX within the event list of the specified event (must be a valid positive integer)\n"
            + "Example: " + COMMAND_WORD + " addStudent 1 Tutorial/1";
    public static final String TUTORIAL_STRING = PREFIX_TUTORIAL.getPrefix();
    public static final String LAB_STRING = PREFIX_LAB.getPrefix();
    public static final String CONSULTATION_STRING = PREFIX_CONSULTATION.getPrefix();
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
    public CommandResult execute(Model model) throws CommandException, ParseException {
        requireNonNull(model);
        if (studentIndex.getZeroBased() < 0) {
            throw new CommandException(MESSAGE_STUDENT_INDEX_TOO_SMALL);
        }
        if (studentIndex.getZeroBased() >= model.getFilteredPersonList().size()) {
            throw new CommandException(MESSAGE_STUDENT_INDEX_TOO_BIG);
        }
        if (eventIndex.getZeroBased() < 0) {
            throw new CommandException(MESSAGE_EVENT_INDEX_TOO_SMALL);
        }
        switch (eventType) {
        case "Tutorial/":
            if (eventIndex.getZeroBased() >= model.getFilteredTutorialList().size()) {
                throw new CommandException(MESSAGE_EVENT_INDEX_TOO_BIG);
            }
            model.addStudentToTutorial(this.studentIndex, this.eventIndex);
            break;
        case "Lab/":
            if (eventIndex.getZeroBased() >= model.getFilteredLabList().size()) {
                throw new CommandException(MESSAGE_EVENT_INDEX_TOO_BIG);
            }
            model.addStudentToLab(this.studentIndex, this.eventIndex);
            break;
        case "Consultation/":
            if (eventIndex.getZeroBased() >= model.getFilteredConsultationList().size()) {
                throw new CommandException(MESSAGE_EVENT_INDEX_TOO_BIG);
            }
            model.addStudentToConsultation(this.studentIndex, this.eventIndex);
            break;
        default:
            throw new CommandException(MESSAGE_EVENT_TYPE_NOT_RECOGNIZED);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS),
                false, false, false, true);
    }
}
