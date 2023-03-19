package seedu.wife.logic.parser;

import static seedu.wife.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.wife.logic.commands.HelpCommand;
import seedu.wife.logic.parser.exceptions.ParseException;
import seedu.wife.model.food.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new HelpCommand object
 */
public class HelpCommandParser {
    public HelpCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
//        if (trimmedArgs.isEmpty()) {
//            throw new ParseException(
//                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
//        }

        //String[] nameKeywords = trimmedArgs.split("\\s+");

        return new HelpCommand(trimmedArgs);
    }
}
