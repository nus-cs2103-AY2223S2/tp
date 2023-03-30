package wingman.logic.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import wingman.logic.core.exceptions.CommandException;
import wingman.logic.core.exceptions.ParseException;
import wingman.model.OperationMode;

/**
 * Tests for {@link CommandGroup}.
 */
@ExtendWith(MockitoExtension.class)
public class CommandGroupTest extends LogicCoreParserTestBase {

    @Test
    void getOperationMode_void_returnsOperationMode() {
        CommandGroup commandGroup1 = new CommandGroup(OperationMode.PILOT,
            commandFactories);
        CommandGroup commandGroup2 = new CommandGroup(OperationMode.CREW,
            commandFactories);
        CommandGroup commandGroup3 = new CommandGroup(OperationMode.FLIGHT,
            commandFactories);
        CommandGroup commandGroup4 = new CommandGroup(OperationMode.LOCATION,
            commandFactories);
        CommandGroup commandGroup5 = new CommandGroup(OperationMode.PLANE,
            commandFactories);

        assertEquals(OperationMode.PILOT, commandGroup1.getOperationMode());
        assertEquals(OperationMode.CREW, commandGroup2.getOperationMode());
        assertEquals(OperationMode.FLIGHT, commandGroup3.getOperationMode());
        assertEquals(OperationMode.LOCATION, commandGroup4.getOperationMode());
        assertEquals(OperationMode.PLANE, commandGroup5.getOperationMode());
    }

    @Test
    void getCommandFactory_void_returnsCommandFactory() {
        assertEquals(commandFactories, commandGroup.getFactories());
    }

    @Test
    void parse_nullTokens_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> commandGroup.parse(null));
    }

    @Test
    void parse_emptyTokens_throwsParseException() {
        Deque<String> tokens = new ArrayDeque<>();
        assertThrows(ParseException.class, () -> commandGroup.parse(tokens));
    }

    @Test
    void parse_invalidTokens_throwsParseException() {
        Deque<String> tokens = new ArrayDeque<>(List.of("invalid"));
        assertThrows(ParseException.class, () -> commandGroup.parse(tokens));
    }

    @Test
    void parse_validTokens1_returnsCommand() throws ParseException, CommandException {
        Deque<String> tokens = new ArrayDeque<>(List.of(COMMAND_WORD1, "arg1", "arg2"));
        CommandParam commandParam = new CommandParam(Optional.of("arg1 arg2"),
            Optional.empty());
        assertEquals(command1, commandGroup.parse(tokens));
        Mockito.verify(commandFactory1).createCommand(commandParam);
    }

    @Test
    void parse_validTokens2_returnsCommand() throws ParseException, CommandException {
        Deque<String> tokens = new ArrayDeque<>(List.of(COMMAND_WORD2, "arg1",
            "arg2", "arg3"));
        CommandParam commandParam = new CommandParam(Optional.of("arg1 arg2 arg3"),
            Optional.empty());
        assertEquals(command2, commandGroup.parse(tokens));
        Mockito.verify(commandFactory2).createCommand(commandParam);
    }
}
