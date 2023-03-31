package seedu.address.logic.commands;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

import seedu.address.commons.util.CheckedFunction;
import seedu.address.logic.commands.exceptions.RecommendationException;
import seedu.address.logic.parser.AddElderlyCommandParser;
import seedu.address.logic.parser.AddPairCommandParser;
import seedu.address.logic.parser.AddVolunteerCommandParser;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.AutoPairCommandParser;
import seedu.address.logic.parser.ClearCommandParser;
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
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.StatsCommandParser;

/**
 * @@author Lee Zong Xun
 * A class representing a recommendation engine that recommends a command based on the user input.
 * This class is implemented by using a command registry and a command info map.
 *
 * Adapted from Agolia Documentation
 * <a href="https://www.algolia.com/doc/guides/solutions/ecommerce/search/autocomplete/predictive-search-suggestions"/>
 */
public class CommandRecommendationEngine {
    /**
     * A singleton instance of the CommandRecommendationEngine class.
     */
    private static CommandRecommendationEngine commandRecommendationEngine;
    /**
     * A map containing the command registry with command word as key and CommandInfo as value.
     */
    private static final CommandInfoMap commandRegistry = new CommandInfoMap();
    /**
     * Used to format the error messages
     */
    private static final String delimiter = "                ";

    static {
        registerCommand(new DeleteVolunteerCommandParser());
        registerCommand(new DeleteElderlyCommandParser());
        registerCommand(new AddVolunteerCommandParser());
        registerCommand(new AddElderlyCommandParser());
        registerCommand(new AddPairCommandParser());
        registerCommand(new EditElderlyCommandParser());
        registerCommand(new EditVolunteerCommandParser());
        registerCommand(new DeletePairCommandParser());
        registerCommand(new ClearCommandParser());
        registerCommand(new FindCommandParser());
        registerCommand(new ExitCommandParser());
        registerCommand(new ListCommandParser());
        registerCommand(new EditCommandParser());
        registerCommand(new HelpCommandParser());
        registerCommand(new StatsCommandParser());
        registerCommand(new AutoPairCommandParser());
    }

    private static <T extends Command> void registerCommand(Parser<T> commandParser) {
        CommandInfo commandInfo = commandParser.getCommandInfo();
        commandRegistry.put(commandInfo.getCmdWord(), commandInfo);
    }

    private CommandRecommendationEngine() { }

    /**
     * Gets a singleton instance of the CommandRecommendationEngine class.
     *
     * @return the singleton instance of the CommandRecommendationEngine class.
     */
    public static CommandRecommendationEngine getInstance() {
        if (commandRecommendationEngine == null) {
            commandRecommendationEngine = new CommandRecommendationEngine();
        }
        return commandRecommendationEngine;
    }

    /**
     * Gets the command registry with command word as key and CommandInfo as value.
     *
     * @return the command registry.
     */
    public CommandInfoMap getCommandInfoMap() {
        return commandRegistry;
    }

    /**
     * Generates the recommended command based on the user input.
     *
     * @param userInput the user input.
     * @return the recommended command.
     * @throws RecommendationException if there is no matching command in the command registry.
     */
    public String generateCommandRecommendations(String userInput) throws RecommendationException {
        if (userInput == null || userInput.isEmpty()) {
            return "";
        }

        // Find entered command
        int commandIndex = userInput.indexOf(" ");

        // Incomplete command
        if (commandIndex == -1) {
            CommandInfo commandInfo = findMatchingCommandInfo(userInput, false);
            if (commandInfo == null) {
                throw new RecommendationException(userInput + delimiter + "Command cannot be found!");
            }
            return commandInfo.getCmdWord() + generateArgumentRecommendation(commandInfo, null, userInput);
        }

        // Complete command
        String commandWord = userInput.substring(0, commandIndex);
        String parsedArgs = userInput.substring(commandIndex);

        // Enforcing exact matching since the command must be complete
        CommandInfo commandInfo = findMatchingCommandInfo(commandWord, true);
        if (commandInfo == null) {
            throw new RecommendationException(userInput + delimiter + "Command cannot be found!");
        }

        String commandArgs = parsedArgs.isBlank() ? null : parsedArgs;
        String recommendedArguments = generateArgumentRecommendation(commandInfo, commandArgs, userInput);
        return userInput + recommendedArguments;
    }

    /**
     * Autocompletes a command based on user input.
     *
     * @param userInput the user input to use for generating recommendations
     * @return the recommended command
     * @throws RecommendationException if there is an error generating recommendations
     */
    public String autocompleteCommand(String userInput) throws RecommendationException {
        userInput = userInput.trim();
        String recommendedCommand = generateCommandRecommendations(userInput);
        String suggestedCommand = recommendedCommand.substring(userInput.length());
        boolean isCompleteCommand = isCommandPrefixComplete(userInput, " ");
        int commandIdx = recommendedCommand.indexOf(" ");
        String command = recommendedCommand.substring(0, commandIdx == -1
                ? recommendedCommand.length()
                : commandIdx);
        if (!isCompleteCommand && !userInput.equals(command)) {
            return command;
        } else {
            int nextIdx = suggestedCommand.indexOf("/") + 1;
            return recommendedCommand.substring(0, userInput.length() + nextIdx);
        }
    }

