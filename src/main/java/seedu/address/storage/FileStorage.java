package seedu.address.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.JFileChooser;

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
        if (!directory.exists()) {
            directory.mkdirs();
        }
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
     * @throws IOException        the io exception
     */
    public void uploadFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String userDirPath = "reports/" + this.username + "/";
            String fileName = selectedFile.getName();
            File userDir = new File(userDirPath);
            if (!userDir.exists()) {
                userDir.mkdirs();
            }
            Path srcPath = selectedFile.toPath();
            Path destPath = Paths.get(userDirPath + fileName);
            try {
                Files.copy(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.out.println("eeeeeror");
            }
        }
    }

}
