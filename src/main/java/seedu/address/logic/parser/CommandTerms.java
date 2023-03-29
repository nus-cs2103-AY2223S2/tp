package seedu.address.logic.parser;

/**
 * Enum of all possible commands that could be made.
 */
public enum CommandTerms {
    MAKE("make"),
    EDIT("edit"),
    DELETE("delete"),
    FIND("find");

    public final String keyword;
    private CommandTerms(String keyword) {
        this.keyword = keyword;
    }
}
