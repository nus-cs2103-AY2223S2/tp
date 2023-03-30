package seedu.address.ui;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DarkModeCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteEventCommand;
import seedu.address.logic.commands.EditContactCommand;
import seedu.address.logic.commands.EditUserCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FavoriteCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.LightModeCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.TabCommand;
import seedu.address.logic.commands.TagEventCommand;
import seedu.address.logic.commands.UnTagEventCommand;
import seedu.address.logic.commands.UnfavoriteCommand;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USER_GUIDE_URL = "https://ay2223s2-cs2103t-f12-3.github.io/tp/UserGuide.html";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;
    @FXML
    private Label helpMessageUrl;
    @FXML
    private VBox commandsContainer;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        fillUrl();
        fillCommandsSummary();
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
        url.putString(USER_GUIDE_URL);
        clipboard.setContent(url);
    }

    private void fillUrl() {
        helpMessageUrl.setText(USER_GUIDE_URL);
    }

    private void fillCommandsSummary() {
        List<String> helpTexts = List.of(
                AddCommand.MESSAGE_USAGE,
                AddEventCommand.MESSAGE_USAGE,
                ClearCommand.MESSAGE_USAGE,
                DarkModeCommand.MESSAGE_USAGE,
                DeleteCommand.MESSAGE_USAGE,
                DeleteEventCommand.MESSAGE_USAGE,
                EditContactCommand.MESSAGE_USAGE,
                EditUserCommand.MESSAGE_USAGE,
                ExitCommand.MESSAGE_USAGE,
                FavoriteCommand.MESSAGE_USAGE,
                FindCommand.MESSAGE_USAGE,
                HelpCommand.MESSAGE_USAGE,
                LightModeCommand.MESSAGE_USAGE,
                ListCommand.MESSAGE_USAGE,
                SelectCommand.MESSAGE_USAGE,
                TabCommand.MESSAGE_USAGE,
                TagEventCommand.MESSAGE_USAGE,
                UnfavoriteCommand.MESSAGE_USAGE,
                UnTagEventCommand.MESSAGE_USAGE
        );
        commandsContainer.getChildren().addAll(helpTexts.stream()
                .map(text -> {
                    Label label = new Label(text);
                    label.setWrapText(true);
                    return label;
                })
                .collect(Collectors.toList()));
    }
}
