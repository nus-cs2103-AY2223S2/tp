package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.model.history.InputHistory;

/**
 * Tests for storage of .txt file containing history executed input.
 */
public class TxtInputHistoryStorageTest {
    private static final InputHistory EXPECTED_HISTORY =
        new InputHistory(List.of("input1", "input2"), List.of("input1", "input2", "input3"));

    @TempDir
    public Path tempDir;

    private Path filePath;
    private TxtInputHistoryStorage storage;

    @BeforeEach
    public void setUp() {
        filePath = tempDir.resolve("inputHistory.txt");
        storage = new TxtInputHistoryStorage(filePath);
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(filePath);
    }

    @Test
    public void getHistoryStoragePath_success() {
        assertEquals(filePath, storage.getHistoryStoragePath());
    }

    @Test
    public void readInputHistory_fileDoesnotExist_returnsEmptyOptional() throws IOException {
        assertFalse(Files.exists(filePath));
        Optional<InputHistory> actualHistory = storage.readInputHistory();
        assertFalse(actualHistory.isPresent());
    }

}
