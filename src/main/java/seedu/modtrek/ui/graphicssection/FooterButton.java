package seedu.modtrek.ui.graphicssection;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import seedu.modtrek.ui.UiPart;

public class FooterButton extends UiPart<Region> {
    private static final String FXML = "graphics_section/FooterButton.fxml";

    private Runnable handler;

    private FooterButtonGroup buttonGroup;

    @FXML
    private VBox footerButtonContainer;

    @FXML
    private Button footerButton;

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

    private void handleOnClick() {
        handler.run();

        buttonGroup.clearButtonsSelectedStyle();

        addSelectedStyle();
    }

    public void addSelectedStyle() {
        footerButton.getStyleClass().add("footer-button-selected");

        Circle deco = new Circle();
        deco.setRadius(3);
        deco.getStyleClass().add("footer-button-selected-deco");
        footerButtonContainer.getChildren().add(deco);
    }

    public void clearSelectedStyle() {
        footerButton.getStyleClass().remove("footer-button-selected");

        footerButtonContainer.getChildren()
                .remove(footerButtonContainer.lookup(".footer-button-selected-deco"));
    }
}
