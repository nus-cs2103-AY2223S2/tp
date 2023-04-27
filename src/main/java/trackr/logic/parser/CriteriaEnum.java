package trackr.logic.parser;

/**
 * Enum representing sorting criteria.
 */
public enum CriteriaEnum {
    TIME_ADDED("Time added"),
    DEADLINE("Deadline"),
    STATUS("Status"),
    NAME("Name"),
    STATUS_AND_DEADLINE("Status and Deadline");

    private final String strRepresentation;

    CriteriaEnum(String strRepresentation) {
        this.strRepresentation = strRepresentation;
    }

    @Override
    public String toString() {
        return strRepresentation;
    }

}
