package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PLATOON_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.RANK_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.UNIT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLATOON_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RANK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIT_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FilterCommand.FilterDescriptor;
import seedu.address.testutil.FilterDescriptorBuilder;

public class FilterCommandParserTest {

    private static final String PHONE_EMPTY = " " + PREFIX_PHONE;
    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE);

    private final FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no field given
        assertParseFailure(parser, "", FilterCommand.MESSAGE_NO_FIELD_GIVEN);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // preamble given
        assertParseFailure(parser, "random string" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "123" + EMAIL_DESC_AMY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // empty command
        assertParseFailure(parser, "", FilterCommand.MESSAGE_NO_FIELD_GIVEN);

        // single empty field
        assertParseFailure(parser, PHONE_EMPTY, FilterCommand.MESSAGE_EMPTY_FIELD);
        assertParseFailure(parser, TAG_EMPTY + "    ", FilterCommand.MESSAGE_EMPTY_FIELD);

        // empty phone followed by valid email
        assertParseFailure(parser, PHONE_EMPTY + EMAIL_DESC_AMY, FilterCommand.MESSAGE_EMPTY_FIELD);

        // valid email followed by empty tag
        assertParseFailure(parser, EMAIL_DESC_AMY + TAG_EMPTY, FilterCommand.MESSAGE_EMPTY_FIELD);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = PHONE_DESC_AMY + TAG_DESC_HUSBAND + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                + NAME_DESC_AMY + TAG_DESC_FRIEND + RANK_DESC_AMY + PLATOON_DESC_AMY + UNIT_DESC_AMY
                + COMPANY_DESC_AMY;

        FilterDescriptor descriptor = new FilterDescriptorBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withRank(VALID_RANK_AMY)
                .withUnit(VALID_UNIT_AMY).withCompany(VALID_COMPANY_AMY).withPlatoon(VALID_PLATOON_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        FilterCommand expectedCommand = new FilterCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = PHONE_DESC_BOB + EMAIL_DESC_AMY;

        FilterDescriptor descriptor = new FilterDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        FilterCommand expectedCommand = new FilterCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        String userInput = NAME_DESC_AMY;
        FilterDescriptor descriptor = new FilterDescriptorBuilder().withName(VALID_NAME_AMY).build();
        FilterCommand expectedCommand = new FilterCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = PHONE_DESC_AMY;
        descriptor = new FilterDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new FilterCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = EMAIL_DESC_AMY;
        descriptor = new FilterDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new FilterCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = ADDRESS_DESC_AMY;
        descriptor = new FilterDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new FilterCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // rank
        userInput = RANK_DESC_AMY;
        descriptor = new FilterDescriptorBuilder().withRank(VALID_RANK_AMY).build();
        expectedCommand = new FilterCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // unit
        userInput = UNIT_DESC_AMY;
        descriptor = new FilterDescriptorBuilder().withUnit(VALID_UNIT_AMY).build();
        expectedCommand = new FilterCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // company
        userInput = COMPANY_DESC_AMY;
        descriptor = new FilterDescriptorBuilder().withCompany(VALID_COMPANY_AMY).build();
        expectedCommand = new FilterCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // platoon
        userInput = PLATOON_DESC_AMY;
        descriptor = new FilterDescriptorBuilder().withPlatoon(VALID_PLATOON_AMY).build();
        expectedCommand = new FilterCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = TAG_DESC_FRIEND;
        descriptor = new FilterDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new FilterCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        FilterDescriptor descriptor = new FilterDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        FilterCommand expectedCommand = new FilterCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
