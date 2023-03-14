//package seedu.address.logic.parser;
//
//import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_COMPANY_A;
//import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_COMPANY_B;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_COMPANY_A;
//import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_COMPANY_B;
//import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
//import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
//import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
//import static seedu.address.testutil.TypicalContacts.COMPANY_A;
//import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.commons.core.index.Index;
//import seedu.address.logic.commands.AddContactCommand;
//import seedu.address.model.contact.Contact;
//import seedu.address.model.contact.Phone;
//import seedu.address.testutil.ContactBuilder;
//
//public class AddContactCommandParserTest {
//    private static final String MESSAGE_INVALID_FORMAT =
//            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddContactCommand.MESSAGE_USAGE);
//    private AddContactCommandParser parser = new AddContactCommandParser();
//
//    @Test
//    public void parse_missingParts_failure() {
//        // no index specified
//        assertParseFailure(parser, PHONE_DESC_COMPANY_A, MESSAGE_INVALID_FORMAT);
//
//        // no field specified
//        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);
//
//        // no index and no field specified
//        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
//    }
//
//    @Test
//    public void parse_invalidPreamble_failure() {
//        // negative index
//        assertParseFailure(parser, "-5" + PHONE_DESC_COMPANY_A + EMAIL_DESC_COMPANY_A, MESSAGE_INVALID_FORMAT);
//
//        // zero index
//        assertParseFailure(parser, "0" + PHONE_DESC_COMPANY_A + EMAIL_DESC_COMPANY_A, MESSAGE_INVALID_FORMAT);
//
//        // invalid arguments being parsed as preamble
//        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);
//
//        // invalid prefix being parsed as preamble
//        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
//    }
//
//    @Test
//    public void parse_invalidValue_failure() {
//        // multiple invalid values, but only the first invalid value is captured
//        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + INVALID_EMAIL_DESC,
//                Phone.MESSAGE_CONSTRAINTS);
//    }
//
//    @Test
//    public void parse_allFieldsSpecified_success() {
//        Index targetIndex = INDEX_SECOND_PERSON;
//        Contact expectedContact = new ContactBuilder(COMPANY_A).build();
//
//        // whitespace only preamble
//        assertParseSuccess(parser, PREAMBLE_WHITESPACE + targetIndex.getOneBased() + PHONE_DESC_COMPANY_A
//                + EMAIL_DESC_COMPANY_A, new AddContactCommand(targetIndex, expectedContact));
//
//        // multiple phones - last phone accepted
//        assertParseSuccess(parser, targetIndex.getOneBased() + PHONE_DESC_COMPANY_B + PHONE_DESC_COMPANY_A
//                + EMAIL_DESC_COMPANY_A, new AddContactCommand(targetIndex, expectedContact));
//
//        // multiple emails - last email accepted
//        assertParseSuccess(parser, targetIndex.getOneBased() + PHONE_DESC_COMPANY_A + EMAIL_DESC_COMPANY_B
//                + EMAIL_DESC_COMPANY_A, new AddContactCommand(targetIndex, expectedContact));
//    }
//}
