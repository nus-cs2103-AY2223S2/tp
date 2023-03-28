package seedu.address.files;

import java.nio.file.Path;

/**
 * An interface representing a generic file loader.
 * @param <T> the type of object to be loaded from the file.
 */
public interface FileReader<T> {

    /**
     * Loads the contents of a file at a given path and returns it.
     * @param path the path of the file to be loaded.
     * @return the contents of the file.
     */
    T loadFile(Path path);

    /**
     * Returns the path of the file.
     * @return the path of the file.
     */
    Path getPath();

    /**
     * Returns the name of the file at a given path.
     * @param path the path of the file.
     * @return the name of the file.
     */
    String getFileName(Path path);

}
