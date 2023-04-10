package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.NameContainsAllKeywordsPredicate;
import seedu.address.model.employee.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object.
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @param args the arguments in String form.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] nameKeywords = trimmedArgs.split("\\s+");
        int length = nameKeywords.length;
        boolean hasAsterisk = ParserUtil.parseAsterisk(nameKeywords);
        boolean isEmpty = trimmedArgs.isEmpty();
        boolean isEmptyAfterAsterisk = hasAsterisk && length == 1;
        if (isEmpty || isEmptyAfterAsterisk) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        if (hasAsterisk) {
            String[] nameKeywordsAsteriskRemoved = Arrays.copyOfRange(nameKeywords, 1, length);
            return new FindCommand(new NameContainsAllKeywordsPredicate(
                    Arrays.asList(nameKeywordsAsteriskRemoved)));
        }
        return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
