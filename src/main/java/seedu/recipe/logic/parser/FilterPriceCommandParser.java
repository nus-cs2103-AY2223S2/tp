package seedu.recipe.logic.parser;

import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Filter;

import javafx.util.Pair;
import seedu.recipe.commons.core.index.Index;
import seedu.recipe.logic.commands.FilterPriceCommand;
import seedu.recipe.logic.commands.StarCommand;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.model.recipe.SatisfyPriceConditionPredicate;

/**
 * Parses input arguments and creates a new FilterPriceCommand object
 */
public class FilterPriceCommandParser implements Parser<FilterPriceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterPriceCommand
     * and returns a FilterPriceCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public FilterPriceCommand parse(String args) throws ParseException {
        try {
            SatisfyPriceConditionPredicate predicate = ParserUtil.parseFilterPrice(args);
            return new FilterPriceCommand(predicate);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterPriceCommand.MESSAGE_USAGE), pe);
        }
    }
}
