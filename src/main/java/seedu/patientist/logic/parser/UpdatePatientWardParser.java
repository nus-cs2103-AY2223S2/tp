package seedu.patientist.logic.parser;

import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_TODO;
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
        ArgumentMultimap argMultimap2 =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID, PREFIX_STATUS, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_PRIORITY, PREFIX_TODO);
        if (areAnyPrefixesPresent(argMultimap2, PREFIX_NAME, PREFIX_ID, PREFIX_STATUS, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_PRIORITY, PREFIX_TODO)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UpdatePatientWardCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getAllValues(PREFIX_WARD).size() != 1) {
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

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
