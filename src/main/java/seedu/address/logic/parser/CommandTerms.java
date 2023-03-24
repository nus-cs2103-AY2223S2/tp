package seedu.address.logic.parser;

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
