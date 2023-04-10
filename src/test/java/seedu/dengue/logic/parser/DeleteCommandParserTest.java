package seedu.dengue.logic.parser;

import static seedu.dengue.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_ALICE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_BENSON;
import static seedu.dengue.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.dengue.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.dengue.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.dengue.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.dengue.logic.commands.DeleteCommand;
import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.person.ContinuousData;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.predicate.DeleteDatePredicate;
import seedu.dengue.model.range.EndDate;
import seedu.dengue.model.range.Range;
import seedu.dengue.model.range.StartDate;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    // empty arg

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArgAllPrefixes_throwsParseException() {
        assertParseFailure(parser, " d/ sd/ ed/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    // single index

    @Test
    public void parse_validArgsSingleIndex_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgsSingleIndex_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    // multi-index

    @Test
    public void parse_validArgsMultiIndex_returnsDeleteCommand() {
        assertParseSuccess(parser, "1 2", new DeleteCommand(List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON)));
    }

    @Test
    public void parse_invalidArgsMultiIndex_throwsParseException() {
        assertParseFailure(parser, "1 2 a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    // date

    @Test
    public void parse_validArgsDate_returnsDeleteCommand() throws ParseException {
        DeleteDatePredicate predicate = new DeleteDatePredicate(Optional.of(new Date(VALID_DATE_ALICE)));
        assertParseSuccess(parser, " d/2023-03-05", new DeleteCommand(predicate));
    }

    // range

    @Test
    public void parse_validArgsCompleteRange_returnsDeleteCommand() throws ParseException {

        Range<Date> range = ContinuousData.generateRange(
                new StartDate(Optional.of(new Date(VALID_DATE_BENSON))),
                new EndDate(Optional.of(new Date(VALID_DATE_ALICE))));
        DeleteDatePredicate predicate = new DeleteDatePredicate(range);

        assertParseSuccess(parser, " sd/2022-03-05 ed/2023-03-05", new DeleteCommand(predicate));
    }

    @Test
    public void parse_validArgsPartialRangeStartGiven_returnsDeleteCommand() throws ParseException {

        Range<Date> range = ContinuousData.generateRange(
                new StartDate(Optional.of(new Date(VALID_DATE_ALICE))),
                new EndDate(Optional.empty()));
        DeleteDatePredicate predicate = new DeleteDatePredicate(range);

        assertParseSuccess(parser, " sd/2023-03-05", new DeleteCommand(predicate));
    }

    @Test
    public void parse_validArgsPartialRangeEndGiven_returnsDeleteCommand() throws ParseException {

        Range<Date> range = ContinuousData.generateRange(
                new StartDate(Optional.empty()),
                new EndDate(Optional.of(new Date(VALID_DATE_ALICE))));
        DeleteDatePredicate predicate = new DeleteDatePredicate(range);

        assertParseSuccess(parser, " ed/2023-03-05", new DeleteCommand(predicate));
    }

    @Test
    public void parse_validArgsCompleteRangeSameDates_returnsDeleteCommand() throws ParseException {

        Range<Date> range = ContinuousData.generateRange(
                new StartDate(Optional.of(new Date(VALID_DATE_ALICE))),
                new EndDate(Optional.of(new Date(VALID_DATE_ALICE))));
        DeleteDatePredicate predicate = new DeleteDatePredicate(range);

        assertParseSuccess(parser, " sd/2023-03-05 ed/2023-03-05", new DeleteCommand(predicate));
    }

    // mixed indexes / dates

    @Test
    public void parse_invalidArgsIndexBeforeDate_throwsParseException() {
        assertParseFailure(parser, "1 d/2023-03-23",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsIndexAfterDate_throwsParseException() {
        assertParseFailure(parser, "d/2023-03-23 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsMixedDates_throwsParseException() {
        assertParseFailure(parser, "d/2023-03-23 sd/2023-03-25",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
