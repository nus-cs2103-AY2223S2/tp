package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPLICANT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICANT_NAME_BENEDICT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICANT_NAME_CHRIS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditListingDescriptor;
import seedu.address.model.listing.Address;
import seedu.address.model.listing.Email;
import seedu.address.model.listing.JobTitle;
import seedu.address.model.listing.JobDescription;
import seedu.address.model.applicant.Applicant;
import seedu.address.testutil.EditListingDescriptorBuilder;

public class EditCommandParserTest {

    private static final String APPLICANT_EMPTY = " " + PREFIX_APPLICANT;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TITLE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_TITLE_DESC, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_TITLE_DESC, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC, JobTitle.MESSAGE_CONSTRAINTS); // invalid job title
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC, JobDescription.MESSAGE_CONSTRAINTS); // invalid job description
        assertParseFailure(parser, "1" + INVALID_APPLICANT_DESC, Applicant.MESSAGE_CONSTRAINTS); // invalid applicant

        // invalid job description followed by valid job title
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC + VALID_TITLE_DESC, JobDescription.MESSAGE_CONSTRAINTS);

        // valid job title followed by invalid job description. The test case for invalid job description followed by valid job title
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + VALID_TITLE_DESC + INVALID_DESCRIPTION_DESC, JobDescription.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_APPLICANT} alone will reset the applicants of the {@code Listing} being edited,
        // parsing it together with a valid applicant results in error
        assertParseFailure(parser, "1" + VALID_APPLICANT_NAME_BENEDICT_DESC + VALID_APPLICANT_NAME_CHRIS_DESC + APPLICANT_EMPTY, Applicant.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + VALID_APPLICANT_NAME_BENEDICT_DESC + APPLICANT_EMPTY + VALID_APPLICANT_NAME_CHRIS_DESC, Applicant.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + APPLICANT_EMPTY + VALID_APPLICANT_NAME_BENEDICT_DESC + VALID_APPLICANT_NAME_CHRIS_DESC, Applicant.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC + INVALID_DESCRIPTION_DESC,
                JobTitle.MESSAGE_CONSTRAINTS);
    }

//    @Test
//    public void parse_allFieldsSpecified_success() {
//        Index targetIndex = INDEX_SECOND_PERSON;
//        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + VALID_APPLICANT_NAME_CHRIS_DESC
//                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + VALID_TITLE_DESC + VALID_APPLICANT_NAME_BENEDICT_DESC;
//
//        EditListingDescriptor descriptor = new EditListingDescriptorBuilder().withJobTitle(VALID_NAME_AMY)
//                .withJobDescription(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
//                .withApplicants(VALID_APPLICANT_HUSBAND, VALID_APPLICANT_FRIEND).build();
//        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
//
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
//
//    @Test
//    public void parse_someFieldsSpecified_success() {
//        Index targetIndex = INDEX_FIRST_PERSON;
//        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;
//
//        EditListingDescriptor descriptor = new EditListingDescriptorBuilder().withJobDescription(VALID_PHONE_BOB)
//                .withEmail(VALID_EMAIL_AMY).build();
//        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
//
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
//
//    @Test
//    public void parse_oneFieldSpecified_success() {
//        // name
//        Index targetIndex = INDEX_THIRD_PERSON;
//        String userInput = targetIndex.getOneBased() + VALID_TITLE_DESC;
//        EditListingDescriptor descriptor = new EditListingDescriptorBuilder().withJobTitle(VALID_NAME_AMY).build();
//        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // job description
//        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
//        descriptor = new EditListingDescriptorBuilder().withJobDescription(VALID_PHONE_AMY).build();
//        expectedCommand = new EditCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // email
//        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
//        descriptor = new EditListingDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
//        expectedCommand = new EditCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // address
//        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
//        descriptor = new EditListingDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
//        expectedCommand = new EditCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // applicants
//        userInput = targetIndex.getOneBased() + VALID_APPLICANT_NAME_BENEDICT_DESC;
//        descriptor = new EditListingDescriptorBuilder().withApplicants(VALID_APPLICANT_FRIEND).build();
//        expectedCommand = new EditCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
//
//    @Test
//    public void parse_multipleRepeatedFields_acceptsLast() {
//        Index targetIndex = INDEX_FIRST_PERSON;
//        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
//                + VALID_APPLICANT_NAME_BENEDICT_DESC + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + VALID_APPLICANT_NAME_BENEDICT_DESC
//                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + VALID_APPLICANT_NAME_CHRIS_DESC;
//
//        EditListingDescriptor descriptor = new EditListingDescriptorBuilder().withJobDescription(VALID_PHONE_BOB)
//                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withApplicants(VALID_APPLICANT_FRIEND, VALID_APPLICANT_HUSBAND)
//                .build();
//        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
//
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
//
//    @Test
//    public void parse_invalidValueFollowedByValidValue_success() {
//        // no other valid values specified
//        Index targetIndex = INDEX_FIRST_PERSON;
//        String userInput = targetIndex.getOneBased() + INVALID_DESCRIPTION_DESC + PHONE_DESC_BOB;
//        EditListingDescriptor descriptor = new EditListingDescriptorBuilder().withJobDescription(VALID_PHONE_BOB).build();
//        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // other valid values specified
//        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_DESCRIPTION_DESC + ADDRESS_DESC_BOB
//                + PHONE_DESC_BOB;
//        descriptor = new EditListingDescriptorBuilder().withJobDescription(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
//                .withAddress(VALID_ADDRESS_BOB).build();
//        expectedCommand = new EditCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
//
//    @Test
//    public void parse_resetApplicants_success() {
//        Index targetIndex = INDEX_THIRD_PERSON;
//        String userInput = targetIndex.getOneBased() + APPLICANT_EMPTY;
//
//        EditListingDescriptor descriptor = new EditListingDescriptorBuilder().withApplicants().build();
//        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
//
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
}
