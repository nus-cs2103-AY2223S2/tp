package seedu.address.logic.commands;

import java.util.HashMap;
import java.util.Map;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;

/**
 * Suggests a command based on the user input.
 */
public class CommandRecommendationEngine {
    private static final Map<String, CommandInfo> commandInfoMap = new HashMap<>();

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

        String[] userInputArray = userInput.split(" ");
        String commandWord = userInputArray[0];
        CommandInfo commandInfo = findMatchingCommandInfo(commandWord);

        if (commandInfo == null) {
            throw new CommandException("Invalid command");
        }

        String recommendedCommand = commandInfo.getCmdWord();
        HashMap<Prefix, String> cmdPrompt = commandInfo.getCmdPrompts();

        if (userInputArray.length > 1) {
            return userInput.stripTrailing() + generateArgumentSuggestions(cmdPrompt, userInput);
        } else {
            return recommendedCommand + generateArgumentSuggestions(cmdPrompt, userInput);
        }
    }

    private CommandInfo findMatchingCommandInfo(String commandWord) {
        return commandInfoMap.keySet()
                .stream()
                .filter(command -> command.startsWith(commandWord))
                .map(commandInfoMap::get)
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the new user input when user auto-completes the command.
     *
     * @param userInput         Current User Input.
     * @param commandSuggestion Current Command Suggestion
     * @return New User Input.
     */
    public String autocompleteCommand(String userInput, String commandSuggestion) {
        String suggestedCommand = commandSuggestion.substring(userInput.length());
        boolean isCommandComplete = userInput.contains(" ");
        int nextIdx = suggestedCommand.indexOf(isCommandComplete ? "/" : " ");
        nextIdx = (nextIdx != -1) ? nextIdx + 1 : suggestedCommand.length();

        userInput += suggestedCommand.substring(0, nextIdx);
        return userInput;
    }


    private String generateArgumentSuggestions(HashMap<Prefix, String> cmdPrompt, String userInput) throws CommandException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userInput, cmdPrompt.keySet()
                .toArray(new Prefix[]{}));
        StringBuilder argumentSuggestion = new StringBuilder();
        String[] userInputArray = userInput.split(" ");
        Prefix currPrefix = null;

        // Check if user is trying to autocomplete a prefix
        if (userInputArray.length > 1 && !userInputArray[userInputArray.length - 1].contains("/")) {
            argumentSuggestion.append("/ ");
            currPrefix = new Prefix(userInputArray[userInputArray.length - 1] + "/");
            argumentMultimap.put(currPrefix, "");
            if (cmdPrompt.get(currPrefix) == null) {
                throw new CommandException("Invalid prefix");
            }
        }

        cmdPrompt.keySet()
                .stream()
                .filter(key -> argumentMultimap.getValue(key).isEmpty())
                .forEach(key -> argumentSuggestion.append(" ").append(key).append(cmdPrompt.get(key)));

        return argumentSuggestion.toString();
    }
}