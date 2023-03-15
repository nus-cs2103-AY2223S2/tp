package seedu.fitbook.logic.parser;

import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbook.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.fitbook.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.fitbook.logic.commands.CommandTestUtil.APPOINTMENT_DESC_DATE_ONE;
import static seedu.fitbook.logic.commands.CommandTestUtil.APPOINTMENT_DESC_DATE_TWO;
import static seedu.fitbook.logic.commands.CommandTestUtil.CALORIE_DESC_AMY;
import static seedu.fitbook.logic.commands.CommandTestUtil.CALORIE_DESC_BOB;
import static seedu.fitbook.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.fitbook.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.fitbook.logic.commands.CommandTestUtil.GENDER_DESC_AMY;
import static seedu.fitbook.logic.commands.CommandTestUtil.GENDER_DESC_BOB;
import static seedu.fitbook.logic.commands.CommandTestUtil.GOAL_DESC_AMY;
import static seedu.fitbook.logic.commands.CommandTestUtil.GOAL_DESC_BOB;
import static seedu.fitbook.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.fitbook.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DESC;
import static seedu.fitbook.logic.commands.CommandTestUtil.INVALID_CALORIE_DESC;
import static seedu.fitbook.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.fitbook.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.fitbook.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.fitbook.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.fitbook.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.fitbook.logic.commands.CommandTestUtil.INVALID_WEIGHT_DESC;
import static seedu.fitbook.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.fitbook.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.fitbook.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.fitbook.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.fitbook.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.fitbook.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.fitbook.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.fitbook.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DATE_ONE;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DATE_TWO;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_CALORIE_BOB;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.fitbook.logic.commands.CommandTestUtil.WEIGHT_DESC_AMY;
import static seedu.fitbook.logic.commands.CommandTestUtil.WEIGHT_DESC_BOB;
import static seedu.fitbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fitbook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.fitbook.testutil.client.TypicalClients.AMY;
import static seedu.fitbook.testutil.client.TypicalClients.BOB;

import org.junit.jupiter.api.Test;

