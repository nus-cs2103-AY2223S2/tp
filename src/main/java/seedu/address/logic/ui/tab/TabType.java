package seedu.address.logic.ui.tab;

/**
 * Defines the unique types of UI tabs.
 */
public enum TabType {
    ADDRESS_BOOK("Address Book"),
    CALENDAR("Calendar");

    private final String title;

    TabType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static TabType[] getAll() {
        return new TabType[]{ADDRESS_BOOK, CALENDAR};
    }
}
