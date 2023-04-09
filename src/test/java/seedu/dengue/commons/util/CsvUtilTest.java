package seedu.dengue.commons.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.dengue.commons.util.CsvUtil.writeToCsvFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.dengue.commons.exceptions.DataConversionException;
import seedu.dengue.testutil.CsvTestClass;

/**
 * Tests JSON Read and Write
 */
public class CsvUtilTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test",
            "data", "CsvUtilTest");

    private static final String[] TEST_HEADER = CsvTestClass.getHeaders();

    private static final String TEST_DATA_FILE = "testCsvDataFile.csv";

    @TempDir
    public Path testFolder;

    private static List<String[]> createTestData() {
        List<String[]> res = new ArrayList<>();
        res.add(new String[]{"row 1", "row 1"});
        res.add(new String[]{"row 2", "row 2"});
        return res;
    }

    @Test
    public void testWriteToCsvFile_noExceptionThrown() throws IOException {
        Path filePath = testFolder.resolve("TempCsvFile.csv");
        writeToCsvFile(filePath, TEST_HEADER, createTestData());
        assertEquals(FileUtil.readFromFile(filePath), CsvTestClass.csvStringRepresentation());
    }

    private Optional<List<CsvTestClass>> readCsvFile(String filePath)
            throws DataConversionException {
        return CsvUtil.readCsvFile(addToTestDataPathIfNotNull(filePath), CsvTestClass.class);
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void testReadFromCsvFile_noExceptionThrown() throws DataConversionException {
        Optional<List<CsvTestClass>> optionalList = readCsvFile(TEST_DATA_FILE);
        if (!optionalList.isPresent()) {
            assert false;
            return;
        }
        assertArrayEquals(optionalList.get().toArray(), Arrays.stream(CsvTestClass.TEST_ARRAY).toArray());
    }

}
