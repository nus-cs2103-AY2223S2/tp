package seedu.address.storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FreezeCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MassOpCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.ShortcutCommand;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.UnfreezeCommand;

/**
 * Utility class providing helper functions to load and save the user's commands from files.
 */
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
    public static final Path FREEZE_PATH = Paths.get("data", "freezeCommand.txt");
    public static final Path HELP_PATH = Paths.get("data", "helpCommand.txt");
    public static final Path IMPORT_PATH = Paths.get("data", "importCommand.txt");
    public static final Path LIST_PATH = Paths.get("data", "listCommand.txt");
    public static final Path MASS_OP_PATH = Paths.get("data", "massOpCommand.txt");
    public static final Path REDO_PATH = Paths.get("data", "redoCommand.txt");
    public static final Path SHORTCUT_PATH = Paths.get("data", "shortcutCommand.txt");
    public static final Path TAG_PATH = Paths.get("data", "tagCommand.txt");
    public static final Path UNDO_PATH = Paths.get("data", "undoCommand.txt");
    public static final Path UNFREEZE_PATH = Paths.get("data", "unfreezeCommand.txt");

    /**
     * Loads user-defined shortcuts from files.
     */
    public static void loadShortcuts() {
        AddCommand.commandWords = loadWords(ADD_PATH, AddCommand.commandWords);
        ClearCommand.commandWords = loadWords(CLEAR_PATH, ClearCommand.commandWords);
        DeleteCommand.commandWords = loadWords(DELETE_PATH, DeleteCommand.commandWords);
        DeleteTagCommand.commandWords = loadWords(DELETE_TAG_PATH, DeleteTagCommand.commandWords);
        EditCommand.commandWords = loadWords(EDIT_PATH, EditCommand.commandWords);
        ExitCommand.commandWords = loadWords(EXIT_PATH, ExitCommand.commandWords);
        ExportCommand.commandWords = loadWords(EXPORT_PATH, ExportCommand.commandWords);
        FilterCommand.commandWords = loadWords(FILTER_PATH, FilterCommand.commandWords);
        FreezeCommand.commandWords = loadWords(FREEZE_PATH, FreezeCommand.commandWords);
        FindCommand.commandWords = loadWords(FIND_PATH, FindCommand.commandWords);
        HelpCommand.commandWords = loadWords(HELP_PATH, HelpCommand.commandWords);
        ImportCommand.commandWords = loadWords(IMPORT_PATH, ImportCommand.commandWords);
        ListCommand.commandWords = loadWords(LIST_PATH, ListCommand.commandWords);
        MassOpCommand.commandWords = loadWords(MASS_OP_PATH, MassOpCommand.commandWords);
        RedoCommand.commandWords = loadWords(REDO_PATH, RedoCommand.commandWords);
        ShortcutCommand.commandWords = loadWords(SHORTCUT_PATH, ShortcutCommand.commandWords);
        TagCommand.commandWords = loadWords(TAG_PATH, TagCommand.commandWords);
        UndoCommand.commandWords = loadWords(UNDO_PATH, UndoCommand.commandWords);
        UnfreezeCommand.commandWords = loadWords(UNFREEZE_PATH, UnfreezeCommand.commandWords);
    }

    /**
     * Check if a shortcut to be used already exists.
     * @return whether the shortcut exists
     */
    public static boolean checkIfShortcutExists(String shortForm) {
        return AddCommand.commandWords.contains(shortForm)
                || ClearCommand.commandWords.contains(shortForm)
                || DeleteCommand.commandWords.contains(shortForm)
                || DeleteTagCommand.commandWords.contains(shortForm)
                || EditCommand.commandWords.contains(shortForm)
                || ExitCommand.commandWords.contains(shortForm)
                || ExportCommand.commandWords.contains(shortForm)
                || FilterCommand.commandWords.contains(shortForm)
                || FreezeCommand.commandWords.contains(shortForm)
                || FindCommand.commandWords.contains(shortForm)
                || HelpCommand.commandWords.contains(shortForm)
                || ImportCommand.commandWords.contains(shortForm)
                || ListCommand.commandWords.contains(shortForm)
                || MassOpCommand.commandWords.contains(shortForm)
                || RedoCommand.commandWords.contains(shortForm)
                || ShortcutCommand.commandWords.contains(shortForm)
                || TagCommand.commandWords.contains(shortForm)
                || UndoCommand.commandWords.contains(shortForm)
                || UnfreezeCommand.commandWords.contains(shortForm);
    }

    /**
     * Saves user-defined shortcuts to a file.
     * @param p File path for the new word to be saved in.
     * @param commandWords ArrayList to be saved into.
     */
    public static void saveWords(Path p, List<String> commandWords) {
        if (!Files.exists(p)) {
            try {
                Files.createFile(p);
            } catch (java.io.IOException ignored) {
                // do nothing
            }
        }

        try {
            FileOutputStream fos = new FileOutputStream(p.toFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(commandWords);
            oos.close();
        } catch (IOException ignored) {
            // do nothing
        }
    }

    /**
     * Loads a user-defined shortcut from a file.
     * @param p File path of file
     * @param commandWords ArrayList to be loaded into
     * @return same array list
     */
    @SuppressWarnings("unchecked")
    public static List<String> loadWords(Path p, List<String> commandWords) {
        if (!Files.exists(p)) {
            try {
                Files.createFile(p);
            } catch (java.io.IOException ignored) {
                // do nothing
            }
        }
        try {
            FileInputStream fis = new FileInputStream(p.toFile());
            ObjectInputStream ois = new ObjectInputStream(fis);
            // suppress the unchecked cast since the object is an ArrayList
            commandWords = (List<String>) ois.readObject();
            ois.close();

        } catch (IOException | ClassNotFoundException ignored) {
            // do nothing
        }
        return commandWords;
    }
}
