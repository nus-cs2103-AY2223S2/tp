package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OVERWRITE;

import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ExportCommand object
 */
public class ExportCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the ExportCommand
     * and returns an ExportCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public ExportCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_OVERWRITE);
        boolean isOverwritingExistingFile = false;

        String overwritingFileArgument = argMultimap.getValue(PREFIX_OVERWRITE).orElse("").trim();

        if (overwritingFileArgument.equals("true")) {
            isOverwritingExistingFile = true;
        }

        String fileName = argMultimap.getPreamble().trim();

        if (fileName.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE));
        }

        return new ExportCommand(fileName, isOverwritingExistingFile);
    }
}
