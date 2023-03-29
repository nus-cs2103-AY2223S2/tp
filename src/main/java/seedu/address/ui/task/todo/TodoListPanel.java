package seedu.address.ui.task.todo;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.task.InternshipTodo;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of todos.
 */
public class TodoListPanel extends UiPart<Region> {
    private static final String FXML = "task/TodoListPanel.fxml";

    @FXML
    private ListView<InternshipTodo> todoListView;

    /**
     * Creates a {@code TodoListPanel} with the given {@code ObservableList}.
     */
    public TodoListPanel(ObservableList<InternshipTodo> todoList) {
        super(FXML);
        todoListView.setItems(todoList);
        todoListView.setCellFactory(listView -> new TodoListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code InternshipTodo} using a {@code TodoCard}.
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

}
