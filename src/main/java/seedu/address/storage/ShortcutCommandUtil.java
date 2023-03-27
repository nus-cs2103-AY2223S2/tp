package seedu.address.storage;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MassOpCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.ShortcutCommand;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.commands.UndoCommand;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ShortcutCommandUtil {
    public static final Path ADD_PATH = Paths.get("data", "addCommand.txt");
    public static final Path CLEAR_PATH = Paths.get("data", "clearCommand.txt");
    public static final Path DELETE_PATH = Paths.get("data", "deleteCommand.txt");
    public static final Path DELETE_TAG_PATH = Paths.get("data", "deleteTagCommand.txt");
    public static final Path EDIT_PATH = Paths.get("data", "editCommand.txt");
    public static final Path EXIT_PATH = Paths.get("data", "exitCommand.txt");
    public static final Path EXPORT_PATH = Paths.get("data", "exportCommand.txt");
    public static final Path FILTER_PATH = Paths.get("data", "filterCommand.txt");
    public static final Path FIND_PATH = Paths.get("data", "findCommand.txt");
    public static final Path HELP_PATH = Paths.get("data", "helpCommand.txt");
    public static final Path IMPORT_PATH = Paths.get("data", "importCommand.txt");
    public static final Path LIST_PATH = Paths.get("data", "listCommand.txt");
    public static final Path MASS_OP_PATH = Paths.get("data", "massOpCommand.txt");
    public static final Path REDO_PATH = Paths.get("data", "redoCommand.txt");
    public static final Path SHORTCUT_PATH = Paths.get("data", "listCommand.txt");
    public static final Path TAG_PATH = Paths.get("data", "tagCommand.txt");
    public static final Path UNDO_PATH = Paths.get("data", "undoCommand.txt");

    public static void loadShortcuts() {
        AddCommand.COMMAND_WORDS = loadWords(ADD_PATH, AddCommand.COMMAND_WORDS);
        ClearCommand.COMMAND_WORDS = loadWords(CLEAR_PATH, ClearCommand.COMMAND_WORDS);
        DeleteCommand.COMMAND_WORDS = loadWords(DELETE_PATH, DeleteCommand.COMMAND_WORDS);
        DeleteTagCommand.COMMAND_WORDS = loadWords(DELETE_TAG_PATH, DeleteTagCommand.COMMAND_WORDS);
        EditCommand.COMMAND_WORDS = loadWords(EDIT_PATH, EditCommand.COMMAND_WORDS);
        ExitCommand.COMMAND_WORDS = loadWords(EXIT_PATH, ExitCommand.COMMAND_WORDS);
        ExportCommand.COMMAND_WORDS = loadWords(EXPORT_PATH, ExportCommand.COMMAND_WORDS);
        FilterCommand.COMMAND_WORDS = loadWords(FILTER_PATH, FilterCommand.COMMAND_WORDS);
        FindCommand.COMMAND_WORDS = loadWords(FIND_PATH, FindCommand.COMMAND_WORDS);
        HelpCommand.COMMAND_WORDS = loadWords(HELP_PATH, HelpCommand.COMMAND_WORDS);
        ImportCommand.COMMAND_WORDS = loadWords(IMPORT_PATH, ImportCommand.COMMAND_WORDS);
        ListCommand.COMMAND_WORDS = loadWords(LIST_PATH, ListCommand.COMMAND_WORDS);
        MassOpCommand.COMMAND_WORDS = loadWords(MASS_OP_PATH, MassOpCommand.COMMAND_WORDS);
        RedoCommand.COMMAND_WORDS = loadWords(REDO_PATH, RedoCommand.COMMAND_WORDS);
        ShortcutCommand.COMMAND_WORDS = loadWords(SHORTCUT_PATH, ShortcutCommand.COMMAND_WORDS);
        TagCommand.COMMAND_WORDS = loadWords(TAG_PATH, TagCommand.COMMAND_WORDS);
        UndoCommand.COMMAND_WORDS = loadWords(UNDO_PATH, UndoCommand.COMMAND_WORDS);
    }

    public static void saveWords(Path p, List<String> COMMAND_WORDS) {
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

    public static List<String> loadWords(Path p, List<String> COMMAND_WORDS) {
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
        return COMMAND_WORDS;
    }
}
