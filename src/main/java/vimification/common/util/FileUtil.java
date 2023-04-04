package vimification.common.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

/**
 * Writes and reads files
 */
public class FileUtil {

    private static final String CHARSET = "UTF-8";

    public static boolean isFileExists(Path path) {
        return Files.exists(path) && Files.isRegularFile(path);
    }

    /**
     * Returns true if {@code path} can be converted into a {@code Path} via
     * {@link Path#of(String)}, otherwise returns false.
     *
     * @param path A string representing the file path. Cannot be null.
     */
    public static boolean isValidPath(String path) {
        try {
            Path.of(path);
        } catch (InvalidPathException ipe) {
            return false;
        }
        return true;
    }

    /**
     * Creates a file if it does not exist along with its missing parent directories.
     *
     * @throws IOException if the file or directory cannot be created.
     */
    public static void createIfMissing(Path path) throws IOException {
        if (isFileExists(path)) {
            return;
        }
        Path parentPath = path.getParent();
        if (parentPath != null) {
            Files.createDirectories(parentPath);
        }
        Files.createFile(path);
    }

    /**
     * Reads the content of a file as string.
     *
     * @param path path to the file
     * @return the string representation of the file
     */
    public static String readFromFile(Path path) throws IOException {
        return new String(Files.readAllBytes(path), CHARSET);
    }

    /**
     * Writes given string to a file. A new file will be created if it does not exist yet.
     *
     * @param path path to the file
     * @param content the content to be written into the file
     */
    public static void writeToFile(Path path, String content) throws IOException {
        Files.write(path, content.getBytes(CHARSET));
    }
}
