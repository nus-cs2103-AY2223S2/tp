package vimification.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

/**
 * Panel containing the creation of a task.
 */
public class TaskCreationPanel extends UiPart<VBox> {

    private static final String FXML = "TaskCreationPanel.fxml";

    Node parent;

    @FXML
    private TextField nameField;

    @FXML
    private TextArea descriptionField;

    public TaskCreationPanel(Node parent) {
        super(FXML);
        this.parent = parent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestFocus() {
        super.requestFocus();
        this.nameField.requestFocus();
    }

    /**
     * Returns focus to parent.
     */
    private void returnFocusToParent() {
        parent.requestFocus();
        this.getRoot().setVisible(false);
    }

    /**
     * Listener for handling all keyboard events to TaskCreationPanel.
     *
     * @param event
     */
    @FXML
    private void handleKeyPressed(KeyEvent event) {

        boolean isEscEvent = event.getCode().equals(KeyCode.ESCAPE);

        if (isEscEvent) {
            nameField.clear();
            descriptionField.clear();

            returnFocusToParent();
            System.out.println("TaskCreationPanel -> Parent");
        }

    }
}
