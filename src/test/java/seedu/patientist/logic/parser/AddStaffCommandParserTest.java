package seedu.patientist.logic.parser;

import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.patientist.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.patientist.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.patientist.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.patientist.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.patientist.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.patientist.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.patientist.logic.commands.CommandTestUtil.INVALID_PID_DESC;
import static seedu.patientist.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.patientist.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.patientist.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.patientist.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.PID_DESC_AMY;
import static seedu.patientist.logic.commands.CommandTestUtil.PID_DESC_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.patientist.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.patientist.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.patientist.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_PID_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.patientist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.patientist.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.patientist.testutil.TypicalStaff.AMY;
import static seedu.patientist.testutil.TypicalStaff.BOB;

import org.junit.jupiter.api.Test;

import seedu.patientist.logic.commands.AddStaffCommand;
import seedu.patientist.model.person.Address;
import seedu.patientist.model.person.Email;
import seedu.patientist.model.person.IdNumber;
import seedu.patientist.model.person.Name;
import seedu.patientist.model.person.Phone;
import seedu.patientist.model.person.staff.Staff;
import seedu.patientist.model.tag.Tag;
import seedu.patientist.testutil.StaffBuilder;

public class AddStaffCommandParserTest {
    private final String STAFF_TAG = "Staff";
    private AddStaffCommandParser parser = new AddStaffCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Staff expectedStaff = new StaffBuilder(BOB).withTags(VALID_TAG_FRIEND, STAFF_TAG).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + PID_DESC_BOB, new AddStaffCommand(expectedStaff));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + PID_DESC_BOB, new AddStaffCommand(expectedStaff));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + PID_DESC_BOB, new AddStaffCommand(expectedStaff));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + PID_DESC_BOB, new AddStaffCommand(expectedStaff));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + PID_DESC_BOB, new AddStaffCommand(expectedStaff));

        // multiple tags - all accepted
        Staff expectedStaffMultipleTags = new StaffBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND, STAFF_TAG)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + PID_DESC_BOB,
                new AddStaffCommand(expectedStaffMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Staff expectedPatient = new StaffBuilder(AMY).withTags(STAFF_TAG).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PID_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY, new AddStaffCommand(expectedPatient));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStaffCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + PID_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + PID_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB
                + PID_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                + PID_DESC_BOB, expectedMessage);

        // missing pid prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + VALID_PID_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB
                + VALID_PID_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + PID_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + PID_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + PID_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + PID_DESC_BOB, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND + PID_DESC_BOB, Tag.MESSAGE_CONSTRAINTS);

        // invalid pid
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_PID_DESC + TAG_DESC_HUSBAND + VALID_TAG_FRIEND, IdNumber.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + PID_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + PID_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStaffCommand.MESSAGE_USAGE));
    }
}