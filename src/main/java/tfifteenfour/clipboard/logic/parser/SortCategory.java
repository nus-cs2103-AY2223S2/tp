package tfifteenfour.clipboard.logic.parser;

import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;

enum SortCategory {
    SORT_BY_NAME("name"),
    SORT_BY_STUDENT_ID("id");

    private String category;

    private SortCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

    public static SortCategory fromString(String category) throws ParseException {
        for (SortCategory sc : SortCategory.values()) {
            if (sc.getCategory().equalsIgnoreCase(category)) {
                return sc;
            }
        }

        throw new ParseException("Unrecognised category for sort: " + category);
    }
}
