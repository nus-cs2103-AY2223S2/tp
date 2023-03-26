package bookopedia.logic.parser;

import static bookopedia.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static bookopedia.logic.parser.CliSyntax.PREFIX_PARCEL;
import static bookopedia.logic.parser.CliSyntax.PREFIX_STATUS;
import static java.util.Objects.requireNonNull;

import bookopedia.commons.core.index.Index;
import bookopedia.logic.commands.MarkParcelCommand;
import bookopedia.logic.parser.exceptions.ParseException;
import bookopedia.model.ParcelStatus;


/**
 * Parses input arguments and creates a new MarkParcelCommand object
 */
public class MarkParcelCommandParser implements Parser<MarkParcelCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkParcelCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PARCEL, PREFIX_STATUS);

        Index personIndex;

        try {
            personIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkParcelCommand.MESSAGE_USAGE), pe);
        }

        Index parcelIndex;

        if (argMultimap.getValue(PREFIX_PARCEL).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkParcelCommand.MESSAGE_USAGE));
        }

        try {
            parcelIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PARCEL).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkParcelCommand.MESSAGE_USAGE), pe);
        }

        ParcelStatus parcelStatus;

        if (argMultimap.getValue(PREFIX_STATUS).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkParcelCommand.MESSAGE_USAGE));
        }

        try {
            parcelStatus = ParserUtil.parseParcelStatus(argMultimap.getValue(PREFIX_STATUS).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkParcelCommand.MESSAGE_USAGE), pe);
        }

        return new MarkParcelCommand(personIndex, parcelIndex, parcelStatus);
    }

}
