package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_PREAMBLE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RejectCommand;
import seedu.address.model.person.Name;
import seedu.address.model.person.NamePhoneNumberPredicate;
import seedu.address.model.person.Phone;

public class RejectCommandParserTest {
    private final RejectCommandParser parser = new RejectCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Name validName = new Name(VALID_NAME_BOB);
        Phone validPhone = new Phone(VALID_PHONE_BOB);
        NamePhoneNumberPredicate validNamePhonePredicate = new NamePhoneNumberPredicate(validName, validPhone);

        // standard preamble
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB,
                new RejectCommand(validNamePhonePredicate));

        // multiple persons - last person accepted (Bob)
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB,
                new RejectCommand(validNamePhonePredicate));

        // one person, multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB,
                new RejectCommand(validNamePhonePredicate));

        // one person, multiple names - last names accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB,
                new RejectCommand(validNamePhonePredicate));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RejectCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, PHONE_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, EMPTY_PREAMBLE, expectedMessage);
    }
}
