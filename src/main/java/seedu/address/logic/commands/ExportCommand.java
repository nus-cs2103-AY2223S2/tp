package seedu.address.logic.commands;

import seedu.address.commons.util.FileUtil;
import seedu.address.model.Model;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.CsvAddressBookStorage;

import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Exports to a csv file at a location specified by the user.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports data into a csv file at "
            + "a location of your choice.";

    private static final String FILE_EXTENSION = "csv";

    @Override
    public CommandResult execute(Model model) {
        JFrame parentComponent = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(parentComponent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File fileToSave = FileUtil.getSelectedFileWithExtension(fileChooser);
            try {
                AddressBookStorage addressBookStorage = new CsvAddressBookStorage(fileToSave.toPath());
                addressBookStorage.saveAddressBook(model.getAddressBook());
                JOptionPane.showMessageDialog(null, "Exported to " + fileToSave);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

        return new CommandResult("Exported to file");
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
