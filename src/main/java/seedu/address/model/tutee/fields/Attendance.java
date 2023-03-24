package seedu.address.model.tutee.fields;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Immutable field representing the dates where the tutee was present
 */
public class Attendance implements Iterable<LocalDate> {
    private final Set<LocalDate> attendances;

    public Attendance(Set<LocalDate> attendances) {
        this.attendances = new HashSet<>(attendances);
    }

    public Attendance() {
        this(new HashSet<>());
    }

    /**
     * Mark the tutee as present on the given date.
     * @param date Date of the tutee's presence
     * @return A new Attendance instance with the new date marked
     */
    public Attendance markAttendance(LocalDate date) {
        Set<LocalDate> copy = new HashSet<>(attendances);
        copy.add(date);
        return new Attendance(copy);
    }

    /**
     * Mark the tutee as absent on the given date.
     * @param date Date of the tutee's absence
     * @return A new Attendance instance with the new date marked
     * @throws NoSuchElementException If the student not present on the
     *     given date, marking them as absent again will throw a {@link NoSuchElementException}
     */
    public Attendance unmarkAttendance(LocalDate date) {
        Set<LocalDate> copy = new HashSet<>(attendances);
        if (!copy.remove(date)) {
            throw new NoSuchElementException();
        }

        return new Attendance(copy);
    }

    /**
     * Check whether the tutee was present or absent on the given date
     * @param date
     * @return True if the tutee was present, false otherwise
     */
    public boolean didAttend(LocalDate date) {
        return attendances.contains(date);
    }

    @Override
    public String toString() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");

        return attendances.stream()
            .map(formatter::format)
            .collect(Collectors.joining(",", "{", "}"));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Attendance)) {
            return false;
        }

        Attendance o = (Attendance) other;
        return o.attendances.equals(this.attendances);
    }

    @Override
    public int hashCode() {
        return attendances.hashCode();
    }

    @Override
    public Iterator<LocalDate> iterator() {
        return attendances.iterator();
    }

    public Stream<LocalDate> stream() {
        return attendances.stream();
    }
}
