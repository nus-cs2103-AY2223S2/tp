package seedu.vms.model.keyword;

public class Keyword {
    private final String keyword;
    private final String mainKeyword;

    public Keyword(String keyword, String mainKeyword) {
        this.keyword = keyword;
        this.mainKeyword = mainKeyword;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public String getMainKeyword() {
        return this.mainKeyword;
    }
}