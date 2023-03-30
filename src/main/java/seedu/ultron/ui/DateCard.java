package seedu.ultron.ui;

import javafx.scene.control.Label;
import seedu.ultron.model.opening.Date;

/**
 * An UI component that displays information of a {@code DateCard}.
 */
public class DateCard extends Label {

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Date date;

    /**
     * Creates a {@code DateCardCode} with the given {@code DateCard} and index to display.
     */
    public DateCard(Date date) {
        super(date.fullName + ": " + date.fullDate);
        this.date = date;
        this.getStyleClass().add("date");
        if (date.isPastDate()) {
            this.getStyleClass().add("past");
        }
    }
}
