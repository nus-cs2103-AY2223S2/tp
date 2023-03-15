package seedu.address.logic.parser;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HelpConsultationCommand;
import seedu.address.logic.commands.HelpLabCommand;
import seedu.address.logic.commands.HelpTutorialCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HelpCommand, HelpTutorialCommand,
 * HelpLabCommand or HelpConsultationCommand object
 */
public class HelpCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns an HelpCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public HelpCommand parse(String args) {
        if (args.equals(" tutorial")) {
            return new HelpTutorialCommand();
        } else if (args.equals(" lab")) {
            return new HelpLabCommand();
        } else if (args.equals(" consultation")) {
            return new HelpConsultationCommand();
        } else {
            return new HelpCommand();
        }
    }
}
