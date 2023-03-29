package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_KEYWORD;

import java.util.ArrayList;
import java.util.Collections;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;

//@@author EvitanRelta-reused
// Reused from https://github.com/AY2223S1-CS2103T-T12-2/tp
// with minor modifications.
/**
 * Suggests a command based on the user input.
 */
public class CommandSuggestor {

    private final ArrayList<String> commandList;
    private final ArrayList<ArrayList<Prefix>> argPrefixList;

    /**
     * Constructs a {@code CommandSuggestor} with predefined commands and argument prompts.
     */
    public CommandSuggestor() {
        commandList = new ArrayList<>();
        argPrefixList = new ArrayList<>();

        commandList.add(AddCommand.COMMAND_WORD);
        argPrefixList.add(AddCommand.ARGUMENT_PREFIXES);

        commandList.add(ClearCommand.COMMAND_WORD);
        argPrefixList.add(ClearCommand.ARGUMENT_PREFIXES);

        commandList.add(DeleteCommand.COMMAND_WORD);
        argPrefixList.add(DeleteCommand.ARGUMENT_PREFIXES);

        commandList.add(EditCommand.COMMAND_WORD);
        argPrefixList.add(EditCommand.ARGUMENT_PREFIXES);

        commandList.add(ExitCommand.COMMAND_WORD);
        argPrefixList.add(ExitCommand.ARGUMENT_PREFIXES);

        commandList.add(DeleteCommand.COMMAND_WORD);
        argPrefixList.add(DeleteCommand.ARGUMENT_PREFIXES);

        commandList.add(FindCommand.COMMAND_WORD);
        argPrefixList.add(FindCommand.ARGUMENT_PREFIXES);

        commandList.add(HelpCommand.COMMAND_WORD);
        argPrefixList.add(HelpCommand.ARGUMENT_PREFIXES);

        commandList.add(ListCommand.COMMAND_WORD);
        argPrefixList.add(ListCommand.ARGUMENT_PREFIXES);

        commandList.add(RemarkCommand.COMMAND_WORD);
        argPrefixList.add(RemarkCommand.ARGUMENT_PREFIXES);

        commandList.add(ShowRemarkCommand.COMMAND_WORD);
        argPrefixList.add(ShowRemarkCommand.ARGUMENT_PREFIXES);

    }

    /**
     * Suggests a command based on the user input.
     *
     * @param userInput User input.
     * @return Suggested command.
     * @throws CommandException If the user input is invalid.
     */
    public String suggestCommand(String userInput) throws CommandException {
        assert userInput != null && !userInput.isEmpty();
        String[] userInputArray = userInput.split(" ", 2);
        String commandWord = userInputArray[0];
        String suggestedCommand = "";
        boolean isCommandComplete = userInput.contains(" ");

        for (String command : commandList) {
            if (command.startsWith(commandWord)) {
                if (isCommandComplete && !command.equals(commandWord)) {
                    continue;
                }
                suggestedCommand = command;
                break;
            }
        }

        if (suggestedCommand.equals("") && !commandWord.equals("")) {
            throw new CommandException("Invalid command");
        }
        ArrayList<Prefix> argPrefixes = argPrefixList.get(commandList.indexOf(suggestedCommand));

        if (userInputArray.length > 1) {
            if (userInput.charAt(userInput.length() - 1) == ' ') {
                userInput = userInput.substring(0, userInput.length() - 1);
            }
            return userInput + suggestArguments(argPrefixes, userInputArray[1]);
        } else {
            return suggestedCommand + suggestArguments(argPrefixes, "");
        }
    }

    /**
     * Returns the new user input when user auto-completes the command.
     *
     * @param userInput Current User Input.
     * @param commandSuggestion Current Command Suggestion
     * @return New User Input.
     */
    public String autocompleteCommand(String userInput, String commandSuggestion) {
        // Command suggested but not yet entered by user
        String suggestedCommand = commandSuggestion.substring(userInput.length());
        boolean isCommandComplete = userInput.contains(" ");
        int autocompleteUptoIndex;
        if (isCommandComplete) {
            autocompleteUptoIndex = suggestedCommand.indexOf(isCommandComplete ? "/" : " ") + 1;
        } else {
            return getLongestMatchingPrefixSuggestion(userInput);
        }

        // If command has no prefix arguments
        if (autocompleteUptoIndex == 0) {
            autocompleteUptoIndex = suggestedCommand.length();
        }

        String autocompletedCommand = suggestedCommand.substring(0, autocompleteUptoIndex);
        if (!autocompletedCommand.contains("<")) {
            userInput = userInput + autocompletedCommand;
        }
        return userInput;
    }

