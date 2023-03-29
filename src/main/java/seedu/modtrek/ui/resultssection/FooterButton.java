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

    @FXML
    private VBox footerButtonContainer;

    @FXML
    private Button footerButton;

    /**
     * Creates a {@code FooterButton}.
     * @param label The label of the button.
     * @param handler The function to be executed on clicking the button.
     */
    public FooterButton(String label, Runnable handler) {
        super(FXML);

        footerButton.setText(label);
        footerButton.setOnAction((event) -> {
            handler.run();
        });
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
