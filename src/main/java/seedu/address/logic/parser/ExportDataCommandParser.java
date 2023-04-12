package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_FILEPATH;

import seedu.address.logic.commands.ExportDataCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ImportDataCommand object
 */
public class ExportDataCommandParser implements Parser<ExportDataCommand> {

    @Override
    public ExportDataCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FILEPATH);

        String filePath = ParserUtil.parseFilePath(argMultimap.getValue(PREFIX_FILEPATH));

        return new ExportDataCommand(filePath);
    }

}
