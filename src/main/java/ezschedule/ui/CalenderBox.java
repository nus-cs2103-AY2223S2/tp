package ezschedule.ui;

import java.time.ZonedDateTime;
import java.util.List;

import ezschedule.model.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CalenderBox extends UiPart<Region> {

    private static final String FXML = "CalenderBox.fxml";
    @FXML
    private StackPane calenderBoxPane;
    @FXML
    private Text calenderDate;
    @FXML
    private VBox calenderEvents;
    @FXML
    private Rectangle calenderHighlight;
    private List<Event> events;

    public CalenderBox(String date, List<Event> events, ZonedDateTime today, ZonedDateTime dateFocus, int currentDate) {
        super(FXML);
        this.events = events;

        calenderDate.setText(date);
        calenderHighlight.setFill(Color.TRANSPARENT);
        calenderHighlight.setStroke(Color.BLACK);
        if (events != null) {
            for (int i = 0; i < events.size(); i++) {
                if (i >= 2) {
                    Text moreActivities = new Text("...");
                    calenderEvents.getChildren().add(moreActivities);
                    moreActivities.setOnMouseClicked(mouseEvent -> {
                        //On ... click print all activities for given date
                        // TODO TO SHOW EVENTS
                        System.out.println(events);
                    });
                    break;
                }
                Text text = new Text(events.get(i).getName().toString());
                calenderEvents.getChildren().add(text);
                text.setOnMouseClicked(mouseEvent -> {
                    //On Text clicked
                    System.out.println(text.getText());
                });
            }
            calenderEvents.setStyle("-fx-background-color:GRAY");
        }

        if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate) {
            calenderHighlight.setStroke(Color.BLUE);
        }
    }
}
