package ezschedule.logic.parser;

import static ezschedule.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ezschedule.logic.commands.CommandTestUtil.DATE_DESC_A;
import static ezschedule.logic.commands.CommandTestUtil.DATE_DESC_B;
import static ezschedule.logic.commands.CommandTestUtil.END_TIME_DESC_A;
import static ezschedule.logic.commands.CommandTestUtil.END_TIME_DESC_B;
import static ezschedule.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static ezschedule.logic.commands.CommandTestUtil.INVALID_END_TIME_DESC;
import static ezschedule.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static ezschedule.logic.commands.CommandTestUtil.INVALID_START_TIME_DESC;
import static ezschedule.logic.commands.CommandTestUtil.NAME_DESC_A;
import static ezschedule.logic.commands.CommandTestUtil.START_TIME_DESC_A;
import static ezschedule.logic.commands.CommandTestUtil.START_TIME_DESC_B;
import static ezschedule.logic.commands.CommandTestUtil.VALID_DATE_A;
import static ezschedule.logic.commands.CommandTestUtil.VALID_DATE_B;
import static ezschedule.logic.commands.CommandTestUtil.VALID_END_TIME_A;
import static ezschedule.logic.commands.CommandTestUtil.VALID_END_TIME_B;
import static ezschedule.logic.commands.CommandTestUtil.VALID_NAME_A;
import static ezschedule.logic.commands.CommandTestUtil.VALID_START_TIME_A;
import static ezschedule.logic.commands.CommandTestUtil.VALID_START_TIME_B;
import static ezschedule.logic.parser.CliSyntax.PREFIX_DATE;
import static ezschedule.logic.parser.CliSyntax.PREFIX_END;
import static ezschedule.logic.parser.CliSyntax.PREFIX_NAME;
import static ezschedule.logic.parser.CliSyntax.PREFIX_START;
import static ezschedule.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ezschedule.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static ezschedule.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static ezschedule.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static ezschedule.testutil.TypicalIndexes.INDEX_THIRD_EVENT;

import org.junit.jupiter.api.Test;

import ezschedule.commons.core.index.Index;
import ezschedule.logic.commands.EditCommand;
import ezschedule.logic.commands.EditCommand.EditEventDescriptor;
import ezschedule.model.event.Date;
import ezschedule.model.event.Name;
import ezschedule.model.event.Time;
import ezschedule.testutil.EditEventDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private final EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_A, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_A, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_A, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_compulsoryPrefixesMissing_failure() {
        // missing name prefix
        assertParseFailure(parser, "1" + VALID_NAME_A, MESSAGE_INVALID_FORMAT);

        // missing date prefix
        assertParseFailure(parser, "1" + VALID_DATE_A, MESSAGE_INVALID_FORMAT);

        // missing start time prefix
        assertParseFailure(parser, "1" + VALID_START_TIME_A, MESSAGE_INVALID_FORMAT);

        // missing end time prefix
        assertParseFailure(parser, "1" + VALID_END_TIME_A, MESSAGE_INVALID_FORMAT);

        // all prefixes missing
        assertParseFailure(parser, "1" + VALID_NAME_A + VALID_DATE_A + VALID_START_TIME_A + VALID_END_TIME_A,
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_compulsoryFieldsEmpty_failure() {
        // missing name field
        assertParseFailure(parser, "1" + " " + PREFIX_NAME, Name.MESSAGE_CONSTRAINTS);

        // missing date field
        assertParseFailure(parser, "1" + " " + PREFIX_DATE, Date.MESSAGE_CONSTRAINTS);

        // missing start time field
        assertParseFailure(parser, "1" + " " + PREFIX_START, Time.MESSAGE_CONSTRAINTS);

        // missing end time field
        assertParseFailure(parser, "1" + " " + PREFIX_END, Time.MESSAGE_CONSTRAINTS);

        // multiple missing fields - date followed by start time
        assertParseFailure(parser, "1" + NAME_DESC_A + " "
                + PREFIX_DATE + " " + PREFIX_START, Date.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, "1" + INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS);

        // invalid start time
        assertParseFailure(parser, "1" + INVALID_START_TIME_DESC, Time.MESSAGE_CONSTRAINTS);

        // invalid end time
        assertParseFailure(parser, "1" + INVALID_END_TIME_DESC, Time.MESSAGE_CONSTRAINTS);

        // invalid date followed by valid start time
        assertParseFailure(parser, "1" + INVALID_DATE_DESC + START_TIME_DESC_A, Date.MESSAGE_CONSTRAINTS);

        // valid date followed by invalid date. The test case for invalid date followed by valid date
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + DATE_DESC_B + INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_DATE_DESC
                + VALID_START_TIME_A + VALID_END_TIME_A, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_EVENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_A + DATE_DESC_A + START_TIME_DESC_A + END_TIME_DESC_A;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_A)
                .withDate(VALID_DATE_A).withStartTime(VALID_START_TIME_A).withEndTime(VALID_END_TIME_A).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + DATE_DESC_A + START_TIME_DESC_A;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withDate(VALID_DATE_A)
                .withStartTime(VALID_START_TIME_A).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_EVENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_A;
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_A).build();
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

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withDate(VALID_DATE_B)
                .withStartTime(VALID_START_TIME_B).withEndTime(VALID_END_TIME_B).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + INVALID_DATE_DESC + DATE_DESC_B;
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withDate(VALID_DATE_B).build();
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
