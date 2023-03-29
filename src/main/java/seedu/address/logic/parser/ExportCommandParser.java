package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_OVERWRITE;

import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.storage.Storage;

/**
 * Parses input arguments and creates a new ExportCommand object
 */
public class ExportCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the ExportCommand
     * and returns an ExportCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public ExportCommand parse(String args, Storage storage) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_OVERWRITE);
        boolean isOverwritingExistingFile = false;

        String overwritingFileArgument = argMultimap.getValue(PREFIX_OVERWRITE).orElse("");

        if (overwritingFileArgument.equals("true")) {
            isOverwritingExistingFile = true;
        }

        String fileName = argMultimap.getPreamble();

        return new ExportCommand(fileName, storage, isOverwritingExistingFile);
    }
}
