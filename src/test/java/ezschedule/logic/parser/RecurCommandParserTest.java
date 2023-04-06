package ezschedule.logic.parser;

import static ezschedule.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ezschedule.logic.commands.CommandTestUtil.DATE_DESC_A;
import static ezschedule.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static ezschedule.logic.commands.CommandTestUtil.INVALID_RECUR_FACTOR;
import static ezschedule.logic.commands.CommandTestUtil.RECUR_FACTOR_DESC_DAY;
import static ezschedule.logic.commands.CommandTestUtil.RECUR_FACTOR_DESC_MONTH;
import static ezschedule.logic.commands.CommandTestUtil.VALID_DATE_A;
import static ezschedule.logic.commands.CommandTestUtil.VALID_NAME_A;
import static ezschedule.logic.commands.CommandTestUtil.VALID_RECUR_FACTOR_DAY;
import static ezschedule.logic.commands.CommandTestUtil.VALID_RECUR_FACTOR_MONTH;
import static ezschedule.logic.parser.CliSyntax.PREFIX_DATE;
import static ezschedule.logic.parser.CliSyntax.PREFIX_EVERY;
import static ezschedule.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ezschedule.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static ezschedule.testutil.TypicalIndexes.INDEX_FIRST_EVENT;

import org.junit.jupiter.api.Test;

import ezschedule.commons.core.index.Index;
import ezschedule.logic.commands.RecurCommand;
import ezschedule.model.event.Date;
import ezschedule.model.event.RecurFactor;

public class RecurCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecurCommand.MESSAGE_USAGE);

    private final RecurCommandParser parser = new RecurCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, DATE_DESC_A + RECUR_FACTOR_DESC_MONTH, MESSAGE_INVALID_FORMAT);

        // no date specified
        assertParseFailure(parser, "1" + RECUR_FACTOR_DESC_MONTH, MESSAGE_INVALID_FORMAT);

        // no recur factor specified
        assertParseFailure(parser, "1" + DATE_DESC_A, MESSAGE_INVALID_FORMAT);

        // no index, no date, no recur factor
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // no index, no date
        assertParseFailure(parser, RECUR_FACTOR_DESC_MONTH, MESSAGE_INVALID_FORMAT);

        // no index, no recur factor
        assertParseFailure(parser, DATE_DESC_A, MESSAGE_INVALID_FORMAT);

        // no date, no recur factor
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_RECUR_FACTOR_DAY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_RECUR_FACTOR_DAY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_compulsoryPrefixesMissing_failure() {
        // missing ending date prefix
        assertParseFailure(parser, "1" + VALID_DATE_A, MESSAGE_INVALID_FORMAT);

        // missing recur factor prefix
        assertParseFailure(parser, "1" + VALID_RECUR_FACTOR_DAY, MESSAGE_INVALID_FORMAT);

        // all prefixes missing
        assertParseFailure(parser, "1" + VALID_NAME_A + VALID_RECUR_FACTOR_DAY,
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_compulsoryFieldsEmpty_failure() {
        // missing end date field
        assertParseFailure(parser, "1" + " " + PREFIX_DATE, MESSAGE_INVALID_FORMAT);

        // missing recur factor field
        assertParseFailure(parser, "1" + " " + PREFIX_EVERY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid end date
        assertParseFailure(parser, "1" + INVALID_DATE_DESC + RECUR_FACTOR_DESC_DAY, Date.MESSAGE_CONSTRAINTS);

        // invalid recur factor
        assertParseFailure(parser, "1" + DATE_DESC_A + INVALID_RECUR_FACTOR, RecurFactor.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_EVENT;
        Date endDate = new Date(VALID_DATE_A);
        RecurFactor rf = new RecurFactor(VALID_RECUR_FACTOR_MONTH);

        String userInput = targetIndex.getOneBased() + DATE_DESC_A + RECUR_FACTOR_DESC_MONTH;

        RecurCommand expectedCommand = new RecurCommand(targetIndex, endDate, rf);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
