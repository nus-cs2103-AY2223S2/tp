package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.model.files.CsvTest.CSV_FOLDER;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.files.Csv;
import seedu.address.model.person.Person;

public class ParseFromCsvToPersonsTest {

    @Test
    public void parse_csvMissingFields_throwParseException() throws IOException {
        Path csvPath = CSV_FOLDER.resolve("missingFieldsCsvFile.csv");
        Csv typicalCsv = new Csv(csvPath.toString());
        Executable executable = () -> new ParseFromCsvToPersons(typicalCsv);
        assertThrows(ParseException.class, executable);
    }

    @Test
    public void parse_validCsv_returnsTrue() throws IOException, ParseException {
        Path csvPath = CSV_FOLDER.resolve("typicalCsvFile.csv");
        Csv typicalCsv = new Csv(csvPath.toString());
        ParseFromCsvToPersons parseFromCsvToPersons = new ParseFromCsvToPersons(typicalCsv);
        List<Person> idealPersonList = Arrays.asList(ALICE, BENSON, CARL);
        assertEquals(idealPersonList, parseFromCsvToPersons.parse());
    }
}
