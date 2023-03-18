package seedu.address.ui.body.calendar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");

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
     * Creates a {@code CalendarEventCard} with the given {@code indexedEvent}.
     */
    public CalendarEventCard(CalendarPanel.IndexedEvent indexedEvent) {
        super(FXML);
        Objects.requireNonNull(indexedEvent);

        Index index = indexedEvent.getIndex();
        Event event = indexedEvent.getEvent();
        description.setText(event.getDescription().getDescription());
        startDateTime.setText(event.getStartDateTime().getDateTime().format(FORMATTER));
        endDateTime.setText(event.getEndDateTime().getDateTime().format(FORMATTER));
        recurrence.setText(event.getRecurrence().toString());
        indexTag.setText(String.format("Index %d", index.getOneBased()));

        if (event.getEndDateTime().getDateTime().isBefore(LocalDateTime.now())) {
            getRoot().setOpacity(0.5);
        }
    }
}
