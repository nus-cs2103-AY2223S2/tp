package vimification.internal.parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Stores mapping of flags to their respective arguments. Each flag may be associated with multiple
 * argument values. Values for a given flag are stored in a set, and the insertion ordering may be
 * maintained.
 */
public class ArgumentCounter {

    /**
     * Flags mapped to their respective arguments.
     **/
    private final Map<ArgumentFlag, Integer> maximumCounts;
    private final Map<ArgumentFlag, Integer> currentCounts;

    @SafeVarargs
    public ArgumentCounter(Pair<ArgumentFlag, Integer>... allowedFlags) {
        this.maximumCounts = Arrays
                .stream(allowedFlags)
                .collect(Collectors.toMap(Pair::getFirst,
                        Pair::getSecond, Integer::sum, HashMap::new));
        this.currentCounts = new HashMap<>();
    }

    private void throwIfNotAllowed(ArgumentFlag flag) {
        if (!maximumCounts.containsKey(flag)) {
            throw new ParserException("Invalid flag " + flag);
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
        int count = currentCounts.merge(flag, 1, Integer::sum);
        if (flag instanceof ComposedArgumentFlag) {
            ComposedArgumentFlag composedFlag = (ComposedArgumentFlag) flag;
            currentCounts.merge(composedFlag.getActualFlag(), 1, Integer::sum);
        }
        if (count < maximumCounts.get(flag)) {
            throw new ParserException("Number of arguments for flag " + flag + " exceeded limit");
        }
    }

    public int get(ArgumentFlag flag) {
        throwIfNotAllowed(flag);
        return currentCounts.get(flag);
    }

    public boolean isEmpty() {
        return currentCounts.isEmpty();
    }
}
