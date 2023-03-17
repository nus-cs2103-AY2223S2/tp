package seedu.address.logic.parser;


import static seedu.address.commons.core.Messages.MESSAGE_UNSUPPORTED_COMMAND;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddStaffCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates AddStaffCommand object
 */
public class AddStaffCommandParser implements Parser<AddStaffCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddStaffCommand
     * and returns an AddStaffCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStaffCommand parse(String args) throws ParseException {
        throw new ParseException(MESSAGE_UNSUPPORTED_COMMAND);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
