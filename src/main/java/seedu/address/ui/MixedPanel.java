package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
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

    private ViewContentPanel viewContentPanel;
    private InternshipTodo currentTodo;
    private Note currentNote;

    @FXML
    private ListView<InternshipTodo> todoListView;

    @FXML
    private ListView<Note> noteListView;

    @FXML
    private VBox container;

    /**
     * Creates a {@code MixedPanel} with the given two {@code ObservableList}.
     */
    public MixedPanel(ObservableList<InternshipTodo> todoList, ObservableList<Note> noteList,
                      ViewContentPanel viewContentPanel) {
        super(FXML);
        this.viewContentPanel = viewContentPanel;
        todoListView.setItems(todoList);
        todoListView.setCellFactory(listView -> new TodoListViewCell());
        noteListView.setItems(noteList);
        noteListView.setCellFactory(listView -> new NoteListViewCell());
        logger.info("Mixed panel updated.");
    }

    /**
     * Handles mouse clicks for todoListView to show the corresponding {@code InternshipTodo}
     * in the {@code ViewContentPanel}
     *
     * @param arg0 mouse click event
     */
    @FXML public void handleTodoMouseClick(MouseEvent arg0) {
        InternshipTodo todoSelected = todoListView.getSelectionModel().getSelectedItem();
        this.currentTodo = todoSelected;
        viewContentPanel.setInternshipTodo(todoSelected);
    }

    /**
     * Handles mouse clicks for noteListView to show the corresponding {@code Note}
     * in the {@code ViewContentPanel}
     *
     * @param arg0 mouse click event
     */
    @FXML public void handleNoteMouseClick(MouseEvent arg0) {
        Note noteSelected = noteListView.getSelectionModel().getSelectedItem();
        this.currentNote = noteSelected;
        viewContentPanel.setNote(noteSelected);
    }

    /**
     * Getter for the vertical box with id container.
     *
     * @return VBox with id container
     */
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
                viewContentPanel.clearPanel();
            } else {
                setGraphic(new TodoCard(todo, getIndex() + 1).getRoot());
                if (currentTodo != null && todo.isSameTodo(currentTodo)) {
                    viewContentPanel.setInternshipTodo(todo);
                }
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
