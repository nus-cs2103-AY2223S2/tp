package trackr.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Observable value of selected tab that changes upon tab commands.
 */
public class ObservableTabIndex {
    private static final SimpleIntegerProperty targetTabIndex = new SimpleIntegerProperty();

    public static final IntegerProperty valueProperty() {
        return targetTabIndex;
    }

    public static final void updateToTab(int target) {
        targetTabIndex.set(target);
    }

    public static final int getTargetTab() {
        return targetTabIndex.getValue();
    }

    public static final int getTargetTab() {
        return targetTabIndex.getValue();
    }

}
