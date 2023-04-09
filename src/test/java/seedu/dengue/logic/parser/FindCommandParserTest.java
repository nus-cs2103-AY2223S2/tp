package seedu.dengue.logic.parser;

import static seedu.dengue.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_AGE_ALICE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_AGE_BENSON;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_ALICE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_BENSON;
import static seedu.dengue.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.dengue.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.dengue.logic.commands.DeleteCommand;
import seedu.dengue.logic.commands.FindCommand;
import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.ContinuousData;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.SubPostal;
import seedu.dengue.model.predicate.FindPredicate;
import seedu.dengue.model.range.EndAge;
import seedu.dengue.model.range.EndDate;
import seedu.dengue.model.range.Range;
import seedu.dengue.model.range.StartAge;
import seedu.dengue.model.range.StartDate;
import seedu.dengue.model.variant.Variant;

public class FindCommandParserTest {

    private final Optional<Name> emptyName = Optional.empty();
    private final Optional<SubPostal> emptySubPostal = Optional.empty();
    private final Optional<Age> emptyAge = Optional.empty();
    private final Optional<Date> emptyDate = Optional.empty();
    private final Set<Variant> emptyVariants = new HashSet<>();
    private final Range<Date> emptyDateRange = ContinuousData.generateRange(
            new StartDate(Optional.empty()), new EndDate(Optional.empty()));
    private final Range<Age> emptyAgeRange = ContinuousData.generateRange(
            new StartAge(Optional.empty()), new EndAge(Optional.empty()));
    private final FindCommandParser parser = new FindCommandParser();

    public FindCommandParserTest() throws ParseException {
    }

