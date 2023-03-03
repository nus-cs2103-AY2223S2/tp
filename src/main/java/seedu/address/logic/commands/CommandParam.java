package seedu.address.logic.commands;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Represents the parameters of a command.
 */
public class CommandParam {
    /**
     * The unnamed token of the command, which is the part right after
     * command word and before the first prefix.
     */
    private final Optional<String> unnamedValue;

    /**
     * The named token of the command, which is the part right after
     * one prefix and before the next prefix.
     */
    private final Optional<Map<String, Optional<String>>> namedValues;

    private CommandParam(Optional<String> unnamedValue,
        Optional<Map<String, Optional<String>>> namedValues) {
        this.unnamedValue = unnamedValue;
        this.namedValues = namedValues;
    }

    /**
     * Creates a command parameter from the given tokens. Note that this
     * function will not throw parser exception, but will instead return a
     * {@code CommandParam} with empty optional fields. It is the caller's
     * responsibility to check if the returned command parameter is valid,
     * because this class does not have enough context to provide a
     * meaningful exception message.
     *
     * @param tokens   the tokens of the command.
     * @param prefixes the prefixes of the command.
     * @return the command parameter created.
     */
    public static CommandParam from(Deque<String> tokens,
        Optional<Set<String>> prefixes) {
        // special cases
        if (tokens.size() == 0) {
            return new CommandParam(Optional.empty(),
                Optional.empty());
        }
        if (prefixes.isEmpty()) {
            return new CommandParam(
                Optional.of(String.join(" ", tokens)),
                Optional.empty());
        }
        // set up
        Set<String> prefixMap = prefixes.get();
        // handle the unnamed token
        Optional<String> unnamedValue =
            parseUnnamedValue(tokens, prefixMap);
        // handle the named tokens
        Map<String, Optional<String>> namedValues =
            parseNamedValues(tokens, prefixMap);
        return new CommandParam(unnamedValue, Optional.of(namedValues));
    }

    private static Optional<String> parseUnnamedValue(Deque<String> tokens,
        Set<String> prefixes) {
        final StringBuilder builder = new StringBuilder();
        while (!prefixes.contains(tokens.peek())) {
            builder.append(tokens.pop()).append(" ");
        }
        if (builder.length() == 0) {
            return Optional.empty();
        }
        return Optional.of(builder.toString().trim());
    }

    private static Map<String, Optional<String>> parseNamedValues(Deque<String> tokens,
        Set<String> prefixes) {
        if (tokens.size() == 0) {
            return new HashMap<>();
        }
        Map<String, Optional<String>> namedValues = new HashMap<>();
        String prefix = tokens.pop();
        final StringBuilder builder = new StringBuilder();
        while (tokens.size() > 0) {
            if (prefixes.contains(tokens.peek())) {
                namedValues.put(prefix, Optional.of(builder.toString().trim()));
                prefix = tokens.pop();
                builder.setLength(0);
            } else {
                builder.append(tokens.pop()).append(" ");
            }
        }
        if (builder.length() > 0) {
            namedValues.put(prefix, Optional.of(builder.toString().trim()));
        }
        for (String p : prefixes) {
            if (!namedValues.containsKey(p)) {
                namedValues.put(p, Optional.empty());
            }
        }
        return namedValues;
    }


    /**
     * Returns the unnamed token of the command, which is the part right after
     * command word and before the first prefix.
     *
     * @return the unnamed token of the command.
     */
    public Optional<String> getUnnamedValue() {
        return unnamedValue;
    }

    /**
     * Returns the named token of the command, which is the part right after
     * one prefix and before the next prefix.
     *
     * @return the named token of the command.
     */
    public Optional<Map<String, Optional<String>>> getNamedValues() {
        // so that the caller cannot modify the named token
        return namedValues.map(Map::copyOf);
    }

    /**
     * Gets the value of the named token with the given prefix.
     */
    public Optional<String> getValue(String prefix) {
        if (namedValues.isEmpty()) {
            return Optional.empty();
        }
        return namedValues.get().get(prefix);
    }

    /**
     * Gets the integer value of the named token with the given prefix.
     */
    public Optional<Integer> getIntValue(String prefix) {
        if (namedValues.isEmpty()) {
            return Optional.empty();
        }
        Optional<String> value = namedValues.get().get(prefix);
        if (value.isEmpty()) {
            return Optional.empty();
        }
        try {
            return Optional.of(Integer.parseInt(value.get()));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
