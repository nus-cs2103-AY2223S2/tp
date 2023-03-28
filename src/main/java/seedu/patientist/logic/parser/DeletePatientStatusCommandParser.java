package seedu.patientist.logic.parser;

import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_INDEX;

import java.util.List;
import java.util.stream.Stream;

import seedu.patientist.commons.core.index.Index;
import seedu.patientist.logic.commands.DeletePatientStatusCommand;
import seedu.patientist.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeletePatientStatusCommand object
 */
public class DeletePatientStatusCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the DeletePatientStatusCommand
     * and returns an DeletePatientStatusCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeletePatientStatusCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_INDEX) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeletePatientStatusCommand.MESSAGE_USAGE));
        }

        List<Index> indexes = ParserUtil.parseIndexes(argMultimap.getAllValues(PREFIX_INDEX));

        if (indexes.size() != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeletePatientStatusCommand.MESSAGE_USAGE));
        }

        return new DeletePatientStatusCommand(indexes.get(0), indexes.get(1));

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
