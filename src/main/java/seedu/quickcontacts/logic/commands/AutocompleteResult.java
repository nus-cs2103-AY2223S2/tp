package seedu.quickcontacts.logic.commands;

import java.util.Optional;

import seedu.quickcontacts.logic.parser.Prefix;

/**
 * Represents the result from autocompletion of command inputs, which consists
 * of the next {@code Prefix} and whether that {@code Prefix} should
 * replace the current one.
 */
public class AutocompleteResult {
    private final Optional<String> result;
    private final boolean isReplaceCurrent;

    /**
     * Generates an empty {@code AutocompleteResult} that does not replace nor insert anything.
     */
    public AutocompleteResult() {
        this.result = Optional.empty();
        this.isReplaceCurrent = false;
    }

    /**
     * Generates an {@code AutocompleteResult} with the suggested {@code Prefix}
     * and whether it should replace the current one. Prefix passed cannot be null.
     *
     * @param prefix Prefix for command.
     * @param isReplaceCurrent True if suggested {@code Prefix} should replace the current, false otherwise.
     */
    public AutocompleteResult(Prefix prefix, boolean isReplaceCurrent) {
        assert prefix != null;
        this.result = Optional.of(prefix.getPrefix());
        this.isReplaceCurrent = isReplaceCurrent;
    }

    /**
     * Generates an {@code AutocompleteResult} with the suggested command word.
     * and whether it should replace the current one. Command word string cannot be null.
     *
     * @param commandWord Command word to be suggested.
     * @param isReplaceCurrent True if suggested command word should replace the current, false otherwise.
     */
    public AutocompleteResult(String commandWord, boolean isReplaceCurrent) {
        assert commandWord != null;
        this.result = Optional.of(commandWord);
        this.isReplaceCurrent = isReplaceCurrent;
    }

    /**
     * Retrieves the suggested result.
     *
     * @return Empty {@code Optional} if no result is suggested, else an {@code Optional} containing the
     *          suggested result.
     */
    public Optional<String> getResult() {
        return result;
    }

    /**
     * Returns whether the autocompletion should replace the current prefix or command word in the user input.
     *
     * @return True if autocompletion should replace the current, false otherwise.
     */
    public boolean isReplaceCurrent() {
        return this.isReplaceCurrent;
    }
}
