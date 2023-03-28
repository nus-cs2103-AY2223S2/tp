package seedu.address.logic.parser;

import java.util.regex.Pattern;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entity.Classification;

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
        boolean isValidCommand = Pattern.matches("^(all|selected)(\\s)(mob|char|item)$", args);
        if (!isValidCommand) {
            throw new ParseException("Invalid command!");
        }
        String[] split = args.trim().split("\\s+", 2);
        String clearType = split[0].toLowerCase();
        Classification classification = ParserUtil.parseClassification(split[1]);
        return new ClearCommand(clearType.equals("selected"), classification);

    }
}
