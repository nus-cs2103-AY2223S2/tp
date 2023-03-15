package seedu.fitbook.logic.parser;

import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_PREFIX;
import static seedu.fitbook.commons.core.Messages.MESSAGE_NO_KEYWORD;
import static seedu.fitbook.commons.core.Messages.MESSAGE_NO_PREFIX;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_CALORIE;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.fitbook.logic.commands.FindCommand;
import seedu.fitbook.logic.parser.exceptions.ParseException;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.client.predicate.AddressContainsKeywordsPredicate;
import seedu.fitbook.model.client.predicate.AppointmentContainsKeywordsPredicate;
import seedu.fitbook.model.client.predicate.CalorieContainsKeywordsPredicate;
import seedu.fitbook.model.client.predicate.EmailContainsKeywordsPredicate;
import seedu.fitbook.model.client.predicate.GenderContainsKeywordsPredicate;
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

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_APPOINTMENT, PREFIX_WEIGHT, PREFIX_GENDER, PREFIX_CALORIE, PREFIX_TAG);

        checkParseStringFormat(argMultimap, args);

        List<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);
        for (String nameKeyword : nameKeywords) {
            System.out.println(nameKeyword);
        }
        List<String> phoneKeywords = argMultimap.getAllValues(PREFIX_PHONE);
        List<String> emailKeywords = argMultimap.getAllValues(PREFIX_EMAIL);
        List<String> addressKeywords = argMultimap.getAllValues(PREFIX_ADDRESS);
        List<String> tagKeywords = argMultimap.getAllValues(PREFIX_TAG);
        List<String> weightKeywords = argMultimap.getAllValues(PREFIX_WEIGHT);
        List<String> genderKeywords = argMultimap.getAllValues(PREFIX_GENDER);
        List<String> calorieKeywords = argMultimap.getAllValues(PREFIX_CALORIE);
        List<String> appointmentKeywords = argMultimap.getAllValues(PREFIX_APPOINTMENT);

        List<Predicate<Client>> predicates = new ArrayList<>();
        if (!nameKeywords.isEmpty()) {
            predicates.add(new NameContainsKeywordsPredicate(nameKeywords));
        }
        if (!phoneKeywords.isEmpty()) {
            predicates.add(new PhoneContainsKeywordsPredicate(phoneKeywords));
        }
        if (!emailKeywords.isEmpty()) {
            predicates.add(new EmailContainsKeywordsPredicate(emailKeywords));
        }
        if (!addressKeywords.isEmpty()) {
            predicates.add(new AddressContainsKeywordsPredicate(addressKeywords));
        }
        if (!tagKeywords.isEmpty()) {
            predicates.add(new TagContainsKeywordsPredicate(tagKeywords));
        }
        if (!weightKeywords.isEmpty()) {
            predicates.add(new WeightContainsKeywordsPredicate(weightKeywords));
        }
        if (!genderKeywords.isEmpty()) {
            predicates.add(new GenderContainsKeywordsPredicate(genderKeywords));
        }
        if (!calorieKeywords.isEmpty()) {
            predicates.add(new CalorieContainsKeywordsPredicate(calorieKeywords));
        }
        if (!appointmentKeywords.isEmpty()) {
            predicates.add(new AppointmentContainsKeywordsPredicate(appointmentKeywords));
        }
        return new FindCommand(predicates);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAvailablePrefixes(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Checks the format of the given {@code String} of arguments in the context of the FindCommand.
     * @throws ParseException if the user input does not conform the expected format
     */
    public void checkParseStringFormat(ArgumentMultimap argMultimap, String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (args.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (trimmedArgs.substring(trimmedArgs.indexOf('/') + 1).isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_NO_KEYWORD, FindCommand.MESSAGE_USAGE));
        }

        if (!trimmedArgs.contains("/")) {
            throw new ParseException(
                    String.format(MESSAGE_NO_PREFIX, FindCommand.MESSAGE_USAGE));
        }

        if (!areAvailablePrefixes(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_WEIGHT,
                PREFIX_GENDER, PREFIX_EMAIL, PREFIX_TAG, PREFIX_APPOINTMENT, PREFIX_CALORIE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_PREFIX, FindCommand.PREFIX_USAGE));
        }

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }
}
