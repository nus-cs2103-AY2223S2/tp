package seedu.address.logic.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import seedu.address.commons.models.Pair;

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
    public static CommandParam from(String[] tokens,
                                    Optional<Map<String, String>> prefixes) {
        // special cases
        if (tokens.length == 0) {
            return new CommandParam(Optional.empty(),
                    Optional.empty());
        }
        if (prefixes.isEmpty()) {
            return new CommandParam(
                    Optional.of(String.join(" ", tokens)),
                    Optional.empty());
        }
        // set up
        Map<String, String> prefixMap = prefixes.get();
        final StringBuilder builder = new StringBuilder();
        // handle the unnamed token
        Pair<Optional<String>, Integer> unnamedValuePair =
                getUnnamedValue(tokens, prefixMap);
        Optional<String> unnamedValue = unnamedValuePair.getFirst();
        int start = unnamedValuePair.getSecond();
        // handle the named tokens
        Map<String, Optional<String>> namedValues =
                getNamedValues(tokens, prefixMap, start);
        return new CommandParam(unnamedValue, Optional.of(namedValues));
    }

    private static Pair<Optional<String>, Integer>
    getUnnamedValue(String[] tokens, Map<String, String> prefixMap) {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tokens.length; i++) {
            if (prefixMap.containsKey(tokens[i])) {
                return new Pair<>(Optional.of(builder.toString().trim()), i);
            }
            builder.append(tokens[i]).append(" ");
        }
        return new Pair<>(Optional.empty(), tokens.length);
    }

    private static Map<String, Optional<String>>
    getNamedValues(String[] tokens, Map<String, String> prefixMap, int start) {
        final StringBuilder builder = new StringBuilder();
        Map<String, Optional<String>> namedValues = new HashMap<>();
        for (int i = start; i < tokens.length; i++) {
            if (!prefixMap.containsKey(tokens[i])) {
                continue;
            }
            String prefix = prefixMap.get(tokens[i]);
            if (i + 1 >= tokens.length || prefixMap.containsKey(tokens[i + 1])) {
                namedValues.put(prefix, Optional.of(builder.toString().trim()));
                builder.setLength(0);
                break;
            }
            builder.append(tokens[i + 1]).append(" ");
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
}
