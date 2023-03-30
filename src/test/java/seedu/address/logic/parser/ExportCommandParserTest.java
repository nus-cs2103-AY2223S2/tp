package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditPersonDescriptorBuilder;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.*;

public class ExportCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE);

    private static final String COMMAND = "export";

    private static final String COMMAND_KEYWORD_ALL = "all";

    private static final String COMMAND_KEYWORD_SHOWN = "shown";

    private ExportCommandParser parser = new ExportCommandParser();

    @Test
    public void parse_invalidKeyword_failure() {
        // wrong keyword
        assertParseFailure(parser, "random", MESSAGE_INVALID_FORMAT);

        // wrong keyword plus valid keyword
        assertParseFailure(parser, String.format("%s random", COMMAND_KEYWORD_SHOWN), MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, String.format("hi %s", COMMAND_KEYWORD_ALL), MESSAGE_INVALID_FORMAT);

        // multiple keywords
        assertParseFailure(parser,
                String.format("%s %s", COMMAND_KEYWORD_ALL, COMMAND_KEYWORD_SHOWN), MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser,
                String.format("%s %s", COMMAND_KEYWORD_SHOWN, COMMAND_KEYWORD_ALL), MESSAGE_INVALID_FORMAT);

        // repeated same keyword
        assertParseFailure(parser,
                String.format("%s %s %s", COMMAND_KEYWORD_ALL, COMMAND_KEYWORD_ALL, COMMAND_KEYWORD_ALL),
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser,
                String.format("%s %s %s %s",
                        COMMAND_KEYWORD_SHOWN, COMMAND_KEYWORD_SHOWN, COMMAND_KEYWORD_SHOWN, COMMAND_KEYWORD_SHOWN),
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_noKeyword_success() {
        String userInput = "";

        ExportCommand expectedCommand = new ExportCommand(false);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_shownKeyword_success() {
        String userInput = COMMAND_KEYWORD_SHOWN;

        ExportCommand expectedCommand = new ExportCommand(false);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_allKeyword_success() {
        String userInput = COMMAND_KEYWORD_ALL;

        ExportCommand expectedCommand = new ExportCommand(true);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_addedWhitespace_success() {
        String userInput = String.format("    %s   ", COMMAND_KEYWORD_ALL);

        ExportCommand expectedCommand = new ExportCommand(true);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
