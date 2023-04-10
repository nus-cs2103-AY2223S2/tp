package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnmarkAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new UnmarkAttendanceCommand object
 */
public class UnmarkAttendanceCommandParser {
    /**
     * informs user that he/she did not add the name field
     */
    public static final String MESSAGE_NAME_DOES_NOT_EXIST_PARSE_FAILURE = "Did not specify name field! \n%1$s";

    /**
     * Parses the given {@code String} of arguments in the context of the UnmarkAttendanceCommand
     * and returns an specific case of GroupAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnmarkAttendanceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UnmarkAttendanceCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getAllValues(PREFIX_NAME).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_NAME_DOES_NOT_EXIST_PARSE_FAILURE,
                    UnmarkAttendanceCommand.MESSAGE_USAGE));
        }

        Name personName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        return new UnmarkAttendanceCommand(index, personName);
    }
}
