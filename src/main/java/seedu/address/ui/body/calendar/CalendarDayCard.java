package seedu.address.ui.body.calendar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.ui.UiPart;

/**
 * A UI component that represents a day's worth of {@code Event}s.
 */
public class CalendarDayCard extends UiPart<Region> {
    private static final String FXML = "body/calendar/CalendarDayCard.fxml";
    private static final DateTimeFormatter DATE_NUMBER_FORMATTER = DateTimeFormatter.ofPattern("dd");
    private static final DateTimeFormatter DATE_MONTH_YEAR_FORMATTER = DateTimeFormatter.ofPattern("MMMM, yyyy");
    private static final DateTimeFormatter DAY_FORMATTER = DateTimeFormatter.ofPattern("eeee");

    @FXML
    private Label dateNumber;
    @FXML
    private Label dateMonthYear;
    @FXML
    private Label day;
    @FXML
    private VBox eventsContainer;

    /**
     * Creates a {@code CalendarDayCard} with the given {@code events}
     * on the specified {@code date}.
     */
    public CalendarDayCard(List<CalendarPanel.IndexedEvent> events, LocalDate date) {
        super(FXML);
        CollectionUtil.requireAllNonNull(events, date);

        dateNumber.setText(date.format(DATE_NUMBER_FORMATTER));
        dateMonthYear.setText(date.format(DATE_MONTH_YEAR_FORMATTER));
        day.setText(date.format(DAY_FORMATTER));
        eventsContainer.getChildren().addAll(events.stream()
                .map(CalendarEventCard::new)
                .map(UiPart::getRoot)
                .collect(Collectors.toList()));
    }
}
