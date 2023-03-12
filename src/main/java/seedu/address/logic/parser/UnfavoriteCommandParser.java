package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnfavoriteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
/**
 * Parses input arguments and creates a new UnfavoriteCommand object
 */
public class UnfavoriteCommandParser implements Parser<UnfavoriteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the StarCommand
     * and returns a StarCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnfavoriteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Optional<Index> index;

        try {
            index = ParserUtil.parseIndex(args);
            if (index.isEmpty()) {
                throw new ParseException(MESSAGE_INVALID_INDEX);
            }
            return new UnfavoriteCommand(index.get());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnfavoriteCommand.MESSAGE_USAGE), pe);
        }
    }
}
