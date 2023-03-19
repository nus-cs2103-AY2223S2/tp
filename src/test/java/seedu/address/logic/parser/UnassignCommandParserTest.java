package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnassignCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UnassignCommandParserTest {

    private final UnassignCommandParser parser = new UnassignCommandParser();

    @Test
    public void parse_validArgs_returnsUnassignCommand() throws ParseException {
        ArgumentTokenizer.tokenize(" pi/1 ti/2", PREFIX_PERSON_INDEX, PREFIX_TASK_INDEX);
        UnassignCommand expectedCommand = new UnassignCommand(Index.fromOneBased(1),
            Index.fromOneBased(2));
        assertEquals(expectedCommand, parser.parse(" pi/1 ti/2"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Missing person index
        assertThrows(ParseException.class, () -> parser.parse(" ti/1"));
        // Missing task index
        assertThrows(ParseException.class, () -> parser.parse(" pi/1"));
        // Invalid prefix
        assertThrows(ParseException.class, () -> parser.parse(" pi/1 ti/1 i/1"));
        // Extra arguments after prefix
        assertThrows(ParseException.class, () -> parser.parse(" pi/1 extra ti/1"));
    }
}
