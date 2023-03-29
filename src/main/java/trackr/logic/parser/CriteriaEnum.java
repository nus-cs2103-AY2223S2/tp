package trackr.logic.parser;

/**
 * Enum representing sorting criteria.
 */
public enum CriteriaEnum {
    DATE_ADDED("Date added"),
    DEADLINE("Deadline"),
    STATUS("Status"),
    NAME("Name"),
    STATUS_AND_DEADLINE("Status and Deadline");

    public final static String MESSAGE_CONSTRAINTS = "Criteria given must be one of the types: "
            + "`Date added`, `Deadline`, `Status`, `Name`, `Status and Deadline` or blank";

    private final String strRepresentation;

    CriteriaEnum(String strRepresentation) {
        this.strRepresentation = strRepresentation;
    }

    @Override
    public String toString() {
        return strRepresentation;
    }

}
