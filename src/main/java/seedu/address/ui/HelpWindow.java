package seedu.address.ui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> implements Initializable {

    public static final String USERGUIDE_URL = "\nhttps://se-education.org/addressbook-level3/UserGuide.html";
    public static final String HELP_MESSAGE = "For more help, Refer to the user guide: " + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    public static final String ADDMETHODS_HELP = "Nested within addmethods are the methods that you " +
            "can use for adding tasks, and people.";
    public static final String ADDPERSON_HELP = "What it does: Adds a person to OfficeConnect. \n\n" +
            "Format: addperson n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…";
    public static final String ADDTASK_HELP = "What it does: Adds a task to OfficeConnect. \n\n" +
            "Format: addtask n/NAME";
    public static final String DELETEMETHODS_HELP = "Nested within deletemethods are the methods that you " +
            "can use for deleting tasks, and people.";
    public static final String DELETEPERSON_HELP = "What it does: Deletes the person at the specified ID.\n\n" +
            "The index refers to the INDEX shown in the displayed person list." +
            "The INDEX must be a positive integer (eg. 1, 2, 3...) \n" +
            "Format: deleteperson INDEX";
    public static final String DELETETASK_HELP = "What it does: Deletes the task at the specified INDEX.\n\n" +
            "The index refers to the INDEX shown in the displayed task list" +
            "The INDEX must be a positive integer (eg. 1, 2, 3...) \n" +
            "Format: deletetask INDEX";
    public static final String LISTMETHODS_HELP = "Nested within are the commands related" +
            " to listing out persons, and tasks.";
    public static final String LISTPERSON_HELP = "What it does: Lists all persons in OfficeConnect. \n\n" +
            "Format: listpersons";
    public static final String LISTTASK_HELP = "What it does: Lists all the tasks in OfficeConnect. \n\n" +
            "Format: listtask";
    public static final String FINDMETHODS_HELP = "Nested within are the commands related to" +
            " finding persons, and tasks.";
    public static final String FINDTASK_HELP = "What it does: Finds the task based on given keyword. \n\n" +
            "Format: findtask KEYWORD";
    public static final String FINDPERSON_HELP = "What it does: Returns a person whose" +
            " name matches the input keyword. \n\n" +
            "The search is case-sensitive, and partial inputs are also accepted. \n" +
            "Format: findperson KEYWORD";
    public static final String ASSIGNMETHODS_HELP = "Nested within are the commands" +
            " related to the assigning of tasks," +
            "to a person.";
    public static final String ASSIGN_HELP = "What it does: Assigns the task at" +
            " specified index to person at specified" +
            "index. The index refers to the index number shown in the displayed persons/task list. \n\n" +
            "Format: assign /task INDEX /person INDEX";

    @FXML
    public TreeView<String> treeView;
    @FXML
    public TreeItem<String> addmethods;

    @FXML
    public TreeItem<String> addperson;

    @FXML
    public TreeItem<String> addtask;

    @FXML
    public TreeItem<String> deletemethods;

    @FXML
    public TreeItem<String> deleteperson;

    @FXML
    public TreeItem<String> deletetask;

    @FXML
    public TreeItem<String> listmethods;

    @FXML
    public TreeItem<String> listperson;

    @FXML
    public TreeItem<String> listtask;

    @FXML
    public TreeItem<String> findmethods;

    @FXML
    public TreeItem<String> findperson;

    @FXML
    public TreeItem<String> findtask;

    @FXML
    public TreeItem<String> assignmethods;

    @FXML
    public TreeItem<String> assign;

    @FXML
    public TextArea method__description;

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        method__description.setWrapText(true);
        treeView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(new ChangeListener<TreeItem<String>>() {
                    @Override
                    public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> oldValue, TreeItem<String> newValue) {
                        TreeItem<String> selectedTreeItem = (TreeItem<String>) newValue;
                        method__description.setText(instructionMap(selectedTreeItem.getValue()));
                    }
                });
        helpMessage.setText(HELP_MESSAGE);
    }

    public String instructionMap(String treeItemValue) {
        switch (treeItemValue) {
            case "Add methods":
                return ADDMETHODS_HELP;
            case "addperson":
                return ADDPERSON_HELP;
            case "addtask":
                return ADDTASK_HELP;
            case "Delete methods":
                return DELETEMETHODS_HELP;
            case "deleteperson":
                return DELETEPERSON_HELP;
            case "deletetask":
                return DELETETASK_HELP;
            case "List methods":
                return LISTMETHODS_HELP;
            case "listperson":
                return LISTPERSON_HELP;
            case "Find methods":
                return FINDMETHODS_HELP;
            case "findperson":
                return FINDPERSON_HELP;
            case "findtask":
                return FINDTASK_HELP;
            case "Assign methods":
                return ASSIGNMETHODS_HELP;
            case "assign":
                return ASSIGN_HELP;
            default:
                return "";
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
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
        getRoot().centerOnScreen();
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
