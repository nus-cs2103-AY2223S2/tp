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
    private FooterButton moduleListButton;

    /**
     * The 'module search' button.
     */
    private FooterButton moduleSearchButton;

    @FXML
    private HBox footerButtonGroup;

    /**
     * Creates a {@code FooterButtonGroup} component.
     * @param progressButtonLabel The text label of the progress button.
     * @param moduleListButtonLabel The text label of the module-list button.
     * @param progressButtonHandler The function to execute on clicking the progress button.
     * @param moduleListButtonHandler The function to execute on clicking the module-list button.
     */
    public FooterButtonGroup(String progressButtonLabel, String moduleListButtonLabel, String moduleSearchButtonLabel,
                             Runnable progressButtonHandler, Runnable moduleListButtonHandler,
                             Runnable moduleSearchButtonHandler) {
        super(FXML);

        progressButton = new FooterButton(progressButtonLabel, progressButtonHandler);
        moduleListButton = new FooterButton(moduleListButtonLabel, moduleListButtonHandler);
        moduleSearchButton = new FooterButton(moduleSearchButtonLabel, moduleSearchButtonHandler);


        footerButtonGroup.getChildren().addAll(progressButton.getRoot(), getDeco(),
                moduleListButton.getRoot(), getDeco(), moduleSearchButton.getRoot());
    }

    /**
     * Updates the styles for all footer buttons for the case where progress button gets selected.
     */
    public void selectProgressButton() {
        progressButton.clearSelectedStyle();
        moduleListButton.clearSelectedStyle();
        moduleSearchButton.clearSelectedStyle();

        progressButton.addSelectedStyle();
    }

    /**
     * Updates the styles for all footer buttons for the case where module-list button gets selected.
     */
    public void selectModuleListButton() {
        progressButton.clearSelectedStyle();
        moduleListButton.clearSelectedStyle();
        moduleSearchButton.clearSelectedStyle();

        moduleListButton.addSelectedStyle();
    }

    /**
     * Updates the styles for all footer buttons for the case where module-search button gets selected.
     */
    public void selectModuleSearchButton() {
        progressButton.clearSelectedStyle();
        moduleListButton.clearSelectedStyle();
        moduleSearchButton.clearSelectedStyle();

        moduleSearchButton.addSelectedStyle();
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
