package taa.ui;

import java.util.logging.Logger;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import javafx.util.Duration;
import taa.commons.core.LogsCenter;
import taa.model.alarm.AlarmList;

/**
 * Controller for a help page
 */
public class AlarmWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s2-cs2103t-t14-4.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "AlarmWindow.fxml";
    @FXML
    private Button copyButton;

    @FXML
    private Label countdownLabel;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public AlarmWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new HelpWindow.
     */
    public AlarmWindow() {
        this(new Stage());
    }

    public void show() {
        logger.fine("Showing help page about the application.");
        double remainingTime = AlarmList.getFirstAlarm().getRemainingTimeSec();
        Timeline[] countdownTimeline = new Timeline[1];
        // Create a timeline object with a keyframe event
        countdownTimeline[0] = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                countdownLabel.setText(Integer.toString((int)AlarmList.getFirstAlarm().getRemainingTimeSec()));
                if (remainingTime <= 0) {
                    countdownTimeline[0].stop(); // Stop the timeline when the countdown reaches zero
                }
            }
        }));
        countdownTimeline[0].setCycleCount(Timeline.INDEFINITE); // Set the timeline to repeat indefinitely
        countdownTimeline[0].play(); // Start the timeline
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }

}
