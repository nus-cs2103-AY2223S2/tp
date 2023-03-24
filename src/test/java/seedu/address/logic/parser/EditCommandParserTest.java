package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.model.entity.Name;
import seedu.address.logic.commands.EditCommand;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.ClassificationTerms.CHAR;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // Missing classification
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no classification and no name
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_wrongClassification_failure() {
        // Wrong classification
        assertParseFailure(parser, "ligma" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, CHAR + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
    }


    @Test
    public void parse_validValue_success() {
        EditCommand expectedCommand = new EditCommand(CHAR.label, VALID_NAME_AMY);
        assertParseSuccess(parser, CHAR + NAME_DESC_AMY, expectedCommand);
    }
}
