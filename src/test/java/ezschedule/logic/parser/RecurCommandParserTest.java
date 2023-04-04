package ezschedule.logic.parser;

import static ezschedule.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ezschedule.logic.commands.CommandTestUtil.*;
import static ezschedule.logic.parser.CliSyntax.*;
import static ezschedule.logic.parser.CliSyntax.PREFIX_START;
import static ezschedule.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ezschedule.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static ezschedule.testutil.TypicalIndexes.*;
import static ezschedule.testutil.TypicalIndexes.INDEX_FIRST_EVENT;

import ezschedule.commons.core.index.Index;
import ezschedule.logic.commands.EditCommand;
import ezschedule.logic.commands.RecurCommand;
import ezschedule.model.event.Date;
import ezschedule.model.event.Name;
import ezschedule.model.event.RecurFactor;
import ezschedule.model.event.Time;
import ezschedule.testutil.EditEventDescriptorBuilder;
import org.junit.jupiter.api.Test;

public class RecurCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecurCommand.MESSAGE_USAGE);

    private final RecurCommandParser parser = new RecurCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index and no recur factor specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // no index specified
        assertParseFailure(parser, VALID_RECUR_FACTOR_DAY, MESSAGE_INVALID_FORMAT);

        // no recur factor specified
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

    // here

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_EVENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_A + DATE_DESC_A + START_TIME_DESC_A + END_TIME_DESC_A;

        EditCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_A)
                .withDate(VALID_DATE_A).withStartTime(VALID_START_TIME_A).withEndTime(VALID_END_TIME_A).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + DATE_DESC_A + START_TIME_DESC_A;

        EditCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withDate(VALID_DATE_A)
                .withStartTime(VALID_START_TIME_A).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_EVENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_A;
        EditCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_A).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + DATE_DESC_A;
        descriptor = new EditEventDescriptorBuilder().withDate(VALID_DATE_A).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start time
        userInput = targetIndex.getOneBased() + START_TIME_DESC_A;
        descriptor = new EditEventDescriptorBuilder().withStartTime(VALID_START_TIME_A).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // end time
        userInput = targetIndex.getOneBased() + END_TIME_DESC_A;
        descriptor = new EditEventDescriptorBuilder().withEndTime(VALID_END_TIME_A).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + DATE_DESC_A + START_TIME_DESC_A + END_TIME_DESC_A
                + DATE_DESC_B + START_TIME_DESC_B + END_TIME_DESC_B;

        EditCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withDate(VALID_DATE_B)
                .withStartTime(VALID_START_TIME_B).withEndTime(VALID_END_TIME_B).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + INVALID_DATE_DESC + DATE_DESC_B;
        EditCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withDate(VALID_DATE_B).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_DATE_DESC + DATE_DESC_B + START_TIME_DESC_B + END_TIME_DESC_B;
        descriptor = new EditEventDescriptorBuilder().withDate(VALID_DATE_B)
                .withStartTime(VALID_START_TIME_B).withEndTime(VALID_END_TIME_B).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
