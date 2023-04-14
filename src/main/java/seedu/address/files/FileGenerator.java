package seedu.address.files;

import java.io.IOException;

/**
 * The interface File generator.
 */
public interface FileGenerator {

    /**
     * Generate.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
    void generate(String filename) throws IOException;

}
