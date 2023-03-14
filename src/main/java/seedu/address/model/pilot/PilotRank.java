package seedu.address.model.pilot;

import seedu.address.model.pilot.exceptions.InvalidPilotRankException;

/**
 * Represents the rank of a pilot.
 */
public enum PilotRank {
    TRAINING_CAPTAIN,
    CAPTAIN,
    SENIOR_FIRST_OFFICER,
    FIRST_OFFICER,
    SECOND_OFFICER,
    CADET;

    public static final String TRAINING_CAPTAIN_STRING = "Training Captain";
    public static final String CAPTAIN_STRING = "Captain";
    public static final String SENIOR_FIRST_OFFICER_STRING = "Senior First Officer";
    public static final String FIRST_OFFICER_STRING = "First Officer";
    public static final String SECOND_OFFICER_STRING = "Second Officer";
    public static final String CADET_STRING = "Cadet";

    /**
     * Returns the string representation of the pilot rank.
     *
     * @return the string representation of the pilot rank
     */
    public String toString() {
        switch (this) {
        case TRAINING_CAPTAIN:
            return TRAINING_CAPTAIN_STRING;
        case CAPTAIN:
            return CAPTAIN_STRING;
        case SENIOR_FIRST_OFFICER:
            return SENIOR_FIRST_OFFICER_STRING;
        case FIRST_OFFICER:
            return FIRST_OFFICER_STRING;
        case SECOND_OFFICER:
            return SECOND_OFFICER_STRING;
        case CADET:
            return CADET_STRING;
        default:
            throw new InvalidPilotRankException(this);
        }
    }

    /**
     * Returns the pilot rank from the string representation.
     *
     * @param rank the string representation of the pilot rank
     * @return the pilot rank
     */
    public static PilotRank fromString(String rank) {
        switch (rank) {
        case TRAINING_CAPTAIN_STRING:
            return TRAINING_CAPTAIN;
        case CAPTAIN_STRING:
            return CAPTAIN;
        case SENIOR_FIRST_OFFICER_STRING:
            return SENIOR_FIRST_OFFICER;
        case FIRST_OFFICER_STRING:
            return FIRST_OFFICER;
        case SECOND_OFFICER_STRING:
            return SECOND_OFFICER;
        case CADET_STRING:
            return CADET;
        default:
            throw new InvalidPilotRankException(rank);
        }
    }

    /**
     * Returns the index of the pilot rank in the enum.
     *
     * @return the index of the pilot rank in the enum
     */
    public int toIndex() {
        switch (this) {
        case TRAINING_CAPTAIN:
            return 0;
        case CAPTAIN:
            return 1;
        case SENIOR_FIRST_OFFICER:
            return 2;
        case FIRST_OFFICER:
            return 3;
        case SECOND_OFFICER:
            return 4;
        case CADET:
            return 5;
        default:
            throw new InvalidPilotRankException(this);
        }
    }

    /**
     * Returns the pilot rank from the index.
     *
     * @param index the index of the pilot rank in the enum
     * @return the pilot rank
     */
    public static PilotRank fromIndex(int index) {
        switch (index) {
        case 0:
            return TRAINING_CAPTAIN;
        case 1:
            return CAPTAIN;
        case 2:
            return SENIOR_FIRST_OFFICER;
        case 3:
            return FIRST_OFFICER;
        case 4:
            return SECOND_OFFICER;
        case 5:
            return CADET;
        default:
            throw new InvalidPilotRankException(index);
        }
    }
}
