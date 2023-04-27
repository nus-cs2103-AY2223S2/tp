package seedu.wife.logic.parser;

import seedu.wife.commons.core.HelpMenu;
import seedu.wife.logic.commands.generalcommands.HelpCommand;
import seedu.wife.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HelpCommand object
 *
 * Dynamic help functionality inspired by AY2223 S1 Team W16-2
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public HelpCommand parse(String args) throws ParseException {
        assert !args.equals(null);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new HelpCommand(HelpMenu.getGeneralHelp());
        }

        HelpMenu helpCommand = HelpMenu.parseCommand(trimmedArgs);
        return new HelpCommand(HelpMenu.getCommandHelp(helpCommand));
    }
}
