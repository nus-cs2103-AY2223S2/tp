package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteSessionCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DeleteSessionCommand object.
 */
public class DeleteSessionCommandParser {
    /**
     * Parses the given {@code args} string into a {@code DeleteSessionCommand} object.
     * @param args The user input string to be parsed.
     * @return A DeleteSessionCommand object representing the parsed user input.
     * @throws ParseException If the user input string does not conform to the expected format.
     */
    public DeleteSessionCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteSessionCommand.MESSAGE_USAGE), pe);
        }

        return new DeleteSessionCommand(index);
    }
}
