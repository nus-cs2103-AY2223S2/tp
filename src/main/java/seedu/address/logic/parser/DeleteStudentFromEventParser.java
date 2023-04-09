package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONSULTATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteStudentFromEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments while checking for validity of user inputs,
 * and creates a new DeleteStudentFromEventCommand object.
 */
public class DeleteStudentFromEventParser implements Parser<DeleteStudentFromEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteStudentFromEventCommand
     * and returns a DeleteStudentFromEventCommand object for execution.
     *
     * @param args the given string arguments to be parsed.
     * @return a DeleteStudentFromEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteStudentFromEventCommand parse(String args) throws ParseException {
        Index studentIndex;
        Index eventIndex;
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TUTORIAL, PREFIX_LAB, PREFIX_CONSULTATION);
        Optional<String> tutorialName = argMultimap.getValue(PREFIX_TUTORIAL);
        Optional<String> labName = argMultimap.getValue(PREFIX_LAB);
        Optional<String> consultationName = argMultimap.getValue(PREFIX_CONSULTATION);

        try {
            studentIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            eventIndex = ParserUtil.parseIndex(
                    tutorialName.orElse(labName.orElse(consultationName.orElse(""))));
        } catch (ParseException pe) {
            throw pe;
        }

        if (tutorialName.isEmpty() && labName.isEmpty() && consultationName.isEmpty()) {
            throw new ParseException(DeleteStudentFromEventCommand.MESSAGE_EVENT_TYPE_NOT_RECOGNIZED
                    + DeleteStudentFromEventCommand.MESSAGE_USAGE);
        }

        String eventType = PREFIX_TUTORIAL.getPrefix();
        if (!labName.isEmpty()) {
            eventType = PREFIX_LAB.getPrefix();
        }
        if (!consultationName.isEmpty()) {
            eventType = PREFIX_CONSULTATION.getPrefix();
        }

        return new DeleteStudentFromEventCommand(studentIndex, eventIndex, eventType);
    }
}
