package seedu.address.logic.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import seedu.address.commons.util.FileUtil;
import seedu.address.model.Model;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.CsvAddressBookStorage;

/**
 * Exports to a csv file at a location specified by the user.
 */
public class ExportCommand extends Command {

    public static List<String> COMMAND_WORDS = new ArrayList<String>(Arrays.asList("export", "exp"));

    private static final Path p = Paths.get("data", "exportCommand.txt");


    public static final String MESSAGE_USAGE = COMMAND_WORDS + ": Exports data into a csv file at "
            + "a location of your choice.";

    public static final String FILE_DESCRIPTION = "CSV Files";

    public static final String[] FILE_EXTENSIONS = new String[]{"csv"};

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
            AddressBookStorage addressBookStorage = new CsvAddressBookStorage(fileToSave.toPath());
            addressBookStorage.saveAddressBook(model.getAddressBook());
            JOptionPane.showMessageDialog(null, "Exported to " + fileToSave);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return new CommandResult("Exported to file", false, false);
    }

    public static void saveWords() {
        if (!Files.exists(p)) {
            try {
                Files.createFile(p);
            } catch (java.io.IOException ignored) {}
        }

        try {
            FileOutputStream fos = new FileOutputStream(p.toFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(COMMAND_WORDS);
            oos.close();
        } catch (IOException ignored) {}
    }

    public static void loadWords() {
        if (!Files.exists(p)) {
            try {
                Files.createFile(p);
            } catch (java.io.IOException ignored) {}
        }
        try {
            FileInputStream fis = new FileInputStream(p.toFile());
            ObjectInputStream ois = new ObjectInputStream(fis);
            COMMAND_WORDS = (List<String>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException ignored) {}
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
