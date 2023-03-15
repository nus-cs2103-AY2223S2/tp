package vimification.taskui;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 */
public class CommandInput extends UiPart<TextField> {

    private static final String FXML = "CommandInput.fxml";
    private Node parent;

    public CommandInput(Node parent) {
        super(FXML);
        this.parent = parent;
    }

    /**
     * Listener for handling all keyboard events to Vimification.
     *
     * @param event
     */
    @FXML
    private void handleKeyPressed(KeyEvent event) {

        boolean isEnterEvent = event.getCode().equals(KeyCode.ENTER);
        boolean isEscEvent = event.getCode().equals(KeyCode.ESCAPE);

        if (isEscEvent) {
            returnFocusToParent();
            System.out.println("You escaped");
        }

        if (isEnterEvent) {
            String commandString = this.getRoot().getText();
            checkIsQuitCommand(commandString);
            executeCommand(commandString);
            returnFocusToParent();
        }

    }

    private void executeCommand(String commandString) {
        System.out.println("Your command is " + commandString);
    }

    private void returnFocusToParent() {
        parent.requestFocus();
        this.getRoot().setVisible(false);
    }

    private void checkIsQuitCommand(String commandString) {
        // TODO : Create a Parser to parse the command and create a Driver to run it.
        if (commandString.equals(":wq!")) {
            Platform.exit();
        }
    }

    /**
     * Specifies whether the root of {@code Node} and any subnodes should be rendered as part of the
     * scene graph. A node may be visible and yet not be shown in the rendered scene if, for
     * instance, it is off the screen or obscured by another Node. Invisible nodes never receive
     * mouse events or keyboard focus and never maintain keyboard focus when they become invisible.
     *
     * @defaultValue true
     */
    public void setVisible(boolean isVisible) {
        this.getRoot().setVisible(isVisible);
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
    }

    @FXML
    public void initialize() {
        this.getRoot().setFocusTraversable(true); // Important
        this.getRoot().setVisible(false);
    }

}
