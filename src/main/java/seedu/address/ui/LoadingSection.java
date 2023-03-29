package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * The loading section that shows a loading screen before showing the MainWindow
 *
 * @author Haiqel Bin Hanaffi
 */
public class LoadingSection extends UiPart<Region> {
    private static final String FXML = "LoadingSection.fxml";

    @FXML
    private Label loadingLabel;

    public LoadingSection() {
        super(FXML);
    }

    public void setPasswordSuccessText() {
        loadingLabel.setText("Password Created Successfully! You are currently entering into MODCheck...");
    }
}
