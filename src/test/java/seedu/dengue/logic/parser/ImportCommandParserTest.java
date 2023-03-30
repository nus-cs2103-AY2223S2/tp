package seedu.dengue.logic.parser;

import static seedu.dengue.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dengue.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.dengue.logic.commands.ImportCommand;


public class ImportCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE);

    private ImportCommandParser parser = new ImportCommandParser();

    @Test
    public void parse_missingValue_failure() {
        // no filename specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "sampledata", MESSAGE_INVALID_FORMAT); // missing .csv
        assertParseFailure(parser, "sample/data", MESSAGE_INVALID_FORMAT); // invalid postal

    }

//    @Test
//    public void parse_validFilename_success() {
//        String expectedCommand = String.format(ImportCommand.MESSAGE_SUCCESS, "sampledata.csv")
//        assertParseSuccess(parser, "sampledata.csv", expectedCommand);
//    }
}
