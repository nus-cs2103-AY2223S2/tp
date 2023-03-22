package seedu.internship.model.event;


import java.util.Objects;
import seedu.internship.model.internship.Internship;

/**
 * Represents a Event in the internship.
 */
public class Event  {
    private final Name name;
    private final Start start;
    private final End end;
    private final Description description;
    private Internship internship;

    /**
     * Every Field must be present and not null.
     */
    public Event (Name name, Start start,End end ,Description descirption, Internship internship) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.description = descirption;
        this.internship = internship;
    }

    /**
     * Every Field must be present and not null., without Internship.
     */
    public Event (Name name, Start start,End end ,Description descirption) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.description = descirption;
    }

    /**
     * The class still is immutable , once internship is defined , it cannot be changed
     * @param intern
     */
    public void setInternship(Internship intern){
        if (this.internship == null) {
            this.internship = intern;
        }
    }

    public Name getName() {
        return name;
    }
    public Start getStart() {
        return start;
    }

    public End getEnd() {
        return end;
    }

    public Internship getInternship() {
        return internship;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns True if both events have the same start, end and internship
     */
    public boolean isSameInternship(Internship internship) {
        return internship != null
                && this.internship.equals(internship);
    }

    /**
     * Returns True if both events have the same start, end and internship
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this){
            return true;
        }

        return otherEvent != null
                && otherEvent.getStart().equals(getStart())
                && otherEvent.getEnd().equals(getEnd())
                && otherEvent.getInternship().equals(getInternship());
    }

    /**
     * Returns true if both events have the same identity and data fields.
     * This defines a stronger notion of equality between two equals.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Internship)) {
            return false;
        }

        Event otherEvent = (Event) other;

        return otherEvent.getStart().equals(getStart())
                && otherEvent.getEnd().equals(getEnd())
                && otherEvent.getInternship().equals(getInternship())
                && otherEvent.getDescription().equals(getDescription())
                && otherEvent.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(start, end, internship,description);
    }

    public boolean isDeadline(){
        return this.start.compareTo(end) == 0;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ")
                .append(getName())
                .append("; start: ")
                .append(getStart())
                .append("; end: ")
                .append(getEnd())
                .append("; Description: ")
                .append(getDescription())
                .append("; Internship: ")
                .append(getInternship());
        return builder.toString();
    }





}
