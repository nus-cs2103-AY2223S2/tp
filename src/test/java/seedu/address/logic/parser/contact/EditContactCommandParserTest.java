package seedu.address.logic.parser.contact;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BANK_OF_AMERICA;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_META;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BANK_OF_AMERICA;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_META;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BANK_OF_AMERICA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_META;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BANK_OF_AMERICA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_META;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.contact.EditContactCommand;
import seedu.address.model.contact.Phone;
import seedu.address.testutil.EditContactDescriptorBuilder;

public class EditContactCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditContactCommand.MESSAGE_USAGE);

    private EditContactCommandParser parser = new EditContactCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, PHONE_DESC_BANK_OF_AMERICA, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditContactCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + PHONE_DESC_BANK_OF_AMERICA + EMAIL_DESC_BANK_OF_AMERICA,
                MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + PHONE_DESC_BANK_OF_AMERICA + EMAIL_DESC_BANK_OF_AMERICA,
                MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + INVALID_EMAIL_DESC,
                Phone.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_APPLICATION;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BANK_OF_AMERICA + EMAIL_DESC_BANK_OF_AMERICA;

        EditContactCommand.EditContactDescriptor descriptor = new EditContactDescriptorBuilder()
                .withPhone(VALID_PHONE_BANK_OF_AMERICA).withEmail(VALID_EMAIL_BANK_OF_AMERICA).build();
        EditContactCommand expectedCommand = new EditContactCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BANK_OF_AMERICA;

        EditContactCommand.EditContactDescriptor descriptor = new EditContactDescriptorBuilder()
                .withPhone(VALID_PHONE_BANK_OF_AMERICA).build();
        EditContactCommand expectedCommand = new EditContactCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // phone
        Index targetIndex = INDEX_THIRD_APPLICATION;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BANK_OF_AMERICA;
        EditContactCommand.EditContactDescriptor descriptor = new EditContactDescriptorBuilder()
                .withPhone(VALID_PHONE_BANK_OF_AMERICA).build();
        EditContactCommand expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BANK_OF_AMERICA;
        descriptor = new EditContactDescriptorBuilder().withEmail(VALID_EMAIL_BANK_OF_AMERICA).build();
        expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BANK_OF_AMERICA + EMAIL_DESC_BANK_OF_AMERICA
                + PHONE_DESC_META + EMAIL_DESC_META;

        EditContactCommand.EditContactDescriptor descriptor = new EditContactDescriptorBuilder()
                .withPhone(VALID_PHONE_META).withEmail(VALID_EMAIL_META).build();
        EditContactCommand expectedCommand = new EditContactCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BANK_OF_AMERICA;
        EditContactCommand.EditContactDescriptor descriptor = new EditContactDescriptorBuilder()
                .withPhone(VALID_PHONE_BANK_OF_AMERICA).build();
        EditContactCommand expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BANK_OF_AMERICA + INVALID_PHONE_DESC
                + PHONE_DESC_BANK_OF_AMERICA;
        descriptor = new EditContactDescriptorBuilder().withPhone(VALID_PHONE_BANK_OF_AMERICA)
                .withEmail(VALID_EMAIL_BANK_OF_AMERICA).build();
        expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
