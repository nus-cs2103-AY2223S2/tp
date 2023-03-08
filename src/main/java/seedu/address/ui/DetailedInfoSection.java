package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class DetailedInfoSection extends UiPart<Region> {

    private static final String FXML = "DetailedInfoSection.fxml";

    @FXML
    private TextArea detailedInfoSection;

    /**
     * Creates a {@code DetailedInfoSection} with the given {@code Stage} and {@code Person}.
     */
    public DetailedInfoSection() {
        super(FXML);
        detailedInfoSection.setText("This is the detailed info section");
    }
}
