package seedu.address.logic.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddElderlyCommandParser;
import seedu.address.logic.parser.AddPairCommandParser;
import seedu.address.logic.parser.AddVolunteerCommandParser;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ClearCommandParser;
import seedu.address.logic.parser.DeleteElderlyCommandParser;
import seedu.address.logic.parser.DeletePairCommandParser;
import seedu.address.logic.parser.DeleteVolunteerCommandParser;
import seedu.address.logic.parser.EditCommandParser;
import seedu.address.logic.parser.EditElderlyCommandParser;
import seedu.address.logic.parser.EditVolunteerCommandParser;
import seedu.address.logic.parser.ExitCommandParser;
import seedu.address.logic.parser.HelpCommandParser;
import seedu.address.logic.parser.ListCommandParser;
import seedu.address.logic.parser.Prefix;

/**
 * Suggests a command based on the user input.
 */
public class CommandRecommendationEngine {
    private static final String INVALID_COMMAND_MESSAGE = "No such command exists!"
            + " Please refer to our user guide for the list of valid commands.";
    private static final String INVALID_PREFIX_MESSAGE = "Invalid prefix!"
            + " Please refer to our user guide for the list of valid arguments.";

    public static final Map<String, CommandInfo> commandInfoMap = new LinkedHashMap<>();
    public static final Map<String, Function<ArgumentMultimap, Boolean>> validator = new HashMap<>();

    static {
        addCommandInfo(new CommandInfo(DeleteVolunteerCommand.COMMAND_WORD, DeleteVolunteerCommand.COMMAND_PROMPTS));
        addCommandInfo(new CommandInfo(DeleteElderlyCommand.COMMAND_WORD, DeleteElderlyCommand.COMMAND_PROMPTS));
        addCommandInfo(new CommandInfo(AddVolunteerCommand.COMMAND_WORD, AddVolunteerCommand.COMMAND_PROMPTS));
        addCommandInfo(new CommandInfo(EditElderlyCommand.COMMAND_WORD, EditElderlyCommand.COMMAND_PROMPTS));
        addCommandInfo(new CommandInfo(DeletePairCommand.COMMAND_WORD, DeletePairCommand.COMMAND_PROMPTS));
        addCommandInfo(new CommandInfo(AddElderlyCommand.COMMAND_WORD, AddElderlyCommand.COMMAND_PROMPTS));
        addCommandInfo(new CommandInfo(AddPairCommand.COMMAND_WORD, AddPairCommand.COMMAND_PROMPTS));
        addCommandInfo(new CommandInfo(ClearCommand.COMMAND_WORD, ClearCommand.COMMAND_PROMPTS));
        addCommandInfo(new CommandInfo(FindCommand.COMMAND_WORD, FindCommand.COMMAND_PROMPTS));
        addCommandInfo(new CommandInfo(ExitCommand.COMMAND_WORD, ExitCommand.COMMAND_PROMPTS));
        addCommandInfo(new CommandInfo(ListCommand.COMMAND_WORD, ListCommand.COMMAND_PROMPTS));
        addCommandInfo(new CommandInfo(EditCommand.COMMAND_WORD, EditCommand.COMMAND_PROMPTS));
        addCommandInfo(new CommandInfo(HelpCommand.COMMAND_WORD, HelpCommand.COMMAND_PROMPTS));

        addValidator(DeleteVolunteerCommand.COMMAND_WORD, DeleteVolunteerCommandParser::validate);
        addValidator(DeleteElderlyCommand.COMMAND_WORD, DeleteElderlyCommandParser::validate);
        addValidator(EditVolunteerCommand.COMMAND_WORD, EditVolunteerCommandParser::validate);
        addValidator(AddVolunteerCommand.COMMAND_WORD, AddVolunteerCommandParser::validate);
        addValidator(EditElderlyCommand.COMMAND_WORD, EditElderlyCommandParser::validate);
        addValidator(AddElderlyCommand.COMMAND_WORD, AddElderlyCommandParser::validate);
        addValidator(DeletePairCommand.COMMAND_WORD, DeletePairCommandParser::validate);
        addValidator(AddPairCommand.COMMAND_WORD, AddPairCommandParser::validate);
        addValidator(ClearCommand.COMMAND_WORD, ClearCommandParser::validate);
        addValidator(EditCommand.COMMAND_WORD, EditCommandParser::validate);
        addValidator(ListCommand.COMMAND_WORD, ListCommandParser::validate);
        addValidator(ExitCommand.COMMAND_WORD, ExitCommandParser::validate);
        addValidator(HelpCommand.COMMAND_WORD, HelpCommandParser::validate);
    }

    private static void addValidator(String command, Function<ArgumentMultimap, Boolean> function) {
        validator.put(command, function);
    }

    /**
     * Adds a new command info to the engine.
     *
     * @param commandInfo Command info to add.
     */
    private static void addCommandInfo(CommandInfo commandInfo) {
        commandInfoMap.put(commandInfo.getCmdWord(), commandInfo);
    }

