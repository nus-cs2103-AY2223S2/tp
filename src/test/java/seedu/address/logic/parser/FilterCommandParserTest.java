package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.FindCommand;





public class FilterCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);

    private FilterCommandParser parser = new FilterCommandParser();


    @Test
    public void parse_missingParts_failure() {
        // No word
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);
    }

}
