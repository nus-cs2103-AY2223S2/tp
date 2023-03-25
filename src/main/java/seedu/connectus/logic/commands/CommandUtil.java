package seedu.connectus.logic.commands;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.connectus.commons.core.index.Index;

/**
 * Contains utility methods used for parsing strings in the various *Command
 * classes.
 */
public class CommandUtil {
    /**
     * Checks if given index is valid on a list.
     */
    public static boolean isIndexValid(Index index, List<?> list) {
        return index.getZeroBased() < list.size();
    }

    /**
     * Converts a set to a list.
     */
    public static <T> List<T> convertSetToList(Set<T> set) {
        return set.stream()
            .sorted().collect(Collectors.toList());
    }
}
