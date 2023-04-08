package taa.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import taa.logic.commands.AddAlarmCommand;
import taa.logic.parser.exceptions.ParseException;
import taa.model.alarm.Alarm;

class AddAlarmCommandParserTest {
    private final AddAlarmCommandParser parser = new AddAlarmCommandParser();
    @Test
    void parse() throws ParseException {
        String input = " t/12 c/finish";
        AddAlarmCommand expectedCommand = new AddAlarmCommand(new Alarm(12, "finish"));
        assertEquals(expectedCommand, parser.parse(input));
    }
}
