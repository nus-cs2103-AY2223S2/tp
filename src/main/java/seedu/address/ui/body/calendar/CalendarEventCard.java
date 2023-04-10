package seedu.address.ui.body.calendar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.index.Index;
import seedu.address.model.event.Event;
import seedu.address.model.event.fields.DateTime;
import seedu.address.model.person.Person;
import seedu.address.ui.UiPart;

/**
 * A UI component that represents a calendar {@code Event}.
 */
public class CalendarEventCard extends UiPart<Region> {
    private static final String FXML = "body/calendar/CalendarEventCard.fxml";

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");

    @FXML
    private VBox eventContainer;
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
    @FXML
    private HBox attendees;
    @FXML
    private VBox attendeesCard;

    /**
     * Creates a {@code CalendarEventCard} with the given {@code indexedEvent}.
     */
    public CalendarEventCard(CalendarPanel.IndexedEvent indexedEvent) {
        super(FXML);
        Objects.requireNonNull(indexedEvent);

        Index index = indexedEvent.getIndex();
        Event event = indexedEvent.getEvent();
        DateTime effectiveStart = event.getEffectiveStartDateTime();
        DateTime effectiveEnd = event.getEffectiveEndDateTime();
        description.setText(event.getDescription().getDescription());
        startDateTime.setText(effectiveStart.toString(FORMATTER));
        endDateTime.setText(effectiveEnd.toString(FORMATTER));
        recurrence.setText(event.getRecurrence().toString());
        indexTag.setText(String.format("Index %d", index.getOneBased()));

        Set<Person> people = event.getTaggedPeople();
        if (people.isEmpty()) {
            eventContainer.getChildren().remove(attendeesCard);
        } else {
            attendees.getChildren().addAll(people.stream()
                    .map(CalendarPersonTag::new)
                    .map(UiPart::getRoot)
                    .collect(Collectors.toList()));
            eventContainer.setPadding(new Insets(10, 0, 0, 0));
        }

        if (effectiveEnd.getDateTime().isBefore(LocalDateTime.now())) {
            getRoot().setOpacity(0.5);
        }
    }
}
