package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NO_FIELD_PROVIDED;
import static seedu.address.logic.commands.FindCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAILABILITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTH_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REGION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.PrefixUtil;
import seedu.address.logic.commands.CommandInfo;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.exceptions.RecommendationException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Person;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.AvailableDate;
import seedu.address.model.person.predicates.AddressContainsKeywordPredicate;
import seedu.address.model.person.predicates.AvailableDatesWithinRangePredicate;
import seedu.address.model.person.predicates.BirthDateEqualPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordPredicate;
import seedu.address.model.person.predicates.MedicalQualificationContainsKeywordPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordPredicate;
import seedu.address.model.person.predicates.NricContainsKeywordPredicate;
import seedu.address.model.person.predicates.PhoneContainsDigitsPredicate;
import seedu.address.model.person.predicates.RegionIsEqualPredicate;
import seedu.address.model.person.predicates.RiskLevelIsEqualPredicate;
import seedu.address.model.person.predicates.SkillLevelIsEqualPredicate;
import seedu.address.model.person.predicates.TagContainsKeywordPredicate;

/**
 * Parses input arguments and creates a new FindCommand object.
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @param args Arguments.
     * @return {@code FindCommand} for execution.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_NRIC, PREFIX_BIRTH_DATE, PREFIX_RISK, PREFIX_REGION, PREFIX_MEDICAL_TAG,
                        PREFIX_AVAILABILITY, PREFIX_TAG);
        try {
            List<Predicate<Person>> sharedfilterList = parseSharedPredicates(argMultimap);
            List<Predicate<Elderly>> elderlyOnlyfilterList = parseElderlyOnlyPredicates(argMultimap);
            List<Predicate<Volunteer>> volunteerOnlyfilterList = parseVolunteerOnlyPredicates(argMultimap);
            if ((sharedfilterList.size() + elderlyOnlyfilterList.size() + volunteerOnlyfilterList.size()) == 0) {
                throw new ParseException(MESSAGE_NO_FIELD_PROVIDED + "\n" + MESSAGE_USAGE);
            }
            return new FindCommand(sharedfilterList, elderlyOnlyfilterList, volunteerOnlyfilterList);
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }
    }

    @Override
    public CommandInfo getCommandInfo() {
        return new CommandInfo(
                FindCommand.COMMAND_WORD,
                FindCommand.COMMAND_PROMPTS,
                FindCommandParser::validate);
    }

    /**
     * Parses the given arguments into predicates shared among volunteers and elderly.
     *
     * @param argMultimap Mapping of prefix to arguments.
     * @return List of shared predicates.
     * @throws ParseException If there is an error parsing any of the arguments.
     */
    private List<Predicate<Person>> parseSharedPredicates(ArgumentMultimap argMultimap)
            throws ParseException {
        List<Predicate<Person>> sharedFilterList = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            sharedFilterList.add(new NameContainsKeywordPredicate<>(
                    argMultimap.getValue(PREFIX_NAME).get().trim()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            sharedFilterList.add(new AddressContainsKeywordPredicate<>(
                    argMultimap.getValue(PREFIX_ADDRESS).get().trim()));
        }
        if (argMultimap.getValue(PREFIX_NRIC).isPresent()) {
            sharedFilterList.add(new NricContainsKeywordPredicate<>(
                    argMultimap.getValue(PREFIX_NRIC).get().trim()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            sharedFilterList.add(new PhoneContainsDigitsPredicate<>(
                    argMultimap.getValue(PREFIX_PHONE).get().trim()));
        }
        if (argMultimap.getValue(PREFIX_BIRTH_DATE).isPresent()) {
            sharedFilterList.add(new BirthDateEqualPredicate<>(
                    ParserUtil.parseBirthDate(argMultimap.getValue(PREFIX_BIRTH_DATE).get()).birthDate.toString()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            sharedFilterList.add(new EmailContainsKeywordPredicate<>(
                    argMultimap.getValue(PREFIX_EMAIL).get().trim()));
        }
        if (argMultimap.getValue(PREFIX_REGION).isPresent()) {
            sharedFilterList.add(new RegionIsEqualPredicate<>(
                    ParserUtil.parseRegion(argMultimap.getValue(PREFIX_REGION).get()).region));
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            sharedFilterList.add(new TagContainsKeywordPredicate<>(
                    argMultimap.getValue(PREFIX_TAG).get().trim()));
        }
        if (argMultimap.getValue(PREFIX_AVAILABILITY).isPresent()) {
            AvailableDate dateRange = ParserUtil.parseDateRange(argMultimap.getValue(PREFIX_AVAILABILITY).get());
            sharedFilterList.add(new AvailableDatesWithinRangePredicate<>(
                    dateRange.getStartDate(), dateRange.getEndDate()));
        }
        return sharedFilterList;
    }

    /**
     * Parses the given arguments into predicates solely for elderly.
     *
     * @param argMultimap Mapping of prefix to arguments.
     * @return List of elderly only predicates.
     * @throws ParseException If there is an error parsing any of the arguments.
     */
    private List<Predicate<Elderly>> parseElderlyOnlyPredicates(ArgumentMultimap argMultimap)
            throws ParseException {
        List<Predicate<Elderly>> elderlyOnlyFilterList = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_RISK).isPresent()) {
            elderlyOnlyFilterList.add(new RiskLevelIsEqualPredicate<>(
                    ParserUtil.parseRiskLevel(argMultimap.getValue(PREFIX_RISK).get()).riskStatus));
        }

        return elderlyOnlyFilterList;
    }

    /**
     * Parses the given arguments into predicates solely for volunteers.
     *
     * @param argMultimap Mapping of prefix to arguments.
     * @return List of volunteer only predicates.
     */
    private List<Predicate<Volunteer>> parseVolunteerOnlyPredicates(ArgumentMultimap argMultimap) {
        List<Predicate<Volunteer>> volunteerOnlyFilterList = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_MEDICAL_TAG).isPresent()) {
            String[] values = argMultimap.getValue(PREFIX_MEDICAL_TAG).get().split(",", 2);
            volunteerOnlyFilterList.add(new MedicalQualificationContainsKeywordPredicate<>(values[0].trim()));
            if (values.length == 2) {
                volunteerOnlyFilterList.add(new SkillLevelIsEqualPredicate<>(values[1].trim()));
            }
        }

        return volunteerOnlyFilterList;
    }

    /**
     * Validates the given ArgumentMultimap by checking that it fulfils certain criteria.
     *
     * @param map the ArgumentMultimap to be validated.
     * @return true if the ArgumentMultimap is valid, false otherwise.
     */
    public static boolean validate(ArgumentMultimap map) throws RecommendationException {
        if (PrefixUtil.checkIfContainsInvalidPrefixes(map)) {
            throw new RecommendationException("Invalid prefix.");
        }
        return true;
    }
}
