package vimification.taskui;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import vimification.internal.command.CommandResult;

/**
 * The Ui component that displays the result of the command at the bottom of {@code MainScreen}
 * after the user inputs a command.
 */
public class CommandResultPanel extends UiPart<HBox> {
    private static final String FXML = "CommandResultPanel.fxml";
    private MainScreen mainScreen;

    @FXML
    private Label resultField;

    public CommandResultPanel(MainScreen mainScreen) {
        super(FXML);
        this.mainScreen = mainScreen;
        resultField.prefWidthProperty().bind(this.getRoot().widthProperty());
        resultField.prefHeightProperty().bind(this.getRoot().heightProperty());
    }

    public void display(CommandResult result) {
        resultField.setText(result.getFeedbackToUser());
        System.out.println("CommandResultPanel.resultField: " + resultField.getText());
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(e -> resultField.setText(""));
        pause.play();
        mainScreen.loadBottomComponent(this);
    }

}
