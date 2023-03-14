package vimification.taskui;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 */
public class CommandInput extends TextField {

    // private static final String FXML = "CommandInput.fxml";
    private Node parent;


    public CommandInput(Node parent) {
        this.parent = parent;
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(getClass().getResource("/view/CommandInput.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
            String commandString = this.getText();
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
        this.setVisible(false);
    }

    private void checkIsQuitCommand(String commandString) {
        // TODO : Create a Parser to parse the command and create a Driver to run it.
        if (commandString.equals(":wq!")) {
            Platform.exit();
        }
    }

    @FXML
    public void initialize() {
        this.setFocusTraversable(true); // Important
        this.setVisible(false);
    }

}
