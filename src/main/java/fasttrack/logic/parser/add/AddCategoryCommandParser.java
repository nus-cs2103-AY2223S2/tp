package fasttrack.logic.parser.add;

import static fasttrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static fasttrack.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static fasttrack.logic.parser.CliSyntax.PREFIX_SUMMARY;

import java.util.stream.Stream;

import fasttrack.logic.commands.add.AddCategoryCommand;
import fasttrack.logic.parser.ArgumentMultimap;
import fasttrack.logic.parser.ArgumentTokenizer;
import fasttrack.logic.parser.Parser;
import fasttrack.logic.parser.ParserUtil;
import fasttrack.logic.parser.Prefix;
import fasttrack.logic.parser.exceptions.ParseException;
import fasttrack.model.category.Category;

/**
 * Parses input arguments and creates a new AddCategoryCommand object
 */
public class AddCategoryCommandParser implements Parser<AddCategoryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCategoryCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCategoryCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY, PREFIX_SUMMARY);

        if (!arePrefixesPresent(argMultimap, PREFIX_CATEGORY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCategoryCommand.MESSAGE_USAGE));
        }
        String summary = "";
        if (arePrefixesPresent(argMultimap, PREFIX_SUMMARY)) {
            summary = argMultimap.getValue(PREFIX_SUMMARY).get();
        }

        Category category = ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get(), summary);

        return new AddCategoryCommand(category);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
