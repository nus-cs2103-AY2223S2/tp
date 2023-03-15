package vimification.taskui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

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
     * Requests that this {@code Node} get the input focus, and that this {@code Node}'s top-level
     * ancestor become the focused window. To be eligible to receive the focus, the node must be
     * part of a scene, it and all of its ancestors must be visible, and it must not be disabled. If
     * this node is eligible, this function will cause it to become this {@code Scene}'s "focus
     * owner". Each scene has at most one focus owner node. The focus owner will not actually have
     * the input focus, however, unless the scene belongs to a {@code Stage} that is both visible
     * and active.
     */
    public void requestFocus() {
        this.getRoot().requestFocus();
        this.nameField.requestFocus();
    }

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
