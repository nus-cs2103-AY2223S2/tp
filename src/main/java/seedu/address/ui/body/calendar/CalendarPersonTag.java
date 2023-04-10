package seedu.address.ui.body.calendar;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays a {@code Person} in a {@code CalendarEventCard}.
 */
public class CalendarPersonTag extends UiPart<Region> {
    private static final String FXML = "body/calendar/CalendarPersonTag.fxml";

    @FXML
    private Label name;

    /**
     * Creates a {@code CalendarPersonTag} with the given {@code Person}.
     */
    public CalendarPersonTag(Person person) {
        super(FXML);
        Objects.requireNonNull(person);

        name.setText(person.getName().toString());
    }
}
