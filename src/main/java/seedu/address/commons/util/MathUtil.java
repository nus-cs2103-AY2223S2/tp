package seedu.address.commons.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.util.Pair;

/**
 * Composes of all the Math Utilities needed.
 */
public class MathUtil {

    /**
     * Returns a Cartesian Product of 2 lists A X B
     */
    public static <T> List<List<T>> getCartesianProduct(List<T> objects, List<T> objects1) {
        return objects.stream()
            .flatMap(a_i -> objects1.stream().map(b_i -> List.of(a_i, b_i)))
            .collect(Collectors.toList());
    }

    /**
     * Assign an index to an object.
     */
    public static <T> List<Pair<Integer, T>> indexObjects(List<T> objects) {
        int size = objects.size();
        return IntStream.range(0, size)
            .mapToObj(num -> new Pair<Integer, T>(num, objects.get(num)))
            .collect(Collectors.toList());
    }
}
