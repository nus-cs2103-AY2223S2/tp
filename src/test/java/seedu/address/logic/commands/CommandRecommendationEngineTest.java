package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.RecommendationException;
import seedu.address.logic.parser.AddElderlyCommandParser;
import seedu.address.logic.parser.AddPairCommandParser;
import seedu.address.logic.parser.AddVolunteerCommandParser;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.DeleteElderlyCommandParser;
import seedu.address.logic.parser.DeletePairCommandParser;
import seedu.address.logic.parser.DeleteVolunteerCommandParser;
import seedu.address.logic.parser.EditCommandParser;
import seedu.address.logic.parser.EditElderlyCommandParser;
import seedu.address.logic.parser.EditVolunteerCommandParser;
import seedu.address.logic.parser.ExitCommandParser;
import seedu.address.logic.parser.FindCommandParser;
import seedu.address.logic.parser.HelpCommandParser;
import seedu.address.logic.parser.ListCommandParser;
import seedu.address.logic.parser.Prefix;

public class CommandRecommendationEngineTest {
    private static final CommandRecommendationEngine commandRecommendationEngine =
            CommandRecommendationEngine.getInstance();

    @Test
    public void recommendCommand_validCommand_success() {
        try {
            String expected = "add_volunteer n/NAME ic/NRIC bd/BIRTH_DATE re/[REGION] a/[ADDRESS] p/[PHONE]"
                    + " e/[EMAIL] mt/[MEDICAL_QUALIFICATION] dr/[AVAILABLE_DATE_START, AVAILABLE_DATE_END] t/[TAG]";
            String actual = commandRecommendationEngine.generateCommandRecommendations("add_volunteer");
            assertEquals(expected, actual);

            expected = "add_elderly n/Zong Xun ic/NRIC bd/BIRTH_DATE re/[REGION] r/[RISK] a/[ADDRESS]"
                    + " p/[PHONE] e/[EMAIL] dr/[AVAILABLE_DATE_START, AVAILABLE_DATE_END] t/[TAG]";
            actual = commandRecommendationEngine.generateCommandRecommendations("add_elderly n/Zong Xun ");
            assertEquals(expected, actual);

            expected = "edit <NRIC> n/[NAME] ic/[NRIC] bd/[BIRTH_DATE] re/[REGION] r/[RISK] a/[ADDRESS] p/[PHONE]"
                    + " e/[EMAIL] mt/[MEDICAL_QUALIFICATION] dr/[AVAILABLE_DATE_START, AVAILABLE_DATE_END] t/[TAG]";
            actual = commandRecommendationEngine.generateCommandRecommendations("edit");
            assertEquals(expected, actual);

            expected = "edit NRIC n/[NAME]";
            actual = commandRecommendationEngine.generateCommandRecommendations("edit NRIC n");
            assertEquals(expected, actual);

            expected = "edit NRIC gib";
            actual = commandRecommendationEngine.generateCommandRecommendations("edit NRIC gib");
            assertEquals(expected, actual);

            expected = "exit";
            actual = commandRecommendationEngine.generateCommandRecommendations("ex");
            assertEquals(expected, actual);

            expected = "auto_pair";
            actual = commandRecommendationEngine.generateCommandRecommendations("auto_p");
            assertEquals(expected, actual);
        } catch (RecommendationException e) {
            fail();
        }
    }

    @Test
    public void recommendCommand_invalidCommand_fail() {
        assertThrows(RecommendationException.class, () ->
                commandRecommendationEngine.generateCommandRecommendations("gibbeish"));

        assertThrows(RecommendationException.class, () ->
                commandRecommendationEngine.generateCommandRecommendations("gibbeish n/pico-san"));
    }


    @Test
    public void parseArguments_invalidArgs_fail() throws RecommendationException {
        String userArgs = "notValidInput n/Zong Xun dil/";
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userArgs,
                commandRecommendationEngine.getCommandInfoMap()
                .get(AddVolunteerCommand.COMMAND_WORD).getCmdPrompts().keySet()
                .toArray(new Prefix[]{}));
        ArgumentMultimap finalArgumentMultimap = argumentMultimap;
        assertThrows(RecommendationException.class, () -> AddVolunteerCommandParser.validate(finalArgumentMultimap));

        argumentMultimap = ArgumentTokenizer.tokenize(userArgs, commandRecommendationEngine.getCommandInfoMap()
                .get(AddElderlyCommand.COMMAND_WORD).getCmdPrompts().keySet()
                .toArray(new Prefix[]{}));
        ArgumentMultimap finalArgumentMultimap1 = argumentMultimap;
        assertThrows(RecommendationException.class, () -> AddElderlyCommandParser.validate(finalArgumentMultimap1));

        argumentMultimap = ArgumentTokenizer.tokenize(userArgs, commandRecommendationEngine.getCommandInfoMap()
                .get(AddPairCommand.COMMAND_WORD).getCmdPrompts().keySet()
                .toArray(new Prefix[]{}));
        ArgumentMultimap finalArgumentMultimap2 = argumentMultimap;
        assertThrows(RecommendationException.class, () -> AddPairCommandParser.validate(finalArgumentMultimap2));

