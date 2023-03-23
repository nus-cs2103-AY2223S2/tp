package ezschedule.ui;

import java.util.List;

import ezschedule.model.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class CalenderBox extends UiPart<Region> {

    @FXML
    private StackPane stackPane;
    @FXML
    private Rectangle rectangle;
    @FXML
    private VBox vbox;

    private List<Event> events;

    private static final String FXML = "CalenderBox.fxml";

    public CalenderBox(List<Event> events) {
        super(FXML);
        this.events = events;
    }
}
