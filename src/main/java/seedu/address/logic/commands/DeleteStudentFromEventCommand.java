package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
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
public class DeleteStudentFromEventCommand extends Command {
    public static final String COMMAND_WORD = "deleteStudent";
    public static final String MESSAGE_SUCCESS = "Student at specified index deleted from event";
    public static final String MESSAGE_EVENT_TYPE_NOT_RECOGNIZED = "The event type that you have entered"
            + "cannot be recognized!";

    public static final String MESSAGE_USAGE = "Delete Student from Event Syntax: "
            + COMMAND_WORD + " "
            + "STUDENT_INDEX EVENT_TYPE/EVENT_INDEX\n"
            + "Parameters: STUDENT_INDEX (must be a valid positive integer), "
            + "EVENT_TYPE (Only Tutorial or Consultation or Lab case-sensitive is allowed)\n"
            + " EVENT_INDEX (must be a valid positive integer)\n"
            + "Example: " + COMMAND_WORD + " deleteStudent 1 Tutorial/1";

    public static final String TUTORIAL_STRING = PREFIX_TUTORIAL.getPrefix();
    public static final String LAB_STRING = PREFIX_LAB.getPrefix();
    public static final String CONSULTATION_STRING = PREFIX_CONSULTATION.getPrefix();
    private final Index index;
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
        this.index = studentIndex;
        this.eventIndex = eventIndex;
        this.eventType = type;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, ParseException {
        requireNonNull(model);

        if (!this.eventType.equals(TUTORIAL_STRING)
                && !this.eventType.equals(LAB_STRING)
                && !this.eventType.equals(CONSULTATION_STRING)) {
            throw new CommandException(MESSAGE_EVENT_TYPE_NOT_RECOGNIZED);
        }

        model.deleteStudentFromEvent(this.index, this.eventIndex, this.eventType);

        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}
