package taa.logic.parser;

import taa.commons.core.Messages;
import taa.logic.commands.ImportCommand;
import taa.logic.parser.exceptions.ParseException;

/** Parses import command. Format: import FILE_NAME. */
public class ImportCommandParser extends CsvCommandParser {
    private static final String SYNTAX_ERR_MSG_FULL =
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MSG_USAGE);

    public ImportCommandParser() {
        super(ImportCommand::new);
    }

    @Override
    void throwParseException() throws ParseException {
        throw new ParseException(SYNTAX_ERR_MSG_FULL);
    }
}
