package seedu.address.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * The UI component that is responsible for outputting the result of user requests.
 */
public class ResultDialog {

    private static final ImageView warningImgPath = new ImageView("/images/icon-warning.png");
    private static String resultMessage;
    private static Stage primaryStage;

    /**
     * Creates a {@code ResultDialog} with the given {@code String}.
     */
    public static void displayResultDialog(String resultMsg, Stage stage) {
        resultMessage = resultMsg;
        primaryStage = stage;
        showResult();
    }

    private static void showResult() {
        final Alert result = new Alert(Alert.AlertType.INFORMATION);

        setDialogStyle(result);

        displayDialog(result);
    }

    private static void setDialogStyle(Alert result) {
        result.initOwner(primaryStage);
        result.getDialogPane().getStylesheets().add("view/ResultDialog.css");
        result.initStyle(StageStyle.TRANSPARENT);
        result.initModality(Modality.NONE);

        result.setTitle("Notification");
        result.setHeaderText(null);
        result.setX(primaryStage.getX() + primaryStage.getWidth() - 1.35 * result.getDialogPane().getWidth());
        result.setY(0.2 * primaryStage.getHeight());
        result.setGraphic(warningImgPath);
        result.setContentText(resultMessage);

        result.getDialogPane().getScene().setFill(Color.TRANSPARENT);
        result.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);
        result.getDialogPane().lookup(".button-bar").setVisible(false);
    }

    private static void displayDialog(Alert result) {
        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(3.5), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                result.setResult(ButtonType.OK);
                result.close();
            }
        }));

        timer.setCycleCount(1);
        timer.play();
        result.showAndWait();
    }
}
