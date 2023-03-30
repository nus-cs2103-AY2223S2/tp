package seedu.quickcontacts.logic.parser;

import static seedu.quickcontacts.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.quickcontacts.logic.commands.CommandTestUtil.MEETING_DATETIME_DESC;
import static seedu.quickcontacts.logic.commands.CommandTestUtil.MEETING_DESCRIPTION_DESC;
import static seedu.quickcontacts.logic.commands.CommandTestUtil.MEETING_LOCATION_DESC;
import static seedu.quickcontacts.logic.commands.CommandTestUtil.MEETING_PERSON_ALICE_DESC;
import static seedu.quickcontacts.logic.commands.CommandTestUtil.MEETING_PERSON_BENSON_DESC;
import static seedu.quickcontacts.logic.commands.CommandTestUtil.MEETING_TITLE_DESC;
import static seedu.quickcontacts.logic.commands.CommandTestUtil.NAME_DESC_ALICE;
import static seedu.quickcontacts.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.quickcontacts.logic.commands.CommandTestUtil.VALID_MEETING_DATETIME;
import static seedu.quickcontacts.logic.commands.CommandTestUtil.VALID_MEETING_DESCRIPTION;
import static seedu.quickcontacts.logic.commands.CommandTestUtil.VALID_MEETING_LOCATION;
import static seedu.quickcontacts.logic.commands.CommandTestUtil.VALID_MEETING_TITLE;
import static seedu.quickcontacts.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.quickcontacts.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.quickcontacts.logic.commands.AddMeetingCommand;
import seedu.quickcontacts.model.meeting.Meeting;
import seedu.quickcontacts.testutil.MeetingBuilder;
import seedu.quickcontacts.testutil.TypicalPersons;

public class AddMeetingCommandParserTest {
    private final AddMeetingCommandParser parser = new AddMeetingCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Meeting expectedMeetingWithOnePerson = new MeetingBuilder().withTitle(VALID_MEETING_TITLE)
                .withDateTime(VALID_MEETING_DATETIME).withAttendees(TypicalPersons.ALICE)
                .withLocation(VALID_MEETING_LOCATION)
                .withDescription(VALID_MEETING_DESCRIPTION).build();
        Meeting expectedMeetingWithTwoPersons = new MeetingBuilder().withTitle(VALID_MEETING_TITLE)
                .withDateTime(VALID_MEETING_DATETIME).withAttendees(TypicalPersons.ALICE, TypicalPersons.BENSON)
                .withLocation(VALID_MEETING_LOCATION).withDescription(VALID_MEETING_DESCRIPTION).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MEETING_TITLE_DESC + MEETING_DATETIME_DESC
                        + MEETING_PERSON_ALICE_DESC + MEETING_LOCATION_DESC + MEETING_DESCRIPTION_DESC,
                new AddMeetingCommand(expectedMeetingWithOnePerson));

        // no whitespace preamble
        assertParseSuccess(parser, MEETING_TITLE_DESC + MEETING_DATETIME_DESC
                        + MEETING_PERSON_ALICE_DESC + MEETING_LOCATION_DESC + MEETING_DESCRIPTION_DESC,
                new AddMeetingCommand(expectedMeetingWithOnePerson));

        // whitespace only preamble and multiple persons
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MEETING_TITLE_DESC + MEETING_DATETIME_DESC
                        + MEETING_PERSON_ALICE_DESC + MEETING_PERSON_BENSON_DESC + MEETING_LOCATION_DESC
                        + MEETING_DESCRIPTION_DESC,
                new AddMeetingCommand(expectedMeetingWithTwoPersons));

        // no whitespace only preamble and multiple persons
        assertParseSuccess(parser, MEETING_TITLE_DESC + MEETING_DATETIME_DESC
                        + MEETING_PERSON_ALICE_DESC + MEETING_PERSON_BENSON_DESC + MEETING_LOCATION_DESC
                        + MEETING_DESCRIPTION_DESC,
                new AddMeetingCommand(expectedMeetingWithTwoPersons));
    }

    @Test
    public void parse_compulsoryFieldsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE);

        // missing title prefix
        assertParseFailure(parser, VALID_MEETING_TITLE + MEETING_DATETIME_DESC + MEETING_PERSON_ALICE_DESC
                + MEETING_LOCATION_DESC + MEETING_DESCRIPTION_DESC, expectedMessage);

        // missing datetime prefix
        assertParseFailure(parser, MEETING_TITLE_DESC + VALID_MEETING_DATETIME + MEETING_PERSON_ALICE_DESC
                + MEETING_LOCATION_DESC + MEETING_DESCRIPTION_DESC, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_MEETING_TITLE + VALID_MEETING_DATETIME + NAME_DESC_ALICE
                + VALID_MEETING_LOCATION + VALID_MEETING_DESCRIPTION, expectedMessage);
    }
}
