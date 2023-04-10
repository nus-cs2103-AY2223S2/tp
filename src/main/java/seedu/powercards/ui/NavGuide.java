package seedu.powercards.ui;

import javax.swing.text.html.ImageView;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;

/**
 * Represents a navigation guide in Review mode.
 */
public class NavGuide extends UiPart<Region> {
    private static final String FXML = "NavGuide.fxml";

    @FXML
    private ImageView imageView;

    public NavGuide() {
        super(FXML);
    }
}
