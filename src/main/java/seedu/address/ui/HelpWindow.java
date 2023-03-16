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

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";


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
        helpMessage.setText(HelpStrings.HELP_MESSAGE);
    }

    /**
     * Generates and displays a String of information based on which TreeItem was clicked in the help interface.
     *
     * @param treeItemValue from the TreeItem's getValue(), we will process the String and return help text.
     */
    public static String instructionMap(String treeItemValue) {
        switch (treeItemValue) {
        case "Add methods":
            return HelpStrings.ADDMETHODS_HELP;
        case "addperson":
            return HelpStrings.ADDPERSON_HELP;
        case "addtask":
            return HelpStrings.ADDTASK_HELP;
        case "Delete methods":
            return HelpStrings.DELETEMETHODS_HELP;
        case "deleteperson":
            return HelpStrings.DELETEPERSON_HELP;
        case "deletetask":
            return HelpStrings.DELETETASK_HELP;
        case "List methods":
            return HelpStrings.LISTMETHODS_HELP;
        case "listperson":
            return HelpStrings.LISTPERSON_HELP;
        case "listtask":
            return HelpStrings.LISTTASK_HELP;
        case "Find methods":
            return HelpStrings.FINDMETHODS_HELP;
        case "findperson":
            return HelpStrings.FINDPERSON_HELP;
        case "findtask":
            return HelpStrings.FINDTASK_HELP;
        case "Assign methods":
            return HelpStrings.ASSIGNMETHODS_HELP;
        case "assign":
            return HelpStrings.ASSIGN_HELP;
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
