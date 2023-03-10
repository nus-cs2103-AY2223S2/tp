package seedu.modtrek.ui.resultssection;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import seedu.modtrek.ui.UiPart;

/**
 * A button in the footer of the ResultsSection.
 */
public class FooterButton extends UiPart<Region> {
    private static final String FXML = "resultssection/FooterButton.fxml";

    /**
     * The function to be executed on clicking the button.
     */
    private Runnable handler;

    /**
     * The group of buttons in the footer, including this button.
     */
    private FooterButtonGroup buttonGroup;

    @FXML
    private VBox footerButtonContainer;

    @FXML
    private Button footerButton;

    /**
     * Creates a {@code FooterButton}.
     * @param label The label of the button.
     * @param handler The function to be executed on clicking the button.
     * @param isSelected Whether the button is initially selected on first render of this button.
     * @param buttonGroup The group of buttons in the footer, including this button.
     */
    public FooterButton(String label, Runnable handler, boolean isSelected,
                        FooterButtonGroup buttonGroup) {
        super(FXML);

        this.handler = handler;
        this.buttonGroup = buttonGroup;

        footerButton.setText(label);
        footerButton.setOnAction((event) -> {
            handleOnClick();
        });

        if (isSelected) {
            addSelectedStyle();
        }
    }

    /**
     * Executes the handler and styles the button accordingly on clicking this button.
     */
    private void handleOnClick() {
        handler.run();

        buttonGroup.clearButtonsSelectedStyle();

        addSelectedStyle();
    }

    /**
     * Adds the selected style of the button.
     */
    public void addSelectedStyle() {
        footerButton.getStyleClass().add("footer-button-selected");

        Circle deco = new Circle();
        deco.setRadius(3);
        deco.getStyleClass().add("footer-button-selected-deco");
        footerButtonContainer.getChildren().add(deco);
    }

    /**
     * Clears the selected style of the button.
     */
    public void clearSelectedStyle() {
        footerButton.getStyleClass().remove("footer-button-selected");

        footerButtonContainer.getChildren()
                .remove(footerButtonContainer.lookup(".footer-button-selected-deco"));
    }
}
