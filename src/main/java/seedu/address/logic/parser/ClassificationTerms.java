package seedu.address.logic.parser;

/**
 * Enum of all possible classifications of entities.
 */
public enum ClassificationTerms {
    //For future use if ever needed
    CHAR("char"),
    ITEM("item"),
    MOB("mob");

    public final String label;
    private ClassificationTerms(String label) {
        this.label = label;
    }
}
