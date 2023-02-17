package seedu.address.ui;

import com.calendarfx.view.DetailedDayView;

import javafx.fxml.FXML;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;

/**
 * A UI component that displays tabbed details.
 */
public class DetailPanel extends UiPart<Region> {
    private static final String FXML = "DetailPanel.fxml";

    /**
     * An enum to identify tabs.
     */
    public enum TabType {
        CONTACT_DETAILS,
        CALENDAR
    }

    @FXML
    private TabPane detailTabs;

    private final PersonDetailPanel personDetailPanel;
    private final Tab personTab;
    private final Tab calendarTab;

    /**
     * Creates a blank {@code DetailPanel}.
     */
    public DetailPanel() {
        super(FXML);

        personDetailPanel = new PersonDetailPanel();

        personTab = new Tab();
        personTab.setText("Contact");
        personTab.setContent(personDetailPanel.getRoot());
        calendarTab = new Tab();
        calendarTab.setText("Calendar");
        calendarTab.setContent(new DetailedDayView());
        detailTabs.getTabs().addAll(personTab, calendarTab);
    }

    public PersonDetailPanel getPersonDetailPanel() {
        return personDetailPanel;
    }

    /**
     * Switches to the specified tab.
     *
     * @param tabType Identifier for the tabs.
     */
    public void selectTab(TabType tabType) {
        SingleSelectionModel<Tab> selectionModel = detailTabs.getSelectionModel();
        switch (tabType) {
        case CONTACT_DETAILS:
            selectionModel.select(personTab);
            break;
        case CALENDAR:
            selectionModel.select(calendarTab);
            break;
        default:
        }
    }
}
