package seedu.address.ui.task.todo;

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
import seedu.address.ui.UiPart;
import seedu.address.ui.ViewContentPanel;

/**
 * Panel containing the list of todos.
 */
public class TodoListPanel extends UiPart<Region> {
    private static final String FXML = "task/TodoListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TodoListPanel.class);

    private ViewContentPanel viewContentPanel;
    private InternshipTodo currentTodo;

    @FXML
    private ListView<InternshipTodo> todoListView;

    @FXML
    private VBox container;

    /**
     * Creates a {@code TodoListPanel} with the given {@code ObservableList}.
     */
    public TodoListPanel(ObservableList<InternshipTodo> todoList, ViewContentPanel viewContentPanel) {
        super(FXML);
        this.viewContentPanel = viewContentPanel;
        todoListView.setItems(todoList);
        todoListView.setCellFactory(listView -> new TodoListViewCell());
        logger.info("Todo List updated.");
    }

    /**
     * Handles mouse clicks for todoListView to show the corresponding {@code InternshipTodo}
     * in the {@code ViewContentPanel}
     * @param arg0 mouse click event
     */
    @FXML public void handleMouseClick(MouseEvent arg0) {
        InternshipTodo todoSelected = todoListView.getSelectionModel().getSelectedItem();
        this.currentTodo = todoSelected;
        viewContentPanel.setInternshipTodo(todoSelected);
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
     * Custom {@code ListCell} that displays the graphics of a {@code InternshipTodo} using a {@code TodoCard}.
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

}
