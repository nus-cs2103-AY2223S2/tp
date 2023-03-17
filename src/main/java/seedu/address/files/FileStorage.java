package seedu.address.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
//import java.util.ArrayList;
//import java.util.List;
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
                /*
                FilesManager filesManager = new FilesManager();
                List<Path> temp = filesManager.getAllDirectories();
                for (int x = 0; x < temp.size(); x++) {
                    System.out.println(temp.get(x).getFileName());
                    List<Path> temp2 = filesManager.getAllFiles(temp.get(x));
                    for (int y = 0; y < temp2.size(); y++) {
                        System.out.println(temp2.get(y).getFileName());
                    }
                }
                */
            }
        });
    }

    private static boolean isImageFile(File file) {
        String fileName = file.getName();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return extension.equalsIgnoreCase("jpg")
                || extension.equalsIgnoreCase("jpeg")
                || extension.equalsIgnoreCase("png")
                || extension.equalsIgnoreCase("bmp")
                || extension.equalsIgnoreCase("gif");
    }
    /*
    private void imgToPdf(File file) {
        if (isImageFile(file)) {
            try {
                BufferedImage image = ImageIO.read(file);
                PDDocument document = new PDDocument();
                PDPage page = new PDPage();
                document.addPage(page);
                PDImageXObject pdfImage = LosslessFactory.createFromImage(document, image);
                PDImageXObject pdImageObject = PDImageXObject.createFromFileByContent(file, document);
                int width = image.getWidth();
                int height = image.getHeight();
                PDRectangle pageSize = new PDRectangle(width, height);
                PDPage page1 = new PDPage(pageSize);
                document.addPage(page1);
                File pdfFile = new File(file.getAbsoluteFile() + ".pdf");
                document.save(pdfFile);
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    */


    private void checkDir(File userDir) {
        if (!userDir.exists()) {
            userDir.mkdirs();
        }
    }

}
