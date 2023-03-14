package seedu.address.ui.body;

import javafx.fxml.FXML;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import seedu.address.logic.Logic;
import seedu.address.ui.UiPart;
import seedu.address.ui.body.address.AddressPanel;
import seedu.address.ui.body.calendar.CalendarPanel;

/**
 * A UI component representing the body section of the app with tabs.
 */
public class BodyPanel extends UiPart<Region> {
    private static final String FXML = "body/BodyPanel.fxml";
    private static final String TAB_ADDRESS_BOOK = "1. Address Book";
    private static final String TAB_CALENDAR = "2. Calendar";

    /**
     * An enum to identify tabs.
     */
    public enum TabType {
        ADDRESS_BOOK,
        CALENDAR
    }

    @FXML
    private TabPane bodyTabs;

    private final Logic logic;

    private final AddressPanel addressPanel;
    private final CalendarPanel calendarPanel;
    private final Tab addressBookTab;
    private final Tab calendarTab;

    /**
     * Creates a {@code BodyPanel} with the given {@code Logic}.
     */
    public BodyPanel(Logic logic) {
        super(FXML);

        this.logic = logic;

        addressPanel = new AddressPanel(logic.getFilteredPersonList());
        addressBookTab = new Tab();
        addressBookTab.setText(TAB_ADDRESS_BOOK);
        addressBookTab.setContent(addressPanel.getRoot());

        calendarPanel = new CalendarPanel();
        calendarTab = new Tab();
        calendarTab.setText(TAB_CALENDAR);
        calendarTab.setContent(calendarPanel.getRoot());

        bodyTabs.getTabs().addAll(addressBookTab, calendarTab);
    }

    /**
     * Switches to the specified tab.
     *
     * @param tabType Identifier for tabs.
     */
    public void selectTab(TabType tabType) {
        SingleSelectionModel<Tab> selectionModel = bodyTabs.getSelectionModel();
        switch (tabType) {
        case ADDRESS_BOOK:
            selectionModel.select(addressBookTab);
            break;
        case CALENDAR:
            selectionModel.select(calendarTab);
            break;
        default:
        }
    }

    public AddressPanel getAddressPanel() {
        return addressPanel;
    }

    public CalendarPanel getCalendarPanel() {
        return calendarPanel;
    }
}
