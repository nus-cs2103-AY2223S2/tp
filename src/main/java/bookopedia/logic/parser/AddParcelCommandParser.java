package bookopedia.logic.parser;

import static bookopedia.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static bookopedia.logic.parser.CliSyntax.PREFIX_PARCEL;
import static java.util.Objects.requireNonNull;

import bookopedia.commons.core.index.Index;
import bookopedia.logic.commands.AddParcelCommand;
import bookopedia.logic.parser.exceptions.ParseException;
import bookopedia.model.parcel.Parcel;

/**
 * Parses input arguments and creates a new AddParcelCommand object
 */
public class AddParcelCommandParser implements Parser<AddParcelCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddParcelCommand
     * and returns a AddParcelCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddParcelCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PARCEL);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddParcelCommand.MESSAGE_USAGE), pe);
        }

        Parcel parcel;

        if (argMultimap.getValue(PREFIX_PARCEL).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddParcelCommand.MESSAGE_USAGE));
        }

        try {
            parcel = ParserUtil.parseParcel(argMultimap.getValue(PREFIX_PARCEL).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddParcelCommand.MESSAGE_USAGE), pe);
        }

        return new AddParcelCommand(index, parcel);
    }
}
