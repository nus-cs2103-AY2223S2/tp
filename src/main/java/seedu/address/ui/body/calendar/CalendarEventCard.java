package seedu.address.ui.body.calendar;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.index.Index;
import seedu.address.model.event.Event;
import seedu.address.ui.UiPart;

/**
 * A UI component that represents a calendar {@code Event}.
 */
public class CalendarEventCard extends UiPart<Region> {
    private static final String FXML = "body/calendar/CalendarEventCard.fxml";

    @FXML
    private Label description;
    @FXML
    private Label startDateTime;
    @FXML
    private Label endDateTime;
    @FXML
    private Label recurrence;
    @FXML
    private Label indexTag;

    /**
     * Creates a {@code CalendarEventCard} with the given {@code event} and {@code index}.
     */
    public CalendarEventCard(Event event, Index index) {
        super(FXML);

        description.setText(event.getDescription().getDescription());
        startDateTime.setText(event.getStartDateTime().toString());
        endDateTime.setText(event.getEndDateTime().toString());
        recurrence.setText(event.getRecurrence().toString());
        indexTag.setText(String.format("Index %d", index.getOneBased()));
    }
}