    /**
     * Suggests prompts for arguments based on the user input.
     *
     * @param argPrefixes Argument prefixes for specified command.
     * @param userInput Current user input.
     * @return Suggested arguments.
     * @throws CommandException If the user input is invalid.
     */
    private String suggestArguments(ArrayList<Prefix> argPrefixes, String userInput)
            throws CommandException {
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(" " + userInput, argPrefixes.toArray(new Prefix[] {}));
        String argumentSuggestion = "";
        String[] userInputArray = userInput.trim().split(" ");
        Prefix currPrefix = null;
        boolean isIndexRequired = argPrefixes.contains(PREFIX_INDEX);
        boolean hasKeyword = argPrefixes.contains(PREFIX_KEYWORD);
        boolean hasPrefix = (!userInput.isEmpty() && (!isIndexRequired || userInputArray.length > 1));

        // Check if user input for index is valid (only if required)
        if (isIndexRequired) {
            if (userInputArray[0].equals("")) {
                argumentSuggestion += " " + argPrefixes.get(0).getPlaceholderText();
            } else {
                if (!userInputArray[0].matches("-?\\d+(\\.\\d+)?")) {
                    throw new CommandException("Invalid index");
                }
            }
        }

        if (hasKeyword) {
            // Check if user input contains keyword
            if (userInput.equals("")) {
                argumentSuggestion += " " + argPrefixes.get(0).getPlaceholderText();
            }
            argumentMultimap.put(PREFIX_KEYWORD, "");
        } else if (hasPrefix && !userInputArray[userInputArray.length - 1].contains("/")) {
            // Check if user is trying to autocomplete a prefix
            currPrefix = new Prefix(userInputArray[userInputArray.length - 1] + "/");
            argumentMultimap.put(currPrefix, "");

            if (argPrefixes.contains(currPrefix)) {
                argumentSuggestion += "/ ";
            } else if (!userInput.contains("/")) {
                throw new CommandException("Invalid prefix");
            }
        }

        for (Prefix prefix : argPrefixes) {
            if (argumentMultimap.getValue(prefix).isEmpty()) {
                argumentSuggestion += " " + prefix + prefix.getPlaceholderText();
            }
        }
        return argumentSuggestion;
    }

    /**
     * Gets the longest matching prefix from all possible command suggestions depending on the user
     * input.
     *
     * @param userInput User input.
     * @return Longest matching prefix.
     * @throws CommandException If the user input is invalid.
     */
    private String getLongestMatchingPrefixSuggestion(String userInput) {
        assert userInput != null && !userInput.isEmpty();
        String[] userInputArray = userInput.split(" ", 2);
        String commandWord = userInputArray[0];
        boolean isCommandComplete = userInput.contains(" ");
        ArrayList<String> matchingCommands = new ArrayList<>();

        for (String command : commandList) {
            if (command.startsWith(commandWord)) {
                if (isCommandComplete && !command.equals(commandWord)) {
                    continue;
                }
                matchingCommands.add(command + " ");
            }
        }
        return getLongestMatchingPrefix(matchingCommands);
    }

    /**
     * Gets longest matching prefix from list of strings.
     *
     * @param matchingCommands List of strings.
     * @return Longest matching prefix.
     */
    private String getLongestMatchingPrefix(ArrayList<String> matchingCommands) {
        Collections.sort(matchingCommands);
        int size = matchingCommands.size();
        if (size == 0) {
            return "";
        }

        if (size == 1) {
            return matchingCommands.get(0);
        }

        // find the minimum length from first and last string
        int end =
                Math.min(matchingCommands.get(0).length(), matchingCommands.get(size - 1).length());

        // find the common prefix between the first and last string
        int i = 0;
        while (i < end
                && matchingCommands.get(0).charAt(i) == matchingCommands.get(size - 1).charAt(i)) {
            i++;
        }

        String prefix = matchingCommands.get(0).substring(0, i);
        return prefix;
    }
}
//@@author