    private static boolean isCommandPrefixComplete(String userInput, String delimiter) {
        return userInput.contains(delimiter);
    }

    private CommandInfo findMatchingCommandInfo(String commandWord, boolean isExactMatching) {
        return commandRegistry.keySet().stream()
                .filter(command -> isExactMatching ? command.equals(commandWord) : command.startsWith(commandWord))
                .sorted().map(commandRegistry::get)
                .findFirst().orElse(null);
    }

    private Prefix findMatchingPrefix(HashMap<Prefix, String> cmdPrompt, String prefix) {
        return cmdPrompt.keySet()
                .stream()
                .filter(command -> command.getPrefix().startsWith(prefix))
                .findFirst()
                .orElse(null);
    }

    private String generateArgumentRecommendation(CommandInfo commandInfo, String userArgs, String userInput)
            throws RecommendationException {

        @SuppressWarnings("unchecked")
        HashMap<Prefix, String> cmdPrompt = (HashMap<Prefix, String>) commandInfo.getCmdPrompts().clone();
        String command = commandInfo.getCmdWord();
        StringBuilder argumentRecommendation = new StringBuilder();
        if (userArgs == null) {
            getRemainingArguments(cmdPrompt,
                    key -> argumentRecommendation.append(" ").append(key).append(cmdPrompt.get(key)),
                    unused -> true);
            return argumentRecommendation.toString();
        }

        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userArgs, cmdPrompt.keySet()
                .toArray(new Prefix[]{}));

        try {
            isValidArgs(command, argumentMultimap);
        } catch (RecommendationException e){
            if (e.getMessage().isEmpty()) {
                throw new RecommendationException(userInput + delimiter + "Invalid prefix");
            } else {
                throw new RecommendationException(userInput + delimiter + e.getMessage());
            }
        }

        // To retrieve the last entered string
        String[] userInputArray = userArgs.split(" ");
        String currPrefixString = userInputArray[userInputArray.length - 1];
        boolean isCompletePrefix = isCommandPrefixComplete(userInputArray[userInputArray.length - 1], "/");
        Prefix matchingPrefix = findMatchingPrefix(cmdPrompt, currPrefixString.split("/")[0]);

        if (isCompletePrefix && matchingPrefix == null) {
            throw new RecommendationException(userInput + delimiter + "Invalid prefix");
        }

        if (isCompletePrefix) {
            argumentMultimap.put(matchingPrefix, "");
        }

        if (!isCompletePrefix && matchingPrefix != null) {
            argumentRecommendation.append(matchingPrefix.getPrefix().substring(currPrefixString.length()));
            argumentRecommendation.append(cmdPrompt.get(matchingPrefix));
            cmdPrompt.remove(matchingPrefix);
        }

        // current set of arguments is complete -> return remaining recommendation
        getRemainingArguments(cmdPrompt,
                key -> argumentRecommendation.append(" ").append(key).append(cmdPrompt.get(key)),
                key -> argumentMultimap.getValue(key).isEmpty());

        return argumentRecommendation.toString();
    }

    /**
     * Validates the current set of arguments according to the specified command validator.
     *
     * @param command          The command type.
     * @param argumentMultimap The map of arguments.
     */
    public static void isValidArgs(String command, ArgumentMultimap argumentMultimap) throws RecommendationException {
        CommandInfo commandInfo = commandRegistry.get(command);
        CheckedFunction<ArgumentMultimap, Boolean> argumentValidator = commandInfo.getCmdValidator();
        argumentValidator.apply(argumentMultimap);
    }

    /**
     * Returns the remaining arguments from the provided command prompt as a list of prefixes.
     *
     * @param cmdPrompt a HashMap containing the command prompt prefixes and their values.
     * @param consumer a Consumer that will receive each remaining argument's prefix.
     * @param predicate a Predicate that will be used to filter the prefixes.
     */
    public static void getRemainingArguments(HashMap<Prefix, String> cmdPrompt,
            Consumer<? super Prefix> consumer, Predicate<Prefix> predicate) {
        cmdPrompt.keySet()
                .stream()
                .filter(predicate)
                .forEach(consumer);
    }

    /**
     * A map that stores the command information of all registered commands.
     */
    public static class CommandInfoMap {
        private final Map<String, CommandInfo> map = new LinkedHashMap<>();

        public void put(String cmdWord, CommandInfo commandInfo) {
            map.put(cmdWord, commandInfo);
        }

        public CommandInfo get(String cmdWord) {
            return map.get(cmdWord);
        }

        public Set<String> keySet() {
            return map.keySet();
        }
    }
}
