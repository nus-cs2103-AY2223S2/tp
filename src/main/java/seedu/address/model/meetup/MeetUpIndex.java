package seedu.address.model.meetup;

import java.util.Objects;

import seedu.address.model.meetup.exceptions.InvalidMeetUpIndexException;

/**
 * Represents the index of the meet up in EduMate
 */
public class MeetUpIndex implements Comparable<MeetUpIndex> {

    private final int meetUpIndex;

    /**
     * Creates a new index for the specific meet up.
     * @param zeroBasedIndex index of the meet up.
     */
    public MeetUpIndex(int zeroBasedIndex) {
        if (zeroBasedIndex < 0) {
            throw new InvalidMeetUpIndexException();
        }
        this.meetUpIndex = zeroBasedIndex;
    }

    public int getMeetUpIndex() {
        return meetUpIndex;
    }

    public int getValue() {
        return meetUpIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetUpIndex);
    }

    @Override
    public String toString() {
        return String.valueOf(meetUpIndex);
    }

    @Override
    public int compareTo(MeetUpIndex other) {
        return this.getMeetUpIndex() - other.getMeetUpIndex();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        MeetUpIndex that = (MeetUpIndex) other;
        return meetUpIndex == that.getMeetUpIndex();
    }

}
