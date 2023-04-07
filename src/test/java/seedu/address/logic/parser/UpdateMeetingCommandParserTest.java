package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.M_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.M_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.M_END_AMY;
import static seedu.address.logic.commands.CommandTestUtil.M_END_BOB;
import static seedu.address.logic.commands.CommandTestUtil.M_START_AMY;
import static seedu.address.logic.commands.CommandTestUtil.M_START_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_END_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_END_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_START_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_START_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UpdateMeetingCommand;
import seedu.address.logic.commands.UpdateMeetingCommand.EditMeetingDescriptor;
import seedu.address.testutil.EditMeetingDescriptorBuilder;

public class UpdateMeetingCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateMeetingCommand.MESSAGE_USAGE);

    private static final String MESSAGE_MISSING_ARGUMENTS_FULL =
        String.format(MESSAGE_MISSING_ARGUMENTS, UpdateMeetingCommand.MESSAGE_USAGE);

    private UpdateMeetingCommandParser parser = new UpdateMeetingCommandParser();

    @Test
    public void parse_missingIndex_failure() {
        assertParseFailure(parser, VALID_MEETING_DESC_AMY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingFields_failure() {
        assertParseFailure(parser, "1 1", UpdateMeetingCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void parse_missingIndexAndFields_failure() {
        assertParseFailure(parser, "", MESSAGE_MISSING_ARGUMENTS_FULL);
    }

    @Test
    public void parse_negativePersonIndex_failure() {
        assertParseFailure(parser, "-5 1" + MEETING_DESC_AMY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_negativeMeetingIndex_failure() {
        assertParseFailure(parser, "1 -5" + MEETING_DESC_AMY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_personIndexZero_failure() {
        assertParseFailure(parser, "0 1" + MEETING_DESC_AMY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_meetingIndexZero_failure() {
        assertParseFailure(parser, "1 0" + MEETING_DESC_AMY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        assertParseFailure(parser, "1 1 some random string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPrefix_failure() {
        assertParseFailure(parser, "1 1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        Index meetingIndex = INDEX_FIRST_MEETING;
        String userInput =
            targetIndex.getOneBased() + " " + meetingIndex.getOneBased() + M_DESC_BOB + M_START_BOB + M_END_BOB;
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withDescription(VALID_MEETING_DESC_BOB)
            .withStart(VALID_MEETING_START_BOB).withEnd(VALID_MEETING_END_BOB).build();
        UpdateMeetingCommand expectedCommand = new UpdateMeetingCommand(targetIndex, meetingIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        Index meetingIndex = INDEX_FIRST_MEETING;
        String userInput = targetIndex.getOneBased() + " " + meetingIndex.getOneBased() + M_DESC_BOB + M_START_BOB;

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withDescription(VALID_MEETING_DESC_BOB)
            .withStart(VALID_MEETING_START_BOB).build();
        UpdateMeetingCommand expectedCommand = new UpdateMeetingCommand(targetIndex, meetingIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // description
        Index targetIndex = INDEX_THIRD_PERSON;
        Index meetingIndex = INDEX_FIRST_MEETING;
        String userInput = targetIndex.getOneBased() + " " + meetingIndex.getOneBased() + M_DESC_AMY;
        EditMeetingDescriptor descriptor =
            new EditMeetingDescriptorBuilder().withDescription(VALID_MEETING_DESC_AMY).build();
        UpdateMeetingCommand expectedCommand = new UpdateMeetingCommand(targetIndex, meetingIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start
        userInput = targetIndex.getOneBased() + " " + meetingIndex.getOneBased() + M_START_BOB;
        descriptor = new EditMeetingDescriptorBuilder().withStart(VALID_MEETING_START_BOB).build();
        expectedCommand = new UpdateMeetingCommand(targetIndex, meetingIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // end
        userInput = targetIndex.getOneBased() + " " + meetingIndex.getOneBased() + M_END_BOB;
        descriptor = new EditMeetingDescriptorBuilder().withEnd(VALID_MEETING_END_BOB).build();
        expectedCommand = new UpdateMeetingCommand(targetIndex, meetingIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        Index meetingIndex = INDEX_FIRST_MEETING;
        String userInput =
            targetIndex.getOneBased() + " " + meetingIndex.getOneBased() + M_DESC_AMY + M_START_AMY + M_END_AMY
                + M_DESC_AMY + M_START_AMY + M_END_AMY + M_DESC_AMY + M_START_AMY + M_END_AMY;

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withDescription(VALID_MEETING_DESC_AMY)
            .withStart(VALID_MEETING_START_AMY).withEnd(VALID_MEETING_END_AMY).build();
        UpdateMeetingCommand expectedCommand = new UpdateMeetingCommand(targetIndex, meetingIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
