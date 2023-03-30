package wingman.logic.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import wingman.logic.core.exceptions.CommandException;
import wingman.logic.core.exceptions.ParseException;
import wingman.testutil.Assert;

@ExtendWith(MockitoExtension.class)
public class FactoryParserTest extends LogicCoreParserTestBase {

    private FactoryParserStub factoryParser;

    @BeforeEach
    void setUpChild() {
        factoryParser = new FactoryParserStub();
    }

    @Test
    void parseFactory_nullTokens_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> factoryParser.parseFactory(null));
    }

    @Test
    void parseFactory_emptyTokens_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> factoryParser.parseFactory(new ArrayDeque<>()));
    }

    @Test
    void parseFactory_invalidTokens_returnsEmpty() throws ParseException, CommandException {
        // set up
        ArrayDeque<String> tokens = new ArrayDeque<>(List.of("invalid"));
        CommandParam param = new CommandParam(Optional.empty(), Optional.empty());
        // execute
        Optional<Command> command = factoryParser.parseFactory(tokens);
        // verify
        assertFalse(command.isPresent());
        Mockito.verify(commandFactory1, Mockito.never()).createCommand(param);
        Mockito.verify(commandFactory2, Mockito.never()).createCommand(param);
    }

    @Test
    void parseFactory_validTokens1_returnValidCommand() throws ParseException, CommandException {
        // set up
        ArrayDeque<String> tokens = new ArrayDeque<>(List.of(COMMAND_WORD1, "arg1", "arg2"));
        CommandParam param = new CommandParam(Optional.of("arg1 arg2"),
            Optional.empty());
        // execute
        Optional<Command> command = factoryParser.parseFactory(tokens);
        // verify
        assertTrue(command.isPresent());
        assertEquals(command1, command.get());
        Mockito.verify(commandFactory1, Mockito.times(1)).createCommand(param);
        Mockito.verify(commandFactory2, Mockito.never()).createCommand(param);
    }

    @Test
    void parseFactory_validTokens2_returnValidCommand() throws ParseException, CommandException {
        // set up
        ArrayDeque<String> tokens = new ArrayDeque<>(List.of(COMMAND_WORD2, "arg1", "arg2"));
        CommandParam param = new CommandParam(Optional.of("arg1 arg2"),
            Optional.empty());
        // execute
        Optional<Command> command = factoryParser.parseFactory(tokens);
        // verify
        assertTrue(command.isPresent());
        assertEquals(command2, command.get());
        Mockito.verify(commandFactory1, Mockito.never()).createCommand(param);
        Mockito.verify(commandFactory2, Mockito.times(1)).createCommand(param);
    }


    private class FactoryParserStub extends FactoryParser {
        @Override
        protected List<CommandFactory<?>> getFactories() {
            return commandFactories;
        }
    }
}
