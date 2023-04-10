package taa.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import taa.commons.core.LogsCenter;

/**
 * Controller for a help page
 * @@author bojie3-reused
 * Reused from https://github.com/AY2223S1-CS2103T-W17-1/tp/blob/master/src/main/java/seedu/travelr/ui/HelpWindow.java
 * by using the same UI appearance, but modified all the contents and URL to fit TAA
 */
public class HelpWindow extends UiPart<Stage> {
    public static final String WELCOME_MESSAGE = "Welcome to TAA!!";
    public static final String MESSAGE = "The following table contains a summary of TAA's commands";
    public static final String COMMAND_SUMMARY =
            "+-----------------------------+---------------------------------------------------+\n"
                    + "| Action                      | Format                                            |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Add a student               | add_student n/STUDENT_NAME cl/CLASS_NAME          |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Delete a student            | delete_student STUDENT_INDEX                      |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Edit student                | edit_student STD_INDEX [n/STD_NAME] [cl/CLS_NAME] |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Create ClassList            | create_class LIST_NAME [STUDENT_NAMES]            |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Group student               | rand_grp CLASS_LIST GROUP_SIZE                    |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Create an alarm             | add_alarm t/TIME c/COMMENT                        |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Delete an alarm             | delete_alarm INDEX                                |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| List alarms                 | list_alarms                                       |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| View class statistics       | class_stats st/FIELD [as/ASSIGNMENT_NAME]         |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| List all student            | list                                              |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Find a student              | find KEYWORD [MORE_KEYWORDS]                      |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Mark Attendance             | markAtd STUDENT_ID w/WEEK_NUMBER                  |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Unmark Attendance           | unmarkAtd STUDENT_ID w/WEEK_NUMBER                |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Insert participation points | insertPP STUDENT_ID w/WEEK_NUMBER pp/POINTS       |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Add Assignment              | add_asgn n/NAME [m/TOTAL_MARKS]                   |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Delete Assignment           | delete n/ASSIGNMENT_ID                            |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Grade Assignment            | grade n/NAME i/STUDENT_ID m/SCORE [late/]         |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Ungrade Assignment          | ungrade n/ASSIGNMENT_ID i/STUDENT_ID              |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| List Assignment             | list_asgn                                         |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Import CSV data             | import [-force] FILE_PATH                         |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Export CSV data             | export [-force] FILE_PATH                         |\n"
                    + "+-----------------------------+---------------------------------------------------+\n";

    public static final String USERGUIDE_URL = "https://ay2223s2-cs2103t-t14-4.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "For more Information, "
            + "please refer to the user guide: " + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label message;

    //@@author AY2223S1-CS2103T-W17-4

    @FXML
    private Label welcomeMessage;

    @FXML
    private Label userGuideMessage;

    @FXML
    private Label commandSummary;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        message.setText(MESSAGE);
        userGuideMessage.setText(HELP_MESSAGE);
        commandSummary.setText(COMMAND_SUMMARY);
        welcomeMessage.setText(WELCOME_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
        setKeys();
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().setHeight(650);
        getRoot().setMaxWidth(900);
        //getRoot().setMaximized(true);
        getRoot().centerOnScreen();
    }

    /**
     * Bind keys as shortcuts for help window.
     */
    private void setKeys() {
        //Bind ESC to minimized help window
        getRoot().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (KeyCode.ESCAPE == event.getCode()) {
                hide();
            }
        });

        //Bind C to copy user guide url
        getRoot().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (KeyCode.C == event.getCode()) {
                copyUrl();
            }
        });
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
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
