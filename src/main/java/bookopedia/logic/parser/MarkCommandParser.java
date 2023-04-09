package bookopedia.logic.parser;

import static bookopedia.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static bookopedia.logic.parser.CliSyntax.PREFIX_STATUS;
import static java.util.Objects.requireNonNull;

import bookopedia.commons.core.index.Index;
import bookopedia.logic.commands.MarkCommand;
import bookopedia.logic.parser.exceptions.ParseException;
import bookopedia.model.DeliveryStatus;


/**
 * Parses input arguments and creates a new MarkCommand object
 */
public class MarkCommandParser implements Parser<MarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STATUS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE), pe);
        }

        DeliveryStatus deliveryStatus;

        if (argMultimap.getValue(PREFIX_STATUS).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        }

        try {
            deliveryStatus = ParserUtil.parseDeliveryStatus(argMultimap.getValue(PREFIX_STATUS).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE), pe);
        }

        return new MarkCommand(index, deliveryStatus);
    }

}
