package seedu.careflow.logic.parser.drugparser;

import static seedu.careflow.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.careflow.logic.commands.drugcommands.FindCommand;
import seedu.careflow.logic.parser.Parser;
import seedu.careflow.logic.parser.exceptions.ParseException;
import seedu.careflow.model.drug.TradeNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public FindCommand parse(String userInput) throws ParseException {
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(
                            MESSAGE_INVALID_COMMAND_FORMAT,
                            seedu.careflow.logic.commands.drugcommands.FindCommand.MESSAGE_USAGE));
        }

        String[] tradeNameKeywords = trimmedArgs.split("\\s+");

        return new seedu.careflow.logic.commands.drugcommands.FindCommand(
                new TradeNameContainsKeywordsPredicate(Arrays.asList(tradeNameKeywords)));
    }
}
