package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_ELDERLY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_VOLUNTEER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPairCommand;

public class AddPairCommandParserTest {
    private final AddPairCommandParser parser = new AddPairCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // TODO: test with the same format as AddCommandParserTest::parse_allFieldsPresent_success()
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPairCommand.MESSAGE_USAGE);

        // missing elderly prefix
        assertParseFailure(parser, NRIC_ELDERLY_DESC_AMY,
                expectedMessage);

        // missing volunteer prefix
        assertParseFailure(parser, NRIC_VOLUNTEER_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_NAME_AMY,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // TODO: Check for invalid elderly or volunteer
    }
}
