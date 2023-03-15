package seedu.fitbook.logic.parser;

import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_PREFIX;
import static seedu.fitbook.commons.core.Messages.MESSAGE_NO_ARGS;
import static seedu.fitbook.commons.core.Messages.MESSAGE_NO_PREFIX;

import java.util.Arrays;

import seedu.fitbook.logic.commands.FindCommand;
import seedu.fitbook.logic.parser.exceptions.ParseException;
import seedu.fitbook.model.client.predicate.AddressContainsKeywordsPredicate;
import seedu.fitbook.model.client.predicate.AppointmentContainsKeywordsPredicate;
import seedu.fitbook.model.client.predicate.CalorieContainsKeywordsPredicate;
import seedu.fitbook.model.client.predicate.EmailContainsKeywordsPredicate;
import seedu.fitbook.model.client.predicate.GenderContainsKeywordsPredicate;
import seedu.fitbook.model.client.predicate.GoalContainsKeywordsPredicate;
import seedu.fitbook.model.client.predicate.NameContainsKeywordsPredicate;
import seedu.fitbook.model.client.predicate.PhoneContainsKeywordsPredicate;
import seedu.fitbook.model.client.predicate.TagContainsKeywordsPredicate;
import seedu.fitbook.model.client.predicate.WeightContainsKeywordsPredicate;

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

        checkParseStringFormat(trimmedArgs);

        String prefix = trimmedArgs.substring(0, trimmedArgs.indexOf('/'));
        String[] allKeywords = {trimmedArgs.substring(trimmedArgs.indexOf('/') + 1)};
        if (prefix.length() == 1) {
            char prefixChar = prefix.charAt(0);
            switch (prefixChar) {
            case NameContainsKeywordsPredicate.PREFIX_PREDICATE:
                return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(allKeywords)));
            case PhoneContainsKeywordsPredicate.PREFIX_PREDICATE:
                return new FindCommand(new PhoneContainsKeywordsPredicate(Arrays.asList(allKeywords)));
            case EmailContainsKeywordsPredicate.PREFIX_PREDICATE:
                return new FindCommand(new EmailContainsKeywordsPredicate(Arrays.asList(allKeywords)));
            case AddressContainsKeywordsPredicate.PREFIX_PREDICATE:
                return new FindCommand(new AddressContainsKeywordsPredicate(Arrays.asList(allKeywords)));
            case TagContainsKeywordsPredicate.PREFIX_PREDICATE:
                return new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList(allKeywords)));
            case WeightContainsKeywordsPredicate.PREFIX_PREDICATE:
                return new FindCommand(new WeightContainsKeywordsPredicate(Arrays.asList(allKeywords)));
            case GenderContainsKeywordsPredicate.PREFIX_PREDICATE:
                return new FindCommand(new GenderContainsKeywordsPredicate(Arrays.asList(allKeywords)));
            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_PREFIX, FindCommand.PREFIX_USAGE));
            }
        } else if (prefix.length() > 1) {
            if (prefix.equals(CalorieContainsKeywordsPredicate.PREFIX_PREDICATE)) {
                return new FindCommand(new CalorieContainsKeywordsPredicate(Arrays.asList(allKeywords)));
            } else if (prefix.equals(AppointmentContainsKeywordsPredicate.PREFIX_PREDICATE)) {
                return new FindCommand(new AppointmentContainsKeywordsPredicate(Arrays.asList(allKeywords)));
            } else if (prefix.equals(GoalContainsKeywordsPredicate.PREFIX_PREDICATE)) {
                return new FindCommand(new GoalContainsKeywordsPredicate(Arrays.asList(allKeywords)));
            }
        }
        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    /**
     * Checks the format of the given {@code String} of arguments in the context of the FindCommand.
     * @throws ParseException if the user input does not conform the expected format
     */
    public void checkParseStringFormat(String trimmedString) throws ParseException {
        if (trimmedString.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_NO_ARGS, FindCommand.MESSAGE_USAGE));
        }

        if (!trimmedString.contains("/")) {
            throw new ParseException(
                    String.format(MESSAGE_NO_PREFIX, FindCommand.PREFIX_USAGE));
        }

        if (trimmedString.substring(trimmedString.indexOf('/') + 1).isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_NO_ARGS, FindCommand.MESSAGE_USAGE));
        }
    }
}
