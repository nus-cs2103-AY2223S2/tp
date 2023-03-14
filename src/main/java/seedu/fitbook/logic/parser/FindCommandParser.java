package seedu.fitbook.logic.parser;

import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.fitbook.logic.commands.FindCommand;
import seedu.fitbook.logic.parser.exceptions.ParseException;
import seedu.fitbook.model.client.predicate.*;

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
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (!trimmedArgs.contains("/")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String prefix = trimmedArgs.substring(0, trimmedArgs.indexOf('/'));
        String[] allKeywords = {trimmedArgs.substring(trimmedArgs.indexOf('/') + 1)};
        if (prefix.length() == 1) {
            char prefixChar = prefix.charAt(0);
            switch (prefixChar) {
                case 'n':
                    return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(allKeywords)));
                case 'p':
                    return new FindCommand(new PhoneContainsKeywordsPredicate(Arrays.asList(allKeywords)));
                case 'e':
                    return new FindCommand(new EmailContainsKeywordsPredicate(Arrays.asList(allKeywords)));
                case 'a':
                    return new FindCommand(new AddressContainsKeywordsPredicate(Arrays.asList(allKeywords)));
                case 't':
                    return new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList(allKeywords)));
                case 'w':
                    return new FindCommand(new WeightContainsKeywordsPredicate(Arrays.asList(allKeywords)));
                case 'g':
                    return new FindCommand(new GenderContainsKeywordsPredicate(Arrays.asList(allKeywords)));
                default:
                // Handle unexpected input
                throw new IllegalArgumentException("Invalid prefix: " + prefixChar);
            }
        } else if (prefix.length() > 1) {
            if (prefix.equals("cal")) {
                return new FindCommand(new CalorieContainsKeywordsPredicate(Arrays.asList(allKeywords)));
            } else if (prefix.equals("app")) {
                return new FindCommand(new AppointmentContainsKeywordsPredicate(Arrays.asList(allKeywords)));
            }
        }
        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }
}
