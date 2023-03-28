package seedu.recipe.storage;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * API to export current RecipeBook
 */
public class ExportManager {

    private final Path recipeBookFilePath = Paths.get("data", "recipebook.json");
    private boolean hasJsonExtension = false;

    public ExportManager() {
    }

    /**
     * Displays a file chooser dialog and exports the recipebook to a JSON file.
     * If file exists, there will be a prompt asking whether to overwrite it.
     * @throws IOException if there is an error while writing to the file.
     */



    public void execute() throws IOException {
        JFileChooser fileChooser = createFile();
        // change the icon and dialog title
        JFrame jFrame = setIcon();
        fileChooser.setDialogTitle("Export RecipeBook");
        int result = fileChooser.showSaveDialog(jFrame);

        if (result == JFileChooser.APPROVE_OPTION) {
            // Check if file exists already
            if (exists(fileChooser)) {
                String selectedFile = fileChooser.getSelectedFile().getName();
                int response = JOptionPane.showConfirmDialog(null,
                        String.format("The file %s already exists. Do you want to overwrite it?", selectedFile),
                        "Confirm Overwrite",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (response == JOptionPane.NO_OPTION) {
                    return;
                }
            }
            writeToFile(fileChooser);
        }
        jFrame.dispose();
    }

    /**
     * Sets a custom icon for the file chooser dialog.
     * @return the JFrame containing the custom icon.
     */
    protected static JFrame setIcon() {
        // Set a custom icon for the JFileChooser
        File iconFile = new File("src/main/resources/images/recipebook_icon1.png");
        JFrame jFrame = new JFrame();
        if (iconFile.exists()) {
            ImageIcon icon = new ImageIcon(iconFile.getAbsolutePath());
            Image image = icon.getImage();

            jFrame.setIconImage(image);
            jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jFrame.setLocationByPlatform(true);
            jFrame.pack();
        }
        return jFrame;
    }

    /**
     * Creates a file chooser dialog with a filter for JSON files.
     * @return the file chooser dialog.
     */
    private JFileChooser createFile() {
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home") + "/Downloads");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON files", "json");
        fileChooser.setFileFilter(filter);
        return fileChooser;
    }

    /**
     * Checks if the selected file already exists.
     * @param chooser the file chooser dialog.
     * @return true if the file exists, false otherwise.
     */
    private boolean exists(JFileChooser chooser) {
        File selectedFile = chooser.getSelectedFile();
        String filePath = selectedFile.getAbsolutePath();
        if (!filePath.endsWith(".json")) {
            filePath += ".json";
        } else {
            hasJsonExtension = true;
        }
        File file = new File(filePath);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    /**
     * Writes the contents of the recipebook JSON file to the selected file.
     * @param chooser the file chooser dialog.
     * @throws IOException if there is an error while writing to the file.
     */
    private void writeToFile(JFileChooser chooser) throws IOException {
        FileReader fr = new FileReader(recipeBookFilePath.toFile());
        FileWriter fw;
        if (hasJsonExtension) {
            fw = new FileWriter(chooser.getSelectedFile());
        } else {
            fw = new FileWriter(chooser.getSelectedFile() + ".json");
        }
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

    /**
     * Example of how to use it. Erase after reading.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ExportManager exportManager = new ExportManager();
        exportManager.execute();
    }
}
