package seedu.library.ui;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.library.commons.core.GuiSettings;
import seedu.library.commons.core.LogsCenter;
import seedu.library.logic.Logic;
import seedu.library.logic.commands.CommandResult;
import seedu.library.logic.commands.exceptions.CommandException;
import seedu.library.logic.parser.exceptions.ParseException;
import seedu.library.model.bookmark.Bookmark;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private BookmarkListPanel bookmarkListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    private ZoomView zoomView;


    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane bookmarkListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;
    @FXML
    private StackPane zoomViewPlaceholder;

    @FXML
    private Label date;



    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());
        // prevent from resizing smaller than specified width
        primaryStage.widthProperty().addListener((o, oldValue, newValue) -> {
            if (newValue.intValue() < 1000) {
                primaryStage.setResizable(false);
                primaryStage.setWidth(1000);
                primaryStage.setResizable(true);
            }
        });
        setAccelerators();
        helpWindow = new HelpWindow();

    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }
    void setDate() {
        LocalDate today = LocalDate.now();
        DayOfWeek day = today.getDayOfWeek();
        date.setText(day.name());
    }
    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        bookmarkListPanel = new BookmarkListPanel(logic.getFilteredBookmarkList());
        bookmarkListPanelPlaceholder.getChildren().add(bookmarkListPanel.getRoot());
        //update right panel when clicking different bookmarks
        bookmarkListPanelPlaceholder.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> handleChange());
        //Prevent user from using arrow keys to change selection
        bookmarkListPanelPlaceholder.addEventFilter(KeyEvent.KEY_PRESSED, event -> event.consume());
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());
        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getLibraryFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());
        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
        zoomView = new ZoomView(bookmarkListPanel.getFirstItem());
        if (bookmarkListPanel.isStartEmpty()) {
            zoomView.hideFields();
        }
        zoomViewPlaceholder.getChildren().add(zoomView.getRoot());






    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Event handler that triggers when the selected bookmark is possibly changed.
     */
    public void handleChange() {
        Bookmark selectedItem = bookmarkListPanel.getSelectedItem();
        if (selectedItem == null || !bookmarkListPanel.isChangedSelect()) {
            handleUpdate();
        } else {
            ZoomView newView = new ZoomView(selectedItem);
            zoomViewPlaceholder.getChildren().add(newView.getRoot());
            if (zoomView.getIsGuiAction() || bookmarkListPanel.isChangedSelect()) {
                resultDisplay.clearDisplay();
            }
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    /**
     * updates view on right panel when interacted with CLI or text command
     */
    private void separateUpdate() {
        Bookmark selectedBookmark = logic.getSelectedBookmark();
        int selectedIndex = logic.getSelectedIndex();
        if (selectedBookmark != null && selectedIndex != -1) {
            //Update with bookmark information
            zoomView = new ZoomView(selectedBookmark);
            zoomViewPlaceholder.getChildren().add(zoomView.getRoot());
        } else {
            zoomView = new ZoomView();
            zoomViewPlaceholder.getChildren().add(zoomView.getRoot());

        }
    }


    /**
     * updates view on right panel when interacted with mouse
     */
    private void handleUpdate() {
        Bookmark currentSelect = bookmarkListPanel.getSelectedItem();
        if (currentSelect == null) {
            zoomView = new ZoomView();
            zoomViewPlaceholder.getChildren().add(zoomView.getRoot());
        } else {
            bookmarkListPanel.unSelect();
            zoomView = new ZoomView();
            zoomViewPlaceholder.getChildren().add(zoomView.getRoot());
        }
    }

    public BookmarkListPanel getBookmarkListPanel() {
        return bookmarkListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.library.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {

            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }
            if (commandResult.isUpdate()) {
                handleUpdate();
            }
            int index = logic.getSelectedIndex();
            if (index != -1) {
                bookmarkListPanel.select(index);
            }
            separateUpdate();

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
