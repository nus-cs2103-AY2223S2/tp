package seedu.vms.commons.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Writes and reads files
 */
public class FileUtil {
    private static final int BUFFER_SIZE = 2097152; // 2MB

    private static final String FORMAT_RESOURCE_FILE_NOT_FOUND = "Could not find resource file: %s";


    public static boolean isFileExists(Path file) {
        return Files.exists(file) && Files.isRegularFile(file);
    }

    /**
     * Returns true if {@code path} can be converted into a {@code Path} via {@link Paths#get(String)},
     * otherwise returns false.
     * @param path A string representing the file path. Cannot be null.
     */
    public static boolean isValidPath(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException ipe) {
            return false;
        }
        return true;
    }

    /**
     * Creates a file if it does not exist along with its missing parent directories.
     * @throws IOException if the file or directory cannot be created.
     */
    public static void createIfMissing(Path file) throws IOException {
        if (!isFileExists(file)) {
            createFile(file);
        }
    }


    /**
     * Creates a file if it does not exist along with its missing parent directories.
     */
    public static void createFile(Path file) throws IOException {
        if (Files.exists(file)) {
            return;
        }

        createParentDirsOfFile(file);

        Files.createFile(file);
    }

    /**
     * Creates parent directories of file if it has a parent directory
     */
    public static void createParentDirsOfFile(Path file) throws IOException {
        Path parentDir = file.getParent();

        if (parentDir != null) {
            Files.createDirectories(parentDir);
        }
    }


    /**
     * Returns the buffered reader of the specified file relative to the
     * application's resource folder.
     *
     * @param pathString - the path to the file in the resource folder as a
     *      String.
     * @throws FileNotFoundException if the file could not be found.
     * @throws NullPointerException if {@param pathString} is {@code null}.
     */
    public static BufferedReader getResourceFileReader(String pathString) throws FileNotFoundException {
        InputStream inStream = FileUtil.class.getResourceAsStream(pathString);
        if (inStream == null) {
            throw new FileNotFoundException(
                    String.format(FORMAT_RESOURCE_FILE_NOT_FOUND, pathString));
        }
        Reader reader = new InputStreamReader(inStream);
        return new BufferedReader(reader, BUFFER_SIZE);
    }


    /**
     * Returns the buffered reader of the specified file.
     *
     * @throws FileNotFoundException if the file cannot be found.
     * @throws NullPointerException if {@code path} is {@code null}.
     */
    public static BufferedReader getFileReader(Path path) throws FileNotFoundException {
        Objects.requireNonNull(path);
        return new BufferedReader(new FileReader(path.toFile()), BUFFER_SIZE);
    }


    /**
     * Returns the buffered writer to the specified file path.
     *
     * @throws IOException if an I/O exception occurs
     *      (see {@link #FileWriter(java.io.File)}).
     * @throws NullPointerException if {@code path} is {@code null}.
     */
    public static BufferedWriter getFileWriter(Path path) throws IOException {
        Objects.requireNonNull(path);
        return new BufferedWriter(new FileWriter(path.toFile()), BUFFER_SIZE);
    }
}
