package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIMESLOT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TYPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.RESOURCE_DESC_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.RESOURCE_DESC_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.TEACHER_DESC_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.TIMESLOT_DESC_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.TIMESLOT_DESC_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESOURCE_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TEACHER_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESLOT_CS3230;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_MODULE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditModuleDescriptor;
import seedu.address.model.module.Address;
import seedu.address.model.module.Name;
// import seedu.address.model.module.Resource; //why is this not used?
import seedu.address.model.module.TimeSlot;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditModuleDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_CS3230, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_CS3230, MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_CS3230, MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        // assertParseFailure(parser, "1" + INVALID_TYPE_DESC, Resource.MESSAGE_CONSTRAINTS); // invalid type
        assertParseFailure(parser, "1"
                + INVALID_TIMESLOT_DESC, TimeSlot.MESSAGE_CONSTRAINTS); // invalid timeSlot
        assertParseFailure(parser, "1"
                + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid type followed by valid timeSlot
        //Commented out because resource can be anything
        // assertParseFailure(parser, "1" + INVALID_TYPE_DESC
        //      + TIMESLOT_DESC_CS3230, Resource.MESSAGE_CONSTRAINTS);

        // valid type followed by invalid type. The test case for invalid type followed by valid type
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        //assertParseFailure(parser, "1" + TAG_DESC_CS3219 + INVALID_TYPE_DESC, Resource.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Module} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_TUTORIAL + TAG_DESC_LECTURE
                + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_TUTORIAL + TAG_EMPTY
                + TAG_DESC_LECTURE, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_TUTORIAL
                + TAG_DESC_LECTURE, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_TIMESLOT_DESC + VALID_ADDRESS_CS3230
                        + VALID_RESOURCE_CS3230, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_MODULE;
        String userInput = targetIndex.getOneBased() + NAME_DESC_CS3230 + TAG_DESC_CS3230
                + TIMESLOT_DESC_CS3230 + ADDRESS_DESC_CS3230 + RESOURCE_DESC_CS3230 + DEADLINE_DESC_CS3230
                + TEACHER_DESC_CS3230 + REMARK_DESC_CS3230;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withName(VALID_NAME_CS3230)
                .withTimeSlot(VALID_TIMESLOT_CS3230)
                .withAddress(VALID_ADDRESS_CS3230)
                .withResource(VALID_RESOURCE_CS3230)
                .withDeadline(VALID_DEADLINE_CS3230)
                .withTeacher(VALID_TEACHER_CS3230)
                .withRemark(VALID_REMARK_CS3230)
                .withTags(VALID_TAG_CS3230).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_MODULE;
        String userInput = targetIndex.getOneBased() + TAG_DESC_CS3230 + TIMESLOT_DESC_CS3230;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withTags(VALID_TAG_CS3230)
                .withTimeSlot(VALID_TIMESLOT_CS3230).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_MODULE;
        String userInput = targetIndex.getOneBased() + NAME_DESC_CS3230;
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withName(VALID_NAME_CS3230).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // type
        userInput = targetIndex.getOneBased() + TAG_DESC_CS3230;
        descriptor = new EditModuleDescriptorBuilder().withTags(VALID_TAG_CS3230).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // timeSlot
        userInput = targetIndex.getOneBased() + TIMESLOT_DESC_CS3230;
        descriptor = new EditModuleDescriptorBuilder().withTimeSlot(VALID_TIMESLOT_CS3230).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_CS3230;
        descriptor = new EditModuleDescriptorBuilder().withAddress(VALID_ADDRESS_CS3230).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_CS3230;
        descriptor = new EditModuleDescriptorBuilder().withTags(VALID_TAG_CS3230).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_MODULE;
        String userInput = targetIndex.getOneBased() + RESOURCE_DESC_CS3219 + RESOURCE_DESC_CS3230
                + TIMESLOT_DESC_CS3219 + TIMESLOT_DESC_CS3230 + ADDRESS_DESC_CS3230 + TAG_DESC_CS3219
                + TAG_DESC_CS3230;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withResource(VALID_RESOURCE_CS3230)
                .withTimeSlot(VALID_TIMESLOT_CS3230).withAddress(VALID_ADDRESS_CS3230).withTags(VALID_TAG_CS3219,
                        VALID_TAG_CS3230).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_MODULE;
        String userInput = targetIndex.getOneBased() + INVALID_TYPE_DESC + RESOURCE_DESC_CS3230;
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withTags("911a")
                .withResource(VALID_RESOURCE_CS3230).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + TAG_DESC_CS3230;
        descriptor = new EditModuleDescriptorBuilder().withTags(VALID_TAG_CS3230).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_failure() {
        Index targetIndex = INDEX_THIRD_MODULE;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withTags().build();
        //EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseFailure(parser, userInput, Tag.MESSAGE_CONSTRAINTS);
    }
}
