package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_KEYWORD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;

//@@author EvitanRelta-reused
// Reused from https://github.com/AY2223S1-CS2103T-T12-2/tp
// with major refactoring and bugfixing.
/**
 * Suggests a command based on the user input.
 */
public class CommandSuggestor {

    private final ArrayList<String> commandList;
    private final HashMap<String, ArrayList<Prefix>> commandArgPrefixes;

    /**
     * Constructs a {@code CommandSuggestor} with predefined commands and argument prompts.
     */
    public CommandSuggestor() {
        commandList = new ArrayList<>();
        commandArgPrefixes = new HashMap<>();

        commandList.add(AddCommand.COMMAND_WORD);
        commandArgPrefixes.put(AddCommand.COMMAND_WORD, AddCommand.ARGUMENT_PREFIXES);

        commandList.add(ClearCommand.COMMAND_WORD);
        commandArgPrefixes.put(ClearCommand.COMMAND_WORD, ClearCommand.ARGUMENT_PREFIXES);

        commandList.add(DeleteCommand.COMMAND_WORD);
        commandArgPrefixes.put(DeleteCommand.COMMAND_WORD, DeleteCommand.ARGUMENT_PREFIXES);

        commandList.add(EditCommand.COMMAND_WORD);
        commandArgPrefixes.put(EditCommand.COMMAND_WORD, EditCommand.ARGUMENT_PREFIXES);

        commandList.add(ExitCommand.COMMAND_WORD);
        commandArgPrefixes.put(ExitCommand.COMMAND_WORD, ExitCommand.ARGUMENT_PREFIXES);

        commandList.add(FindCommand.COMMAND_WORD);
        commandArgPrefixes.put(FindCommand.COMMAND_WORD, FindCommand.ARGUMENT_PREFIXES);

        commandList.add(HelpCommand.COMMAND_WORD);
        commandArgPrefixes.put(HelpCommand.COMMAND_WORD, HelpCommand.ARGUMENT_PREFIXES);

        commandList.add(ListCommand.COMMAND_WORD);
        commandArgPrefixes.put(ListCommand.COMMAND_WORD, ListCommand.ARGUMENT_PREFIXES);

        commandList.add(RemarkCommand.COMMAND_WORD);
        commandArgPrefixes.put(RemarkCommand.COMMAND_WORD, RemarkCommand.ARGUMENT_PREFIXES);

        commandList.add(ShowRemarkCommand.COMMAND_WORD);
        commandArgPrefixes.put(ShowRemarkCommand.COMMAND_WORD, ShowRemarkCommand.ARGUMENT_PREFIXES);

        // Assert 'commandArgPrefixes' keys contains all the elements of 'commandList'
        assert commandList.stream().allMatch(commandArgPrefixes::containsKey);
        // and vice versa.
        assert commandArgPrefixes.keySet().stream().allMatch(commandList::contains);
        // Assert that they both contains only the same values and nothing else, with no duplicates.
        assert commandArgPrefixes.keySet().size() == commandList.size();
    }

