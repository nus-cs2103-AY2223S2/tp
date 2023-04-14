package seedu.address.files;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * The type Image storage.
 */
public class FileStorage {

    private static final Logger logger = Logger.getLogger(FilesManager.class.getName());
    private static final long DEFAULT_FILE_SIZE = 10 * 1024 * 1024;
    private String username;

    /**
     * Instantiates a new File storage.
     *
     * @param username the username
     */
    public FileStorage(String username) {
        this.username = username;
    }

    /**
     * Create drc.
     *
     * @param path the path
     */
    public static void createDrc(String path) {
        File directory = new File(path);
        checkDir(directory);
    }

    /**
     * Checks if the specified directory exists and creates it if necessary.
     * <p>
     * This method checks if the given directory exists. If it does not exist,
     * the method creates the directory along with any necessary parent directories.
     *
     * @param userDir The directory to be checked and created if necessary.
     */
    public static void checkDir(File userDir) {
        if (!userDir.exists()) {
            userDir.mkdirs();
        }
    }

    /**
     * Deletes a directory and all its contents.
     * <p>
     * This method checks if the specified directory exists. If it does, the method
     * iterates through all the files in the directory and deletes each one. Finally,
     * the directory itself is deleted. If the directory does not exist, the method
     * does nothing.
     *
     * @param path The path of the directory to be deleted.
     */
    public static void deleteDrc(String path) {
        File directory = new File(path);
        if (directory.exists()) {
            for (File file: directory.listFiles()) {
                file.delete();
            }
            directory.delete();
        }
    }

    /**
     * Deletes a file at the specified path.
     * <p>
     * This method checks if the specified file exists. If it does, the method
     * deletes the file. If the file does not exist, the method does nothing.
     *
     * @param path The path of the file to be deleted.
     */
    public static void deleteFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Determines if the given file is an image file based on its extension.
     * <p>
     * This method checks if the file has one of the following extensions (case-insensitive):
     * JPG, JPEG, PNG, BMP, or GIF. If the file has one of these extensions, it is
     * considered to be an image file, and the method returns true. Otherwise, it
     * returns false.
     *
     * @param file The file to be checked for being an image file.
     * @return true if the file is an image file, false otherwise.
     */
    public static boolean isImageFile(File file) {
        String fileName = file.getName();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return extension.equalsIgnoreCase("jpg")
                || extension.equalsIgnoreCase("jpeg")
                || extension.equalsIgnoreCase("png")
                || extension.equalsIgnoreCase("bmp")
                || extension.equalsIgnoreCase("gif");
    }

    /**
     * Uploads selected PDF and image files to the user's reports directory.
     * <p>
     * This method presents a file dialog allowing the user to select multiple
     * files with extensions: PDF, JPG, JPEG, and PNG. Selected files with valid
     * extensions are copied to the "reports/{username}/" directory, where
     * {username} is replaced with the username of the current user. If the target
     * directory does not exist, it is created. Existing files with the same name
     * in the target directory are replaced. Files with unsupported extensions and file Limit of more than 10mb are
     * ignored, and a message is printed to the console.
     * <p>
     * This method should be called from the Event Dispatch Thread (EDT) to ensure
     * proper GUI interaction. The SwingUtilities.invokeLater() method is used to
     * achieve this.
     */
    public void uploadFile() {
        SwingUtilities.invokeLater(() -> {
            JFileChooser fileChooser = configFileChooser();
            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File[] selectedFiles = fileChooser.getSelectedFiles();
                String userDirPath = "reports/" + this.username + "/";
                File userDir = new File(userDirPath);
                checkDir(userDir);
                try {
                    copySelectedFiles(selectedFiles, userDirPath, DEFAULT_FILE_SIZE);
                    JOptionPane.showMessageDialog(null, "Files uploaded successfully!",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        });
    }

    private JFileChooser configFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter(
                "PDF and Image files", "pdf", "jpg", "jpeg", "png"));
        fileChooser.setMultiSelectionEnabled(true);
        return fileChooser;
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private boolean isAllowedFileType(String extension) {
        return extension.equalsIgnoreCase("pdf")
                || extension.equalsIgnoreCase("jpg")
                || extension.equalsIgnoreCase("jpeg")
                || extension.equalsIgnoreCase("png");
    }

    /**
     * Copies selected files to user's directory path.
     * Only files with allowed file types and within the specified maximum file size are copied.
     * @param selectedFiles array of files to be copied
     * @param userDirPath path of the user's directory to copy files to
     * @param maxSize maximum file size allowed in bytes
     */
    private void copySelectedFiles(File[] selectedFiles, String userDirPath, long maxSize) throws IOException {

        for (File selectedFile : selectedFiles) { // loop through each selected file
            String fileName = selectedFile.getName().toLowerCase();
            String extension = getFileExtension(fileName);

            if (isAllowedFileType(extension)) {
                Path srcPath = selectedFile.toPath();
                Path destPath = Paths.get(userDirPath + fileName);
                BasicFileAttributes attr = Files.readAttributes(srcPath, BasicFileAttributes.class);
                long fileSize = attr.size();
                if (fileSize <= maxSize) {
                    Files.copy(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);
                } else {
                    throw new IOException("File Size exceed limit, Please upload a different file");
                }
            } else {
                throw new IOException("Wrong file type, only accept PDF and Image types");
            }
        }
    }
}
