package wingman.model.crew;

import wingman.model.crew.exceptions.InvalidCrewRankException;
import wingman.model.pilot.exceptions.InvalidPilotRankException;

/**
 * Represents the rank of a crew.
 */
public enum CrewRank {
    CABIN_SERVICE_DIRECTOR,
    SENIOR_FLIGHT_ATTENDANT,
    FLIGHT_ATTENDANT,
    TRAINEE;

    public static final String CABIN_SERVICE_DIRECTOR_STRING = "Cabin Service Director";
    public static final String SENIOR_FLIGHT_ATTENDANT_STRING = "Senior Flight Attendant";
    public static final String FLIGHT_ATTENDANT_STRING = "Flight Attendant";
    public static final String TRAINEE_STRING = "Trainee";

    /**
     * Returns the string representation of the crew rank.
     *
     * @return the string representation of the crew rank
     */
    public String toString() {
        switch (this) {
        case CABIN_SERVICE_DIRECTOR:
            return CABIN_SERVICE_DIRECTOR_STRING;
        case SENIOR_FLIGHT_ATTENDANT:
            return SENIOR_FLIGHT_ATTENDANT_STRING;
        case FLIGHT_ATTENDANT:
            return FLIGHT_ATTENDANT_STRING;
        case TRAINEE:
            return TRAINEE_STRING;
        default:
            throw new InvalidPilotRankException(this);
        }
    }

    /**
     * Returns the crew rank from the string representation.
     *
     * @param rank the string representation of the crew rank
     * @return the crew rank
     */
    public static CrewRank fromString(String rank) {
        switch (rank) {
        case CABIN_SERVICE_DIRECTOR_STRING:
            return CABIN_SERVICE_DIRECTOR;
        case SENIOR_FLIGHT_ATTENDANT_STRING:
            return SENIOR_FLIGHT_ATTENDANT;
        case FLIGHT_ATTENDANT_STRING:
            return FLIGHT_ATTENDANT;
        case TRAINEE_STRING:
            return TRAINEE;
        default:
            throw new InvalidCrewRankException(rank);
        }
    }

    /**
     * Returns the index of the crew rank in the enum.
     *
     * @return the index of the crew rank in the enum
     */
    public int toIndex() {
        switch (this) {
        case CABIN_SERVICE_DIRECTOR:
            return 0;
        case SENIOR_FLIGHT_ATTENDANT:
            return 1;
        case FLIGHT_ATTENDANT:
            return 2;
        case TRAINEE:
            return 3;
        default:
            throw new InvalidCrewRankException(this);
        }
    }

    /**
     * Returns the crew rank from the index.
     *
     * @param index the index of the crew rank in the enum
     * @return the crew rank
     */
    public static CrewRank fromIndex(int index) {
        switch (index) {
        case 0:
            return CABIN_SERVICE_DIRECTOR;
        case 1:
            return SENIOR_FLIGHT_ATTENDANT;
        case 2:
            return FLIGHT_ATTENDANT;
        case 3:
            return TRAINEE;
        default:
            throw new InvalidCrewRankException(index);
        }
    }
}
