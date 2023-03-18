package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.FindCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Person;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.predicates.AddressContainsKeywordPredicate;
import seedu.address.model.person.predicates.AgeIsEqualPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordPredicate;
import seedu.address.model.person.predicates.NricContainsKeywordPredicate;
import seedu.address.model.person.predicates.PhoneContainsDigitsPredicate;
import seedu.address.model.person.predicates.RiskLevelIsEqualPredicate;
import seedu.address.model.person.predicates.TagIsEqualPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_NRIC, PREFIX_AGE, PREFIX_RISK, PREFIX_TAG);

        List<Predicate<Person>> sharedfilterList = parseSharedPredicates(argMultimap);
        List<Predicate<Elderly>> elderlyOnlyfilterList = parseElderlyOnlyPredicates(argMultimap);
        List<Predicate<Volunteer>> volunteerOnlyfilterList = parseVolunteerOnlyPredicates(argMultimap);

        if ((sharedfilterList.size() + elderlyOnlyfilterList.size() + volunteerOnlyfilterList.size()) == 0) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        return new FindCommand(sharedfilterList, elderlyOnlyfilterList, volunteerOnlyfilterList);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and builds the list of person filters.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    private List<Predicate<Person>> parseSharedPredicates(ArgumentMultimap argMultimap)
            throws ParseException {
        List<Predicate<Person>> sharedFilterList = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            sharedFilterList.add(new NameContainsKeywordPredicate<>(
                    ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).fullName));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            sharedFilterList.add(new AddressContainsKeywordPredicate<>(
                    ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()).value));
        }
        if (argMultimap.getValue(PREFIX_NRIC).isPresent()) {
            sharedFilterList.add(new NricContainsKeywordPredicate<>(
                    ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get()).value));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            sharedFilterList.add(new PhoneContainsDigitsPredicate<>(
                    ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()).value));
        }
        if (argMultimap.getValue(PREFIX_AGE).isPresent()) {
            sharedFilterList.add(new AgeIsEqualPredicate<>(
                    ParserUtil.parseAge(argMultimap.getValue(PREFIX_AGE).get()).value));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            sharedFilterList.add(new EmailContainsKeywordPredicate<>(
                    ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()).value));
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            sharedFilterList.add(new TagIsEqualPredicate<>(
                    ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get()).tagName));
        }
        return sharedFilterList;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and builds the list of elderly filters.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    private List<Predicate<Elderly>> parseElderlyOnlyPredicates(ArgumentMultimap argMultimap)
            throws ParseException {
        List<Predicate<Elderly>> elderlyOnlyFilterList = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_RISK).isPresent()) {
            elderlyOnlyFilterList.add(new RiskLevelIsEqualPredicate<>(
                    ParserUtil.parseRiskLevel(argMultimap.getValue(PREFIX_RISK).get()).riskStatus));
        }

        // TODO: Medical conditions filter

        return elderlyOnlyFilterList;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and builds the list of volunteer filters.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    private List<Predicate<Volunteer>> parseVolunteerOnlyPredicates(ArgumentMultimap argMultimap)
            throws ParseException {
        List<Predicate<Volunteer>> volunteerOnlyFilterList = new ArrayList<>();

        // TODO: Medical qualifications filter

        return volunteerOnlyFilterList;
    }
}
