package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class ReviewStat extends UiPart<Region> {
    private static final String FXML = "ReviewStat.fxml";

    @FXML
    private Label description;

    public ReviewStat(String string) {
        super(FXML);
        description.setText(string);
    }

}
