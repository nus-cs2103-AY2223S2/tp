package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

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
    public static final String MESSAGE_EVENT_TYPE_NOT_RECOGNIZED = "The event type that you have entered"
            + " cannot be recognized!";
    public static final String TUTORIAL_STRING = "tutorial";
    public static final String LAB_STRING = "lab";
    public static final String CONSULTATION_STRING = "consultation";
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

        if (this.eventType.equals(TUTORIAL_STRING)) {
            model.addStudentToTutorial(this.studentIndex, this.eventIndex);
        } else if (this.eventType.equals(LAB_STRING)) {
            model.addStudentToLab(this.studentIndex, this.eventIndex);
        } else if (this.eventType.equals(CONSULTATION_STRING)) {
            model.addStudentToConsultation(this.studentIndex, this.eventIndex);
        } else {
            throw new CommandException(MESSAGE_EVENT_TYPE_NOT_RECOGNIZED);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS), false, false, false, true);
    }
}
