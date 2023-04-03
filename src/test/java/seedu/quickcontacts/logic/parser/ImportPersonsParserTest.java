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

import seedu.quickcontacts.logic.commands.ImportPersonsCommand;
import seedu.quickcontacts.testutil.TypicalPersons;

public class ImportPersonsParserTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "ImportExportPersonsTest");
    private static final Path VALID_PERSONS_FILE = TEST_DATA_FOLDER.resolve("validPersons.json");
    private final ImportPersonsParser parser = new ImportPersonsParser();

    @Test
    public void parse_success() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = Files.newBufferedReader(VALID_PERSONS_FILE);
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        assertParseSuccess(parser, stringBuilder.toString(),
                new ImportPersonsCommand(TypicalPersons.getTypicalPersons(), false));
    }

    @Test
    public void parse_invalidJson_failure() {
        assertParseFailure(parser, "abc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportPersonsParser.MALFORMED_JSON));
    }

    @Test
    public void parse_noPersons_failure() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportPersonsCommand.MESSAGE_USAGE));
    }
}
