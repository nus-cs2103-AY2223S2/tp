package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.model.files.Csv;

public class CsvTest {

    private static final Path CSV_FOLDER = Paths.get("src", "test", "data", "CsvTest");
    @Test
    public void canReadTypicalCsvFile() throws FileNotFoundException, IOException {
        Path csvPath = CSV_FOLDER.resolve("typicalCsvFile.csv");

        Csv typicalCsv = new Csv(csvPath.toString());

        assertEquals(typicalCsv.getNumOfRows(), 4);
        assertEquals(typicalCsv.getNumOfCols(), 11);

        for (int i = 0; i < typicalCsv.getNumOfCols(); i++) {
            assertEquals(typicalCsv.getRow(1)[i],
                    new String[]{"alphaeus", "7 Hougang Street, #12-01", "alphaolive@test.com", "987654321",
                        "2LT", "6SIR", "Bravo", "Support", "g6pd", "", ""}[i]);
        }

        assertEquals(typicalCsv.getColumnIndex("phone"), 3);

        assertEquals(typicalCsv.getEntry(3, 1), "5 orchard road, #11-11");

        assertEquals(typicalCsv.getEntry(3, "address"), "5 orchard road, #11-11");
    }

}
