package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Utility methods related to Collections
 */
public class CollectionUtil {

    /** @see #requireAllNonNull(Collection) */
    public static void requireAllNonNull(Object... items) {
        requireNonNull(items);
        Stream.of(items).forEach(Objects::requireNonNull);
    }

    /**
     * Throws NullPointerException if {@code items} or any element of {@code items} is null.
     */
    public static void requireAllNonNull(Collection<?> items) {
        requireNonNull(items);
        items.forEach(Objects::requireNonNull);
    }

    /**
     * Returns true if {@code items} contain any elements that are non-null.
     */
    public static boolean isAnyNonNull(Object... items) {
        return items != null && Arrays.stream(items).anyMatch(Objects::nonNull);
    }

    /**
     * Zips two streams together with a BiFunction.
     * Solution adapted from
     * "<a href="https://stackoverflow.com/questions
     * /17640754/zipping-streams-using-jdk8-with-lambda
     * -java-util-stream-streams-zip">Stack Overflow</a>".
     *
     * Apparently, there's no raw Java that can zip streams together.
     * If you can find alternatives, you can just take this out.
     */
    public static <T, U, R> Stream<R> zip(Stream<T> firstStream, Stream<U> secondStream, BiFunction<T, U, R> zipper) {
        final Iterator<T> firstIterator = firstStream.iterator();
        final Iterator<U> secondIterator = secondStream.iterator();
        final Iterator<R> outputIterator = new Iterator<>() {
            @Override
            public boolean hasNext() {
                return firstIterator.hasNext() && secondIterator.hasNext();
            }

            @Override
            public R next() {
                return zipper.apply(firstIterator.next(), secondIterator.next());
            }
        };

        final boolean isParallel = firstStream.isParallel() || secondStream.isParallel();
        final Iterable<R> iterable = () -> outputIterator;
        return StreamSupport.stream(iterable.spliterator(), isParallel);
    }
}
