package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLATFORM;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddPlatformCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.platform.Platform;

/**
 * Parses input arguments and creates a new AddPlatformCommand object
 */
public class AddPlatformCommandParser implements Parser<AddPlatformCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPlatformCommand
     * and returns an AddPlatformCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPlatformCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PLATFORM);

        if (!arePrefixesPresent(argMultimap, PREFIX_PLATFORM)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPlatformCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddPlatformCommand.MESSAGE_USAGE), pe);
        }

        Platform platformToAdd = ParserUtil.parsePlatform(argMultimap.getValue(PREFIX_PLATFORM).get());

        return new AddPlatformCommand(index, platformToAdd);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
