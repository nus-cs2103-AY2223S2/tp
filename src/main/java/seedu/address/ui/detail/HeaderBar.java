package seedu.address.ui.detail;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.ui.UiPart;

/**
 * An ui for the status bar that is displayed at the header of the application.
 */
public class HeaderBar extends UiPart<Region> {
    private static final Logger logger = LogsCenter.getLogger(HeaderBar.class);
    private static final String FXML = "HeaderBar.fxml";

    @FXML
    private ImageView icon;

    @FXML
    private Label title;

    /**
     * Creates a {@code HeaderBar} with the given {@code Stage} and {@code Student}.
     *
     * @param titleText The text to display in the header bar.
     * @param iconPath The path to the icon to display in the header bar.
     */
    public HeaderBar(String titleText, String iconPath) {
        super(FXML);
        title.setText(titleText);
        icon.setImage(new Image(iconPath));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HeaderBar // instanceof handles nulls
                && title.getText().equals(((HeaderBar) other).title.getText())); // state check
    }
}
