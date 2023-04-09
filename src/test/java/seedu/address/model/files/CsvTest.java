package seedu.address.model.files;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class CsvTest {

    public static final Path CSV_FOLDER = Paths.get("src", "test", "data", "CsvTest");
    @Test
    public void equals() throws IOException {
        Path csvPath = CSV_FOLDER.resolve("typicalCsvFile.csv");

        Csv typicalCsv = new Csv(csvPath.toString());

        assertEquals(typicalCsv.getNumOfRows(), 4);
        assertEquals(typicalCsv.getNumOfCols(), 11);

        for (int i = 0; i < typicalCsv.getNumOfCols(); i++) {
            assertEquals(typicalCsv.getRow(1)[i],
                    new String[]{"Alice Pauline", "123, Jurong West Ave 6, #08-111", "alice@example.com", "94351253",
                        "REC", "N/A", "N/A", "N/A", "friends", "", ""}[i]);
        }

        assertEquals(typicalCsv.getColumnIndex("phone"), 3);

        assertEquals(typicalCsv.getEntry(3, 1), "wall street");

        assertEquals(typicalCsv.getEntry(3, "address"), "wall street");
    }

}
