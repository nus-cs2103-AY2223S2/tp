package seedu.address.ui;

import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "\nhttps://ay2223s2-cs2103-f10-1.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "For more help, Refer to the user guide: " + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    private static final String ADDMETHODS_HELP = "Nested within addmethods are the methods that you "
            + "can use for adding tasks, and people.";
    private static final String ADDPERSON_HELP = "What it does: Adds a person to OfficeConnect. \n\n"
            + "Format: addperson n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…";
    private static final String ADDTASK_HELP = "What it does: Adds a task to OfficeConnect. \n\n"
            + "Format: addtask n/NAME";
    private static final String DELETEMETHODS_HELP = "Nested within deletemethods are the methods that you "
            + "can use for deleting tasks, and people.";
    private static final String DELETEPERSON_HELP = "What it does: Deletes the person at the specified ID.\n\n"
            + "The index refers to the INDEX shown in the displayed person list."
            + "The INDEX must be a positive integer (eg. 1, 2, 3...) \n\n"
            + "Format: deleteperson INDEX";
    private static final String DELETETASK_HELP = "What it does: Deletes the task at the specified INDEX.\n\n"
            + "The index refers to the INDEX shown in the displayed task list"
            + "The INDEX must be a positive integer (eg. 1, 2, 3...) \n\n"
            + "Format: deletetask INDEX";
    private static final String LISTMETHODS_HELP = "Nested within are the commands related"
            + " to listing out persons, and tasks.";
    private static final String LISTPERSON_HELP = "What it does: Lists all persons in OfficeConnect. \n\n"
            + "Format: listpersons";
    private static final String LISTTASK_HELP = "What it does: Lists all the tasks in OfficeConnect. \n\n"
            + "Format: listtask";
    private static final String FINDMETHODS_HELP = "Nested within are the commands related to"
            + " finding persons, and tasks.";
    private static final String FINDTASK_HELP = "What it does: Finds the task based on given keyword. \n\n"
            + "Format: findtask KEYWORD";
    private static final String FINDPERSON_HELP = "What it does: Returns a person whose"
            + " name matches the input keyword. \n\n"
            + "The search is case-sensitive, and partial inputs are also accepted. \n\n"
            + "Format: findperson KEYWORD";
    private static final String ASSIGNMETHODS_HELP = "Nested within are the commands"
            + " related to the assigning of tasks,"
            + " to a person.";
    private static final String ASSIGN_HELP = "What it does: Assigns the task at"
            + " specified index to person at specified"
            + "index. The index refers to the index number shown in the displayed persons/task list. \n\n"
            + "Format: assign /task INDEX /person INDEX";

    @FXML
    private TreeView<String> treeView;

    @FXML
    private TextArea methodDescription;

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        methodDescription.setWrapText(true);
        treeView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(new ChangeListener<TreeItem<String>>() {
                    @Override
                    public void changed(ObservableValue<? extends TreeItem<String>> observable,
                                        TreeItem<String> oldValue, TreeItem<String> newValue) {
                        TreeItem<String> selectedTreeItem = (TreeItem<String>) newValue;
                        methodDescription.setText(instructionMap(selectedTreeItem.getValue()));
                    }
                });
        helpMessage.setText(HELP_MESSAGE);
    }

    /**
     * Generates and displays a String of information based on which TreeItem was clicked in the help interface.
     *
     * @param treeItemValue from the TreeItem's getValue(), we will process the String and return help text.
     */
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
        case "listtask":
            return LISTTASK_HELP;
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
