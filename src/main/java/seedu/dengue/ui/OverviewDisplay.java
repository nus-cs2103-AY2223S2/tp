package seedu.dengue.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.dengue.model.overview.Overview;

/**
 * A ui for the current overview that is displayed on the right of the application.
 */
public class OverviewDisplay extends UiPart<Region> {
    private static final String FXML = "OverviewDisplay.fxml";

    @FXML
    private TextArea overviewTitle;
    @FXML
    private TextArea overviewSubtitle;
    @FXML
    private TextArea overviewContent;

    /**
     * Constructs a new blank {@code OverviewDisplay} instance.
     */
    public OverviewDisplay() {
        super(FXML);
    }

    private void setOverviewTitle(String newOverviewTitle) {
        requireNonNull(newOverviewTitle);
        overviewTitle.setText(newOverviewTitle);
    }

    private void setOverviewSubtitle(String newOverviewSubtitle) {
        requireNonNull(newOverviewSubtitle);
        overviewSubtitle.setText(newOverviewSubtitle);
    }

    private void setOverviewContent(String newOverviewContent) {
        requireNonNull(newOverviewContent);
        overviewContent.setText(newOverviewContent);
    }

    /**
     * Updates the OverviewDisplay with the given {@code Overview}.
     *
     * @param overview The overview to display.
     */
    public void setOverview(Overview overview) {
        requireNonNull(overview);
        setOverviewTitle(overview.getOverviewTitle());
        setOverviewSubtitle(overview.getOverviewSubtitle());
        setOverviewContent(overview.getOverviewContent());
    }
}
