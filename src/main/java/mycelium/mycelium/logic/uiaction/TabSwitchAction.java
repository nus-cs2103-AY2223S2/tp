package mycelium.mycelium.logic.uiaction;

import java.util.Objects;

import mycelium.mycelium.ui.MainWindow;

/**
 * Represents a UI action that switches the tab.
 */
public class TabSwitchAction extends UiAction {
    private TabSwitch tabSwitch;

    /**
     * Creates a new TabSwitchAction.
     *
     * @param tabSwitch The tab to switch to.
     */
    public TabSwitchAction(TabSwitch tabSwitch) {
        this.tabSwitch = tabSwitch;
    }

    @Override
    public void execute(MainWindow mainWindow) {
        logger.info("Switching to tab " + tabSwitch.name());
        switch (tabSwitch) {
        case PROJECT:
            mainWindow.selectProjectTab();
            break;
        case CLIENT:
            mainWindow.selectClientTab();
            break;
        default:
            // Do Nothing
            break;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TabSwitchAction)) {
            return false;
        }

        TabSwitchAction otherTabSwitchAction = (TabSwitchAction) other;
        return otherTabSwitchAction.tabSwitch.equals(this.tabSwitch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tabSwitch);
    }

    /**
     * Represents the tab to switch to.
     */
    public enum TabSwitch {
        PROJECT, CLIENT
    }
}
