package seedu.dengue.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the current overview that is displayed on the right of the application.
 */
public class OverviewDisplay extends UiPart<Region> {

    private static final String FXML = "OverviewDisplay.fxml";

    @FXML
    private TextArea overviewTitle;
    @FXML
    private TextArea overviewContent;

    public OverviewDisplay() {
        super(FXML);
    }

    public void setOverviewTitle(String newOverviewTitle) {
        requireNonNull(newOverviewTitle);
        overviewTitle.setText(newOverviewTitle);
    }

    public void setOverviewContent(String newOverviewContent) {
        requireNonNull(newOverviewContent);
        overviewContent.setText(newOverviewContent);
    }
}
