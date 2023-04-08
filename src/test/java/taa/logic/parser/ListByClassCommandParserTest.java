package taa.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import taa.logic.commands.ListByClassCommand;
import taa.logic.parser.exceptions.ParseException;
import taa.model.ClassIdMatchesPredicate;

class ListByClassCommandParserTest {
    private final ListByClassCommandParser parser = new ListByClassCommandParser();

    @Test
    void parse() throws ParseException {
        String input = "T02";
        ListByClassCommand expectedCommand = new ListByClassCommand(new ClassIdMatchesPredicate("T02"));
        assertEquals(expectedCommand, parser.parse(input));
    }
}
