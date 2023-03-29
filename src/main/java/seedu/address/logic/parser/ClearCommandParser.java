package seedu.address.logic.parser;

import java.util.regex.Pattern;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ClearCommand object
 */
public class ClearCommandParser implements Parser<ClearCommand> {


    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param args
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public ClearCommand parse(String args) throws ParseException {
        args = args.trim();
        boolean isValidCommand = Pattern.matches("^(all|selected)$", args);
        if (!isValidCommand) {
            throw new ParseException("Invalid command!");
        }

        return new ClearCommand(args.equals("selected"));

    }
}
