package seedu.address.model.tutee.fields;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Immutable field representing the lessons where the tutee was present
 */
public class Lesson implements Iterable<String> {
    private final Set<String> lessons;

    public Lesson(Set<String> lessons) {
        this.lessons = lessons;
    }

    public Lesson() {
        this.lessons = new HashSet<>();
        lessons.add("Meet Tutor");
    }

    /**
     * Add new lesson to the lesson list of the tutee.
     * @param lesson Lesson the tutee learned.
     * @return A new Lesson list including the new lesson.
     */
    public Lesson learn(String lesson) {
        lessons.add(lesson);
        return this;
    }

    /**
     * Remove lesson to the lesson list of the tutee.
     * @param lesson Lesson the tutee learned.
     * @return A new Lesson list not including the new lesson.
     * @throws NoSuchElementException If the lesson have not been learned
     *     remove it will throw a {@link NoSuchElementException}
     */
    public Lesson unlearn(String lesson) {
        if (!lessons.remove(lesson)) {
            throw new NoSuchElementException();
        }

        return this;
    }

    /**
     * Check whether the tutee learn this lesson
     * @param lesson
     * @return True if the tutee learned, false otherwise
     */
    public boolean didAttend(String lesson) {
        return lessons.contains(lesson);
    }

    @Override
    public String toString() {

        return lessons.stream()
            .collect(Collectors.joining(",", "{", "}"));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Lesson)) {
            return false;
        }

        Lesson o = (Lesson) other;
        return o.lessons.equals(this.lessons);
    }

    @Override
    public int hashCode() {
        return lessons.hashCode();
    }

    @Override
    public Iterator<String> iterator() {
        return lessons.iterator();
    }

    public List<String> list() {
        return new ArrayList<>(lessons);
    }

    public Stream<String> stream() {
        return lessons.stream();
    }
}
