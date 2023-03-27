package vimification.taskui;

import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;

/**
 * The Ui component that displays the result of the command at the bottom of {@code MainScreen}
 * after the user inputs a command.
 */
public class CommandResult extends UiPart<HBox> {
    private static final String FXML = "CommandResult.fxml";
    private MainScreen mainScreen;

    private Alert result;

    public CommandResult(MainScreen mainScreen) {
        super(FXML);
        this.mainScreen = mainScreen;
    }

    public void display(CommandResult result) {}

    public Alert getResultAlert() {
        return result;
    }

}
