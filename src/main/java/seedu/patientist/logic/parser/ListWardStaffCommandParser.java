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

import seedu.patientist.logic.commands.ListWardStaffCommand;
import seedu.patientist.logic.parser.exceptions.ParseException;
import seedu.patientist.model.ward.Ward;

/**
 * Finds and lists all staff in patientist book whose ward contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ListWardStaffCommandParser implements Parser<ListWardStaffCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListWardStaffCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty() || !Ward.isValidWardName(trimmedArgs)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListWardStaffCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID, PREFIX_STATUS, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_PRIORITY, PREFIX_TODO, PREFIX_WARD);
        if (areAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ID, PREFIX_STATUS, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_PRIORITY, PREFIX_TODO, PREFIX_WARD)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListWardStaffCommand.MESSAGE_USAGE));
        }

        return new ListWardStaffCommand(trimmedArgs);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
