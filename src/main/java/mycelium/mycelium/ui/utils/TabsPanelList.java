package mycelium.mycelium.ui.utils;

/**
 * TabsPanelList is a list of TabsPanel.
 * It allows the user to switch between the TabsPanels.
 */
public class TabsPanelList {
    private TabsPanel[] tabsPanels;
    private int focused;

    /**
     * Creates a TabsPanelList with the given TabsPanels.
     * The first TabsPanel is highlighted.
     *
     * @param tabsPanels The TabsPanels to be added to the TabsPanelList.
     */
    public TabsPanelList(TabsPanel... tabsPanels) {
        this.tabsPanels = tabsPanels;
        this.focused = 0;
        tabsPanels[focused].highlight();
    }

    public void nextItem() {
        tabsPanels[focused].nextItem();
    }

    public void prevItem() {
        tabsPanels[focused].prevItem();
    }

    public void nextTab() {
        tabsPanels[focused].nextTab();
    }

    /**
     * Switches to the next TabsPanel.
     * The current TabsPanel is unhighlighted.
     * The next TabsPanel is highlighted.
     */
    public void nextTabPanel() {
        tabsPanels[focused].unhighlight();
        assert tabsPanels.length > 0;
        focused = (focused + 1) % tabsPanels.length;
        tabsPanels[focused].highlight();
    }
}
