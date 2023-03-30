package seedu.address.logic.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import seedu.address.commons.util.FileUtil;
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
            + "a location of your choice.";

    public static final String MESSAGE_SUCCESS = "Exported to file";

    public static final String FILE_DESCRIPTION = "CSV Files";

    public static final String[] FILE_EXTENSIONS = new String[]{"csv"};

    private boolean isAllEnabled;

    public ExportCommand(boolean isAllEnabled) {
        this.isAllEnabled = isAllEnabled;
    }

    @Override
    public CommandResult execute(Model model) {
        JFrame parentComponent = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(FILE_DESCRIPTION, FILE_EXTENSIONS);
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showSaveDialog(parentComponent);

        if (returnVal != JFileChooser.APPROVE_OPTION) {
            return new CommandResult("FileChooser closed", false, false);
        }

        File fileToSave = FileUtil.getSelectedFileWithExtension(fileChooser);
        try {
            CsvAddressBookStorage addressBookStorage = new CsvAddressBookStorage(fileToSave.toPath());
            if (isAllEnabled) {
                addressBookStorage.saveAddressBook(model.getAddressBook());
            } else {
                addressBookStorage.saveAddressBook(model.getFilteredPersonList());
            }
            JOptionPane.showMessageDialog(null, "Exported to " + fileToSave);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return new CommandResult(MESSAGE_SUCCESS, false, false);
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
