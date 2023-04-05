package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DIRECTORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILEPATH;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExportProgressCommand;

/**
 * Parser test for export progress command.
 */
class ExportProgressCommandParserTest {
    private ExportProgressCommandParser parser = new ExportProgressCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, "1 " + PREFIX_FILEPATH + " " + System.getProperty("user.home"),
                new ExportProgressCommand(INDEX_FIRST_STUDENT, System.getProperty("user.home")));

        assertParseSuccess(parser, "1 " + PREFIX_FILEPATH + " " + Paths.get("").toAbsolutePath(),
                new ExportProgressCommand(INDEX_FIRST_STUDENT, Paths.get("").toAbsolutePath().toString()));
    }

    @Test
    public void parse_indexMissing_failure() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ExportProgressCommand.MESSAGE_USAGE));

        assertParseFailure(parser, PREFIX_FILEPATH + " " + System.getProperty("user.home"),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ExportProgressCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        assertParseSuccess(parser, "1",
                new ExportProgressCommand(INDEX_FIRST_STUDENT, ""));
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "-", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ExportProgressCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ExportProgressCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ExportProgressCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "??", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ExportProgressCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "1 " + PREFIX_FILEPATH + " " + "1",
               MESSAGE_INVALID_DIRECTORY);

        assertParseFailure(parser, "1 " + PREFIX_FILEPATH + " " + "??", MESSAGE_INVALID_DIRECTORY);

        assertParseFailure(parser, "1 " + PREFIX_FILEPATH + " " + "%", MESSAGE_INVALID_DIRECTORY);
    }
}
