package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddVolunteerCommandParser;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;

public class CommandRecommendationEngineTest {
    private final CommandRecommendationEngine commandRecommendationEngine = new CommandRecommendationEngine();

    @Test
    public void recommendCommand_null_failure() {
        assertThrows(AssertionError.class, () -> commandRecommendationEngine.recommendCommand(null));
    }

    @Test
    public void recommendVolunteerCommand_validCommand_success() {
        String expected = "add_volunteer n/<name> vnr/<nric> a/<address> p/<phone> "
                + "e/<email> t/<tag> re/<region> ag/<age> dr/<start_date,end_date>";
        try {
            String actual = commandRecommendationEngine.recommendCommand("add_volunteer");
            assertEquals(expected, actual);
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void recommendVolunteerCommandArgument_validCommand_success() {
        String expected = "add_volunteer n/Zon vnr/<nric> a/<address> p/<phone> "
                + "e/<email> t/<tag> re/<region> ag/<age> dr/<start_date,end_date>";
        try {
            String actual = commandRecommendationEngine.recommendCommand("add_volunteer n/Zon");
            assertEquals(expected, actual);
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void parseVolunteerArguments_validArgs_success() {
        String userArgs = "n/Zong Xun";
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userArgs, CommandRecommendationEngine
                .commandInfoMap.get(AddVolunteerCommand.COMMAND_WORD).getCmdPrompts().keySet()
                .toArray(new Prefix[]{}));

        assertTrue(AddVolunteerCommandParser.validate(argumentMultimap));
    }

    @Test
    public void parseVolunteerCommand_incompleteArgs_success() throws CommandException {
        String input = "add_volunteer n/Zong Xun vn";
        String expected = "add_volunteer n/Zong Xun vnr/<nric> vnr/<nric> a/<address> p/<phone> e/<email> t/<tag> "
                + "re/<region> ag/<age> dr/<start_date,end_date>";
        assertEquals(expected, commandRecommendationEngine.recommendCommand(input));
    }

    @Test
    public void parseVolunteerCommand_invalidCommand_failure() {
        String input = "hello";
        assertThrows(CommandException.class, () -> commandRecommendationEngine.recommendCommand(input));
    }

    @Test
    public void parseVolunteerArguments_invalidArgs_failure() {
        String userArgs = "add_volunteer n/Zong Xun n/Another name";
        assertThrows(CommandException.class, () -> commandRecommendationEngine.recommendCommand(userArgs));
    }

    @Test
    public void autocompleteAddVolunteerCommands_validCommand_success() {
        String suggested = "add_volunteer n/Zon vnr/<nric> a/<address> p/<phone> "
                + "e/<email> t/<tag> re/<region> ag/<age> dr/<start_date,end_date>";

        assertEquals("add_volunteer n/",
                commandRecommendationEngine.autocompleteCommand("add_volunteer ", suggested));
    }

    @Test
    public void parseVolunteerArguments_invalidArgsPrefix_failure() {
        String input = "add_volunteer n/Zon hello/<nric>";

        assertThrows(CommandException.class,
                () -> commandRecommendationEngine.recommendCommand(input));
    }
}
