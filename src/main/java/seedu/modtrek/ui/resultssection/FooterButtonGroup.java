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
     * The list of buttons in the group.
     */
    private FooterButton[] buttons;

    @FXML
    private HBox footerButtonGroup;

    /**
     * Creates a {@code FooterButtonGroup}.
     * @param labels The labels for the group of buttons.
     * @param handlers The corresponding functions to execute on clicking each button in the group.
     */
    public FooterButtonGroup(String[] labels, Runnable[] handlers) {
        super(FXML);

        assert labels.length == handlers.length
                : "Number of footer button labels should be equal to number of handlers";

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

    /**
     * Clears the selected style of all the buttons in the group.
     */
    public void clearButtonsSelectedStyle() {
        for (int i = 0; i < buttons.length; i++) {
            FooterButton button = buttons[i];
            button.clearSelectedStyle();
        }
    }

    /**
     * Adds a decoration (a circle) to separate each button in the group.
     */
    private void addDeco() {
        Circle deco = new Circle();
        deco.setRadius(3);
        deco.getStyleClass().add("footer-buttons-deco");
        footerButtonGroup.getChildren().add(deco);
    }
}
