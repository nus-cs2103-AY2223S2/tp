package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.INDEX_PLACEHOLDER;
import static seedu.address.logic.parser.CliSyntax.KEYWORD_PLACEHOLDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.REMARK_PLACEHOLDER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;

//Solution below adapted from https://github.com/AY2223S1-CS2103T-T12-2/tp
// with almost complete overhauling, including refactoring, bug-fixing, adding
// of asserts, and changing the behaviour of the feature to suit our needs.
/**
 * Suggests and autocompletes command/argument based on the user input.
 */
public class AutocompleteEngine {

    private static final ArrayList<String> COMMAND_LIST = new ArrayList<>(List.of(
            AddCommand.COMMAND_WORD,
            ClearCommand.COMMAND_WORD,
            DeleteCommand.COMMAND_WORD,
            EditCommand.COMMAND_WORD,
            ExitCommand.COMMAND_WORD,
            FindCommand.COMMAND_WORD,
            FilterCommand.COMMAND_WORD,
            HelpCommand.COMMAND_WORD,
            ListCommand.COMMAND_WORD,
            RemarkCommand.COMMAND_WORD,
            ShowRemarkCommand.COMMAND_WORD,
            RedoCommand.COMMAND_WORD,
            UndoCommand.COMMAND_WORD
    ));
    private static final Map<String, ArrayList<Prefix>> ARGUMENT_PREFIX_MAP = Map.ofEntries(
            Map.entry(AddCommand.COMMAND_WORD, AddCommand.ARGUMENT_PREFIXES),
            Map.entry(ClearCommand.COMMAND_WORD, ClearCommand.ARGUMENT_PREFIXES),
            Map.entry(DeleteCommand.COMMAND_WORD, DeleteCommand.ARGUMENT_PREFIXES),
            Map.entry(EditCommand.COMMAND_WORD, EditCommand.ARGUMENT_PREFIXES),
            Map.entry(ExitCommand.COMMAND_WORD, ExitCommand.ARGUMENT_PREFIXES),
            Map.entry(FindCommand.COMMAND_WORD, FindCommand.ARGUMENT_PREFIXES),
            Map.entry(FilterCommand.COMMAND_WORD, FilterCommand.ARGUMENT_PREFIXES),
            Map.entry(HelpCommand.COMMAND_WORD, HelpCommand.ARGUMENT_PREFIXES),
            Map.entry(ListCommand.COMMAND_WORD, ListCommand.ARGUMENT_PREFIXES),
            Map.entry(RemarkCommand.COMMAND_WORD, RemarkCommand.ARGUMENT_PREFIXES),
            Map.entry(ShowRemarkCommand.COMMAND_WORD, ShowRemarkCommand.ARGUMENT_PREFIXES),
            Map.entry(RedoCommand.COMMAND_WORD, RedoCommand.ARGUMENT_PREFIXES),
            Map.entry(UndoCommand.COMMAND_WORD, UndoCommand.ARGUMENT_PREFIXES)
    );


    private final Model model;

    /**
     * Constructs a {@code CommandSuggestor} with predefined commands and argument prompts.
     */
    public AutocompleteEngine(Model model) {
        this.model = model;

        // Assert that 'commandArgPrefixes' keys and 'commandList' both contains only the same
        // values and nothing else, with no duplicates.
        assert COMMAND_LIST.stream().allMatch(ARGUMENT_PREFIX_MAP::containsKey)
                : "'commandArgPrefixes' keys should contains all the elements in 'commandList'";
        assert ARGUMENT_PREFIX_MAP.keySet().stream().allMatch(COMMAND_LIST::contains)
                : "'commandList' should contains all the elements in 'commandArgPrefixes' keys";
        assert ARGUMENT_PREFIX_MAP.keySet().size() == COMMAND_LIST.size()
                : "The number of 'commandArgPrefixes' keys should equal the size of 'commandList'";

        assert ARGUMENT_PREFIX_MAP.values().stream()
                .allMatch(argPrefix -> argPrefix.stream()
                        .dropWhile(Prefix::isPlaceholder)
                        .noneMatch(Prefix::isPlaceholder))
                : "All prefix-less arguments (eg. index/keywords) should come before prefixed args";

        assert ARGUMENT_PREFIX_MAP.values().stream()
                .allMatch(argPrefix -> argPrefix.stream()
                        .dropWhile(INDEX_PLACEHOLDER::equals)
                        .noneMatch(INDEX_PLACEHOLDER::equals))
                : "All index arguments should come before any other type of args";

        assert ARGUMENT_PREFIX_MAP.values().stream()
                .allMatch(argPrefix -> argPrefix.stream()
                        .dropWhile(prefix -> !prefix.isOptional())
                        .allMatch(Prefix::isOptional))
                : "All optional arguments should come after any compulsory args";

        assert ARGUMENT_PREFIX_MAP.values().stream()
                .allMatch(argPrefix -> argPrefix.stream()
                        .dropWhile(prefix -> !prefix.isRepeatable())
                        .allMatch(Prefix::isRepeatable))
                : "All repeatable arguments should come after any non-repeatable args";
    }

