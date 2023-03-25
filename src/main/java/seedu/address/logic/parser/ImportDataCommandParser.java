package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE_PATH;

import seedu.address.logic.commands.ImportDataCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ImportDataCommand object
 */
public class ImportDataCommandParser implements Parser<ImportDataCommand> {

    @Override
    public ImportDataCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FILE_PATH);


        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportDataCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.getValue(PREFIX_FILE_PATH).isPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportDataCommand.MESSAGE_USAGE));
        }

        return new ImportDataCommand(argMultimap.getValue(PREFIX_FILE_PATH).get());
    }

}
