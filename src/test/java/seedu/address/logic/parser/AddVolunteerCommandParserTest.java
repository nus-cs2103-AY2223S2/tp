package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.AGE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.AGE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.AVAILABLE_DATES;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AGE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_REGION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_VOLUNTEER_NRIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_VOLUNTEER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_VOLUNTEER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REGION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REGION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_SINGLE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_STRONG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AGE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REGION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SINGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_STRONG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalVolunteers.AMY;
import static seedu.address.testutil.TypicalVolunteers.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddVolunteerCommand;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.Age;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.person.information.Region;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.VolunteerBuilder;

public class AddVolunteerCommandParserTest {
    private final AddVolunteerCommandParser parser = new AddVolunteerCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Volunteer expectedVolunteer = new VolunteerBuilder(BOB).withTags(VALID_TAG_STRONG)
                .withAvailableDates(VALID_START_DATE, VALID_END_DATE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + NRIC_VOLUNTEER_DESC_BOB + AGE_DESC_BOB + REGION_DESC_BOB
                + TAG_DESC_STRONG + AVAILABLE_DATES, new AddVolunteerCommand(expectedVolunteer));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + NRIC_VOLUNTEER_DESC_BOB + AGE_DESC_BOB + REGION_DESC_BOB
                + TAG_DESC_STRONG + AVAILABLE_DATES, new AddVolunteerCommand(expectedVolunteer));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + NRIC_VOLUNTEER_DESC_BOB + AGE_DESC_BOB + REGION_DESC_BOB
                + TAG_DESC_STRONG + AVAILABLE_DATES, new AddVolunteerCommand(expectedVolunteer));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + NRIC_VOLUNTEER_DESC_BOB + AGE_DESC_BOB + REGION_DESC_BOB
                + TAG_DESC_STRONG + AVAILABLE_DATES, new AddVolunteerCommand(expectedVolunteer));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + NRIC_VOLUNTEER_DESC_BOB + AGE_DESC_BOB + REGION_DESC_BOB
                + TAG_DESC_STRONG + AVAILABLE_DATES, new AddVolunteerCommand(expectedVolunteer));

        // multiple nrics - last nric accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_VOLUNTEER_DESC_AMY + NRIC_VOLUNTEER_DESC_BOB + AGE_DESC_BOB + REGION_DESC_BOB
                + TAG_DESC_STRONG + AVAILABLE_DATES, new AddVolunteerCommand(expectedVolunteer));

        // multiple age - last age accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_VOLUNTEER_DESC_BOB + AGE_DESC_AMY + AGE_DESC_BOB + REGION_DESC_BOB
                + TAG_DESC_STRONG, new AddVolunteerCommand(expectedVolunteer));

        // multiple region - last region accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_VOLUNTEER_DESC_BOB + AGE_DESC_BOB + REGION_DESC_AMY + REGION_DESC_BOB
                + TAG_DESC_STRONG + AVAILABLE_DATES, new AddVolunteerCommand(expectedVolunteer));

        // multiple tags - all accepted
        Volunteer expectedVolunteerMultipleTags = new VolunteerBuilder(BOB).withTags(VALID_TAG_STRONG, VALID_TAG_SINGLE)
                .withAvailableDates(VALID_START_DATE, VALID_END_DATE)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_VOLUNTEER_DESC_BOB + AGE_DESC_BOB + REGION_DESC_BOB + TAG_DESC_SINGLE + TAG_DESC_STRONG + AVAILABLE_DATES,
                new AddVolunteerCommand(expectedVolunteerMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Volunteer expectedVolunteer = new VolunteerBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + NRIC_VOLUNTEER_DESC_AMY + AGE_DESC_AMY + REGION_DESC_AMY,
                new AddVolunteerCommand(expectedVolunteer));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddVolunteerCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_VOLUNTEER_DESC_BOB + AGE_DESC_BOB + REGION_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_VOLUNTEER_DESC_BOB + AGE_DESC_BOB + REGION_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB
                + NRIC_VOLUNTEER_DESC_BOB + AGE_DESC_BOB + REGION_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                + NRIC_VOLUNTEER_DESC_BOB + AGE_DESC_BOB + REGION_DESC_BOB, expectedMessage);

        // missing nric prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + VALID_NRIC_BOB + AGE_DESC_BOB + REGION_DESC_BOB, expectedMessage);

        // missing age prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_VOLUNTEER_DESC_BOB + VALID_AGE_BOB + REGION_DESC_BOB, expectedMessage);

        // missing region prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_VOLUNTEER_DESC_BOB + AGE_DESC_BOB + VALID_REGION_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB
                + VALID_NRIC_BOB + VALID_AGE_BOB + VALID_REGION_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_VOLUNTEER_DESC_BOB + AGE_DESC_BOB + REGION_DESC_BOB + TAG_DESC_SINGLE
                + TAG_DESC_STRONG + AVAILABLE_DATES, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_VOLUNTEER_DESC_BOB + AGE_DESC_BOB + REGION_DESC_BOB + TAG_DESC_SINGLE
                + TAG_DESC_STRONG + AVAILABLE_DATES, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + NRIC_VOLUNTEER_DESC_BOB + AGE_DESC_BOB + REGION_DESC_BOB + TAG_DESC_SINGLE
                + TAG_DESC_STRONG + AVAILABLE_DATES, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + NRIC_VOLUNTEER_DESC_BOB + AGE_DESC_BOB + REGION_DESC_BOB + TAG_DESC_SINGLE
                + TAG_DESC_STRONG + AVAILABLE_DATES, Address.MESSAGE_CONSTRAINTS);

        // invalid nric
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_VOLUNTEER_NRIC_DESC + AGE_DESC_BOB + REGION_DESC_BOB + TAG_DESC_SINGLE
                + TAG_DESC_STRONG + AVAILABLE_DATES, Nric.MESSAGE_CONSTRAINTS);

        // invalid age
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_VOLUNTEER_DESC_BOB + INVALID_AGE_DESC + REGION_DESC_BOB + TAG_DESC_SINGLE
                + TAG_DESC_STRONG + AVAILABLE_DATES, Age.MESSAGE_CONSTRAINTS);

        // ivalid region
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_VOLUNTEER_DESC_BOB + AGE_DESC_BOB + INVALID_REGION_DESC + TAG_DESC_SINGLE
                + TAG_DESC_STRONG, Region.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_VOLUNTEER_DESC_BOB + AGE_DESC_BOB + REGION_DESC_BOB + INVALID_TAG_DESC
                + VALID_TAG_STRONG, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC + NRIC_VOLUNTEER_DESC_BOB + AGE_DESC_BOB + REGION_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + NRIC_VOLUNTEER_DESC_BOB + AGE_DESC_BOB + REGION_DESC_BOB
                        + TAG_DESC_SINGLE + TAG_DESC_STRONG + AVAILABLE_DATES,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddVolunteerCommand.MESSAGE_USAGE));
    }
}
