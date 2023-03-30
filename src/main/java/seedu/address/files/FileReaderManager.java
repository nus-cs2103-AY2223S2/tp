package seedu.address.files;

import java.nio.file.Path;
import java.util.Locale;

/**
 * The type File reader manager.
 */
public class FileReaderManager {
    private ImageReader imageReader;
    private PdfReader pdfReader;

    /**
     * Instantiates a new File reader manager.
     *
     * @param path the path
     */
    public FileReaderManager(Path path) {
        String extension = getFileExtension(path.getFileName().toString());
        if (isImage(extension)) {
            imageReader = new ImageReader(path);
        } else if (extension.equalsIgnoreCase("pdf")) {
            pdfReader = new PdfReader(path);
        } else {
            throw new IllegalArgumentException("Invalid file type");
        }
    }

    /**
     * Display file.
     */
    public void displayFile() {
        if (imageReader != null) {
            imageReader.displayImage();
        } else if (pdfReader != null) {
            pdfReader.displayPdf();
        }
    }

    private boolean isImage(String extension) {
        return "jpg".equalsIgnoreCase(extension)
                || "jpeg".equalsIgnoreCase(extension)
                || "png".equalsIgnoreCase(extension);
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase(Locale.ENGLISH);
    }
}
