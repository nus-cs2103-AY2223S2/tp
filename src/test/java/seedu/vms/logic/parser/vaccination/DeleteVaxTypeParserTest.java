package seedu.vms.logic.parser.vaccination;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.vms.logic.parser.exceptions.ParseException;

public class DeleteVaxTypeParserTest {
    private static final String INVALID_NAME = "UN?CHI";
    private static final String EMPTY_NAME = "";


    @Test
    public void parse_invalidName_exceptionThrown() {
        assertThrows(ParseException.class, () -> attemptParse(INVALID_NAME));
        assertThrows(ParseException.class, () -> attemptParse(EMPTY_NAME));
    }


    private void attemptParse(String command) throws Exception {
        new DeleteVaxTypeParser().parse(command);
    }
}
