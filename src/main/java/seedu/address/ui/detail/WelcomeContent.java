package seedu.address.ui.detail;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.ui.UiPart;

import java.util.logging.Logger;

/**
 * An ui for the status bar that is displayed at the header of the application.
 */
public class WelcomeContent extends UiPart<Region> {
    private static final Logger logger = LogsCenter.getLogger(WelcomeContent.class);
    private static final String FXML = "WelcomeContent.fxml";

    @FXML
    Label welcomeMessage;

    /**
     * Creates a {@code WelcomeContent} with the given {@code Stage} and {@code Student}.
     */
    public WelcomeContent() {
        super(FXML);
        welcomeMessage.setText("Welcome to TutorPro!\n\n"
                + "Your one-stop solution for managing\n"
                + "all your students and lessons.");
    }
}
