package codoc.ui;

import java.util.logging.Logger;

import codoc.commons.core.LogsCenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s2-cs2103t-f12-2.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Unsure of where to begin? "
            + "You may refer to the user guide for a quick tutorial!\n" + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private TableView<CommandExample> tvtable;

    @FXML
    private TableColumn<CommandExample, String> commandCol;

    @FXML
    private TableColumn<CommandExample, String> exampleCol;

    @FXML
    private final CommandExample[] examples = {
        new CommandExample("Add", "add n/Bob Sim y/2 c/1 e/e0823741@nus.edu g/bobabob "
                + "l/linkedin.com/in/bom-sim-086g93847 m/ay2223s2 CS2103T m/AY2223S2 cs2101 s/python s/java"),
        new CommandExample("View contact", "view 3"),
        new CommandExample("View tab", "view c, view m, view s"),
        new CommandExample("Edit contact in the right panel", "edit n/David m+/AY2223S2 CS2109S s-/python"),
        new CommandExample("Find by attributes", "find n/David c/Computer Science m/CS2109S s/java"),
        new CommandExample("List the full list of contacts", "list"),
        new CommandExample("Delete contact", "delete 3"),
        new CommandExample("Clear all contacts", "clear"),
        new CommandExample("View user guide and command list", "help"),
        new CommandExample("Exits the application", "exit")
    };

    private final ObservableList<CommandExample> commandExamples = FXCollections.observableArrayList(examples);
    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        commandCol.setCellValueFactory(new PropertyValueFactory<>("Command"));
        exampleCol.setCellValueFactory(new PropertyValueFactory<>("Example"));
        commandCol.setStyle("-fx-text-fill: black");
        exampleCol.setStyle("-fx-text-fill: black");
        commandCol.setResizable(false);
        exampleCol.setResizable(false);
        tvtable.setItems(commandExamples);
        tvtable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tvtable.getSelectionModel().setCellSelectionEnabled(true);
        tvtable.setOnMouseClicked(this::copyExample);
        wrapTextinCell();
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

    private void copyExample(MouseEvent event) {
        if (event.getClickCount() >= 1) {
            TablePosition<?, ?> pos = tvtable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            Object data = pos.getTableColumn().getCellObservableValue(row).getValue();
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(data.toString());
            clipboard.setContent(content);
        }
    }

    private void wrapTextinCell() {
        exampleCol.setCellFactory(tc -> {
            TableCell<CommandExample, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(exampleCol.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
        commandCol.setCellFactory(tc -> {
            TableCell<CommandExample, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(commandCol.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
    }
}
