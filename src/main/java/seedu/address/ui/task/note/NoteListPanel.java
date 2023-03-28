package seedu.address.ui.task.note;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.task.Note;
import seedu.address.ui.UiPart;
import seedu.address.ui.ViewContentPanel;

/**
 * Panel containing the list of notes.
 */
public class NoteListPanel extends UiPart<Region> {
    private static final String FXML = "task/NoteListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(NoteListPanel.class);

    private ViewContentPanel viewContentPanel;
    private Note currentNote;

    @FXML
    private ListView<Note> noteListView;

    @FXML
    private VBox container;

    /**
     * Creates a {@code NoteListPanel} with the given {@code ObservableList}.
     */
    public NoteListPanel(ObservableList<Note> noteList, ViewContentPanel viewContentPanel) {
        super(FXML);
        this.viewContentPanel = viewContentPanel;
        noteListView.setItems(noteList);
        noteListView.setCellFactory(listView -> new NoteListViewCell());
    }

    /**
     * Handles mouse clicks for noteListView to show the corresponding {@code Note}
     * in the {@code ViewContentPanel}
     * @param arg0 mouse click event
     */
    @FXML public void handleMouseClick(MouseEvent arg0) {
        Note noteSelected = noteListView.getSelectionModel().getSelectedItem();
        this.currentNote = noteSelected;
        viewContentPanel.setNote(noteSelected);
    }

    public VBox getContainer() {
        return container;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code NoteList} using a {@code NoteCard}.
     */
    class NoteListViewCell extends ListCell<Note> {
        @Override
        public void updateItem(Note note, boolean empty) {
            super.updateItem(note, empty);

            if (empty || note == null) {
                setGraphic(null);
                setText(null);
                viewContentPanel.clearPanel();
            } else {
                setGraphic(new NoteCard(note, getIndex() + 1).getRoot());
                if (currentNote != null && note.isSameNote(currentNote)) {
                    viewContentPanel.setNote(note);
                }
            }
        }
    }

}
