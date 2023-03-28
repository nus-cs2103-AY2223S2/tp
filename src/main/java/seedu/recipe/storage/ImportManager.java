package seedu.recipe.storage;

import static seedu.recipe.storage.ExportManager.setIcon;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.collections.ObservableList;
import seedu.recipe.commons.exceptions.DataConversionException;
import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.ReadOnlyRecipeBook;
import seedu.recipe.model.recipe.Recipe;

/**
 * API to import a RecipeBook from other directories
 */
public class ImportManager {

    public ImportManager() {
    }

    /**
     * Prompts the user to select a JSON file to import and returns an ObservableList of Recipe objects
     * parsed from the selected file.
     * @return An ObservableList of Recipe parsed from the selected JSON file.
     * @throws IOException if an I/O error occurs.
     * @throws DataConversionException if the JSON data in the file cannot be converted into a RecipeBook object.
     * @throws IllegalValueException if there were any data constraints violated during the conversion.
     */
    public ObservableList<Recipe> execute() throws IOException, DataConversionException, IllegalValueException {
        File importedFile = this.selectFile();
        if (!importedFile.exists()) {
            return null;
        }
        ObservableList<Recipe> importedRecipes = importRecipes(importedFile);
        return importedRecipes;
    }

    /**
     * Prompts the user to select a JSON file to import and returns the selected File object.
     * @return The File object representing the selected JSON file.
     * @throws IOException if an I/O error occurs.
     */
    public File selectFile() throws IOException {
        // Create a file chooser that opens at the Downloads folder
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home") + "/Downloads");

        // Set filter to only show JSON files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON Files", "json");
        fileChooser.setFileFilter(filter);

        // change the icon and dialog title
        JFrame jFrame = setIcon();
        fileChooser.setDialogTitle("Import RecipeBook");
        // Show the file chooser dialog and get the result
        int result = fileChooser.showOpenDialog(jFrame);

        // User canceled the file chooser dialog
        if (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file selected.");
            jFrame.dispose();
            return null;
        }
        File selectedFile = fileChooser.getSelectedFile();
        // Check if the file is a JSON file
        if (!selectedFile.getName().endsWith(".json")) {
            System.out.println("Selected file is not a JSON file.");
            jFrame.dispose();
            return null;
        }
        jFrame.dispose();
        return selectedFile;
    }

    /**
     * Parses the Recipe objects from the specified JSON file and returns an ObservableList of Recipe objects.
     * @param selectedFile The File object representing the JSON file to parse.
     * @return An ObservableList of Recipe objects parsed from the specified JSON file.
     * @throws DataConversionException if the JSON data in the file cannot be converted into a RecipeBook object.
     */
    public ObservableList<Recipe> importRecipes(File selectedFile) throws DataConversionException {
        Path filePath = selectedFile.toPath();
        System.out.println("Selected file: " + filePath.toString());

        JsonRecipeBookStorage importedStorage = new JsonRecipeBookStorage((filePath));
        Optional<ReadOnlyRecipeBook> importedRecipeBook;
        try {
            importedRecipeBook = importedStorage.readRecipeBook();
        } catch (DataConversionException e) {
            throw e;
        }
        return importedRecipeBook.get().getRecipeList();
    }

    /**
     * Example of how to use it. Erase after reading.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, IllegalValueException {
        ImportManager importManager = new ImportManager();
        try {
            ObservableList<Recipe> importedRecipes = importManager.execute();
            for (Recipe recipe : importedRecipes) {
                System.out.println("Recipe: " + recipe.toString());
            }
        } catch (DataConversionException e) {
            throw new RuntimeException(e);
        }
    }
}
