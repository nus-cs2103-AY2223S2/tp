package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ExportProgressCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Parses input arguments and creates a new ExportProgressCommand object
 */
public class ExportProgressCommandParser implements Parser<ExportProgressCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ExportProgressCommand
     * and returns an ExportProgressCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ExportProgressCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_FILENAME, PREFIX_FILEPATH);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportProgressCommand.MESSAGE_USAGE));
        }

        String fileName = ParserUtil.parseFileName(argMultimap.getValue(PREFIX_FILENAME));
        String filePath = ParserUtil.parseFilePath(argMultimap.getValue(PREFIX_FILEPATH));

        return new ExportProgressCommand(index, fileName, filePath);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
