package seedu.address.logic.parser.drugparser;

import seedu.address.logic.commands.drugcommands.FindCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.drug.TradeNameContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

import java.util.Arrays;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

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
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, seedu.address.logic.commands.drugcommands.FindCommand.MESSAGE_USAGE));
        }

        String[] tradeNameKeywords = trimmedArgs.split("\\s+");

        return new seedu.address.logic.commands.drugcommands.FindCommand(new TradeNameContainsKeywordsPredicate(Arrays.asList(tradeNameKeywords)));
    }
}