    /**
     * Recommends a command based on the user input.
     *
     * @param userInput User input.
     * @return Suggested command.
     * @throws CommandException If the user input is invalid.
     */
    public String recommendCommand(String userInput) throws CommandException {
        assert userInput != null && !userInput.isEmpty();

        String[] userInputArray = userInput.trim().split(" ");
        String commandWord = userInputArray[0];
        CommandInfo commandInfo = findMatchingCommandInfo(commandWord);

        if (commandInfo == null) {
            throw new CommandException(INVALID_COMMAND_MESSAGE);
        }

        String command = commandInfo.getCmdWord();
        String userArgs = null;
        if (userInputArray.length > 1) {
            String[] userArgsArray = Arrays.copyOfRange(userInputArray, 1, userInputArray.length);
            userArgs = Arrays.stream(userArgsArray).reduce("", ((a, b) -> a + " " + b));
        }
        String recommendedArguments = generateArgumentRecommendation(commandInfo.getCmdPrompts(), userArgs, command);

        if (userInputArray.length > 1) { // command is specified
            return userInput.stripTrailing() + recommendedArguments;
        } else {
            return command + recommendedArguments;
        }
    }

    /**
     * Returns the new user input when user auto-completes the command.
     *
     * @param userInput          Current User Input.
     * @param recommendedCommand Current Command Suggestion
     * @return New User Input.
     */
    public String autocompleteCommand(String userInput, String recommendedCommand) {
        // remaining values
        String suggestedCommand = recommendedCommand.substring(userInput.trim().length());

        // if command is complete, autocomplete the relevant arguments
        int nextIdx = suggestedCommand.indexOf(isCommandPrefixComplete(userInput, " ") ? "/" : " ");
        nextIdx = (nextIdx != -1) ? nextIdx + 1 : suggestedCommand.length();
        userInput = userInput.trim() + suggestedCommand.substring(0, nextIdx);

        return userInput;
    }

    private static boolean isCommandPrefixComplete(String userInput, String delimiter) {
        return userInput.contains(delimiter);
    }

    private CommandInfo findMatchingCommandInfo(String commandWord) {
        return commandInfoMap.keySet()
                .stream()
                .filter(command -> command.startsWith(commandWord))
                .map(commandInfoMap::get)
                .findFirst()
                .orElse(null);
    }

    private Prefix findMatchingPrefix(HashMap<Prefix, String> cmdPrompt, String prefix) {
        return cmdPrompt.keySet()
                .stream()
                .filter(command -> command.getPrefix().startsWith(prefix))
                .findFirst()
                .orElse(null);
    }

    /**
     * Generates recommendations for parsed arguments. This function is invoked on each keystroke.
     *
     * @param cmdPrompt The map of prefix to values for a particular command
     * @param userArgs  The current user input in the command textbox
     * @return The recommended arguments
     * @throws CommandException User typed an invalid prefix.
     */
    private String generateArgumentRecommendation(HashMap<Prefix, String> cmdPrompt, String userArgs, String command)
            throws CommandException {

        StringBuilder argumentRecommendation = new StringBuilder();
        if (userArgs == null) {
            cmdPrompt.keySet()
                    .forEach(key -> argumentRecommendation.append(" ").append(key).append(cmdPrompt.get(key)));
            return argumentRecommendation.toString();
        }

        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userArgs, cmdPrompt.keySet()
                .toArray(new Prefix[]{}));


        Function<ArgumentMultimap, Boolean> argumentValidator = validator.get(command);
        Boolean isValidArgs = argumentValidator.apply(argumentMultimap);
        if (!isValidArgs) {
            throw new CommandException(INVALID_PREFIX_MESSAGE);
        }

        String[] userInputArray = userArgs.split(" ");

        String currPrefixString = userInputArray[userInputArray.length - 1];
        boolean isCompletePrefix = isCommandPrefixComplete(userInputArray[userInputArray.length - 1], "/");
        Prefix matchingPrefix = findMatchingPrefix(cmdPrompt, currPrefixString.split("/")[0]);

        if (isCompletePrefix && matchingPrefix == null) {
            throw new CommandException(INVALID_PREFIX_MESSAGE);
        }

        if (isCompletePrefix) {
            argumentMultimap.put(matchingPrefix, "");
        }

        if (!isCompletePrefix && matchingPrefix != null) {
            argumentRecommendation.append(matchingPrefix.getPrefix().substring(currPrefixString.length()));
            argumentRecommendation.append(cmdPrompt.get(matchingPrefix));
        }

        // current set of arguments is complete -> return remaining recommendation
        cmdPrompt.keySet()
                .stream()
                .filter(key -> argumentMultimap.getValue(key).isEmpty())
                .forEach(key -> argumentRecommendation.append(" ").append(key).append(cmdPrompt.get(key)));

        return argumentRecommendation.toString();
    }
}
