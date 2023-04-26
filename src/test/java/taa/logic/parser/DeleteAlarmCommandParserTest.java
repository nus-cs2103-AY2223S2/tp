package taa.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import taa.logic.commands.DeleteAlarmCommand;
import taa.logic.parser.exceptions.ParseException;

class DeleteAlarmCommandParserTest {

    private final DeleteAlarmCommandParser parser = new DeleteAlarmCommandParser();
    @Test
    void parse() throws ParseException {
        String input = " 1";
        DeleteAlarmCommand expectedCommand = new DeleteAlarmCommand(1);
        assertEquals(expectedCommand, parser.parse(input));
    }
}
