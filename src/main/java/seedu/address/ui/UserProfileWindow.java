package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.User;

public class UserProfileWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(UserProfileWindow.class);
    private static final String FXML = "UserProfileWindow.fxml";

    private static final String DUMMY_PROFILE = "Komyo San\n" +
            "phone: 81210395\n" +
            "email: komyosan@gmail.com\n" +
            "address: 100 Sin Ming Avenue\n" +
            "telegramHandle: @komyosan\n" +
            "groups: [Study, BrightHill]\n" +
            "modules: [BT3101, CS3282, CS2107, CS3247, CE3115, IS3240]";

    @FXML
    private Label userDetails;

    public UserProfileWindow(Stage root) {
        super(FXML, root);
        userDetails.setText(String.valueOf(User.getUser()));
    }

    public UserProfileWindow() {
        this(new Stage());
    }

    public void show() {
        logger.fine("Showing user profile page.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the user profile window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the user profile window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

}
