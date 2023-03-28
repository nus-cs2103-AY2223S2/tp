package mycelium.mycelium.ui.utils;

/**
 * TabsPanel is an interface for a panel that contains tabs.
 * It is used to switch between the tabs within a panel.
 * It also allows the panel to be highlighted and unhighlighted.
 * This is used to show which panel is in focus.
 */
public interface TabsPanel {
    static final String HIGHLIGHTED_STYLE_CLASS = "highlighted";
    /**
     * Switches to the next tab.
     */
    void nextItem();

    /**
     * Switches to the previous tab.
     */
    void prevItem();

    /**
     * Switches to the next tab.
     */
    void nextTab();

    /**
     * Highlight the TabPanel to show it is in focus
     */
    void highlight();

    /**
     * Unhighlight the TabPanel to show it is not in focus
     */
    void unhighlight();
}
