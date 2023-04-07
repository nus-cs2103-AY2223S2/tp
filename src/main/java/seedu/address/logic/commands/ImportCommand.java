package seedu.address.logic.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.CsvAddressBookStorage;

/**
 * Exports to a csv file at a location specified by the user.
 */
public class ImportCommand extends Command {
    //CHECKSTYLE.OFF: VisibilityModifier
    public static List<String> commandWords = new ArrayList<String>(Arrays.asList("import", "i"));
    //CHECKSTYLE.ON: VisibilityModifier

    public static final String MESSAGE_USAGE = commandWords + ": Imports customer data from an existing dataset.\n"
            + "Type \"import\" or \"import combine\" to combine the existing data with the imported dataset.\n"
            + "Type \"import reset\" to reset the existing data to the imported dataset.";

    public static final String MESSAGE_SUCCESS = "Imported data from file";

    public static final String MESSAGE_FILECHOOSER_CLOSED = "FileChooser closed";

    public static final String MESSAGE_RESET_FEEDBACK = "Data has been reset to imported data.";

    public static final String ERROR_FILE_NOT_FOUND = "The file you have chosen does not exist:  %s";

    public static final String ERROR_WHILE_IMPORTING = "Error while importing from: %s";

    public static final String SUCCESS_IMPORT = "Importing from: %s";

    public static final String FILE_DESCRIPTION = "CSV Files";

    public static final String FILE_EXTENSION = "*.csv";

    private boolean isResetEnabled;

    public ImportCommand(boolean isResetEnabled) {
        this.isResetEnabled = isResetEnabled;
    }

    @Override
    public CommandResult execute(Model model) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(FILE_DESCRIPTION, FILE_EXTENSION)
        );

        boolean isValidFile = false;

        while (!isValidFile) {
            File fileToImport = fileChooser.showOpenDialog(model.getPrimaryStage());

            if (fileToImport == null) {
                return new CommandResult(MESSAGE_FILECHOOSER_CLOSED, false, false);
            }

            if (!fileToImport.exists()) {
                showAlert(String.format(ERROR_FILE_NOT_FOUND, fileToImport), Alert.AlertType.ERROR);
                isValidFile = false;
                continue;
            }

            isValidFile = true;
            String successMsg = String.format(SUCCESS_IMPORT, fileToImport);
            String errorMsg = String.format(ERROR_WHILE_IMPORTING, fileToImport);

            try {
                AddressBookStorage addressBookStorage = new CsvAddressBookStorage(fileToImport.toPath());
                Optional<ReadOnlyAddressBook> newAddressBook = addressBookStorage.readAddressBook();
                if (newAddressBook.isEmpty()) {
                    showAlert(errorMsg, Alert.AlertType.ERROR);
                    return new CommandResult(errorMsg, false, false);
                }

                String feedback;

                if (isResetEnabled) {
                    model.setAddressBook(newAddressBook.get());
                    feedback = MESSAGE_RESET_FEEDBACK;
                } else {
                    feedback = model.addPersonsFromAddressBook(newAddressBook.get());
                }
                showAlert(successMsg + "\n" + feedback, Alert.AlertType.INFORMATION);

            } catch (DataConversionException | IOException e) {
                showAlert(errorMsg + "\n" + e, Alert.AlertType.ERROR);
                return new CommandResult(errorMsg + "\n" + e, false, false);
            }
        }

        return new CommandResult(MESSAGE_SUCCESS, true, false);
    }

    /**
     * Displays an alert to inform user of an error or success while importing.
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
        if (!(other instanceof ImportCommand)) {
            return false;
        }

        ImportCommand e = (ImportCommand) other;
        return true;
    }
}
