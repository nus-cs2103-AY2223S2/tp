package seedu.loyaltylift.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import seedu.loyaltylift.model.attribute.Note;

/**
 * A panel that displays a Customer's or Order's note.
 */
public class NotePanel extends UiPart<VBox> {

    private static final String FXML = "NotePanel.fxml";

    @FXML
    private Text note;

    /**
     * Creates a {@code NotePanel} with the given {@code Note}.
     * @param note The note to be displayed.
     */
    public NotePanel(Note note) {
        super(FXML);

        this.note.setText(note.value);
    }
}
