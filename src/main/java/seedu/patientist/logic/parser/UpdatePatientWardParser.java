package seedu.patientist.logic.parser;

import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_WARD;

import java.util.stream.Stream;

import seedu.patientist.commons.core.index.Index;
import seedu.patientist.logic.commands.UpdatePatientWardCommand;
import seedu.patientist.logic.parser.exceptions.ParseException;
import seedu.patientist.model.ward.Ward;

/**
 * Parses input arguments and creates a new UpdatePatientWardCommand object
 */
public class UpdatePatientWardParser implements Parser<UpdatePatientWardCommand> {
    private Index index;
    /**
     * Parses the given {@code String} of arguments in the context of the UpdatePatientWardCommand
     * and returns a UpdatePatientWardCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdatePatientWardCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_WARD);
        if (!arePrefixesPresent(argMultimap, PREFIX_WARD) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UpdatePatientWardCommand.MESSAGE_USAGE));
        }
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble().trim());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdatePatientWardCommand.MESSAGE_USAGE), pe);
        }

        Ward ward = ParserUtil.parseWard(argMultimap.getValue(PREFIX_WARD).get());
        return new UpdatePatientWardCommand(index, ward.getWardName());
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
