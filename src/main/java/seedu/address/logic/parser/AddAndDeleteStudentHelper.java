package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CONSULTATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddStudentToEventCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
/**
 * Aids in checking validity of inputs for AddStudentToEventParser and DeleteStudentFromEventParser.
 * Deals with common checks between the 2 classes.
 */
public class AddAndDeleteStudentHelper {
    public static int FIELD_COUNT = 2;
    public static int NUM_OF_EACH_FIELD = 1;
    public static final String MESSAGE_TOO_MANY_FIELDS = "There are too many fields or duplicated fields "
            + "in your input!";
    public static final String MESSAGE_EVENT_TYPE_NOT_RECOGNIZED = "Either you did not enter an "
            + "event type or the event type that you have entered "
            + "cannot be recognized!\n";

    protected static void checkFieldCount(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getSize() > FIELD_COUNT
                || argMultimap.getAllValues(PREFIX_TUTORIAL).size() > NUM_OF_EACH_FIELD
                || argMultimap.getAllValues(PREFIX_LAB).size() > NUM_OF_EACH_FIELD
                || argMultimap.getAllValues(PREFIX_CONSULTATION).size() > NUM_OF_EACH_FIELD) {
            throw new ParseException(MESSAGE_TOO_MANY_FIELDS);
        }
    }

    protected static void checkIfFieldsAreEmpty(Optional<String> tutorialInd,
                                         Optional<String> labInd,
                                         Optional<String> consultationInd) throws ParseException {
        if (tutorialInd.isEmpty() && labInd.isEmpty() && consultationInd.isEmpty()) {
            throw new ParseException(MESSAGE_EVENT_TYPE_NOT_RECOGNIZED);
        }
    }

    protected static String getEventType(Optional<String> tutorialInd,
                                Optional<String> labInd,
                                Optional<String> consultationInd) {
        String eventType = "";
        if (tutorialInd.isPresent()) {
            eventType = PREFIX_TUTORIAL.getPrefix();
        }
        if (labInd.isPresent()) {
            eventType = PREFIX_LAB.getPrefix();
        }
        if (consultationInd.isPresent()) {
            eventType = PREFIX_CONSULTATION.getPrefix();
        }
        return eventType;
    }
}
