package seedu.address.ui.body;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Logic;
import seedu.address.logic.ui.tab.TabInfo;
import seedu.address.ui.UiPart;
import seedu.address.ui.body.address.AddressPanel;
import seedu.address.ui.body.calendar.CalendarPanel;
import seedu.address.ui.body.user.UserPanel;

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
    private final UserPanel userPanel;

    /**
     * Creates a {@code BodyPanel} with the given {@code Logic}.
     */
    public BodyPanel(Logic logic) {
        super(FXML);

        this.logic = logic;
        this.addressPanel = new AddressPanel(logic.getFilteredPersonList());
        this.calendarPanel = new CalendarPanel(logic.getEventList());
        this.userPanel = new UserPanel(logic.getUserData());

        fillTabs();
        bindSelectedTab();
        bindSelectedIndex();
    }

    public Logic getLogic() {
        return logic;
    }

    public AddressPanel getAddressPanel() {
        return addressPanel;
    }

    public CalendarPanel getCalendarPanel() {
        return calendarPanel;
    }

    public UserPanel getUserPanel() {
        return userPanel;
    }

    private void fillTabs() {
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
            case USER:
                tab.setContent(userPanel.getRoot());
                break;
            default:
                continue;
            }
            bodyTabs.getTabs().add(tab);
        }
    }

    /**
     * Binds the {@code TabPane} to the selected tab from {@code logic}
     * so that the UI updates on {@code tab} commands.
     */
    private void bindSelectedTab() {
        logic.getSelectedTab().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            bodyTabs.getSelectionModel().select(newValue.getIndex().getZeroBased());
        });
    }

    /**
     * Binds the {@code logic} to the selected tab in the {@code TabPane}
     * so that {@code logic} is updated on the last selected tab.
     */
    private void bindSelectedIndex() {
        bodyTabs.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            logic.setSelectedTab(Index.fromZeroBased(newValue.intValue()));
        });
    }
}
