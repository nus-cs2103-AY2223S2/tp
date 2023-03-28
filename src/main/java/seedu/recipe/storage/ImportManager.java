package seedu.recipe.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import seedu.recipe.commons.exceptions.DataConversionException;
import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.ReadOnlyRecipeBook;
import seedu.recipe.model.recipe.Recipe;

/**
 * API to import a RecipeBook from other directories
 */
public class ImportManager {

    private final Stage owner;

    public ImportManager(Stage owner) {
        this.owner = owner;
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
        if (importedFile == null) {
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
        FileChooser fileChooser = new FileChooser();

        // Set filter to only show JSON files
        ExtensionFilter filter = new ExtensionFilter("JSON Files", "*.json");
        fileChooser.getExtensionFilters().add(filter);

        // Set initial directory to the Downloads folder
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"), "Downloads"));

        // Set dialog title
        fileChooser.setTitle("Import RecipeBook");

        // Show the file chooser dialog and get the result
        File selectedFile = fileChooser.showOpenDialog(owner);

        // User canceled the file chooser dialog
        if (selectedFile == null) {
            System.out.println("No file selected.");
            return null;
        }

        // Check if the file is a JSON file
        if (!selectedFile.getName().endsWith(".json")) {
            System.out.println("Selected file is not a JSON file.");
            return null;
        }

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
}
