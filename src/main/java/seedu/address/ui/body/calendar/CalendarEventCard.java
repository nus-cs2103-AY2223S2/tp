package seedu.address.ui.body.calendar;

import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

public class CalendarEventCard extends UiPart<Region> {
    private static final String FXML = "body/calendar/CalendarEventCard.fxml";

    public CalendarEventCard() {
        super(FXML);
    }
}
