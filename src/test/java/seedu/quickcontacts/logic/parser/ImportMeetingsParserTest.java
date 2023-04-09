package seedu.quickcontacts.logic.parser;

import static seedu.quickcontacts.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.quickcontacts.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.quickcontacts.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.quickcontacts.logic.commands.ImportMeetingsCommand;
import seedu.quickcontacts.testutil.TypicalMeetings;

public class ImportMeetingsParserTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "ImportExportMeetingsTest");
    private static final Path VALID_MEETINGS_FILE = TEST_DATA_FOLDER.resolve("validMeetings.json");
    private final ImportMeetingsParser parser = new ImportMeetingsParser();

    @Test
    public void parse_success() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = Files.newBufferedReader(VALID_MEETINGS_FILE);
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        assertParseSuccess(parser, stringBuilder.toString(),
                new ImportMeetingsCommand(TypicalMeetings.getTypicalMeetings(), false));
    }

    @Test
    public void parse_invalidJson_failure() {
        assertParseFailure(parser, "abc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportMeetingsParser.MALFORMED_JSON));
    }

    @Test
    public void parse_noMeetings_failure() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportMeetingsCommand.MESSAGE_USAGE));
    }
}
