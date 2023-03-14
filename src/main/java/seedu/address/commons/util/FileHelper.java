package seedu.address.commons.util;

import java.io.IOException;
import java.nio.file.Path;

/**
 * The singleton instance that wraps around the {@code FileUtil} class.
 */
public class FileHelper {
    /**
     * The singleton instance of this class.
     */
    public static final FileHelper INSTANCE;

    static {
        INSTANCE = new FileHelper();
    }

    private FileHelper() {
    }

    /**
     * @see FileUtil#isFileExists(Path)
     */
    public boolean isFileExists(Path filePath) {
        return FileUtil.isFileExists(filePath);
    }

    /**
     * @see FileUtil#isValidPath(String)
     */
    public boolean isValidPath(String filePath) {
        return FileUtil.isValidPath(filePath);
    }

    /**
     * @see FileUtil#createIfMissing(Path)
     */
    public void createIfMissing(Path filePath) throws IOException {
        FileUtil.createIfMissing(filePath);
    }

    /**
     * @see FileUtil#createFile(Path)
     */
    public void createFile(Path filePath) throws IOException {
        FileUtil.createFile(filePath);
    }

    /**
     * @see FileUtil#createParentDirsOfFile(Path)
     */
    public void createParentDirsOfFile(Path filePath) throws IOException {
        FileUtil.createParentDirsOfFile(filePath);
    }

    /**
     * @see FileUtil#readFromFile(Path)
     */
    public String readFromFile(Path filePath) throws IOException {
        return FileUtil.readFromFile(filePath);
    }

    /**
     * @see FileUtil#writeToFile(Path, String)
     */
    public void writeToFile(Path filePath, String content) throws IOException {
        FileUtil.writeToFile(filePath, content);
    }
}
