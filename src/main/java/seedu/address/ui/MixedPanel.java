package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.task.InternshipTodo;
import seedu.address.model.task.Note;
import seedu.address.ui.task.note.NoteCard;
import seedu.address.ui.task.todo.TodoCard;

/**
 * Panel containing the list of notes and todos.
 */
public class MixedPanel extends UiPart<Region> {
    private static final String FXML = "task/MixedPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(MixedPanel.class);

    @FXML
    private ListView<InternshipTodo> todoListView;

    @FXML
    private ListView<Note> noteListView;

    @FXML
    private VBox container;

    /**
     * Creates a {@code MixedPanel} with the given two {@code ObservableList}.
     */
    public MixedPanel(ObservableList<InternshipTodo> todoList, ObservableList<Note> noteList) {
        super(FXML);
        todoListView.setItems(todoList);
        todoListView.setCellFactory(listView -> new TodoListViewCell());
        noteListView.setItems(noteList);
        noteListView.setCellFactory(listView -> new NoteListViewCell());
    }

    public VBox getContainer() {
        return container;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code ApplicationTodo} using a {@code TodoCard}.
     */
    class TodoListViewCell extends ListCell<InternshipTodo> {
        @Override
        protected void updateItem(InternshipTodo todo, boolean empty) {
            super.updateItem(todo, empty);

            if (empty || todo == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TodoCard(todo, getIndex() + 1).getRoot());
            }
        }
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
            } else {
                setGraphic(new NoteCard(note, getIndex() + 1).getRoot());
            }
        }
    }
}
