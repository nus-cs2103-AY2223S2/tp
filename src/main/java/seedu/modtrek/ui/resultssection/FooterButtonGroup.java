package seedu.modtrek.ui.resultssection;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import seedu.modtrek.ui.UiPart;

/**
 * A group of buttons in the footer of the ResultsSection.
 */
public class FooterButtonGroup extends UiPart<Region> {
    private static final String FXML = "resultssection/FooterButtonGroup.fxml";

    /**
     * The 'display degree progress' button.
     */
    private FooterButton progressButton;

    /**
     * The 'display module list' button.
     */
    private FooterButton modulelistButton;

    @FXML
    private HBox footerButtonGroup;

    /**
     * Creates a {@code FooterButtonGroup}.
     * @param progressButtonLabel The text label of the progress button.
     * @param modulelistButtonLabel The text label of the module-list button.
     * @param progressButtonHandler The function to execute on clicking the progress button.
     * @param modulelistButtonHandler The function to execute on clicking the module-list button.
     */
    public FooterButtonGroup(String progressButtonLabel, String modulelistButtonLabel,
                             Runnable progressButtonHandler, Runnable modulelistButtonHandler) {
        super(FXML);

        progressButton = new FooterButton(progressButtonLabel, progressButtonHandler);
        modulelistButton = new FooterButton(modulelistButtonLabel, modulelistButtonHandler);

        footerButtonGroup.getChildren().addAll(progressButton.getRoot(), getDeco(), modulelistButton.getRoot());
    }

    /**
     * Updates the styles for progress button and module-list button for the case where
     * progress button gets selected.
     */
    public void selectProgressButton() {
        progressButton.clearSelectedStyle();
        modulelistButton.clearSelectedStyle();
        progressButton.addSelectedStyle();
    }

    /**
     * Updates the styles for progress button and module-list button for the case where
     * module-list button gets selected.
     */
    public void selectModulelistButton() {
        progressButton.clearSelectedStyle();
        modulelistButton.clearSelectedStyle();
        modulelistButton.addSelectedStyle();
    }

    /**
     * Creates a decoration (a circle) to separate each button in the group.
     */
    private Circle getDeco() {
        Circle deco = new Circle();
        deco.setRadius(3);
        deco.getStyleClass().add("footer-buttons-deco");
        return deco;
    }
}
