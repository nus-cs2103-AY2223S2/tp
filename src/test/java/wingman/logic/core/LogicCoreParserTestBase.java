package wingman.logic.core;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import wingman.logic.core.exceptions.CommandException;
import wingman.logic.core.exceptions.ParseException;
import wingman.model.OperationMode;

/**
 * Base class for {@link CommandGroup} tests, basically setting up some
 * common mocks to be used by the subclasses.
 */
@ExtendWith(MockitoExtension.class)
public abstract class LogicCoreParserTestBase {

    protected static final String COMMAND_WORD1 = "command1";
    protected static final String COMMAND_WORD2 = "command2";
    @Mock
    protected Command command1;
    @Mock
    protected Command command2;
    @Mock
    protected CommandFactory<Command> commandFactory1;
    @Mock
    protected CommandFactory<Command> commandFactory2;
    protected List<CommandFactory<?>> commandFactories;
    protected CommandGroup commandGroup;

    @BeforeEach
    void setUp() throws ParseException, CommandException {
        Mockito
            .lenient()
            .when(commandFactory1.getCommandWord())
            .thenReturn(COMMAND_WORD1);
        Mockito
            .lenient()
            .when(commandFactory2.getCommandWord())
            .thenReturn(COMMAND_WORD2);
        Mockito
            .lenient()
            .when(commandFactory1.getPrefixes())
            .thenReturn(Optional.empty());
        Mockito
            .lenient()
            .when(commandFactory2.getPrefixes())
            .thenReturn(Optional.empty());
        Mockito
            .lenient()
            .when(commandFactory1.createCommand(Mockito.any()))
            .thenReturn(command1);
        Mockito
            .lenient()
            .when(commandFactory2.createCommand(Mockito.any()))
            .thenReturn(command2);
        commandFactories = List.of(commandFactory1, commandFactory2);
        commandGroup = new CommandGroup(OperationMode.PILOT, commandFactories);
    }

}
