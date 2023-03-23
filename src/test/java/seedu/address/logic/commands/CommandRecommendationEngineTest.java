package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandRecommendationEngine.isValidArgs;
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
        String userArgs = "duahwdiuahd n/Zong Xun";
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userArgs, CommandRecommendationEngine
                .commandInfoMap.get(AddVolunteerCommand.COMMAND_WORD).getCmdPrompts().keySet()
                .toArray(new Prefix[]{}));
        assertFalse(AddVolunteerCommandParser.validate(argumentMultimap));
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

        assertThrows(CommandException.class, () -> commandRecommendationEngine.recommendCommand(input));
    }

    @Test
    public void parseAddVolunteerArguments_invalidArgs_false() {
        String userArgs = " n/Zon n/Zon";
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userArgs,
                CommandRecommendationEngine.commandInfoMap.get(AddVolunteerCommand.COMMAND_WORD)
                        .getCmdPrompts().keySet()
                        .toArray(new Prefix[]{}));

        boolean isValidArgs = isValidArgs(AddVolunteerCommand.COMMAND_WORD, argumentMultimap);
        assertFalse(isValidArgs);
    }

    @Test
    public void parseAddElderlyArguments_invalidNameArgs_false() {
        String userArgs = " n/Zon n/Zon";
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userArgs,
                CommandRecommendationEngine.commandInfoMap.get(AddElderlyCommand.COMMAND_WORD)
                        .getCmdPrompts().keySet()
                        .toArray(new Prefix[]{}));

        boolean isValidArgs = isValidArgs(AddElderlyCommand.COMMAND_WORD, argumentMultimap);
        assertFalse(isValidArgs);
    }

    @Test
    public void parseAddElderlyArguments_invalidNricArgs_false() {
        String userArgs = " enr/Zon enr/Zon";
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userArgs,
                CommandRecommendationEngine.commandInfoMap.get(AddElderlyCommand.COMMAND_WORD)
                        .getCmdPrompts().keySet()
                        .toArray(new Prefix[]{}));

        boolean isValidArgs = isValidArgs(AddElderlyCommand.COMMAND_WORD, argumentMultimap);
        assertFalse(isValidArgs);
    }

    @Test
    public void parseAddPairArguments_invalidElderlyNricArgs_false() {
        String userArgs = " enr/Zon enr/Zon";
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userArgs,
                CommandRecommendationEngine.commandInfoMap.get(AddPairCommand.COMMAND_WORD)
                        .getCmdPrompts().keySet()
                        .toArray(new Prefix[]{}));

        boolean isValidArgs = isValidArgs(AddPairCommand.COMMAND_WORD, argumentMultimap);
        assertFalse(isValidArgs);
    }

    @Test
    public void parseAddPairArguments_invalidVolunteerNricArgs_false() {
        String userArgs = " vnr/Zon vnr/Zon";
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userArgs,
                CommandRecommendationEngine.commandInfoMap.get(AddPairCommand.COMMAND_WORD)
                        .getCmdPrompts().keySet()
                        .toArray(new Prefix[]{}));

        boolean isValidArgs = isValidArgs(AddPairCommand.COMMAND_WORD, argumentMultimap);
        assertFalse(isValidArgs);
    }

    @Test
    public void parseDeletePairArguments_invalidElderlyNricArgs_false() {
        String userArgs = " enr/Zon enr/Zon";
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userArgs,
                CommandRecommendationEngine.commandInfoMap.get(DeletePairCommand.COMMAND_WORD)
                        .getCmdPrompts().keySet()
                        .toArray(new Prefix[]{}));

        boolean isValidArgs = isValidArgs(DeletePairCommand.COMMAND_WORD, argumentMultimap);
        assertFalse(isValidArgs);
    }

    @Test
    public void parseDeletePairArguments_invalidVolunteerNricArgs_false() {
        String userArgs = " vnr/Zon vnr/Zon";
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userArgs,
                CommandRecommendationEngine.commandInfoMap.get(DeletePairCommand.COMMAND_WORD)
                        .getCmdPrompts().keySet()
                        .toArray(new Prefix[]{}));

        boolean isValidArgs = isValidArgs(DeletePairCommand.COMMAND_WORD, argumentMultimap);
        assertFalse(isValidArgs);
    }

    @Test
    public void parseDeleteElderlyArguments_invalidArgs_false() {
        String userArgs = " diaohdwoi diowhdaid";
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userArgs,
                CommandRecommendationEngine.commandInfoMap.get(DeleteElderlyCommand.COMMAND_WORD)
                        .getCmdPrompts().keySet()
                        .toArray(new Prefix[]{}));

        boolean isValidArgs = isValidArgs(DeleteElderlyCommand.COMMAND_WORD, argumentMultimap);
        assertFalse(isValidArgs);
    }

    @Test
    public void parseDeleteVolunteerArguments_invalidArgs_false() {
        String userArgs = " diaohdwoi diowhdaid";
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userArgs,
                CommandRecommendationEngine.commandInfoMap.get(DeleteVolunteerCommand.COMMAND_WORD)
                        .getCmdPrompts().keySet()
                        .toArray(new Prefix[]{}));

        boolean isValidArgs = isValidArgs(DeleteVolunteerCommand.COMMAND_WORD, argumentMultimap);
        assertFalse(isValidArgs);
    }

    @Test
    public void parseEditArguments_invalidNameArgs_false() {
        String userArgs = " n/Zon p/21323321 e/21323321 a/21323321 ag/30 re/cadd t/dawd nr/dwadad nr/dawdad";
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userArgs,
                CommandRecommendationEngine.commandInfoMap.get(EditCommand.COMMAND_WORD)
                        .getCmdPrompts().keySet()
                        .toArray(new Prefix[]{}));

        boolean isValidArgs = isValidArgs(EditCommand.COMMAND_WORD, argumentMultimap);
        assertFalse(isValidArgs);
    }

    @Test
    public void parseEditElderlyArguments_invalidNameArgs_false() {
        String userArgs = " n/Zon p/21323321 e/21323321 a/21323321 ag/30 re/cadd t/dawd enr/dwadad enr/dawdad";
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userArgs,
                CommandRecommendationEngine.commandInfoMap.get(EditElderlyCommand.COMMAND_WORD)
                        .getCmdPrompts().keySet()
                        .toArray(new Prefix[]{}));

        boolean isValidArgs = isValidArgs(EditElderlyCommand.COMMAND_WORD, argumentMultimap);
        assertFalse(isValidArgs);
    }

    @Test
    public void parseEditVolunteerArguments_invalidNameArgs_false() {
        String userArgs = " hdoiawhdoawida n/Zon p/21323321 e/21323321 a/21323321 ag/30 re/cadd"
                + " t/dawd vnr/dwadad vnr/dawdad";
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userArgs,
                CommandRecommendationEngine.commandInfoMap.get(EditVolunteerCommand.COMMAND_WORD)
                        .getCmdPrompts().keySet()
                        .toArray(new Prefix[]{}));

        boolean isValidArgs = isValidArgs(EditVolunteerCommand.COMMAND_WORD, argumentMultimap);
        assertFalse(isValidArgs);
    }

    @Test
    public void parseFindArguments_invalidNameArgs_false() {
        String userArgs = " n/Zon p/21323321 e/21323321 a/21323321 ag/30 re/cadd t/dawd nr/dwadad nr/dawdad";
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userArgs,
                CommandRecommendationEngine.commandInfoMap.get(FindCommand.COMMAND_WORD)
                        .getCmdPrompts().keySet()
                        .toArray(new Prefix[]{}));

        boolean isValidArgs = isValidArgs(FindCommand.COMMAND_WORD, argumentMultimap);
        assertFalse(isValidArgs);
    }
}
