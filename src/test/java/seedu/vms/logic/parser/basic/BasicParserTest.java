package seedu.vms.logic.parser.basic;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.vms.logic.commands.basic.ExitCommand;
import seedu.vms.logic.commands.basic.HelpCommand;


public class BasicParserTest {
    private final BasicParser parser = new BasicParser();


    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parse(ExitCommand.COMMAND_WORD).getCommand() instanceof ExitCommand);
        assertTrue(parser.parse(ExitCommand.COMMAND_WORD + " 3").getCommand() instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parse(HelpCommand.COMMAND_WORD).getCommand() instanceof HelpCommand);
        assertTrue(parser.parse(HelpCommand.COMMAND_WORD + " 3").getCommand() instanceof HelpCommand);
    }
}
