package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONSULTATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddStudentToEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments while checking for validity of the user input,
 * and creates a new AddStudentToEventCommand object.
 */
public class AddStudentToEventParser implements Parser<AddStudentToEventCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddStudentToEventCommand
     * and returns an AddStudentToEventCommand object for execution.
     *
     * @param args the given string arguments to be parsed.
     * @return an AddStudentToEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AddStudentToEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TUTORIAL, PREFIX_LAB, PREFIX_CONSULTATION);

        Optional<String> tutorialName = argMultimap.getValue(PREFIX_TUTORIAL);
        Optional<String> labName = argMultimap.getValue(PREFIX_LAB);
        Optional<String> consultationName = argMultimap.getValue(PREFIX_CONSULTATION);

        try {
            AddAndDeleteStudentHelper.checkFieldCount(argMultimap);
        } catch (ParseException e) {
            throw new ParseException(AddStudentToEventCommand.MESSAGE_TOO_MANY_FIELDS);
        }

        try {
            AddAndDeleteStudentHelper.checkIfFieldsAreEmpty(tutorialName, labName, consultationName);
        } catch (ParseException e) {
            throw new ParseException(AddStudentToEventCommand.MESSAGE_EVENT_TYPE_NOT_RECOGNIZED
                    + AddStudentToEventCommand.MESSAGE_USAGE);
        }

        Index studentIndex;
        Index eventIndex;
        try {
            studentIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            // studentIndex is not a non-zero integer
            throw new ParseException(AddStudentToEventCommand.MESSAGE_STUDENT_INDEX_INVALID);
        }
        try {
            eventIndex = ParserUtil.parseIndex(
                    tutorialName.orElse(labName.orElse(consultationName.orElse(""))));
        } catch (ParseException pe) {
            // eventIndex not a non-zero integer
            throw new ParseException(AddStudentToEventCommand.MESSAGE_EVENT_INDEX_INVALID);
        }

        String eventType = AddAndDeleteStudentHelper.getEventType(tutorialName, labName, consultationName);

        return new AddStudentToEventCommand(studentIndex, eventIndex, eventType);
    }
}
