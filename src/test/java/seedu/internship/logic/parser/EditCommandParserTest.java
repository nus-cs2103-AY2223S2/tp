package seedu.internship.logic.parser;

import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.logic.commands.CommandTestUtil.COMPANY_DESC_ML1;
import static seedu.internship.logic.commands.CommandTestUtil.COMPANY_DESC_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.DESCRIPTION_DESC_ML1;
import static seedu.internship.logic.commands.CommandTestUtil.DESCRIPTION_DESC_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC;
import static seedu.internship.logic.commands.CommandTestUtil.INVALID_POSITION_DESC;
import static seedu.internship.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.internship.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.internship.logic.commands.CommandTestUtil.POSITION_DESC_ML1;
import static seedu.internship.logic.commands.CommandTestUtil.STATUS_DESC_ML1;
import static seedu.internship.logic.commands.CommandTestUtil.STATUS_DESC_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.TAG_DESC_FUN;
import static seedu.internship.logic.commands.CommandTestUtil.TAG_DESC_IMPORTANT;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_COMPANY_ML1;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_COMPANY_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ML1;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_POSITION_ML1;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_STATUS_ML1;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_STATUS_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_TAG_FUN;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_TAG_IMPORTANT;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.internship.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.internship.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.internship.testutil.TypicalIndexes.INDEX_THIRD_INTERNSHIP;

import org.junit.jupiter.api.Test;

import seedu.internship.commons.core.index.Index;
import seedu.internship.logic.commands.EditCommand;
import seedu.internship.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.internship.model.internship.Company;
import seedu.internship.model.internship.Position;
import seedu.internship.model.internship.Status;
import seedu.internship.model.tag.Tag;
import seedu.internship.testutil.EditInternshipDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_POSITION_ML1, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + POSITION_DESC_ML1, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + POSITION_DESC_ML1, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_POSITION_DESC, Position.MESSAGE_CONSTRAINTS); // invalid position
        assertParseFailure(parser, "1" + INVALID_COMPANY_DESC, Company.MESSAGE_CONSTRAINTS); // invalid company
        assertParseFailure(parser, "1" + INVALID_STATUS_DESC, Status.MESSAGE_CONSTRAINTS); // invalid status

        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid company followed by valid status
        assertParseFailure(parser, "1" + INVALID_COMPANY_DESC + STATUS_DESC_ML1, Company.MESSAGE_CONSTRAINTS);

        // valid company followed by invalid company. The test case for invalid company followed by valid company
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + COMPANY_DESC_ML1 + INVALID_COMPANY_DESC, Company.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FUN + TAG_DESC_IMPORTANT + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FUN + TAG_EMPTY + TAG_DESC_IMPORTANT, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FUN + TAG_DESC_IMPORTANT, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_POSITION_DESC + INVALID_COMPANY_DESC + INVALID_STATUS_DESC,
                Position.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_SE1 + TAG_DESC_IMPORTANT
                + POSITION_DESC_ML1 + COMPANY_DESC_ML1 + STATUS_DESC_ML1 + TAG_DESC_FUN;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withPosition(VALID_POSITION_ML1)
                .withCompany(VALID_COMPANY_ML1).withStatus(VALID_STATUS_ML1).withDescription(VALID_DESCRIPTION_SE1)
                .withTags(VALID_TAG_IMPORTANT, VALID_TAG_FUN).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_SE1 + COMPANY_DESC_ML1;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_SE1)
                .withCompany(VALID_COMPANY_ML1).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // position
        Index targetIndex = INDEX_THIRD_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + POSITION_DESC_ML1;
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withPosition(VALID_POSITION_ML1).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // company
        userInput = targetIndex.getOneBased() + COMPANY_DESC_ML1;
        descriptor = new EditInternshipDescriptorBuilder().withCompany(VALID_COMPANY_ML1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // status
        userInput = targetIndex.getOneBased() + STATUS_DESC_ML1;
        descriptor = new EditInternshipDescriptorBuilder().withStatus(VALID_STATUS_ML1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_ML1;
        descriptor = new EditInternshipDescriptorBuilder().withDescription(VALID_DESCRIPTION_ML1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FUN;
        descriptor = new EditInternshipDescriptorBuilder().withTags(VALID_TAG_FUN).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + COMPANY_DESC_ML1 + STATUS_DESC_ML1 + DESCRIPTION_DESC_ML1
                + TAG_DESC_FUN + COMPANY_DESC_ML1 + STATUS_DESC_ML1 + DESCRIPTION_DESC_ML1 + TAG_DESC_FUN
                + COMPANY_DESC_SE1 + STATUS_DESC_SE1 + DESCRIPTION_DESC_SE1 + TAG_DESC_IMPORTANT;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withCompany(VALID_COMPANY_SE1)
                .withStatus(VALID_STATUS_SE1)
                .withDescription(VALID_DESCRIPTION_SE1)
                .withTags(VALID_TAG_FUN, VALID_TAG_IMPORTANT)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + INVALID_STATUS_DESC + STATUS_DESC_SE1;
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withStatus(VALID_STATUS_SE1).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + COMPANY_DESC_SE1 + INVALID_STATUS_DESC + DESCRIPTION_DESC_SE1
                + STATUS_DESC_SE1;
        descriptor = new EditInternshipDescriptorBuilder().withCompany(VALID_COMPANY_SE1).withStatus(VALID_STATUS_SE1)
                .withDescription(VALID_DESCRIPTION_SE1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
