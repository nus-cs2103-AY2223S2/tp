package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.person.User;

/**
 * Controller for user profile page
 */
public class UserProfilePanel extends UiPart<Region> {

    private static final String FXML = "UserProfilePanel.fxml";
    private static final Logger logger = LogsCenter.getLogger(UserProfilePanel.class);

    private static final String PHONE_NUMBER = "Phone number: ";
    private static final String EMAIL = "Email: ";
    private static final String ADDRESS = "Address: ";
    private static final String TELEGRAM = "Telegram";
    private static final String GROUP_TAGS = "Groups: ";
    private static final String MODULE_TAGS = "Modules: ";

    private Logic logic;
    private User user;

    @FXML
    private StackPane userProfilePane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private Label telegramHandle;
    @FXML //TODO: MIGHT CHANGE TO FLOW PANE LATER ON
    private Label groupTags;
    @FXML //TODO: MIGHT CHANGE TO FLOW PANE LATER ON
    private Label moduleTags;

    /**
     * Creates a new User profile window.
     */
    public UserProfilePanel(Logic logic) {
        super(FXML);
        this.logic = logic;
        this.user = this.logic.getUser();

        name.setText(String.valueOf(this.user.getName()));
        phone.setText(PHONE_NUMBER + this.user.getPhone());
        email.setText(EMAIL + this.user.getEmail());
        address.setText(ADDRESS + this.user.getAddress());
        telegramHandle.setText(TELEGRAM + this.user.getTelegramHandle());
        moduleTags.setText(MODULE_TAGS + this.user.getModuleTags());
        groupTags.setText(GROUP_TAGS + this.user.getGroupTags().toString());
    }
}
