package tfifteenfour.clipboard.ui.sessionpage;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.MainApp;
import tfifteenfour.clipboard.commons.core.LogsCenter;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.ui.UiPart;

/**
 * An UI component that displays information of a {@code Session}.
 */
public class UnselectedSessionListCard extends UiPart<Region> {

    private static final String FXML = "ListCard.fxml";
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Session session;

    @FXML
    private Label id;
    @FXML
    private Label code;

    /**
     * Creates a {@code UnselectedSessionListCard} with the given {@code Session} and index to display.
     */
    public UnselectedSessionListCard(Session session, int displayedIndex) {
        super(FXML);
        this.session = session;
        id.setText(displayedIndex + ". ");
        code.setText(session.getSessionName());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnselectedSessionListCard)) {
            return false;
        }

        // state check
        UnselectedSessionListCard card = (UnselectedSessionListCard) other;
        return id.getText().equals(card.id.getText())
                && session.equals(card.session);
    }
}
