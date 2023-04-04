package trackr.logic.parser.menu;

import static java.util.Objects.requireNonNull;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;

import trackr.logic.commands.menu.FindMenuItemCommand;
import trackr.logic.parser.ArgumentMultimap;
import trackr.logic.parser.ArgumentTokenizer;
import trackr.logic.parser.Parser;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.menu.ItemNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindMenuItemCommand object
 */
public class FindMenuItemCommandParser implements Parser<FindMenuItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTaskCommand
     * and returns a FindMenuItemCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindMenuItemCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        ItemNameContainsKeywordsPredicate predicate = new ItemNameContainsKeywordsPredicate();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String[] itemNameKeywords = argMultimap.getValue(PREFIX_NAME).get().trim().split("\\s+");
            predicate.setItemNameKeywords(Arrays.asList(itemNameKeywords));
        }
        return new FindMenuItemCommand(predicate);
    }

}
