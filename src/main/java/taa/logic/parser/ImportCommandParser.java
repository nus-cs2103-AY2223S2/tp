package taa.logic.parser;

import java.io.File;

import taa.commons.core.Messages;
import taa.commons.util.StringUtil;
import taa.logic.commands.ImportCommand;
import taa.logic.parser.exceptions.ParseException;

/** Parses import command. Format: import FILE_NAME. */
public class ImportCommandParser implements Parser<ImportCommand> {
    private static final String FORCE_FLAG = "-force";
    private static final String SYNTAX_ERR_MSG_FULL =
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MSG_USAGE);

    @Override
    public ImportCommand parse(String args) throws ParseException {
        final String sanitizedArgs = StringUtil.rmAdjSpace(args).trim();
        if (sanitizedArgs.isEmpty()) {
            throw new ParseException(SYNTAX_ERR_MSG_FULL);
        }
        final String[] tokens = sanitizedArgs.split(" ");
        final boolean isForced=FORCE_FLAG.equals(tokens[0]);
        final int fileIdx = isForced ? 1 : 0;
        if (tokens.length > fileIdx + 1) {
            throw new ParseException(SYNTAX_ERR_MSG_FULL);
        }
        return new ImportCommand(new File(tokens[fileIdx]), isForced);
    }
}
