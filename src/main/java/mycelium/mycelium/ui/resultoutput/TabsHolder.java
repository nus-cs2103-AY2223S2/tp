package mycelium.mycelium.ui.resultoutput;

import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import mycelium.mycelium.ui.UiPart;

/**
 * The ui for the holder of all the tabs.
 */
public class TabsHolder extends UiPart<TabPane> {
    private static final String FXML = "TabsHolder.fxml";
    private ObservableList<Tab> tabs;

    /**
     * Initialises a {@code TabsHolder} with a given set of tabs.
     * @param tabs Tabs to be added to the holder
     */
    public TabsHolder(TabPage... tabs) {
        super(FXML);
        this.tabs = getRoot().getTabs();
        for (TabPage tab : tabs) {
            this.tabs.add(tab.getRoot());
        }
    }
}
