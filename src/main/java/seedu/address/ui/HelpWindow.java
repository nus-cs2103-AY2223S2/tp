package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    /**
     * Text to be displayed when the user gets help.
     */
    public static final String AB3_NAME = "AddressBook v1.3 2023\n\n";
    public static final String OPTIONS = "Usage: \n"
            + "  add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]             add a new contact\n"
            + "  edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]     edit details of an existing contact\n"
            + "  find KEYWORD [MORE_KEYWORDS]                                    find contact by keyword\n"
            + "  delete INDEX                                                    delete contact by index\n"
            + "  tag INDEX TAG_NAME                                              add tag to a contact\n"
            + "  delete_tag [n/NAME] [t/TAG_NAME]                                delete tag of a contact\n"
            + "  filter [t/TAG_NAME]                                             show only contacts with tag\n"
            + "  load [f/FILENAME]                                               load from CSV file\n"
            + "  export [shown|all]                                              export contacts to CSV file\n"
            + "  import [combine|reset]                                          import contacts from CSV file\n"
            + "  undo                                                            undo last operation\n"
            + "  redo                                                            redo undone operation\n"
            + "  mass tag NEW_TAG [t/EXISTING_TAG]                               add tag to users with existing tag\n"
            + "  shortcut COMMAND ABBREVIATION                                   add shortcut for command\n"
            + "  clear                                                           clear address book\n"
            + "  exit                                                            exit the program\n"
            + "  freeze                                                          freezes E-Lister\n"
            + "  unfreeze                                                        unfreezes E-Lister\n"
            + "\n";

    public static final String EXAMPLE = "Example:   "
            + "add n/John Doe p/98765432 e/johnd@example.com i/4000 a/John street, block 123, #01-01\n\n";

    public static final String USERGUIDE_URL = "https://se-education.org/addressbook-level3/UserGuide.html";
    public static final String HELP_MESSAGE = AB3_NAME + OPTIONS + EXAMPLE
        + "For more information, get more help from the online user guide.";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

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
        helpMessage.setText(HELP_MESSAGE);
        helpMessage.setFont(Font.font(java.awt.Font.MONOSPACED));
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
     * Opens the URL in web browser.
     */
    @FXML
    private void openUserGuide() {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(USERGUIDE_URL));
                logger.fine("Opened user guide in web browser.");
            }
        } catch (IOException | URISyntaxException e) {
            logger.fine("Failed to open user guide in web browser.");
        }
    }
}
