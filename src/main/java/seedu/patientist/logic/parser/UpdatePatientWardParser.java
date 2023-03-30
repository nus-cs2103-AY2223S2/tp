package seedu.patientist.logic.parser;

import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_WARD;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import seedu.patientist.commons.core.index.Index;
import seedu.patientist.logic.commands.UpdatePatientWardCommand;
import seedu.patientist.logic.parser.exceptions.ParseException;

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
        try {
            index = ParserUtil.parseIndex(args.substring(1, args.indexOf("w/") - 1));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdatePatientWardCommand.MESSAGE_USAGE), pe);
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_WARD);
        if (!arePrefixesPresent(argMultimap, PREFIX_WARD)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UpdatePatientWardCommand.MESSAGE_USAGE));
        }

        List<String> wardList = new ArrayList<>();
        wardList.addAll(argMultimap.getAllValues(PREFIX_WARD));
        if (wardList.size() != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UpdatePatientWardCommand.MESSAGE_USAGE));
        }
        return new UpdatePatientWardCommand(index, wardList.get(0), wardList.get(1));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
