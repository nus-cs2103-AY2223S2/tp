package seedu.internship.model.internship;

import java.util.Objects;

/**
 * A single point of data, with a name and an integer value.
 */
public class Datapoint {

    private String name;
    private int value;

    /**
     * Creates a Datapoint instance.
     * @param name Name of Datapoint.
     * @param value Integer value of Datapoint.
     */
    public Datapoint(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Creates a Datapoint instance with default value of 0.
     * @param name Name of Datapoint.
     */
    public Datapoint(String name) {
        this(name, 0);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public String getNameValue() {
        return name + " " + value;
    }

    /**
     * Increases value of Datapoint by given amount.
     * @param amount Integer amount to increase value by.
     */
    public void incrementValue(int amount) {
        value += amount;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Datapoint)) {
            return false;
        }

        Datapoint otherDatapoint = (Datapoint) other;

        return name.equals(otherDatapoint.getName())
                && value == otherDatapoint.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
