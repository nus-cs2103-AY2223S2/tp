package seedu.address.files;

import java.nio.file.Path;

/**
 * The interface File reader.
 */
public interface FileReader<T> {

    T loadFile(Path path);

    Path getPath();

    String getFileName(Path path);

}
