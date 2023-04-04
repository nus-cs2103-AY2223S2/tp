package seedu.recipe.logic.parser;

import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_RECIPE;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_STEP;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.recipe.logic.commands.FindCommand;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.model.recipe.ContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_INGREDIENT, PREFIX_STEP,
                PREFIX_TAG, PREFIX_RECIPE, PREFIX_DESCRIPTION);
        if (!onlyOnePrefixPresent(argMultimap, PREFIX_TITLE, PREFIX_INGREDIENT, PREFIX_STEP, PREFIX_TAG, PREFIX_RECIPE,
                PREFIX_DESCRIPTION) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        Prefix p = getPrefix(argMultimap, PREFIX_TITLE, PREFIX_INGREDIENT, PREFIX_STEP, PREFIX_TAG, PREFIX_RECIPE,
                PREFIX_DESCRIPTION);
        String arguments = argMultimap.getValue(p).get();
        String[] recipeKeywords = arguments.split("\\s+");
        return new FindCommand(getPredicate(p, Arrays.asList(recipeKeywords), argMultimap));
    }

    private ContainsKeywordsPredicate getPredicate(Prefix prefix, List<String> keywords, ArgumentMultimap argMultimap)
            throws ParseException {
        if (prefix.equals(PREFIX_RECIPE)) {
            return ParserUtil.parseRecipePredicate(keywords, argMultimap);
        } else if (prefix.equals(PREFIX_DESCRIPTION)) {
            return ParserUtil.parseDescriptionPredicate(keywords, argMultimap);
        } else if (prefix.equals(PREFIX_INGREDIENT)) {
            return ParserUtil.parseIngredientsPredicate(keywords, argMultimap);
        } else if (prefix.equals(PREFIX_STEP)) {
            return ParserUtil.parseStepsPredicate(keywords, argMultimap);
        } else if (prefix.equals(PREFIX_TITLE)) {
            return ParserUtil.parseTitlePredicate(keywords, argMultimap);
        } else if (prefix.equals(PREFIX_TAG)) {
            return ParserUtil.parseTagsPredicate(keywords, argMultimap);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean onlyOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        boolean prefixPresent = Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
        boolean onlyOnePrefix = Stream.of(prefixes).filter(prefix ->
                argumentMultimap.getValue(prefix).isPresent()).count() == 1;
        return prefixPresent && onlyOnePrefix;
    }

    private static Prefix getPrefix(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix -> argumentMultimap.getValue(prefix).isPresent())
                .collect(Collectors.toList()).get(0);
    }

}
