package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.EditIsolatedEventCommand.MESSAGE_NOT_EDITED;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditIsolatedEventCommand;

public class EditIsolatedEventParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditIsolatedEventCommand.MESSAGE_USAGE);
    private EditIsolatedEventCommandParser parser = new EditIsolatedEventCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no field specified
        assertParseFailure(parser, "1 1", MESSAGE_NOT_EDITED);

        // wrong input format
        assertParseFailure(parser, "1 1 2", MESSAGE_INVALID_FORMAT);
    }
}
