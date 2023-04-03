package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ExportCommand object.
 */
public class ExportCommandParser implements Parser<ExportCommand> {
    private static final String ALL_KEYWORD = "all";
    private static final String SHOWN_KEYWORD = "shown";

    /**
     * Creates a ExportCommand where isAllEnabled depends on the parsed user input.
     * If keyword is "shown" or no keyword is present, export only the filtered list.
     *
     * @param args The input from user.
     * @return ExportCommand with isAllEnabled set to true if the keyword "all" is in the user input.
     * @throws ParseException If the command is in invalid format.
     */
    public ExportCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim().toLowerCase();
        if (trimmedArgs.isEmpty() || trimmedArgs.equals(SHOWN_KEYWORD)) {
            return new ExportCommand(false);
        } else if (trimmedArgs.equals(ALL_KEYWORD)) {
            return new ExportCommand(true);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE));
        }
    }

}
