package seedu.internship.logic.parser;

import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.logic.commands.CommandTestUtil.COMPANY_DESC_ML1;
import static seedu.internship.logic.commands.CommandTestUtil.COMPANY_DESC_SE1;
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
import static seedu.internship.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_POSITION_ML1;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_STATUS_ML1;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_STATUS_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_TAG_FUN;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_TAG_IMPORTANT;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.internship.logic.commands.FindCommand;
import seedu.internship.model.internship.Company;
import seedu.internship.model.internship.Position;
import seedu.internship.model.internship.Status;
import seedu.internship.model.tag.Tag;
import seedu.internship.testutil.FilterInternshipDescriptorBuilder;

public class FindCommandParserTest {
    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);

    private FindCommandParser parser = new FindCommandParser();


    @Test
    public void parse_missingParts_failure() {
        // missing all fields
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_POSITION_DESC, Position.MESSAGE_CONSTRAINTS); // invalid position
        assertParseFailure(parser, INVALID_COMPANY_DESC, Company.MESSAGE_CONSTRAINTS); // invalid company
        assertParseFailure(parser, INVALID_STATUS_DESC, Status.MESSAGE_CONSTRAINTS); // invalid status

        assertParseFailure(parser, INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid company followed by valid status
        assertParseFailure(parser, INVALID_COMPANY_DESC + STATUS_DESC_ML1, Company.MESSAGE_CONSTRAINTS);

        // valid company followed by invalid company. The test case for invalid company followed by valid company
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, COMPANY_DESC_ML1 + INVALID_COMPANY_DESC, Company.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, INVALID_POSITION_DESC + INVALID_COMPANY_DESC + INVALID_STATUS_DESC,
                Position.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = DESCRIPTION_DESC_SE1 + TAG_DESC_IMPORTANT
                + POSITION_DESC_ML1 + COMPANY_DESC_ML1 + STATUS_DESC_ML1 + TAG_DESC_FUN;

        FindCommand.FilterInternshipDescriptor descriptor = new FilterInternshipDescriptorBuilder()
                .withPosition(VALID_POSITION_ML1)
                .withCompany(VALID_COMPANY_ML1).withStatus(VALID_STATUS_ML1).withDescription(VALID_DESCRIPTION_SE1)
                .withTags(VALID_TAG_IMPORTANT, VALID_TAG_FUN).build();
        FindCommand expectedCommand = new FindCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = DESCRIPTION_DESC_SE1 + COMPANY_DESC_ML1;

        FindCommand.FilterInternshipDescriptor descriptor = new FilterInternshipDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_SE1)
                .withCompany(VALID_COMPANY_ML1).build();
        FindCommand expectedCommand = new FindCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // position
        String userInput = POSITION_DESC_ML1;
        FindCommand.FilterInternshipDescriptor descriptor = new FilterInternshipDescriptorBuilder()
                .withPosition(VALID_POSITION_ML1).build();
        FindCommand expectedCommand = new FindCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // company
        userInput = COMPANY_DESC_ML1;
        descriptor = new FilterInternshipDescriptorBuilder().withCompany(VALID_COMPANY_ML1).build();
        expectedCommand = new FindCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // status
        userInput = STATUS_DESC_ML1;
        descriptor = new FilterInternshipDescriptorBuilder().withStatus(VALID_STATUS_ML1).build();
        expectedCommand = new FindCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);


        // tags
        userInput = TAG_DESC_FUN;
        descriptor = new FilterInternshipDescriptorBuilder().withTags(VALID_TAG_FUN).build();
        expectedCommand = new FindCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }


    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String userInput = INVALID_STATUS_DESC + STATUS_DESC_SE1;
        FindCommand.FilterInternshipDescriptor descriptor = new FilterInternshipDescriptorBuilder()
                .withStatus(VALID_STATUS_SE1).build();
        FindCommand expectedCommand = new FindCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = COMPANY_DESC_SE1 + INVALID_STATUS_DESC + DESCRIPTION_DESC_SE1
                + STATUS_DESC_SE1;
        descriptor = new FilterInternshipDescriptorBuilder().withCompany(VALID_COMPANY_SE1).withStatus(VALID_STATUS_SE1)
                .withDescription(VALID_DESCRIPTION_SE1).build();
        expectedCommand = new FindCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
