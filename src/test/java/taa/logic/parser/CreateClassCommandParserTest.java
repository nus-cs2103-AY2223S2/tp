package taa.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import taa.logic.commands.CreateClassCommand;
import taa.logic.parser.exceptions.ParseException;
import taa.model.ClassList;

class CreateClassCommandParserTest {
    private final CreateClassCommandParser parser = new CreateClassCommandParser();
    @Test
    void parse() throws ParseException {
        String input = "create_class T02";
        CreateClassCommand expectedCommand = new CreateClassCommand(new ClassList("T02"));
        assertEquals(expectedCommand, parser.parse(input));
    }
}
