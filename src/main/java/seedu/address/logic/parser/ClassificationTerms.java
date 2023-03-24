package seedu.address.logic.parser;

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
