package seedu.modtrek.ui.graphicssection;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import seedu.modtrek.ui.UiPart;

public class FooterButtonGroup extends UiPart<Region> {
    private static final String FXML = "graphics_section/FooterButtonGroup.fxml";

    private FooterButton[] buttons;

    @FXML
    private HBox footerButtonGroup;

    public FooterButtonGroup(String[] labels, Runnable[] handlers) {
        super(FXML);

        assert labels.length == handlers.length :
                "Number of footer button labels should be equal to number of handlers";

        buttons = new FooterButton[labels.length];

        /* Add the buttons */
        for (int i = 0; i < labels.length; i++) {
            FooterButton button = new FooterButton(labels[i], handlers[i], i == 0, this);
            footerButtonGroup.getChildren().add(button.getRoot());
            buttons[i] = button;

            if (i != labels.length - 1) {
                addDeco();
            }
        }
    }

    public void clearButtonsSelectedStyle() {
        for (int i = 0; i < buttons.length; i++) {
            FooterButton button = buttons[i];
            button.clearSelectedStyle();
        }
    }

    private void addDeco() {
        Circle deco = new Circle();
        deco.setRadius(3);
        deco.getStyleClass().add("footer-buttons-deco");
        footerButtonGroup.getChildren().add(deco);
    }
}
