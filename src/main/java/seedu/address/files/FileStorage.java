package seedu.address.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The type Image storage.
 */
public class FileStorage {

    private String username;
    /**
     * Instantiates a new Image storage.
     *
     * @param username the username
     */
    public FileStorage(String username) {
        this.username = username;
        String path = "reports/" + username;
        createDrc(path);
    }

    private void createDrc(String path) {
        File directory = new File(path);
        checkDir(directory);
    }

    /**
     * Delete drc.
     *
     * @param path the path
     */
    public static void deleteDrc(String path) {
        String path1 = "reports/" + path;
        File directory = new File(path1);
        if (directory.exists()) {
            for (File file: directory.listFiles()) {
                file.delete();
            }
            directory.delete();
        }
    }

    /**
     * Upload file.
     *
     * @throws IOException the io exception
     */
    public void uploadFile() {
        SwingUtilities.invokeLater(() -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter(
                    "PDF and Image files", "pdf", "jpg", "jpeg", "png", "gif"));
            fileChooser.setMultiSelectionEnabled(true);
            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File[] selectedFiles = fileChooser.getSelectedFiles();
                String userDirPath = "reports/" + this.username + "/";
                File userDir = new File(userDirPath);
                checkDir(userDir);
                for (File selectedFile : selectedFiles) { // loop through each selected file
                    String fileName = selectedFile.getName();
                    Path srcPath = selectedFile.toPath();
                    Path destPath = Paths.get(userDirPath + fileName);
                    try {
                        Files.copy(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        System.out.println("Error copying file");
                    }
                }
            }
        });
    }


    private void checkDir(File userDir) {
        if (!userDir.exists()) {
            userDir.mkdirs();
        }
    }

}
