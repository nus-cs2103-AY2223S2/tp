package seedu.address.ui.body;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import seedu.address.logic.Logic;
import seedu.address.logic.ui.tab.TabInfo;
import seedu.address.ui.UiPart;
import seedu.address.ui.body.address.AddressPanel;
import seedu.address.ui.body.calendar.CalendarPanel;

/**
 * A UI component representing the body section of the app with tabs.
 */
public class BodyPanel extends UiPart<Region> {
    private static final String FXML = "body/BodyPanel.fxml";

    @FXML
    private TabPane bodyTabs;

    private final Logic logic;

    private final AddressPanel addressPanel;
    private final CalendarPanel calendarPanel;

    /**
     * Creates a {@code BodyPanel} with the given {@code Logic}.
     */
    public BodyPanel(Logic logic) {
        super(FXML);

        this.logic = logic;
        this.addressPanel = new AddressPanel(logic.getFilteredPersonList());
        this.calendarPanel = new CalendarPanel();

        for (TabInfo tabInfo : logic.getTabInfoList()) {
            Tab tab = new Tab();
            tab.setText(tabInfo.toString());
            switch (tabInfo.getTabType()) {
            case ADDRESS_BOOK:
                tab.setContent(addressPanel.getRoot());
                break;
            case CALENDAR:
                tab.setContent(calendarPanel.getRoot());
                break;
            default:
                continue;
            }
            bodyTabs.getTabs().add(tab);
        }

        logic.getSelectedTab().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            bodyTabs.getSelectionModel().select(newValue.getIndex().getZeroBased());
        });
    }

    public AddressPanel getAddressPanel() {
        return addressPanel;
    }

    public CalendarPanel getCalendarPanel() {
        return calendarPanel;
    }
}
