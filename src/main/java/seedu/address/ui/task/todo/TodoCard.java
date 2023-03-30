package seedu.address.ui.task.todo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.task.InternshipTodo;
import seedu.address.model.task.NoteContent;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Todo}.
 */
public class TodoCard extends UiPart<Region> {

    private static final String FXML = "task/TodoListCard.fxml";

    /**
     * NoteList: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     */

    public final InternshipTodo todo;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label companyName;
    @FXML
    private Label jobTitle;
    @FXML
    private Label deadline;
    @FXML
    private Label note;
    @FXML
    private Label createDate;
    @FXML
    private Label tags;

    /**
     * Creates a {@code TodoCard} with the given {@code InternshipTodo} and index to display.
     */
    public TodoCard(InternshipTodo todo, int displayedIndex) {
        super(FXML);
        this.todo = todo;
        id.setText("#" + displayedIndex + " ");
        companyName.setText(todo.getInternshipTitle().fullName);
        jobTitle.setText("Job: " + todo.getJobTitle().fullName);
        deadline.setText("Deadline: " + todo.getDeadline().fullName);
        createDate.setText("Created by: " + todo.getDateString());
        tags.setText(todo.getType().toString());
        NoteContent noteContent = todo.getNote();
        if (noteContent != null) {
            note.setText("Note: " + noteContent.content);
            note.setVisible(true);
        } else {
            note.setVisible(false);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TodoCard)) {
            return false;
        }

        // state check
        TodoCard card = (TodoCard) other;
        return id.getText().equals(card.id.getText())
                && todo.equals(card.todo);
    }
}
