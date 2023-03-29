package seedu.address.ui.task.note;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.task.Note;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays information of a {@code NoteList}.
 */
public class NoteCard extends UiPart<Region> {

    private static final String FXML = "task/NoteListCard.fxml";

    /**
     * NoteList: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Note note;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label noteContent;
    @FXML
    private Label date;
    @FXML
    private Label tags;

    /**
     * Creates a {@code NoteCard} with the given {@code NoteList} and index to display.
     */
    public NoteCard(Note note, int displayedIndex) {
        super(FXML);
        this.note = note;
        id.setText("#" + displayedIndex + " ");
        noteContent.setText(note.getNote().content);
        date.setText("Created by: " + note.getDateString());
        tags.setText(note.getType().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoteCard)) {
            return false;
        }

        // state check
        NoteCard card = (NoteCard) other;
        return id.getText().equals(card.id.getText())
                && note.equals(card.note);
    }
}
