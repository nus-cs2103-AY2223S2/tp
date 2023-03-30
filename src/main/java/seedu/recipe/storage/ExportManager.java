package seedu.recipe.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * API to export current RecipeBook
 */
public class ExportManager {

    private final Path recipeBookFilePath;
    private final Stage owner;

    /**
     * Creates an instance of the ExportManager that is responsible for exporting the RecipeBook to a JSON file.
     *
     * @param owner the owner stage of MainWindow
     * @param recipeBookFilePath the file path to the RecipeBook to be exported.
     */
    public ExportManager(Stage owner, Path recipeBookFilePath) {
        this.owner = owner;
        this.recipeBookFilePath = recipeBookFilePath;
    }

    /**
     * Displays a file chooser dialog and exports the RecipeBook to a JSON file.
     * If file exists, there will be a prompt asking whether to overwrite it.
     *
     * @throws IOException if there is an error while writing to the file.
     */
    public void execute() throws IOException {
        FileChooser fileChooser = createFile();
        File selectedFile = fileChooser.showSaveDialog(this.owner);
        if (selectedFile != null) {
            writeToFile(selectedFile);
        }
    }

    /**
     * Creates a file chooser dialog with a filter for JSON files.
     *
     * @return the file chooser dialog.
     */
    private FileChooser createFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export RecipeBook");
        fileChooser.setInitialFileName("recipebook.json");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files", "*.json"));
        return fileChooser;
    }

    /**
     * Writes the contents of the RecipeBook JSON file to the selected file.
     *
     * @param selectedFile the selected file to write to.
     * @throws IOException if there is an error while writing to the file.
     */
    private void writeToFile(File selectedFile) throws IOException {
        FileReader fr = new FileReader(recipeBookFilePath.toFile());
        FileWriter fw = new FileWriter(selectedFile);
        try (BufferedReader reader = new BufferedReader(fr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                fw.write(line);
                fw.write(System.lineSeparator()); // add line separator
            }
        }
        fr.close();
        fw.close();
    }
}
