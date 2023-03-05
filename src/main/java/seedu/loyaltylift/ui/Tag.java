package seedu.loyaltylift.ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import seedu.loyaltylift.model.customer.Customer;

public class Tag extends UiPart<StackPane> {

    private static final String FXML = "Tag.fxml";

    @FXML
    private Label tag;

    private String colorToHex(Color color) {
        return "#" + color.toString().substring(2, 8);
    }

    public Tag(Color backgroundColor, Color textColor, String text) {
        super(FXML);

        this.getRoot().setStyle("-fx-background-color: " + colorToHex(backgroundColor));
        tag.setStyle("-fx-text-fill: " + colorToHex(textColor));
        tag.setText(text);
    }
}
