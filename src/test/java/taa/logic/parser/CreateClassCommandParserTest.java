package taa.logic.parser;

import taa.logic.commands.CreateClassCommand;
import org.junit.jupiter.api.Test;
import taa.logic.parser.exceptions.ParseException;
import taa.model.ClassList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateClassCommandParserTest {
    private final CreateClassCommandParser parser = new CreateClassCommandParser();
    @Test
    void parse() throws ParseException {
        String input = "create_class T02";
        CreateClassCommand expectedCommand = new CreateClassCommand(new ClassList("T02"));
        assertEquals(expectedCommand, parser.parse(input));
    }
}
