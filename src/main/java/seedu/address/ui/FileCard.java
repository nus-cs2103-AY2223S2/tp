package seedu.address.ui;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.files.FilesManager;


/**
 * An UI component that displays information of a {@code Person}.
 */
public class FileCard extends UiPart<Region> {

    private static final String FXML = "FileListItem.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final UiFile file;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label fileName;
    @FXML
    private Button view;
    @FXML
    private Button delete;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public FileCard(UiFile file, int displayedIndex) {
        super(FXML);
        this.file = file;
        id.setText(displayedIndex + ". ");
        fileName.setText(file.getFileName());
        FilesManager filesManager = new FilesManager(file.getPerson());
        view.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                filesManager.displayFile(file.getFileName());
            }
        });
        delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Runnable runnable = () -> {
                    int response = JOptionPane.showConfirmDialog(null,
                                "Are you sure you want to delete this file?",
                                "Confirm Delete",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        filesManager.deleteFile(file.getFileName());
                        // Perform the UI update on the JavaFX Application Thread
                        Platform.runLater(() -> {
                            file.delete();
                        });
                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();

            }
        });
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FileCard)) {
            return false;
        }

        // state check
        FileCard card = (FileCard) other;
        return id.getText().equals(card.id.getText())
            && file.equals(card.file);
    }
}
