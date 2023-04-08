package vimification.internal.parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Stores the maximum counts and the current counts of different flags when parsing a command.
 */
public class ArgumentCounter {

    private final Map<ArgumentFlag, Integer> maximumCounts;
    private final Map<ArgumentFlag, Integer> currentCounts;

    /**
     * Constructs a new {@code ArgumentCounter} instance with the maximum counts for different
     * flags.
     *
     * @param allowedFlags the allowed flags. An exception will be thrown if methods of this
     *        instance is called with flags that are not allowed
     */
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
     * Increases the count of the given flag by 1.
     *
     * @param flag the given flag
     * @throws ParserException if the given flag is not allowed, or if the count exceeds the limit
     */
    public void add(ArgumentFlag flag) {
        throwIfNotAllowed(flag);
        int count = currentCounts.merge(flag, 1, Integer::sum);
        if (flag instanceof ComposedArgumentFlag) {
            ComposedArgumentFlag composedFlag = (ComposedArgumentFlag) flag;
            currentCounts.merge(composedFlag.getActualFlag(), 1, Integer::sum);
        }
        if (count > maximumCounts.get(flag)) {
            throw new ParserException("Number of arguments for flag " + flag + " exceeded limit");
        }
    }

    /**
     * Gets the current count of the given flag.
     *
     * @param flag the given flag
     * @return the count of the given flag
     */
    public int get(ArgumentFlag flag) {
        throwIfNotAllowed(flag);
        return currentCounts.get(flag);
    }

    /**
     * Returns the total count of all flags.
     *
     * @return the total count of all flags
     */
    public int totalCount() {
        return currentCounts.values().stream().mapToInt(Integer::valueOf).sum();
    }

    @Override
    public String toString() {
        return "ArgumentCounter [maximumCounts=" + maximumCounts + ", currentCounts="
                + currentCounts + "]";
    }
}
