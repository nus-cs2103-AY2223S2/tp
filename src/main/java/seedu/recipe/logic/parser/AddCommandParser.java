package seedu.recipe.logic.parser;

import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_STEP;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.recipe.logic.commands.AddCommand;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.model.recipe.Description;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.Title;
import seedu.recipe.model.tag.Tag;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_INGREDIENT, PREFIX_TITLE,
                        PREFIX_STEP, PREFIX_TAG);

        if (args.length() == 0 || isNonePrefixPresent(argMultimap) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        if (!areAllPrefixesPresent(argMultimap, PREFIX_TITLE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.NO_TITLE_FAILURE));
        }

        if (!areAllPrefixesPresent(argMultimap, PREFIX_DESCRIPTION)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.NO_DESC_FAILURE));
        }

        if (!areAllPrefixesPresent(argMultimap, PREFIX_INGREDIENT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.NO_INGREDIENT_FAILURE));
        }

        if (!areAllPrefixesPresent(argMultimap, PREFIX_STEP)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.NO_STEP_FAILURE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Set<Ingredient> ingredients = ParserUtil.parseIngredients(argMultimap.getAllValues(PREFIX_INGREDIENT));
        List<Step> steps = ParserUtil.parseSteps(argMultimap.getAllValues(PREFIX_STEP));
        Set<Tag> tags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Recipe recipe = new Recipe(title, description, ingredients, steps, tags);

        return new AddCommand(recipe);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAllPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static boolean isNonePrefixPresent(ArgumentMultimap argumentMultimap) {
        Prefix[] prefixArray = {PREFIX_TITLE, PREFIX_DESCRIPTION, PREFIX_INGREDIENT, PREFIX_STEP};
        int numPresent = 0;
        for (int i = 0; i < prefixArray.length; i++) {
            if (argumentMultimap.getValue(prefixArray[i]).isPresent()) {
                numPresent++;
            }
        }
        return numPresent == 0;
    }

}
