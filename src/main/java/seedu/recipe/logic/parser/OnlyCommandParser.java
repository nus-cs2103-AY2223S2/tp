package seedu.recipe.logic.parser;

import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.recipe.logic.commands.OnlyCommand;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.model.recipe.RecipeIngredientsSubsetPredicate;

/**
 * Parses input arguments and creates a new OnlyCommand object
 */
public class OnlyCommandParser implements Parser<OnlyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the OnlyCommand
     * and returns a OnlyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public OnlyCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, OnlyCommand.MESSAGE_USAGE));
        }
        String[] keywords = trimmedArgs.split("\\s+");
        RecipeIngredientsSubsetPredicate recipePredicate =
                new RecipeIngredientsSubsetPredicate(Arrays.asList(keywords));
        return new OnlyCommand(recipePredicate);
    }

}
