package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.ClassificationTerms.CHAR;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();


    @Test
    public void parse_missingParts_failure() {
        // No word
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // One word
        assertParseFailure(parser, "Awooga", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validValue_success() {
        EditCommand expectedCommand = new EditCommand(CHAR.label, VALID_NAME_AMY);
        assertParseSuccess(parser, CHAR.label + NAME_DESC_AMY, expectedCommand);
    }
}
