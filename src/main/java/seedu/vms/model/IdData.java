package seedu.vms.model;

import java.util.Objects;

/**
 * Represents an association of an ID with a value.
 *
 * @param <T> - the type of value stored.
 */
public class IdData<T> implements Comparable<IdData<T>> {
    private final boolean isActive;
    private final int id;
    private final T value;


    /**
     * Constructs an {@code IdData} with its active state set as {@code true}.
     *
     * @param id - the ID of this data.
     * @param value - the value of this data.
     */
    public IdData(int id, T value) {
        this(true, id, value);
    }


    /**
     * Constructs an {@code IdData}.
     *
     * @param isActive - if the data is active.
     * @param id - the ID of this data.
     * @param value - the value of this data.
     */
    public IdData(boolean isActive, int id, T value) {
        this.isActive = isActive;
        this.id = id;
        this.value = value;
    }


    /**
     * Returns if the data is active.
     *
     * @return {@code true} if the data is active and {@code false} otherwise.
     */
    public boolean isActive() {
        return isActive;
    }


    public int getId() {
        return id;
    }


    public T getValue() {
        return value;
    }


    @Override
    public int compareTo(IdData<T> other) {
        return id - other.id;
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof IdData)) {
            return false;
        }

        IdData<?> o = (IdData<?>) other;
        return this.isActive == o.isActive && this.id == o.id
                && this.value.equals(o.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isActive, id, value);
    }


    @Override
    public String toString() {
        return value.toString();
    }
}