    /**
     * Suggests a command based on the user input.
     *
     * @param userInput User input.
     * @return Suggested command.
     * @throws CommandException If the user input is invalid.
     */
    public String suggestCommand(String userInput) throws CommandException {
        assert userInput != null;

        if (userInput.isBlank()) {
            return userInput;
        }

        String strippedLeadingInput = userInput.stripLeading();
        String inputLeadingSpaces = userInput.substring(
                0, userInput.length() - strippedLeadingInput.length());

        String[] splitArr = strippedLeadingInput.split(" ", 2);
        String commandWord = splitArr[0];
        String commandBody = splitArr.length > 1 ? " " + splitArr[1] : "";

        CommandException noSuchCommandException = new CommandException(
            String.format("No command starting with \"%s\" found.", commandWord));

        boolean isCommandComplete = strippedLeadingInput.contains(" ");
        if (!isCommandComplete) {
            String suggestedCommand = commandList.stream()
                    .filter(command -> command.startsWith(commandWord))
                    .findFirst()
                    .orElseThrow(() -> noSuchCommandException);
            return inputLeadingSpaces + suggestedCommand + suggestArguments(suggestedCommand, commandBody);
        }

        boolean isInvalidCommand = !commandList.contains(commandWord);
        if (isInvalidCommand) {
            throw noSuchCommandException;
        }

        return userInput + suggestArguments(commandWord, commandBody);
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
     * Suggests prompts for arguments for {@code command} based on the user input.
     *
     * @param commmandBody The command body of the current user input.
     * @param command The command to suggest arguments for.
     * @return Suggested arguments.
     * @throws CommandException If the user input is invalid.
     */
    private String suggestArguments(String command, String commmandBody)
            throws CommandException {
        ArrayList<Prefix> argPrefixes = commandArgPrefixes.get(command);
        assert argPrefixes != null;
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(" " + commmandBody, argPrefixes.toArray(Prefix[]::new));

        if (commmandBody.isBlank()) {
            String allArgs = argPrefixes.stream()
                    .map(prefix -> prefix.getPrefix() + prefix.getPlaceholderText())
                    .collect(Collectors.joining(" "));
            String leadingPadding = commmandBody.isEmpty() ? " " : "";
            return leadingPadding + allArgs;
        }

        String[] splitArr = commmandBody.trim().split(" ");
        assert splitArr.length > 0;
        String firstWord = splitArr[0];
        String lastWord = splitArr[splitArr.length - 1];
        assert !firstWord.isBlank();
        assert !firstWord.contains(" ");
        assert !lastWord.isBlank();
        assert !lastWord.contains(" ");

        boolean isIndexRequired = argPrefixes.contains(PREFIX_INDEX);
        if (isIndexRequired) {
            // Index is assumed to always be the first arg.
            assert argPrefixes.get(0) == PREFIX_INDEX;

            //TODO to remove when commands are dry-run to see if they're valid,
            //where an error should be thrown should the first word not be a integer.
            boolean hasIntAsFirstWord = firstWord.matches("\\d+");
            if (!hasIntAsFirstWord) {
                throw new CommandException("Invalid index.");
            }
        }

        boolean hasNoTrailingSpace = !commmandBody.endsWith(" ");
        if (hasNoTrailingSpace) {
            // User is not filling the value of prefix.
            // Example of filling the value: "add n/Sha" where user is halfway filling the name field.
            // But it's false when: "add n/" where user is not filling it yet.
            boolean isFillingPrefixValue = lastWord.contains("/") && !lastWord.endsWith("/");
            if (isFillingPrefixValue) {
                // Don't suggest anything if user is filling.
                return "";
            }

            String matchingArgs = argPrefixes.stream()
                    // Excludes prefix-less arguments like index/keywords.
                    .filter(prefix -> !prefix.isPlaceholder())
                    // Get only unfilled arguments.
                    .filter(prefix -> argumentMultimap.getValue(prefix).isEmpty())
                    .map(prefix -> prefix.getPrefix() + prefix.getPlaceholderText())
                    .filter(str -> str.startsWith(lastWord))
                    .collect(Collectors.joining(" "));

            return matchingArgs.isEmpty()
                    ? ""
                    : matchingArgs.substring(lastWord.length());
        }

        String argumentSuggestion = "";
        String[] userInputArray = commmandBody.trim().split(" ");
        Prefix currPrefix = null;
        boolean hasKeyword = argPrefixes.contains(PREFIX_KEYWORD);
        boolean hasPrefix = (!commmandBody.isEmpty() && (!isIndexRequired || userInputArray.length > 1));

        if (hasKeyword) {
            // Check if user input contains keyword
            if (commmandBody.isEmpty()) {
                argumentSuggestion += " " + argPrefixes.get(0).getPlaceholderText();
            }
            argumentMultimap.put(PREFIX_KEYWORD, "");
        } else if (hasPrefix && !userInputArray[userInputArray.length - 1].contains("/")) {
            // Check if user is trying to autocomplete a prefix
            currPrefix = new Prefix(userInputArray[userInputArray.length - 1] + "/");
            argumentMultimap.put(currPrefix, "");

            if (argPrefixes.contains(currPrefix)) {
                argumentSuggestion += "/ ";
            } else if (!commmandBody.contains("/")) {
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
