package seedu.internship.ui.pages;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.internship.commons.core.LogsCenter;
import seedu.internship.model.event.Event;
import seedu.internship.ui.UiPart;

import java.util.List;
import java.util.logging.Logger;

public class ClashInfoItem extends UiPart<Region> {
    private static final String FXML = "ClashInfoItem.fxml";
    private final Logger logger = LogsCenter.getLogger(ClashInfoPage.class);

    private List<Event> clashingEvents;
    private Event mainEvent;

    @javafx.fxml.FXML
    private Label description;
    @FXML
    private Label startLabel;
    @FXML
    private Label start;
    @FXML
    private Label endLabel;
    @FXML
    private Label end;
    @FXML
    private VBox eventInfoBox;
    @FXML
    private ListView<Event> clashList;

    public ClashInfoItem(Event mainEvent, List<Event> clashingEvents) {
        super(FXML);
        this.clashingEvents = clashingEvents;
        this.mainEvent = mainEvent;
        setEventInfoBox();
        clashList.setItems(FXCollections.observableList(clashingEvents));
        clashList.setCellFactory(listView -> new EventListViewCell());
    }

    public void setEventInfoBox() {
        if (mainEvent.isDeadline()) {
            start.setManaged(false);
            startLabel.setManaged(false);
            endLabel.setText("Deadline: ");
            end.setText(mainEvent.getEnd().toString());
        } else {
            start.setManaged(true);
            startLabel.setManaged(true);
            start.setText(mainEvent.getStart().toString());
            endLabel.setText("End: ");
            end.setText(mainEvent.getEnd().toString());
        }

    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Internship} using a {@code InternshipCard}.
     */
    class EventListViewCell extends ListCell<Event> {
        @Override
        protected void updateItem(Event event, boolean empty) {
            super.updateItem(event, empty);

            if (empty || event == null) {
                setGraphic(null);
                setText(null);
            } else {
                String companyAndPosition = String.format("[%s, %s] ",
                        event.getInternship().getCompany(),
                        event.getInternship().getPosition());
                setGraphic(new Label(companyAndPosition + event.getName()));
            }
        }
    }

}
