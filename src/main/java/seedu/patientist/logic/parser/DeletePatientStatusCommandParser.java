package seedu.patientist.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import seedu.patientist.commons.core.index.Index;
import seedu.patientist.logic.commands.DeletePatientStatusCommand;
import seedu.patientist.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeletePatientStatusCommand object
 */
public class DeletePatientStatusCommandParser implements Parser<DeletePatientStatusCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeletePatientStatusCommand
     * and returns an DeletePatientStatusCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeletePatientStatusCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        Index indexPerson;
        Index indexStatus;

        try {
            String[] indexes = argMultimap.getPreamble().strip().split("\\s+");
            if (indexes.length != 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeletePatientStatusCommand.MESSAGE_USAGE));
            }
            indexPerson = ParserUtil.parseIndex(indexes[0]);
            indexStatus = ParserUtil.parseIndex(indexes[1]);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeletePatientStatusCommand.MESSAGE_USAGE), pe);
        }

        return new DeletePatientStatusCommand(indexPerson, indexStatus);

    }

}
