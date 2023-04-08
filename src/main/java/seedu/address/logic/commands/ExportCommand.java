package seedu.address.logic.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import seedu.address.model.Model;
import seedu.address.storage.CsvAddressBookStorage;

/**
 * Exports to a csv file at a location specified by the user.
 */
public class ExportCommand extends Command {
    //CHECKSTYLE.OFF: VisibilityModifier
    public static List<String> commandWords = new ArrayList<String>(Arrays.asList("export", "exp"));
    //CHECKSTYLE.ON: VisibilityModifier

    public static final String MESSAGE_USAGE = commandWords + ": Exports data into a csv file at "
            + "a location of your choice.\n"
            + "Type \"export\" or \"export shown\" to export only the filtered contacts shown.\n"
            + "Type \"export all\" to export all contacts, even if they are not shown in the filtered list.";

    public static final String MESSAGE_SUCCESS = "Exported to file";

    public static final String MESSAGE_FILECHOOSER_CLOSED = "FileChooser closed";

    public static final String MESSAGE_EXPORTED_TO = "Exported to %s";

    public static final String ERROR_WHILE_EXPORTING = "Error while exporting to: %s";

    public static final String FILE_DESCRIPTION = "CSV Files";

    public static final String FILE_EXTENSION = "*.csv";

    private boolean isAllEnabled;

    public ExportCommand(boolean isAllEnabled) {
        this.isAllEnabled = isAllEnabled;
    }

    @Override
    public CommandResult execute(Model model) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(FILE_DESCRIPTION, FILE_EXTENSION)
        );
        File fileToSave = fileChooser.showSaveDialog(model.getPrimaryStage());

        if (fileToSave == null) {
            return new CommandResult(MESSAGE_FILECHOOSER_CLOSED, false, false);
        }

        String successMsg = String.format(MESSAGE_EXPORTED_TO, fileToSave);
        String errorMsg = String.format(ERROR_WHILE_EXPORTING, fileToSave);

        try {
            CsvAddressBookStorage addressBookStorage = new CsvAddressBookStorage(fileToSave.toPath());
            if (isAllEnabled) {
                addressBookStorage.saveAddressBook(model.getAddressBook());
            } else {
                addressBookStorage.saveAddressBook(model.getFilteredPersonList());
            }
            showAlert(successMsg, Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            showAlert(errorMsg + "\n" + e, Alert.AlertType.ERROR);
            return new CommandResult(errorMsg + "\n" + e, false, false);
        }

        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }

    /**
     * Displays an alert to inform user of an error or success while exporting.
     * @param message Message to be displayed.
     * @param alertType To indicate if it is an error or just an info message.
     */
    public void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType, message);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ExportCommand)) {
            return false;
        }

        ExportCommand e = (ExportCommand) other;
        return true;
    }
}
