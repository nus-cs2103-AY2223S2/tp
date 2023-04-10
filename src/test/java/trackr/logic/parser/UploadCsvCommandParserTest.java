package trackr.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

//@@author chongweiguan-reused
public class UploadCsvCommandParserTest {
    private UploadCsvCommandParser parser = new UploadCsvCommandParser();

    @Test
    public void equal_parser() {
        assertTrue(parser.equals(parser));
    }
    //@@author
}
