package codoc.logic.parser;

import static codoc.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static codoc.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static java.util.Objects.requireNonNull;

import codoc.commons.core.index.Index;
import codoc.logic.commands.ViewCommand;
import codoc.logic.parser.exceptions.ParseException;

import java.math.BigInteger;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String arg) throws ParseException {
        requireNonNull(arg);
        String trimmedArgs = arg.trim();
        if (isNumeric(trimmedArgs)) {
            Index index = ParserUtil.parseIndex(arg);
            return new ViewCommand(index);
        } else {
            if (trimmedArgs.equals("c") || trimmedArgs.equals("m") || trimmedArgs.equals("s")) {
                return new ViewCommand(trimmedArgs);
            }
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }
    }

    private boolean isNumeric(String arg) {
        try {
            Index num = ParserUtil.parseIndex(arg);
        } catch (ParseException e) { // Could be due to overflowing integer
            try {
                BigInteger val = new BigInteger(arg); // Try to use BigInteger
                // If it can parse into BigInteger
                return true;
            } catch (NumberFormatException nfe) { // BigInteger fails too.
                return false;
            }
        }
        return true;
    }

}
