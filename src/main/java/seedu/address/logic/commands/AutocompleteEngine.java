package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.INDEX_PLACEHOLDER;
import static seedu.address.logic.parser.CliSyntax.KEYWORD_PLACEHOLDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.REMARK_PLACEHOLDER;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

//Solution below adapted from https://github.com/AY2223S1-CS2103T-T12-2/tp
// with almost complete overhauling, including refactoring, bug-fixing, adding
// of asserts, and changing the behaviour of the feature to suit our needs.
/**
 * Suggests and autocompletes command/argument based on the user input.
 */
public class AutocompleteEngine {

    private static final List<String> COMMAND_LIST = List.of(
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
    );
    private static final Map<String, List<Prefix>> ARGUMENT_PREFIX_MAP = Map.ofEntries(
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
     * Constructs a {@code AutocompleteEngine} with a predefined model.
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
     * @throws ParseException If {@code userInput} is likely invalid (based on heuristics).
     */
    public String suggestCommand(String userInput) throws ParseException {
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

        ParseException unknownParseException = new ParseException(MESSAGE_UNKNOWN_COMMAND);

        boolean isCommandComplete = strippedLeadingInput.contains(" ");
        if (!isCommandComplete) {
            String suggestedCommand = COMMAND_LIST.stream()
                    .filter(command -> command.startsWith(commandWord))
                    .findFirst()
                    .orElseThrow(() -> unknownParseException);
            return inputLeadingSpaces + suggestedCommand + suggestArguments(suggestedCommand, commandBody);
        }

        boolean isInvalidCommand = !COMMAND_LIST.contains(commandWord);
        if (isInvalidCommand) {
            throw unknownParseException;
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
        assert commandSuggestion.length() >= userInput.length()
                : "'commandSuggestion' should be longer than 'userInput'";
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

    private Map<Prefix, List<String>> getExistingArgValuesForAutocomplete() {
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
     * @param commandBody The command body of the current user input.
     * @return Suggested arguments.
     * @throws ParseException If the user input is invalid.
     */
    private String suggestArguments(String command, String commandBody) throws ParseException {
        List<Prefix> argPrefixes = ARGUMENT_PREFIX_MAP.get(command);
        assert argPrefixes != null;
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(" " + commandBody, argPrefixes.toArray(Prefix[]::new));
        Map<Prefix, List<String>> existingArgValues = getExistingArgValuesForAutocomplete();

        if (commandBody.isBlank()) {
            return getFullArgSuggestion(argPrefixes, commandBody);
        }

        String[] splitArr = commandBody.trim().split(" +");
        List<String> words = Arrays.asList(splitArr);
        assert words.size() > 0 : "number of words should be > 0";
        assert !words.get(0).isBlank() : "first word should not be blank";
        assert !words.get(0).contains(" ") : "'first word' should not contain any spaces";
        String lastWord = splitArr[splitArr.length - 1];
        assert !lastWord.isBlank() : "'lastWord' should not be blank";
        assert !lastWord.contains(" ") : "'lastWord' should not contain any spaces";

        boolean isIndexRequired = argPrefixes.contains(INDEX_PLACEHOLDER);
        if (isIndexRequired) {
            validateIndex(argPrefixes, words);
        }

        boolean hasNoTrailingSpace = !commandBody.endsWith(" ");
        if (hasNoTrailingSpace) {
            Prefix currPrefix = new Prefix(lastWord.replaceAll("[^\\/]*$", ""));
            boolean toAutocompleteArgValue = existingArgValues.containsKey(currPrefix);

            if (toAutocompleteArgValue) {
                return getExistingArgSuggestion(existingArgValues, argumentMultimap, lastWord, currPrefix);
            }

            // Example of filling the value:
            // "add n/Sha" or "add n/", where user is halfway filling the name field.
            boolean isFillingPrefixValue = lastWord.contains("/");
            if (isFillingPrefixValue) {
                // Don't suggest anything if user is filling.
                return "";
            }

            return getMatchingArgSuggestion(argPrefixes, argumentMultimap, lastWord);
        }

        return getRemainingArgSuggestion(argPrefixes, argumentMultimap, words);
    }

    /** Gets the full argument suggestion for the command. (ie. suggest all the arguments) */
    private static String getFullArgSuggestion(List<Prefix> argPrefixes, String commandBody) {
        String allArgs = argPrefixes.stream()
                .map(Prefix::toPlaceholderString)
                .collect(Collectors.joining(" "));
        String leadingPadding = commandBody.isEmpty() ? " " : "";
        return leadingPadding + allArgs;
    }

    /**
     * Validate that the index value is valid (but doesn't check if index exist in the list).
     * Assumes that the command requires index argument(s).
     */
    private static void validateIndex(List<Prefix> argPrefixes, List<String> words) throws ParseException {
        long numOfIndexRequired = argPrefixes.stream().filter(INDEX_PLACEHOLDER::equals).count();
        boolean areAllValidIndexes = words.stream().limit(numOfIndexRequired)
                .allMatch(word -> word.matches("\\d+") && !word.matches("0+"));

        if (!areAllValidIndexes) {
            throw new ParseException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }

    /** Get the suggestion for the remaining unfilled arguments. */
    private String getRemainingArgSuggestion(List<Prefix> argPrefixes,
            ArgumentMultimap argumentMultimap, List<String> words) {
        long numOfNonRepeatingPrefixlessArgs = argPrefixes.stream()
                .filter(Prefix::isPlaceholder)
                .filter(prefix -> !prefix.isRepeatable())
                .count();
        String remainingArgs = argPrefixes.stream()
                // Skip the filled prefix-less arguments.
                .skip(Math.min(words.size(), numOfNonRepeatingPrefixlessArgs))
                // Remove filled non-repeating prefixed arguments.
                .filter(prefix -> argumentMultimap.getValue(prefix).isEmpty()
                        || prefix.isPlaceholder()
                        || prefix.isRepeatable())
                .map(Prefix::toPlaceholderString)
                .collect(Collectors.joining(" "));
        return remainingArgs;
    }

    /** Get suggestion for existing arguments. */
    private String getExistingArgSuggestion(Map<Prefix, List<String>> existingArgValues,
            ArgumentMultimap argumentMultimap, String lastWord, Prefix currPrefix) {
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

    /**
     * Get suggestion for arguments starting with what the user is typing.
     * (eg. "add e" returns the suggestion "add e/EMAIL] [edu/EDUCATION]")
     */
    private String getMatchingArgSuggestion(List<Prefix> argPrefixes, ArgumentMultimap argumentMultimap,
            String lastWord) {
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

}
