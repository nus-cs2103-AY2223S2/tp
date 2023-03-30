package wingman.logic.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import wingman.logic.core.exceptions.CommandException;
import wingman.logic.core.exceptions.ParseException;
import wingman.model.OperationMode;
import wingman.testutil.Assert;

/**
 * A test class for {@code WingmanParser}.
 */
@ExtendWith(MockitoExtension.class)
public class WingmanParserTest extends LogicCoreParserTestBase {
    static final OperationMode GROUP1_MODE = OperationMode.PILOT;
    static final OperationMode GROUP2_MODE = OperationMode.CREW;
    @Mock
    private CommandGroup commandGroup1;
    @Mock
    private CommandGroup commandGroup2;
    @Mock
    private Command group1Command;
    @Mock
    private Command group2Command;
    private List<CommandGroup> commandGroups;
    private WingmanParser parser;

    @BeforeEach
    void setUpGroups() throws ParseException, CommandException {
        Mockito.lenient()
            .when(commandGroup1.getOperationMode())
            .thenReturn(GROUP1_MODE);
        Mockito.lenient()
            .when(commandGroup2.getOperationMode())
            .thenReturn(GROUP2_MODE);

        Mockito.lenient()
            .when(commandGroup1.parse(Mockito.any()))
            .thenReturn(group1Command);
        Mockito.lenient()
            .when(commandGroup2.parse(Mockito.any()))
            .thenReturn(group2Command);

        commandGroups = List.of(commandGroup1, commandGroup2);

        parser = new WingmanParser(commandGroups, commandFactories);
    }

    @Test
    void parse_unknownOperationMode_throwsParseException() {
        // setup
        parser = new WingmanParser(commandGroups, List.of());
        final String input = "unknown command";

        // verify
        Assert.assertThrows(ParseException.class, () -> parser.parse(OperationMode.PLANE,
            input));
    }

    @Test
    void parse_knownOperationMode_returnsCommand() throws ParseException, CommandException {
        // setup
        final String input = "command";

        // execute
        final Command result1 = parser.parse(GROUP1_MODE, input);
        final Command result2 = parser.parse(GROUP2_MODE, input);
        // verify
        assertEquals(group1Command, result1);
        assertEquals(group2Command, result2);
    }

    @Test
    void parse_topLevelCommand_returnsTopLevelCommandButNotGroupCommand() throws ParseException, CommandException {
        // execute
        final Command result = parser.parse(GROUP1_MODE, COMMAND_WORD1);
        // verify
        assertEquals(command1, result);
        assertNotEquals(group1Command, result);
        assertNotEquals(group2Command, result);
    }
}
