package trackr.model.commons;

/**
 * Enum representing all tabs.
 */
public enum TabEnum {
    HOME,
    ORDERS,
    TASKS,
    CONTACTS,
    MENU;

    public static int getTabIndex(String targetStr) {
        return TabEnum.valueOf(targetStr).ordinal();
    }
}
