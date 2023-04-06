package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.RecommendationException;
import seedu.address.logic.parser.AddElderlyCommandParser;
import seedu.address.logic.parser.AddPairCommandParser;
import seedu.address.logic.parser.AddVolunteerCommandParser;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.AutoPairCommandParser;
import seedu.address.logic.parser.DeleteElderlyCommandParser;
import seedu.address.logic.parser.DeletePairCommandParser;
import seedu.address.logic.parser.DeleteVolunteerCommandParser;
import seedu.address.logic.parser.EditElderlyCommandParser;
import seedu.address.logic.parser.EditVolunteerCommandParser;
import seedu.address.logic.parser.ExitCommandParser;
import seedu.address.logic.parser.FindCommandParser;
import seedu.address.logic.parser.HelpCommandParser;
import seedu.address.logic.parser.ListCommandParser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.StatsCommandParser;

public class CommandRecommendationEngineTest {
    private static final CommandRecommendationEngine commandRecommendationEngine =
            CommandRecommendationEngine.getInstance();

    private void testCommandParser(String userArgs, String commandWord, Class<?> commandParserClass) {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userArgs,
                commandRecommendationEngine.getCommandInfoMap()
                        .get(commandWord)
                        .getCmdPrompts()
                        .keySet()
                        .toArray(new Prefix[]{}));
        assertThrows(RecommendationException.class, () -> {
            try {
                Method validateMethod = commandParserClass.getDeclaredMethod("validate", ArgumentMultimap.class);
                validateMethod.setAccessible(true);
                validateMethod.invoke(commandParserClass, argumentMultimap);
            } catch (InvocationTargetException e) {
                throw new RecommendationException();
            }
        });
    }

    @Test
    public void recommendCommand_validCommand_success() {
        try {
            // Complete commands
            String expected = "add_volunteer n/NAME ic/NRIC bd/BIRTH_DATE re/[REGION] a/[ADDRESS] p/[PHONE]"
                    + " e/[EMAIL] mt/[MEDICAL_QUALIFICATION] dr/[AVAILABLE_DATE_START, AVAILABLE_DATE_END] t/[TAG]";
            String actual = commandRecommendationEngine.generateCommandRecommendations("add_volunteer");
            assertEquals(expected, actual);

            expected = "add_elderly n/Zong Xun ic/NRIC bd/BIRTH_DATE re/[REGION] r/[RISK] a/[ADDRESS]"
                    + " p/[PHONE] e/[EMAIL] dr/[AVAILABLE_DATE_START, AVAILABLE_DATE_END] t/[TAG]";
            actual = commandRecommendationEngine.generateCommandRecommendations("add_elderly n/Zong Xun ");
            assertEquals(expected, actual);

            // Incomplete field values
            expected = "edit NRIC n/[NAME]";
            actual = commandRecommendationEngine.generateCommandRecommendations("edit NRIC n");
            assertEquals(expected, actual);

            // Unrecognised field
            expected = "edit NRIC gib";
            actual = commandRecommendationEngine.generateCommandRecommendations("edit NRIC gib");
            assertEquals(expected, actual);

            // Incomplete command
            expected = "exit";
            actual = commandRecommendationEngine.generateCommandRecommendations("ex");
            assertEquals(expected, actual);

            expected = "auto_pair";
            actual = commandRecommendationEngine.generateCommandRecommendations("auto_p");
            assertEquals(expected, actual);

            // With preamble
            expected = "list <[UNPAIRED \\ PAIRED]>";
            actual = commandRecommendationEngine.generateCommandRecommendations("list");
            assertEquals(expected, actual);

            // Without preamble
            expected = "list paired";
            actual = commandRecommendationEngine.generateCommandRecommendations("list paired");
            assertEquals(expected, actual);

            expected = "edit T0046357P n/[NAME] ic/[NRIC] bd/[BIRTH_DATE] re/[REGION] r/[RISK] a/[ADDRESS] p/[PHONE]"
                    + " e/[EMAIL] mt/[MEDICAL_QUALIFICATION] dr/[AVAILABLE_DATE_START, AVAILABLE_DATE_END] t/[TAG]";
            actual = commandRecommendationEngine.generateCommandRecommendations("edit T0046357P ");
            assertEquals(expected, actual);
        } catch (RecommendationException e) {
            fail();
        }
    }

    @Test
    public void recommendCommand_invalidCommand_fail() {
        // Invalid command
        assertThrows(RecommendationException.class, () ->
                commandRecommendationEngine.generateCommandRecommendations("gibbeish"));

        assertThrows(RecommendationException.class, () ->
                commandRecommendationEngine.generateCommandRecommendations("gibbeish n/pico-san"));
    }


    @Test
    public void parseArguments_invalidArgs_fail() {
        // Invalid prefix
        String userArgs = " n/Zong Xun invalid/Hello World"; // require empty space to simulate preamble
        testCommandParser(userArgs,
                AddVolunteerCommand.COMMAND_WORD,
                AddVolunteerCommandParser.class);

        testCommandParser(userArgs,
                AddElderlyCommand.COMMAND_WORD,
                AddElderlyCommandParser.class);

        testCommandParser(userArgs,
                AddPairCommand.COMMAND_WORD,
                AddPairCommandParser.class);

        testCommandParser(userArgs,
                ExitCommand.COMMAND_WORD,
                ExitCommandParser.class);

        testCommandParser(userArgs,
                ListCommand.COMMAND_WORD,
                ListCommandParser.class);

        testCommandParser(userArgs,
                HelpCommand.COMMAND_WORD,
                HelpCommandParser.class);

        testCommandParser(userArgs,
                DeletePairCommand.COMMAND_WORD,
                DeletePairCommandParser.class);

        testCommandParser(userArgs,
                EditElderlyCommand.COMMAND_WORD,
                EditElderlyCommandParser.class);

        testCommandParser(userArgs,
                EditVolunteerCommand.COMMAND_WORD,
                EditVolunteerCommandParser.class);

        testCommandParser(userArgs,
                FindCommand.COMMAND_WORD,
                FindCommandParser.class);

        // Requires non-empty preamble
        userArgs = "";
        testCommandParser(userArgs,
                DeleteElderlyCommand.COMMAND_WORD,
                DeleteElderlyCommandParser.class);

        testCommandParser(userArgs,
                DeleteVolunteerCommand.COMMAND_WORD,
                DeleteVolunteerCommandParser.class);

        // Requires empty preamble
        userArgs = "non-empty";
        testCommandParser(userArgs,
                StatsCommand.COMMAND_WORD,
                StatsCommandParser.class);

        testCommandParser(userArgs,
                AutoPairCommand.COMMAND_WORD,
                AutoPairCommandParser.class);

        testCommandParser(userArgs,
                ExitCommand.COMMAND_WORD,
                ExitCommandParser.class);
    }

    @Test
    public void parsePreamble_nonEmpty_success() {
        try {
            // Non-empty
            String userArgs = "1";
            String command = EditElderlyCommand.COMMAND_WORD;

            ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userArgs, commandRecommendationEngine.getCommandInfoMap()
                    .get(command).getCmdPrompts().keySet()
                    .toArray(new Prefix[]{}));
            assertTrue(EditElderlyCommandParser.validate(argumentMultimap));

            argumentMultimap = ArgumentTokenizer.tokenize(userArgs, commandRecommendationEngine.getCommandInfoMap()
                    .get(EditVolunteerCommand.COMMAND_WORD).getCmdPrompts().keySet()
                    .toArray(new Prefix[]{}));
            assertTrue(EditVolunteerCommandParser.validate(argumentMultimap));

            argumentMultimap = ArgumentTokenizer.tokenize(userArgs, commandRecommendationEngine.getCommandInfoMap()
                    .get(EditVolunteerCommand.COMMAND_WORD).getCmdPrompts().keySet()
                    .toArray(new Prefix[]{}));
            assertTrue(EditVolunteerCommandParser.validate(argumentMultimap));

            // Valid prefixes
            userArgs = " n/Zong Xun";
            argumentMultimap = ArgumentTokenizer.tokenize(userArgs, commandRecommendationEngine.getCommandInfoMap()
                    .get(FindCommand.COMMAND_WORD).getCmdPrompts().keySet()
                    .toArray(new Prefix[]{}));
            assertTrue(FindCommandParser.validate(argumentMultimap));

            argumentMultimap = ArgumentTokenizer.tokenize(userArgs, commandRecommendationEngine.getCommandInfoMap()
                    .get(AddVolunteerCommand.COMMAND_WORD).getCmdPrompts().keySet()
                    .toArray(new Prefix[]{}));
            assertTrue(AddVolunteerCommandParser.validate(argumentMultimap));

            userArgs = " vic/T0046358D";
            argumentMultimap = ArgumentTokenizer.tokenize(userArgs, commandRecommendationEngine.getCommandInfoMap()
                    .get(AddPairCommand.COMMAND_WORD).getCmdPrompts().keySet()
                    .toArray(new Prefix[]{}));
            assertTrue(AddPairCommandParser.validate(argumentMultimap));
        } catch (RecommendationException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parseUserInputForAutocompletion_validInput_success() {
        try {
            // Incomplete command
            String userInput = "add_v";
            String autocomplete = commandRecommendationEngine.autocompleteCommand(userInput);
            assertEquals("add_volunteer", autocomplete);

            // Complete command
            userInput = "add_elderly";
            autocomplete = commandRecommendationEngine.autocompleteCommand(userInput);
            assertEquals("add_elderly n/", autocomplete);

            // No preamble
            userInput = "auto_pair";
            autocomplete = commandRecommendationEngine.autocompleteCommand(userInput);
            assertEquals("auto_pair", autocomplete);

            userInput = "exit     "; // with spaces
            autocomplete = commandRecommendationEngine.autocompleteCommand(userInput);
            assertEquals("exit", autocomplete);

            // With preamble
            userInput = "list";
            autocomplete = commandRecommendationEngine.autocompleteCommand(userInput);
            assertEquals("list", autocomplete);

            userInput = "edit";
            autocomplete = commandRecommendationEngine.autocompleteCommand(userInput);
            assertEquals("edit", autocomplete);

            userInput = "edit_elderly";
            autocomplete = commandRecommendationEngine.autocompleteCommand(userInput);
            assertEquals("edit_elderly", autocomplete);

            userInput = "edit_volunteer";
            autocomplete = commandRecommendationEngine.autocompleteCommand(userInput);
            assertEquals("edit_volunteer", autocomplete);

            // Incomplete field value
            userInput = "add_elderly n/";
            autocomplete = commandRecommendationEngine.autocompleteCommand(userInput);
            assertEquals("add_elderly n/", autocomplete);

            userInput = "add_elderly n/Zong Xun ";
            autocomplete = commandRecommendationEngine.autocompleteCommand(userInput);
            assertEquals("add_elderly n/Zong Xun ic/", autocomplete);
        } catch (RecommendationException e) {
            fail();
        }
    }
}
