package vimification.internal.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

/**
 * Stores mapping of flags to their respective arguments. Each flag may be associated with multiple
 * argument values. Values for a given flag are stored in a set, and the insertion ordering may be
 * maintained.
 */
public class ArgumentCounter {

    private final Set<ArgumentFlag> allowedFlags;

    /**
     * Flags mapped to their respective arguments.
     **/
    private final Map<ArgumentFlag, Integer> args;

    public ArgumentCounter(ArgumentFlag... allowedFlags) {
        this.allowedFlags = Set.of(allowedFlags);
        this.args = new HashMap<>();
    }

    private void throwIfNotAllowed(ArgumentFlag flag) {
        if (!allowedFlags.contains(flag)) {
            throw new ParserException("Invalid flag for this command");
        }
    }


    /**
     * Associates the specified argument value with {@code prefix} key in this map. If the map
     * previously contained a mapping for the key, the new value is appended to the list of existing
     * values.
     *
     * @param prefix Prefix key with which the specified argument value is to be associated
     * @param argValue Argument value to be associated with the specified prefix key
     */
    public void add(ArgumentFlag flag) {
        throwIfNotAllowed(flag);
        long count = args.merge(flag, 1, Integer::sum);
        if (count > flag.getMaxCount()) {
            throw new ParserException("Number of argument exceeded limit");
        }
    }

    public Set<String> get(ArgumentFlag flag) {
        throwIfNotAllowed(flag);
        // Set<String> result = args.get(flag);
        // return result == null ? Set.of() : result;
        return null;
    }

    public Set<String> remove(ArgumentFlag flag) {
        throwIfNotAllowed(flag);
        // Set<String> result = args.remove(flag);
        // return result == null ? Set.of() : result;
        return null;
    }

    // public Optional<String> getFirst(ArgumentFlag flag) {
    // throwIfNotAllowed(flag);
    // return args.getOrDefault(flag, Set.of())
    // .stream()
    // .findFirst();
    // }
}
