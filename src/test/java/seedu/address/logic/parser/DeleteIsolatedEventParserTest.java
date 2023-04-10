package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.testutil.SampleDeleteEventInput.INVALID_INPUT_CONTAIN_ALPHABET;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteIsolatedEventCommand;

public class DeleteIsolatedEventParserTest {
    private DeleteIsolatedEventParser parser = new DeleteIsolatedEventParser();

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteIsolatedEventCommand.MESSAGE_USAGE);

        assertParseFailure(parser, INVALID_INPUT_CONTAIN_ALPHABET, expectedMessage);

    }
}
