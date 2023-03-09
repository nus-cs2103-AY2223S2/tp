package seedu.address.ui.detail;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.ui.UiPart;

/**
 * An ui for the status bar that is displayed at the header of the application.
 */
public class DetailedInfoRegion extends UiPart<Region> {
    private static final String FXML = "DetailedInfoRegion.fxml";

    @FXML
    private StackPane headerBarPlaceholder;

    @FXML
    private TextArea detailedInfoSection;

    /**
     * Creates a {@code DetailedInfoRegion} with the given {@code Stage} and {@code Student}.
     */
    public DetailedInfoRegion(String textToDisplay) {
        super(FXML);

        HeaderBar headerBar = new HeaderBar("Detailed Information");
        headerBarPlaceholder.getChildren().add(headerBar.getRoot());

        detailedInfoSection.setText(textToDisplay);
    }

    public void setHeaderBar(String titleText) {
        HeaderBar headerBar = new HeaderBar(titleText);
        headerBarPlaceholder.getChildren().add(headerBar.getRoot());
    }

    public void setDetailedInfoSection(String textToDisplay) {
        detailedInfoSection.setText(textToDisplay);
    }
}
