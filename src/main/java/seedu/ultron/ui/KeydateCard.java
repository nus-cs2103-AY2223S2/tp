package seedu.ultron.ui;

import javafx.scene.control.Label;
import seedu.ultron.model.opening.Keydate;

/**
 * An UI component that displays information of a {@code KeydateCard}.
 */
public class KeydateCard extends Label {

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Keydate keydate;

    /**
     * Creates a {@code DateCardCode} with the given {@code KeydateCard} and index to display.
     */
    public KeydateCard(Keydate keydate) {
        super(keydate.fullKey + ": " + keydate.fullDate);
        this.keydate = keydate;
        this.getStyleClass().add("keydate");
        if (keydate.isPastKeydate()) {
            this.getStyleClass().add("past");
        }
    }
}
