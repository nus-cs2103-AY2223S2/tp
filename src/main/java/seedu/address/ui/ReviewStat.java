package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.Pair;

public class ReviewStat extends UiPart<Region> {
    private static final String FXML = "ReviewStat.fxml";

    @FXML
    private Label title;

    @FXML
    private Label description;

    public ReviewStat(Pair<String, String> pair) {
        super(FXML);
        title.setText(pair.getKey());
        description.setText(pair.getValue());
    }

}
