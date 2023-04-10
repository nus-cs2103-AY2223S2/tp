package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Stores mapping of prefixes to their respective arguments.
 * Each key may be associated with multiple argument values.
 * Values for a given key are stored in a list, and the insertion ordering is maintained.
 * Keys are unique, but the list of argument values may contain duplicate argument values, i.e. the same argument value
 * can be inserted multiple times for the same prefix.
 */
public class ArgumentMultimap {

    /**
     * Prefixes mapped to their respective arguments
     **/
    private final Map<Prefix, List<String>> argMultimap = new LinkedHashMap<>();

    /**
     * Associates the specified argument value with {@code prefix} key in this map.
     * If the map previously contained a mapping for the key, the new value is appended to the list of existing values.
     *
     * @param prefix   Prefix key with which the specified argument value is to be associated.
     * @param argValue Argument value to be associated with the specified prefix key.
     */
    public void put(Prefix prefix, String argValue) {
        List<String> argValues = getAllEntries(prefix);
        argValues.add(argValue);
        argMultimap.put(prefix, argValues);
    }

    /**
     * Returns the last value of {@code prefix}.
     *
     * @param prefix Prefix of the value.
     * @return Last value of {@code prefix}.
     */
    public Optional<String> getValue(Prefix prefix) {
        List<String> values = getAllEntries(prefix);
        return values.isEmpty() ? Optional.empty() : Optional.of(values.get(values.size() - 1));
    }

    /**
     * Returns all values of {@code prefix}.
     * If the prefix does not exist or has no values, this will return an empty list.
     * Modifying the returned list will not affect the underlying data structure of the ArgumentMultimap.
     *
     * @param prefix Prefix of the values.
     * @return All values of {@code prefix}.
     */
    public List<String> getAllEntries(Prefix prefix) {
        if (!argMultimap.containsKey(prefix)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(argMultimap.get(prefix));
    }

    /**
     * Returns the list of values in the argMultiMap.
     *
     * @return All values inside the argMultiMap
     */
    public Set<Map.Entry<Prefix, List<String>>> getAllEntries() {
        return argMultimap.entrySet();
    }

    /**
     * Returns the preamble (text before the first valid prefix). Trims any leading/trailing spaces.
     *
     * @return Trimmed preamble.
     */
    public String getPreamble() {
        return getValue(new Prefix("")).orElse("");
    }

    @Override
    public String toString() {
        return "ArgumentMultimap"
                + argMultimap;
    }
}