    // empty string

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
    }

    // empty arguments

    @Test
    public void parse_emptyArgPrefixes_throwsParseException() {
        assertParseFailure(parser, " d/ sd/ ed/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    // mixed date prefixes
    @Test
    public void parse_invalidArgsMixedDates_throwsParseException() {
        assertParseFailure(parser, "d/2023-03-23 sd/2023-03-25",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    // mixed age prefixes
    @Test
    public void parse_invalidArgsMixedAges_throwsParseException() {
        assertParseFailure(parser, "a/23 ea/25",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    // name

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        Optional<Name> testName = Optional.of(new Name("Alice"));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(
                        testName, emptySubPostal, emptyAge,
                        emptyDate, emptyVariants, emptyDateRange, emptyAgeRange));
        assertParseSuccess(parser, " n/Alice", expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/   Alice ", expectedFindCommand);
    }

    // date

    @Test
    public void parse_validDateArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        Optional<Date> testDate = Optional.of(new Date("2011-11-11"));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(
                        emptyName, emptySubPostal, emptyAge,
                        testDate, emptyVariants, emptyDateRange, emptyAgeRange));
        assertParseSuccess(parser, " d/2011-11-11", expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " d/   2011-11-11 ", expectedFindCommand);
        //different date format
        assertParseSuccess(parser, " d/   2011/11/11 ", expectedFindCommand);
    }

    // postal

    @Test
    public void parse_validPostalArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        Optional<SubPostal> testPostal = Optional.of(new SubPostal("s123"));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(
                        emptyName, testPostal, emptyAge,
                        emptyDate, emptyVariants, emptyDateRange, emptyAgeRange));
        assertParseSuccess(parser, " p/s123", expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " p/   s123 ", expectedFindCommand);
        // test different formats of sub postal
        assertParseSuccess(parser, " p/123 ", expectedFindCommand);
        assertParseSuccess(parser, " p/S123 ", expectedFindCommand);
    }

    // age

    @Test
    public void parse_validAgeArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        Optional<Age> testAge = Optional.of(new Age("123"));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(
                        emptyName, emptySubPostal, testAge,
                        emptyDate, emptyVariants, emptyDateRange, emptyAgeRange));
        assertParseSuccess(parser, " a/123", expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " a/   123 ", expectedFindCommand);
    }

    // single variant

    @Test
    public void parse_validVariantArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        Set<Variant> testVariant = new HashSet<>();
        testVariant.add(new Variant("DENV1"));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(
                        emptyName, emptySubPostal, emptyAge,
                        emptyDate, testVariant, emptyDateRange, emptyAgeRange));
        assertParseSuccess(parser, " v/DENV1", expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " v/   DENV1 ", expectedFindCommand);
        // different formats of variant
        assertParseSuccess(parser, " v/DenV1 ", expectedFindCommand);
    }

    // multiple variants

    @Test
    public void parse_validVariantsArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        Set<Variant> testVariants = new HashSet<>();
        testVariants.add(new Variant("DENV1"));
        testVariants.add(new Variant("DENV2"));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(
                        emptyName, emptySubPostal, emptyAge,
                        emptyDate, testVariants, emptyDateRange, emptyAgeRange));
        assertParseSuccess(parser, " v/DENV1 v/DENV2", expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " v/   DENV1 v/     DENV2", expectedFindCommand);
        // different formats of variant
        assertParseSuccess(parser, " v/DenV1 v/DEnV2", expectedFindCommand);
    }

    // date range

    @Test
    public void parse_validArgsCompleteDateRange_returnsFindCommand() throws ParseException {

        Range<Date> testDateRange = ContinuousData.generateRange(
                new StartDate(Optional.of(new Date(VALID_DATE_BENSON))),
                new EndDate(Optional.of(new Date(VALID_DATE_ALICE))));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(
                        emptyName, emptySubPostal, emptyAge,
                        emptyDate, emptyVariants, testDateRange, emptyAgeRange));

        assertParseSuccess(parser, " sd/2022-03-05 ed/2023-03-05", expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " sd/   2022-03-05 ed/ 2023-03-05   ", expectedFindCommand);
        // prefix order switching
        assertParseSuccess(parser, " ed/2023-03-05 sd/2022-03-05", expectedFindCommand);
    }

    @Test
    public void parse_validArgsPartialDateRangeStartGiven_returnsFindCommand() throws ParseException {

        Range<Date> testDateRange = ContinuousData.generateRange(
                new StartDate(Optional.of(new Date(VALID_DATE_ALICE))),
                new EndDate(Optional.empty()));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(
                        emptyName, emptySubPostal, emptyAge,
                        emptyDate, emptyVariants, testDateRange, emptyAgeRange));

        assertParseSuccess(parser, " sd/2023-03-05", expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " sd/  2023-03-05  ", expectedFindCommand);
    }

    @Test
    public void parse_validArgsPartialDateRangeEndGiven_returnsFindCommand() throws ParseException {

        Range<Date> testDateRange = ContinuousData.generateRange(
                new StartDate(Optional.empty()),
                new EndDate(Optional.of(new Date(VALID_DATE_ALICE))));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(
                        emptyName, emptySubPostal, emptyAge,
                        emptyDate, emptyVariants, testDateRange, emptyAgeRange));

        assertParseSuccess(parser, " ed/2023-03-05", expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " ed/  2023-03-05  ", expectedFindCommand);
    }

    @Test
    public void parse_validArgsCompleteDateRangeSameDates_returnsFindCommand() throws ParseException {

        Range<Date> testDateRange = ContinuousData.generateRange(
                new StartDate(Optional.of(new Date(VALID_DATE_ALICE))),
                new EndDate(Optional.of(new Date(VALID_DATE_ALICE))));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(
                        emptyName, emptySubPostal, emptyAge,
                        emptyDate, emptyVariants, testDateRange, emptyAgeRange));

        assertParseSuccess(parser, " sd/2023-03-05 ed/2023-03-05", expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " sd/  2023-03-05 ed/  2023-03-05  ", expectedFindCommand);
        // prefix order switching
        assertParseSuccess(parser, " ed/2023-03-05 sd/2023-03-05", expectedFindCommand);
    }

    // age range

    @Test
    public void parse_validArgsCompleteAgeRange_returnsFindCommand() throws ParseException {

        Range<Age> testAgeRange = ContinuousData.generateRange(
                new StartAge(Optional.of(new Age(VALID_AGE_ALICE))),
                new EndAge(Optional.of(new Age(VALID_AGE_BENSON))));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(
                        emptyName, emptySubPostal, emptyAge,
                        emptyDate, emptyVariants, emptyDateRange, testAgeRange));

        assertParseSuccess(parser, " sa/21 ea/85", expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " sa/ 21  ea/   85", expectedFindCommand);
        // prefix order switching
        assertParseSuccess(parser, " ea/85 sa/21", expectedFindCommand);
    }

    @Test
    public void parse_validArgsPartialAgeRangeStartGiven_returnsFindCommand() throws ParseException {

        Range<Age> testAgeRange = ContinuousData.generateRange(
                new StartAge(Optional.of(new Age(VALID_AGE_ALICE))),
                new EndAge(Optional.empty()));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(
                        emptyName, emptySubPostal, emptyAge,
                        emptyDate, emptyVariants, emptyDateRange, testAgeRange));

        assertParseSuccess(parser, " sa/21", expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " sa/  21  ", expectedFindCommand);
    }

    @Test
    public void parse_validArgsPartialAgeRangeEndGiven_returnsFindCommand() throws ParseException {

        Range<Age> testAgeRange = ContinuousData.generateRange(
                new StartAge(Optional.empty()),
                new EndAge(Optional.of(new Age(VALID_AGE_ALICE))));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(
                        emptyName, emptySubPostal, emptyAge,
                        emptyDate, emptyVariants, emptyDateRange, testAgeRange));

        assertParseSuccess(parser, " ea/21", expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " ea/  21  ", expectedFindCommand);
    }

    @Test
    public void parse_validArgsCompleteAgeRangeSameAges_returnsFindCommand() throws ParseException {

        Range<Age> testAgeRange = ContinuousData.generateRange(
                new StartAge(Optional.of(new Age(VALID_AGE_ALICE))),
                new EndAge(Optional.of(new Age(VALID_AGE_ALICE))));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(
                        emptyName, emptySubPostal, emptyAge,
                        emptyDate, emptyVariants, emptyDateRange, testAgeRange));

        assertParseSuccess(parser, " sa/21 ea/21", expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " sa/ 21  ea/   21", expectedFindCommand);
        // prefix order switching
        assertParseSuccess(parser, " ea/21 sa/21", expectedFindCommand);
    }
}
