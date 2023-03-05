package seedu.address.ui.body.calendar;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.ui.UiPart;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class CalendarDayView extends UiPart<Region> {
    private static final String FXML = "body/calendar/CalendarDayView.fxml";

    @FXML
    private Label date;
    @FXML
    private Label entryPlaceholder;
    @FXML
    private VBox entryContainer;

    /**
     * Creates a {@code CalendarDayView} with the given {@code dateToShow} and {@code entries}.
     * TODO: replace {@code entries} with Entry object
     */
    public CalendarDayView(LocalDate dateToShow, List<String> entries) {
        super(FXML);
        Objects.requireNonNull(dateToShow);
        date.setText(dateToShow.format(DateTimeFormatter.ISO_LOCAL_DATE));

        if (entries == null || entries.isEmpty()) {
            return;
        }
        ObservableList<Node> children = entryContainer.getChildren();
        children.clear();
        for (String entry : entries) {

        }
    }
}
