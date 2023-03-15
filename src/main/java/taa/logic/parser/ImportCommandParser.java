package taa.logic.parser;

import java.io.File;

import taa.commons.core.Messages;
import taa.logic.commands.ImportCommand;
import taa.logic.parser.exceptions.ParseException;

/** Parses import command. Format: import FILE_NAME. */
public class ImportCommandParser implements Parser<ImportCommand> {
    @Override
    public ImportCommand parse(String args) throws ParseException {
        args = args.trim();
        if (args.isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MSG_USAGE));
        }
        return new ImportCommand(new File(args));
    }
}