        argumentMultimap = ArgumentTokenizer.tokenize(userArgs, commandRecommendationEngine.getCommandInfoMap()
                .get(ExitCommand.COMMAND_WORD).getCmdPrompts().keySet()
                .toArray(new Prefix[]{}));
        ArgumentMultimap finalArgumentMultimap3 = argumentMultimap;
        assertThrows(RecommendationException.class, () -> ExitCommandParser.validate(finalArgumentMultimap3));

        argumentMultimap = ArgumentTokenizer.tokenize(userArgs, commandRecommendationEngine.getCommandInfoMap()
                .get(ListCommand.COMMAND_WORD).getCmdPrompts().keySet()
                .toArray(new Prefix[]{}));
        ArgumentMultimap finalArgumentMultimap4 = argumentMultimap;
        assertThrows(RecommendationException.class, () -> ListCommandParser.validate(finalArgumentMultimap4));

        argumentMultimap = ArgumentTokenizer.tokenize(userArgs, commandRecommendationEngine.getCommandInfoMap()
                .get(HelpCommand.COMMAND_WORD).getCmdPrompts().keySet()
                .toArray(new Prefix[]{}));
        ArgumentMultimap finalArgumentMultimap5 = argumentMultimap;
        assertThrows(RecommendationException.class, () -> HelpCommandParser.validate(finalArgumentMultimap5));

        argumentMultimap = ArgumentTokenizer.tokenize(userArgs, commandRecommendationEngine.getCommandInfoMap()
                .get(DeletePairCommand.COMMAND_WORD).getCmdPrompts().keySet()
                .toArray(new Prefix[]{}));
        ArgumentMultimap finalArgumentMultimap6 = argumentMultimap;
        assertThrows(RecommendationException.class, () -> DeletePairCommandParser.validate(finalArgumentMultimap6));

        argumentMultimap = ArgumentTokenizer.tokenize(userArgs, commandRecommendationEngine.getCommandInfoMap()
                .get(FindCommand.COMMAND_WORD).getCmdPrompts().keySet()
                .toArray(new Prefix[]{}));
        ArgumentMultimap finalArgumentMultimap10 = argumentMultimap;
        assertThrows(RecommendationException.class, () -> FindCommandParser.validate(finalArgumentMultimap10));

        argumentMultimap = ArgumentTokenizer.tokenize(userArgs, commandRecommendationEngine.getCommandInfoMap()
                .get(EditCommand.COMMAND_WORD).getCmdPrompts().keySet()
                .toArray(new Prefix[]{}));
        ArgumentMultimap finalArgumentMultimap11 = argumentMultimap;
        assertThrows(RecommendationException.class, () -> EditCommandParser.validate(finalArgumentMultimap11));

        userArgs = "";
        argumentMultimap = ArgumentTokenizer.tokenize(userArgs, commandRecommendationEngine.getCommandInfoMap()
                .get(DeleteElderlyCommand.COMMAND_WORD).getCmdPrompts().keySet()
                .toArray(new Prefix[]{}));
        ArgumentMultimap finalArgumentMultimap7 = argumentMultimap;
        assertThrows(RecommendationException.class, () -> DeleteElderlyCommandParser.validate(finalArgumentMultimap7));

        argumentMultimap = ArgumentTokenizer.tokenize(userArgs, commandRecommendationEngine.getCommandInfoMap()
                .get(DeleteVolunteerCommand.COMMAND_WORD).getCmdPrompts().keySet()
                .toArray(new Prefix[]{}));
        ArgumentMultimap finalArgumentMultimap8 = argumentMultimap;
        assertThrows(RecommendationException.class, () ->
                DeleteVolunteerCommandParser.validate(finalArgumentMultimap8));

        userArgs = "n/Zon hello/<nric>";
        argumentMultimap = ArgumentTokenizer.tokenize(userArgs, commandRecommendationEngine.getCommandInfoMap()
                .get(AddVolunteerCommand.COMMAND_WORD).getCmdPrompts().keySet()
                .toArray(new Prefix[]{}));
        ArgumentMultimap finalArgumentMultimap9 = argumentMultimap;
        assertThrows(RecommendationException.class, () -> AddVolunteerCommandParser.validate(finalArgumentMultimap9));

        userArgs = "1";
        argumentMultimap = ArgumentTokenizer.tokenize(userArgs, commandRecommendationEngine.getCommandInfoMap()
                .get(EditElderlyCommand.COMMAND_WORD).getCmdPrompts().keySet()
                .toArray(new Prefix[]{}));
        assertTrue(EditElderlyCommandParser.validate(argumentMultimap));

        argumentMultimap = ArgumentTokenizer.tokenize(userArgs, commandRecommendationEngine.getCommandInfoMap()

                .get(EditVolunteerCommand.COMMAND_WORD).getCmdPrompts().keySet()
                .toArray(new Prefix[]{}));
        assertTrue(EditVolunteerCommandParser.validate(argumentMultimap));
    }

    @Test
    public void parseUserInputForAutocompletion_validInput_success() {
        try {
            String userInput = "add_v";
            String autocomplete = commandRecommendationEngine.autocompleteCommand(userInput);
            assertEquals("add_volunteer", autocomplete);

            userInput = "auto_p";
            autocomplete = commandRecommendationEngine.autocompleteCommand(userInput);
            assertEquals("auto_pair", autocomplete);

            userInput = "add_elderly";
            autocomplete = commandRecommendationEngine.autocompleteCommand(userInput);
            assertEquals("add_elderly n/", autocomplete);
        } catch (RecommendationException e) {
            fail();
        }
    }
}
