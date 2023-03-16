package seedu.address.ui.detail;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.ui.UiPart;
import seedu.address.ui.exam.EmptyExamsContent;

/**
 * An ui for the status bar that is displayed at the header of the application.
 * This is the welcome screen that is displayed when the application is first opened.
 */
public class WelcomeContent extends UiPart<Region> {
    private static final Logger logger = LogsCenter.getLogger(WelcomeContent.class);
    private static final String FXML = "WelcomeContent.fxml";
    private static final String WELCOME_MESSAGE = "Welcome to TutorPro!\n\n"
            + "Your one-stop solution for managing\n"
            + "all your students and lessons.";

    @FXML
    private Label welcomeMessage;

    /**
     * Creates a {@code WelcomeContent} with the given {@code Stage} and {@code Student}.
     */
    public WelcomeContent() {
        super(FXML);
        welcomeMessage.setText(WELCOME_MESSAGE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmptyExamsContent // instanceof handles nulls
                && welcomeMessage.getText().equals(((WelcomeContent) other).welcomeMessage.getText())); // state check
    }
}
