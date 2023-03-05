package seedu.address.ui.body.calendar;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.ui.UiPart;

public class CalendarDayCard extends UiPart<Region> {
    private static final String FXML = "body/calendar/CalendarDayCard.fxml";

    @FXML
    private VBox eventsContainer;

    public CalendarDayCard() {
        super(FXML);

        eventsContainer.getChildren().addAll(
                new CalendarEventCard().getRoot(),
                new CalendarEventCard().getRoot(),
                new CalendarEventCard().getRoot()
        );
    }
}