import seedu.fitbook.logic.commands.AddCommand;
import seedu.fitbook.model.client.Address;
import seedu.fitbook.model.client.Appointment;
import seedu.fitbook.model.client.Calorie;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.client.Email;
import seedu.fitbook.model.client.Name;
import seedu.fitbook.model.client.Phone;
import seedu.fitbook.model.tag.Tag;
import seedu.fitbook.testutil.client.ClientBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Client expectedClient = new ClientBuilder(BOB).withTags(VALID_TAG_FRIEND)
                .withAppointments(VALID_APPOINTMENT_DATE_ONE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + APPOINTMENT_DESC_DATE_ONE + WEIGHT_DESC_BOB + GENDER_DESC_BOB + GOAL_DESC_BOB
                + TAG_DESC_FRIEND + CALORIE_DESC_BOB, new AddCommand(expectedClient));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + APPOINTMENT_DESC_DATE_ONE + WEIGHT_DESC_BOB + GENDER_DESC_BOB + GOAL_DESC_BOB
                + TAG_DESC_FRIEND + CALORIE_DESC_BOB, new AddCommand(expectedClient));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + APPOINTMENT_DESC_DATE_ONE + WEIGHT_DESC_BOB + GENDER_DESC_BOB + GOAL_DESC_BOB
                + TAG_DESC_FRIEND + CALORIE_DESC_BOB, new AddCommand(expectedClient));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + APPOINTMENT_DESC_DATE_ONE + WEIGHT_DESC_BOB + GENDER_DESC_BOB + GOAL_DESC_BOB
                + TAG_DESC_FRIEND + CALORIE_DESC_BOB, new AddCommand(expectedClient));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + APPOINTMENT_DESC_DATE_ONE + WEIGHT_DESC_BOB + GENDER_DESC_BOB + GOAL_DESC_BOB
                + TAG_DESC_FRIEND + CALORIE_DESC_BOB, new AddCommand(expectedClient));

        // multiple calorie - last calorie accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + CALORIE_DESC_AMY + CALORIE_DESC_BOB
                        + WEIGHT_DESC_BOB + GENDER_DESC_BOB + APPOINTMENT_DESC_DATE_ONE + GOAL_DESC_BOB,
                new AddCommand(expectedClient));

        // multiple gender - last gender accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + CALORIE_DESC_BOB + APPOINTMENT_DESC_DATE_ONE
                        + WEIGHT_DESC_BOB + GENDER_DESC_AMY + GENDER_DESC_BOB + GOAL_DESC_BOB,
                new AddCommand(expectedClient));

        // multiple weight - last weight accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + CALORIE_DESC_AMY + CALORIE_DESC_BOB
                        + APPOINTMENT_DESC_DATE_ONE + WEIGHT_DESC_AMY + WEIGHT_DESC_BOB + GENDER_DESC_BOB
                        + GOAL_DESC_BOB, new AddCommand(expectedClient));

        // multiple goal - last goal accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + CALORIE_DESC_BOB
                + APPOINTMENT_DESC_DATE_ONE + WEIGHT_DESC_BOB + GENDER_DESC_BOB
                + GOAL_DESC_AMY + GOAL_DESC_BOB, new AddCommand(expectedClient));

        // multiple tags - all accepted
        Client expectedClientMultipleTags = new ClientBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .withAppointments(VALID_APPOINTMENT_DATE_ONE).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + APPOINTMENT_DESC_DATE_ONE + WEIGHT_DESC_BOB + GENDER_DESC_BOB + GOAL_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + CALORIE_DESC_BOB,
                new AddCommand(expectedClientMultipleTags));

        // multiple appointments - all accepted
        Client expectedClientMultipleAppointments = new ClientBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .withAppointments(VALID_APPOINTMENT_DATE_ONE, VALID_APPOINTMENT_DATE_TWO).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + GENDER_DESC_BOB + WEIGHT_DESC_BOB + APPOINTMENT_DESC_DATE_ONE + APPOINTMENT_DESC_DATE_TWO
                        + GOAL_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + CALORIE_DESC_BOB,
                new AddCommand(expectedClientMultipleAppointments));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Client expectedClient = new ClientBuilder(AMY).withAppointments().withCalorie("0000").withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                + WEIGHT_DESC_AMY + GENDER_DESC_AMY + GOAL_DESC_AMY, new AddCommand(expectedClient));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + APPOINTMENT_DESC_DATE_ONE + WEIGHT_DESC_BOB + GENDER_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + VALID_CALORIE_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + APPOINTMENT_DESC_DATE_ONE + WEIGHT_DESC_BOB + GENDER_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + VALID_CALORIE_BOB,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + APPOINTMENT_DESC_DATE_ONE + WEIGHT_DESC_BOB + GENDER_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + VALID_CALORIE_BOB, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                        + APPOINTMENT_DESC_DATE_ONE + WEIGHT_DESC_BOB + GENDER_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + VALID_CALORIE_BOB,
                Address.MESSAGE_CONSTRAINTS);

        // invalid gender
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + APPOINTMENT_DESC_DATE_ONE + WEIGHT_DESC_BOB + INVALID_GENDER_DESC + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND + VALID_CALORIE_BOB, Address.MESSAGE_CONSTRAINTS);

        // invalid weight
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + APPOINTMENT_DESC_DATE_ONE + INVALID_WEIGHT_DESC + GENDER_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND + VALID_CALORIE_BOB, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + WEIGHT_DESC_BOB + GENDER_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND + VALID_CALORIE_BOB,
                Tag.MESSAGE_CONSTRAINTS);

        // invalid calorie
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + VALID_TAG_HUSBAND + VALID_TAG_FRIEND + INVALID_CALORIE_DESC
                + WEIGHT_DESC_BOB + GENDER_DESC_BOB, Calorie.MESSAGE_CONSTRAINTS);

        // invalid appointment
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + WEIGHT_DESC_BOB + GENDER_DESC_BOB + TAG_DESC_HUSBAND + INVALID_APPOINTMENT_DESC
                        + VALID_CALORIE_BOB, Appointment.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + WEIGHT_DESC_BOB + GENDER_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + WEIGHT_DESC_BOB + GENDER_DESC_BOB + TAG_DESC_HUSBAND
                        + TAG_DESC_FRIEND + VALID_CALORIE_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