    /**
     * Suggests a command based on the user input.
     *
     * @param userInput User input.
     * @return Suggested command (including the user input).
     * @throws CommandException If the user input is invalid.
     */
    public String suggestCommand(String userInput) throws CommandException {
        assert userInput != null : "'userInput' should not be 'null'";

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
            String suggestedCommand = COMMAND_LIST.stream()
                    .filter(command -> command.startsWith(commandWord))
                    .findFirst()
                    .orElseThrow(() -> noSuchCommandException);
            return inputLeadingSpaces + suggestedCommand + suggestArguments(suggestedCommand, commandBody);
        }

        boolean isInvalidCommand = !COMMAND_LIST.contains(commandWord);
        if (isInvalidCommand) {
            throw noSuchCommandException;
        }

        return userInput + suggestArguments(commandWord, commandBody);
    }

    /**
     * Returns the new user input when user auto-completes the command.
     *
     * @param userInput Current user input.
     * @param commandSuggestion Current command suggestion.
     * @return New user input.
     */
    public String autocompleteCommand(String userInput, String commandSuggestion) {
        // Command suggested but not yet entered by user
        String remainingSuggestion = commandSuggestion.substring(userInput.length());
        Pattern nextAutocompleteRegex = Pattern.compile("^ *\\[*[a-z0-9_]*\\/*", Pattern.CASE_INSENSITIVE);
        String nextAutocomplete = Optional.of(nextAutocompleteRegex.matcher(remainingSuggestion))
                .filter(Matcher::find)
                .map(Matcher::group)
                .map(match -> match.replaceAll("[\\[\\]\\.]", "")) // Remove optional/repeating prefix artifacts.
                .filter(match -> !match.trim().equals(INDEX_PLACEHOLDER.toPlaceholderString()))
                .filter(match -> !match.trim().equals(KEYWORD_PLACEHOLDER.toPlaceholderString()))
                .filter(match -> !match.trim().equals(REMARK_PLACEHOLDER.toPlaceholderString()))
                .orElse("");
        return userInput + nextAutocomplete;
    }

    private Map<Prefix, ArrayList<String>> getExistingArgValuesForAutocomplete() {
        return new HashMap<>(Map.of(
                PREFIX_TAG, model.getExistingTagValues(),
                PREFIX_MODULE, model.getExistingModuleValues(),
                PREFIX_EDUCATION, model.getExistingEducationValues()
        ));
    }

    /**
     * Suggests prompts for arguments for {@code command} based on the user input.
     *
     * @param command The command to suggest arguments for.
     * @param commmandBody The command body of the current user input.
     * @return Suggested arguments.
     * @throws CommandException If the user input is invalid.
     */
    private String suggestArguments(String command, String commmandBody) throws CommandException {
        ArrayList<Prefix> argPrefixes = ARGUMENT_PREFIX_MAP.get(command);
        assert argPrefixes != null;
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(" " + commmandBody, argPrefixes.toArray(Prefix[]::new));
        Map<Prefix, ArrayList<String>> existingArgValues = getExistingArgValuesForAutocomplete();

        if (commmandBody.isBlank()) {
            String allArgs = argPrefixes.stream()
                    .map(Prefix::toPlaceholderString)
                    .collect(Collectors.joining(" "));
            String leadingPadding = commmandBody.isEmpty() ? " " : "";
            return leadingPadding + allArgs;
        }

        String[] splitArr = commmandBody.trim().split(" +");
        ArrayList<String> words = new ArrayList<>(Arrays.asList(splitArr));
        int numOfWords = splitArr.length;
        assert numOfWords > 0 : "'numOfWords' should be > 0";
        String firstWord = splitArr[0];
        String lastWord = splitArr[splitArr.length - 1];
        assert !firstWord.isBlank() : "'firstWord' should not be blank";
        assert !firstWord.contains(" ") : "'firstWord' should not contain any spaces";
        assert !lastWord.isBlank() : "'lastWord' should not be blank";
        assert !lastWord.contains(" ") : "'lastWord' should not contain any spaces";

        boolean isIndexRequired = argPrefixes.contains(INDEX_PLACEHOLDER);
        if (isIndexRequired) {
            long numOfIndexRequired = argPrefixes.stream().filter(INDEX_PLACEHOLDER::equals).count();
            boolean areAllValidIndexes = words.stream().limit(numOfIndexRequired)
                    .allMatch(word -> word.matches("\\d+"));

            if (!areAllValidIndexes) {
                throw new CommandException("Invalid index.");
            }
        }

        boolean hasNoTrailingSpace = !commmandBody.endsWith(" ");
        if (hasNoTrailingSpace) {
            Prefix currPrefix = new Prefix(lastWord.replaceAll("[^\\/]*$", ""));
            boolean toAutocompleteArgValue = existingArgValues.containsKey(currPrefix);

            if (toAutocompleteArgValue) {
                String argValue = lastWord.replaceAll("^.*\\/", "");
                String matchingExistingValues = existingArgValues.get(currPrefix)
                        .stream()
                        .filter(value -> value.startsWith(argValue))
                        .filter(value -> argumentMultimap.getAllValues(currPrefix).stream()
                                .noneMatch(value::equals))
                        .collect(Collectors.joining(" | "));
                return matchingExistingValues.isEmpty()
                        ? ""
                        : matchingExistingValues.substring(argValue.length());
            }

            // Example of filling the value:
            // "add n/Sha" or "add n/", where user is halfway filling the name field.
            boolean isFillingPrefixValue = lastWord.contains("/");
            if (isFillingPrefixValue) {
                // Don't suggest anything if user is filling.
                return "";
            }

            String matchingArgs = argPrefixes.stream()
                    // Excludes prefix-less arguments like index/keywords.
                    .filter(prefix -> !prefix.isPlaceholder())
                    // Remove filled non-repeating prefixed arguments.
                    .filter(prefix -> argumentMultimap.getValue(prefix).isEmpty()
                            || prefix.isRepeatable())
                    .filter(prefix -> prefix.getPrefix().startsWith(lastWord))
                    .map(Prefix::toPlaceholderString)
                    .collect(Collectors.joining(" "));

            return matchingArgs.isEmpty()
                    ? ""
                    : matchingArgs.replaceFirst("^\\[", "") // If first arg is optional, remove it's opening bracket.
                            .substring(lastWord.length());
        }

        long numOfNonRepeatingPrefixlessArgs = argPrefixes.stream()
                .filter(Prefix::isPlaceholder)
                .filter(prefix -> !prefix.isRepeatable())
                .count();
        String remainingArgs = argPrefixes.stream()
                // Skip the filled prefix-less arguments.
                .skip(Math.min(numOfWords, numOfNonRepeatingPrefixlessArgs))
                // Remove filled non-repeating prefixed arguments.
                .filter(prefix -> argumentMultimap.getValue(prefix).isEmpty()
                        || prefix.isPlaceholder()
                        || prefix.isRepeatable())
                .map(Prefix::toPlaceholderString)
                .collect(Collectors.joining(" "));
        return remainingArgs;
    }
}
