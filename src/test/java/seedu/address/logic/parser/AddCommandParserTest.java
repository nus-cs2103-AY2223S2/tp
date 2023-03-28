package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIMESLOT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TYPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TIMESLOT_DESC_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.TIMESLOT_DESC_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_DESC_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_DESC_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESOURCE_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESLOT_CS3219;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.CS3230;
import static seedu.address.testutil.TypicalModules.CS3219;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.module.Address;
import seedu.address.model.module.Module;
import seedu.address.model.module.Name;
import seedu.address.model.module.Resource;
import seedu.address.model.module.TimeSlot;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ModuleBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Module expectedModule = new ModuleBuilder(CS3219).withTags(VALID_TAG_TUTORIAL).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_CS3219 + TYPE_DESC_CS3219 + TIMESLOT_DESC_CS3219
                + ADDRESS_DESC_CS3219 + TAG_DESC_FRIEND, new AddCommand(expectedModule));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_CS3230 + NAME_DESC_CS3219 + TYPE_DESC_CS3219 + TIMESLOT_DESC_CS3219
                + ADDRESS_DESC_CS3219 + TAG_DESC_FRIEND, new AddCommand(expectedModule));

        // multiple types - last type accepted
        assertParseSuccess(parser, NAME_DESC_CS3219 + TYPE_DESC_CS3230 + TYPE_DESC_CS3219 + TIMESLOT_DESC_CS3219
                + ADDRESS_DESC_CS3219 + TAG_DESC_FRIEND, new AddCommand(expectedModule));

        // multiple timeSlots - last timeSlot accepted
        assertParseSuccess(parser, NAME_DESC_CS3219 + TYPE_DESC_CS3219 + TIMESLOT_DESC_CS3230 + TIMESLOT_DESC_CS3219
                + ADDRESS_DESC_CS3219 + TAG_DESC_FRIEND, new AddCommand(expectedModule));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_CS3219 + TYPE_DESC_CS3219 + TIMESLOT_DESC_CS3219 + ADDRESS_DESC_CS3230
                + ADDRESS_DESC_CS3219 + TAG_DESC_FRIEND, new AddCommand(expectedModule));

        // multiple tags - all accepted
        Module expectedModuleMultipleTags = new ModuleBuilder(CS3219).withTags(VALID_TAG_TUTORIAL, VALID_TAG_LECTURE)
                .build();
        assertParseSuccess(parser, NAME_DESC_CS3219 + TYPE_DESC_CS3219 + TIMESLOT_DESC_CS3219 + ADDRESS_DESC_CS3219
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedModuleMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Module expectedModule = new ModuleBuilder(CS3230).withTags().build();
        assertParseSuccess(parser, NAME_DESC_CS3230 + TYPE_DESC_CS3230 + TIMESLOT_DESC_CS3230 + ADDRESS_DESC_CS3230,
                new AddCommand(expectedModule));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_CS3219 + TYPE_DESC_CS3219 + TIMESLOT_DESC_CS3219 + ADDRESS_DESC_CS3219,
                expectedMessage);

        // missing type prefix
        assertParseFailure(parser, NAME_DESC_CS3219 + VALID_RESOURCE_CS3219 + TIMESLOT_DESC_CS3219 + ADDRESS_DESC_CS3219,
                expectedMessage);

        // missing timeSlot prefix
        assertParseFailure(parser, NAME_DESC_CS3219 + TYPE_DESC_CS3219 + VALID_TIMESLOT_CS3219 + ADDRESS_DESC_CS3219,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_CS3219 + TYPE_DESC_CS3219 + TIMESLOT_DESC_CS3219 + VALID_ADDRESS_CS3219,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_CS3219 + VALID_RESOURCE_CS3219 + VALID_TIMESLOT_CS3219 + VALID_ADDRESS_CS3219,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + TYPE_DESC_CS3219 + TIMESLOT_DESC_CS3219 + ADDRESS_DESC_CS3219
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid type
        assertParseFailure(parser, NAME_DESC_CS3219 + INVALID_TYPE_DESC + TIMESLOT_DESC_CS3219 + ADDRESS_DESC_CS3219
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Resource.MESSAGE_CONSTRAINTS);

        // invalid timeSlot
        assertParseFailure(parser, NAME_DESC_CS3219 + TYPE_DESC_CS3219 + INVALID_TIMESLOT_DESC + ADDRESS_DESC_CS3219
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, TimeSlot.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_CS3219 + TYPE_DESC_CS3219 + TIMESLOT_DESC_CS3219 + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_CS3219 + TYPE_DESC_CS3219 + TIMESLOT_DESC_CS3219 + ADDRESS_DESC_CS3219
                + INVALID_TAG_DESC + VALID_TAG_TUTORIAL, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + TYPE_DESC_CS3219 + TIMESLOT_DESC_CS3219 + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_CS3219 + TYPE_DESC_CS3219 + TIMESLOT_DESC_CS3219
                + ADDRESS_DESC_CS3219 + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
