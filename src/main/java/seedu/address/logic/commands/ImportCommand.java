package seedu.address.logic.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import seedu.address.commons.util.FileUtil;
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

    public static final String MESSAGE_USAGE = commandWords + ": Imports customer data from an existing dataset.";

    public static final String FILE_DESCRIPTION = "CSV Files";

    public static final String[] FILE_EXTENSIONS = new String[]{"csv"};

    @Override
    public CommandResult execute(Model model) {
        JFrame parentComponent = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(FILE_DESCRIPTION, FILE_EXTENSIONS);
        fileChooser.setFileFilter(filter);

        boolean isValidFile = false;

        while (!isValidFile) {
            int returnVal = fileChooser.showOpenDialog(parentComponent);

            if (returnVal != JFileChooser.APPROVE_OPTION) {
                return new CommandResult("FileChooser closed", false, false);
            }

            File fileToImport = FileUtil.getSelectedFileWithExtension(fileChooser);
            if (!fileToImport.exists()) {
                JOptionPane.showMessageDialog(null, "The file you have chosen does not exist:  "
                        + fileToImport);
                isValidFile = false;
                continue;
            }

            isValidFile = true;

            try {
                AddressBookStorage addressBookStorage = new CsvAddressBookStorage(fileToImport.toPath());
                Optional<ReadOnlyAddressBook> newAddressBook = addressBookStorage.readAddressBook();
                if (newAddressBook.isEmpty()) {
                    String errorMsg = "Error while importing from: " + fileToImport;
                    JOptionPane.showMessageDialog(null, errorMsg);
                    return new CommandResult(errorMsg, false, false);
                }
                model.setAddressBook(newAddressBook.get());
                JOptionPane.showMessageDialog(null, "You have imported from:  " + fileToImport);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

        return new CommandResult("Imported data from file", true, false);
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
