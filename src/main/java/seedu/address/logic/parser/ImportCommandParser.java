package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ImportCommand object.
 */
public class ImportCommandParser implements Parser<ImportCommand> {
    private static final String RESET_KEYWORD = "reset";
    private static final String COMBINE_KEYWORD = "combine";

    /**
     * Creates a ImportCommand where isResetEnabled depends on the parsed user input.
     * If keyword is "combine" or no keyword is present, combine the imported dataset with the existing one.
     *
     * @param args The input from user.
     * @return ImportCommand with isResetEnabled set to true if the keyword "reset" is in the user input.
     * @throws ParseException If the command is in invalid format.
     */
    public ImportCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim().toLowerCase();
        if (trimmedArgs.isEmpty() || trimmedArgs.equals(COMBINE_KEYWORD)) {
            return new ImportCommand(false);
        } else if (trimmedArgs.equals(RESET_KEYWORD)) {
            return new ImportCommand(true);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
        }
    }

}
