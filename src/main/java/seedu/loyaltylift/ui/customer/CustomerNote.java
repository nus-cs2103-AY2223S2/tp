package seedu.loyaltylift.ui.customer;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import seedu.loyaltylift.model.customer.Note;
import seedu.loyaltylift.ui.UiPart;

/**
 * A panel that displays a Customer's note.
 */
public class CustomerNote extends UiPart<VBox> {

    private static final String FXML = "Customer/CustomerNote.fxml";

    @FXML
    private Text note;

    /**
     * Creates a {@code CustomerNote} with the given {@code Note}.
     * @param note The note to be displayed.
     */
    public CustomerNote(Note note) {
        super(FXML);

        this.note.setText(note.value);
    }
}
