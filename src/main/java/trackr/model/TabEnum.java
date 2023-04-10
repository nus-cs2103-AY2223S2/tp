package trackr.model;

//@@author arkarsg
/**
 * Enum representing all tabs.
 */
public enum TabEnum {
    HOME,
    ORDERS,
    TASKS,
    CONTACTS,
    MENU,
    OTHERS;

    public static int getTabIndex(String targetStr) {
        return TabEnum.valueOf(targetStr).ordinal();
    }
}
