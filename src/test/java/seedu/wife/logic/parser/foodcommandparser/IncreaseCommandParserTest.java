package seedu.wife.logic.parser.foodcommandparser;

import static seedu.wife.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_NAME_MEIJI;
import static seedu.wife.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.wife.logic.commands.foodcommands.IncreaseCommand;

public class IncreaseCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, IncreaseCommand.MESSAGE_USAGE);

    private IncreaseCommandParser parser = new IncreaseCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_MEIJI, MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

}
