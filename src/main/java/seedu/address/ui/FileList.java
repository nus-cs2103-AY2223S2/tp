package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Panel containing the list of persons.
 */
public class FileList extends UiPart<Region> {
    private static final String FXML = "DetailDisplay.fxml";
    private final Logger logger = LogsCenter.getLogger(FileList.class);
    @FXML
    private ListView<UiFile> viewDisplay;
    private MainWindow mainWindow;
    private DetailDisplay detailDisplay;
    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public FileList(ObservableList<UiFile> fileList, MainWindow mainWindow) {
        super(FXML);
        viewDisplay.setItems(fileList);
        viewDisplay.setCellFactory(listView -> new FileListViewCell());
        this.mainWindow = mainWindow;
        this.detailDisplay = mainWindow.getDetailDisplay();
    }
    @FXML
    private void displayFile() throws CommandException, ParseException {
        try {
            int targetIndex = viewDisplay.getSelectionModel().getSelectedIndex() + 1;
            int size = viewDisplay.getItems().size();
            if (targetIndex > 0 && targetIndex <= size) {
                ObservableFile.clickGenerate();
            } else {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
        } catch (CommandException e) {
            logger.info("Invalid command: " + "Something wrong happened when clicking the person.");
        }
    }
    /**
     * Custom {@code ListCell} that displays the graphics of a {@code File} using a {@code PersonCard}.
     */

    static class FileListViewCell extends ListCell<UiFile> {
        @Override
        protected void updateItem(UiFile file, boolean empty) {
            super.updateItem(file, empty);
            if (empty || file == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FileCard(file, getIndex() + 1).getRoot());
            }
        }
    }
}
