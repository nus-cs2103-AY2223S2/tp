package seedu.quickcontacts.logic.parser;

import static seedu.quickcontacts.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import seedu.quickcontacts.logic.commands.AddMeetingCommand;
import seedu.quickcontacts.logic.commands.CommandTestUtil;
import seedu.quickcontacts.model.meeting.Meeting;
import seedu.quickcontacts.testutil.MeetingBuilder;
import seedu.quickcontacts.testutil.TypicalPersons;

public class AddMeetingCommandParserTest {
    private final AddMeetingCommandParser parser = new AddMeetingCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Meeting expectedMeetingWithOnePerson = new MeetingBuilder().withTitle(CommandTestUtil.VALID_MEETING_TITLE)
            .withDateTime(CommandTestUtil.VALID_MEETING_DATETIME).withAttendees(TypicalPersons.ALICE)
            .withLocation(CommandTestUtil.VALID_MEETING_LOCATION)
            .withDescription(CommandTestUtil.VALID_MEETING_DESCRIPTION).build();
        Meeting expectedMeetingWithTwoPersons = new MeetingBuilder().withTitle(CommandTestUtil.VALID_MEETING_TITLE)
            .withDateTime(CommandTestUtil.VALID_MEETING_DATETIME)
            .withAttendees(TypicalPersons.ALICE, TypicalPersons.BENSON)
            .withLocation(CommandTestUtil.VALID_MEETING_LOCATION)
            .withDescription(CommandTestUtil.VALID_MEETING_DESCRIPTION).build();

        // whitespace only preamble
        CommandParserTestUtil.assertParseSuccess(parser, CommandTestUtil.PREAMBLE_WHITESPACE
                + CommandTestUtil.MEETING_TITLE_DESC + CommandTestUtil.MEETING_DATETIME_DESC
                + CommandTestUtil.MEETING_PERSON_ALICE_DESC + CommandTestUtil.MEETING_LOCATION_DESC
                + CommandTestUtil.MEETING_DESCRIPTION_DESC, new AddMeetingCommand(expectedMeetingWithOnePerson));

        // no whitespace preamble
        CommandParserTestUtil.assertParseSuccess(parser, CommandTestUtil.MEETING_TITLE_DESC
                + CommandTestUtil.MEETING_DATETIME_DESC + CommandTestUtil.MEETING_PERSON_ALICE_DESC
                + CommandTestUtil.MEETING_LOCATION_DESC + CommandTestUtil.MEETING_DESCRIPTION_DESC,
            new AddMeetingCommand(expectedMeetingWithOnePerson));

        // whitespace only preamble and multiple persons
        CommandParserTestUtil.assertParseSuccess(parser, CommandTestUtil.PREAMBLE_WHITESPACE
                + CommandTestUtil.MEETING_TITLE_DESC + CommandTestUtil.MEETING_DATETIME_DESC
                + CommandTestUtil.MEETING_PERSON_ALICE_DESC + CommandTestUtil.MEETING_PERSON_BENSON_DESC
                + CommandTestUtil.MEETING_LOCATION_DESC + CommandTestUtil.MEETING_DESCRIPTION_DESC,
            new AddMeetingCommand(expectedMeetingWithTwoPersons));

        // no whitespace only preamble and multiple persons
        CommandParserTestUtil.assertParseSuccess(parser, CommandTestUtil.MEETING_TITLE_DESC
                + CommandTestUtil.MEETING_DATETIME_DESC + CommandTestUtil.MEETING_PERSON_ALICE_DESC
                + CommandTestUtil.MEETING_PERSON_BENSON_DESC + CommandTestUtil.MEETING_LOCATION_DESC
                + CommandTestUtil.MEETING_DESCRIPTION_DESC, new AddMeetingCommand(expectedMeetingWithTwoPersons));
    }

    @Test
    public void parse_compulsoryFieldsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE);

        // missing title prefix
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_MEETING_TITLE
            + CommandTestUtil.MEETING_DATETIME_DESC + CommandTestUtil.MEETING_PERSON_ALICE_DESC
            + CommandTestUtil.MEETING_LOCATION_DESC + CommandTestUtil.MEETING_DESCRIPTION_DESC, expectedMessage);

        // missing datetime prefix
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.MEETING_TITLE_DESC
            + CommandTestUtil.VALID_MEETING_DATETIME + CommandTestUtil.MEETING_PERSON_ALICE_DESC
            + CommandTestUtil.MEETING_LOCATION_DESC + CommandTestUtil.MEETING_DESCRIPTION_DESC, expectedMessage);

        // missing person prefix
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.MEETING_TITLE_DESC
            + CommandTestUtil.MEETING_DATETIME_DESC + CommandTestUtil.NAME_DESC_AMY
            + CommandTestUtil.MEETING_LOCATION_DESC + CommandTestUtil.MEETING_DESCRIPTION_DESC, expectedMessage);

        // missing location prefix
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.MEETING_TITLE_DESC
            + CommandTestUtil.MEETING_DATETIME_DESC + CommandTestUtil.MEETING_PERSON_ALICE_DESC
            + CommandTestUtil.VALID_MEETING_LOCATION + CommandTestUtil.MEETING_DESCRIPTION_DESC, expectedMessage);

        // missing description prefix
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.MEETING_TITLE_DESC
            + CommandTestUtil.MEETING_DATETIME_DESC + CommandTestUtil.MEETING_PERSON_ALICE_DESC
            + CommandTestUtil.MEETING_LOCATION_DESC + CommandTestUtil.VALID_MEETING_DESCRIPTION, expectedMessage);

        // all prefixes missing
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_MEETING_TITLE
            + CommandTestUtil.VALID_MEETING_DATETIME + CommandTestUtil.NAME_DESC_ALICE
            + CommandTestUtil.VALID_MEETING_LOCATION + CommandTestUtil.VALID_MEETING_DESCRIPTION, expectedMessage);
    }
}
