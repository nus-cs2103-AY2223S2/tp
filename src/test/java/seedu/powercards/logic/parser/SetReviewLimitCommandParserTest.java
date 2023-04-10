package seedu.powercards.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.powercards.logic.commands.reviewcommands.SetReviewLimitCommand;
import seedu.powercards.logic.parser.exceptions.ParseException;

public class SetReviewLimitCommandParserTest {

    private SetReviewLimitCommandParser parser = new SetReviewLimitCommandParser();

    @Test
    public void parse_validArgs_returnsSetReviewLimitCommand() throws ParseException {
        String args = "10";
        SetReviewLimitCommand expectedCommand = new SetReviewLimitCommand(10);
        assertEquals(expectedCommand, parser.parse(args));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String args = "abc";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_missingArgs_throwsParseException() {
        String args = "";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }
}
