package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;


/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {
    public static final String USERGUIDE_URL = "https://ay2223s2-cs2103t-t13-3.github.io/tp/UserGuide.html";
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";
    private final ObservableList<HelpCommand> data =
            FXCollections.observableArrayList(
                    new HelpCommand("Add", "add r/ROLE c/COMPANY_NAME e/COMPANY_EMAIL s/STATUS\n"),
                    new HelpCommand("List", "list\n"),
                    new HelpCommand("Clear", "clear\n"),
                    new HelpCommand("Delete", "delete INDEX\n"),
                    new HelpCommand("Edit", "edit INDEX [r/ROLE] [c/COMPANY_NAME] [e/COMPANY EMAIL] [s/STATUS]\n"),
                    new HelpCommand("Find", "find [search term] [r/ROLE] [c/COMPANY_NAME] [s/STATUS]\n"),
                    new HelpCommand("Help", "help\n")
            );
    @FXML
    private Button copyButton;
    @FXML
    private TableColumn<HelpCommand, String> commandColumn;
    @FXML
    private TableColumn<HelpCommand, String> formatColumn;
    @FXML
    private TableView<HelpCommand> helpTable;


    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpTable.setEditable(true);
        commandColumn.setCellValueFactory(
                new PropertyValueFactory<>("command"));
        formatColumn.setCellValueFactory(
                new PropertyValueFactory<>("format"));
        helpTable.setItems(data);
        helpTable.getColumns().addAll(commandColumn, formatColumn);
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


