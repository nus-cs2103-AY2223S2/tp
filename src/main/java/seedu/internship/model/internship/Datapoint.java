package seedu.internship.model.internship;

import java.util.Objects;

public class Datapoint {

    private String name;
    private int value;

    public Datapoint(String name, int value) {
        this.name = name;
        this.value = value;
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
