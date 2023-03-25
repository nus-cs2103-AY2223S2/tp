package seedu.address.model.util;

import javafx.util.Pair;
import seedu.address.model.scheduler.time.TimePeriod;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MathUtil {
    public static <T> List<List<T>> getCartesianProduct(List<T> objects, List<T> objects1) {
        return objects.stream()
            .flatMap(a_i -> objects1.stream().map(b_i -> List.of(a_i, b_i)))
            .collect(Collectors.toList());
    }

    public static <T> List<Pair<Integer, T>> indexObjects(List<T> objects) {
        int size = objects.size();
        return IntStream.range(0, size)
            .mapToObj(num -> new Pair<Integer, T>(num, objects.get(num)))
            .collect(Collectors.toList());
    }
}
