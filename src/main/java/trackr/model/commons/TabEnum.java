package trackr.model.commons;

/**
 * Enum representing all tabs.
 */
public enum TabEnum {
    HOME,
    ORDERS,
    TASK,
    CONTACT,
    MENU;

    public static int getTabIndex(String targetStr) {
        return TabEnum.valueOf(targetStr).ordinal();
    }
}
