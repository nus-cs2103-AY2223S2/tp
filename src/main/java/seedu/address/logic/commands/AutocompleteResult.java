package seedu.address.logic.commands;

import java.util.Optional;

import seedu.address.logic.parser.Prefix;

/**
 * Represents the result from autocompletion of command inputs, which consists
 * of the next {@code Prefix} and whether that {@code Prefix} should
 * replace the current one.
 */
public class AutocompleteResult {
    private final Prefix prefix;
    private final boolean isReplaceCurrent;

    /**
     * Generates an {@code AutocompleteResult} with the suggested {@code Prefix}
     * and whether it should replace the current one.
     *
     * @param prefix Prefix for command.
     * @param isReplaceCurrent True if suggested {@code Prefix} should replace the current, false otherwise.
     */
    public AutocompleteResult(Prefix prefix, boolean isReplaceCurrent) {
        this.prefix = prefix;
        this.isReplaceCurrent = isReplaceCurrent;
    }

    /**
     * Retrieves the suggested {@code Prefix}.
     *
     * @return Empty {@code Optional} if no {@code Prefix} is suggested, else an {@code Optional} containing the
     *          suggested {@code Prefix}.
     */
    public Optional<Prefix> getPrefix() {
        return prefix == null ? Optional.empty() : Optional.of(this.prefix);
    }

    /**
     * Returns whether the autocompletion should replace the current {@code Prefix}.
     *
     * @return True if autocompletion should replace the current, false otherwise.
     */
    public boolean isReplaceCurrent() {
        return this.isReplaceCurrent;
    }
}
