package seedu.address.logic.parser;


import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSION;

import java.util.ArrayList;
import java.util.stream.Stream;

import seedu.address.logic.commands.CreateSessionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.session.Location;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionName;
/**
 * Parses input arguments and creates a new CreateSessionCommand object.
 */
public class CreateSessionCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateSessionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SESSION,
                        PREFIX_LOCATION);
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_SESSION,
                PREFIX_LOCATION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateSessionCommand.MESSAGE_USAGE));
        }

        SessionName name = ParserUtil.parseSessionName(argMultimap.getValue(PREFIX_NAME).get());
        ArrayList<String> startEnd = ParserUtil.parseSession(argMultimap.getValue(PREFIX_SESSION).get());
        String startDateTime = startEnd.get(0);
        String endDateTime = startEnd.get(1);
        Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get());

        try {
            Session session = new Session(startDateTime, endDateTime, name, location);
            return new CreateSessionCommand(session);
        } catch (IllegalArgumentException argE) {
            throw new ParseException(argE.getMessage());
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
