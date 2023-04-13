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
        if (tabsPanels.length == 0) {
            return;
        }
        assert tabsPanels.length > 0;
        focused = (focused + 1) % tabsPanels.length;
        tabsPanels[focused].highlight();
    }

    /**
     * Switches to the TabsPanel of a given index.
     * The current TabsPanel is unhighlighted.
     * The TabsPanel of the given index is highlighted.
     * If the index is out of bounds, nothing happens.
     *
     * @param index The index of the TabsPanel to be focused.
     */
    public void focusTabPanel(int index) {
        if (index < 0 || index >= tabsPanels.length) {
            return;
        }
        tabsPanels[focused].unhighlight();
        focused = index;
        tabsPanels[focused].highlight();
    }

    /**
     * Returns the TabsPanel of a given index.
     *
     * @param index The index of the TabsPanel to be returned.
     * @return The TabsPanel of the given index. If the index is out of bounds, null is returned.
     */
    public TabsPanel get(int index) {
        if (index < 0 || index >= tabsPanels.length) {
            return null;
        }
        return tabsPanels[index];
    }
}
