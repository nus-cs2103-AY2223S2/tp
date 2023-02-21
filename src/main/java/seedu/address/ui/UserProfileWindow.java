package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.User;

/**
 * Controller for user profile page
 */
public class UserProfileWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(UserProfileWindow.class);
    private static final String FXML = "UserProfileWindow.fxml";

    @FXML
    private Label userDetails;

    /**
     * Creates a new User profile window.
     *
     * @param root Stage to use as the root of the UserProfileWindow.
     */
    public UserProfileWindow(Stage root) {
        super(FXML, root);
        userDetails.setText(String.valueOf(User.getUser()));
    }

    /**
     * Creates a new UserProfileWindow.
     */
    public UserProfileWindow() {
        this(new Stage());
    }

    /**
     * Shows the user profile window.
     */
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
