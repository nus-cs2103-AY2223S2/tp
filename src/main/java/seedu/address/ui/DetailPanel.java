package seedu.address.ui;

import com.calendarfx.view.DetailedDayView;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;

/**
 * A UI component that displays tabbed details.
 */
public class DetailPanel extends UiPart<Region> {
    private static final String FXML = "DetailPanel.fxml";

    @FXML
    private TabPane detailTabs;

    private final PersonDetailPanel personDetailPanel;

    /**
     * Creates a blank {@code DetailPanel}.
     */
    public DetailPanel() {
        super(FXML);

        personDetailPanel = new PersonDetailPanel();

        Tab personTab = new Tab();
        personTab.setText("Contact");
        personTab.setContent(personDetailPanel.getRoot());
        Tab calendarTab = new Tab();
        calendarTab.setText("Calendar");
        calendarTab.setContent(new DetailedDayView());
        detailTabs.getTabs().addAll(personTab, calendarTab);
    }

    public PersonDetailPanel getPersonDetailPanel() {
        return personDetailPanel;
    }
}
