package taa.logic.parser;

import taa.commons.core.Messages;
import taa.logic.commands.ExportCommand;
import taa.logic.parser.exceptions.ParseException;

/** Parses import command. Format: import FILE_NAME. */
public class ExportCommandParser extends CsvCommandParser {

    private static final String SYNTAX_ERR_MSG_FULL =
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MSG_USAGE);

    public ExportCommandParser() {
        super(ExportCommand::new);
    }

    @Override
    void throwParseException() throws ParseException {
        throw new ParseException(SYNTAX_ERR_MSG_FULL);
    }
}
