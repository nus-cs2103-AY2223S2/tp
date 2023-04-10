package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Tests TXT Read and Save
 */
public class TxtUtilTest {
    private static final String EXPECTED_TEXT = "expected";

    @TempDir
    public Path tempDir;

    private Path testFilePath;

    @BeforeEach
    public void setUp() throws IOException {
        testFilePath = tempDir.resolve("test.txt");
        Files.createFile(testFilePath);
    }

    @Test
    public void readTxtFile_fleDoesNotExist_returnsEmptyOptional() {
        Path nonExistentFilePath = tempDir.resolve("nonExistentFile.txt");
        Optional<String> actual = TxtUtil.readTxtFile(nonExistentFilePath);
        assertTrue(actual.isEmpty());
    }

    @Test
    public void readTxtFile_fileExists_returnsExpectedText() throws IOException {
        FileUtil.writeToFile(testFilePath, EXPECTED_TEXT);
        Optional<String> actual = TxtUtil.readTxtFile(testFilePath);
        assertTrue(actual.isPresent());
        assertEquals(EXPECTED_TEXT, actual.get());
    }

    @Test
    public void saveTxtFile() throws IOException {
        TxtUtil.saveTxtFile(EXPECTED_TEXT, testFilePath);
        String actualText = FileUtil.readFromFile(testFilePath);
        assertEquals(EXPECTED_TEXT, actualText);
    }

}
