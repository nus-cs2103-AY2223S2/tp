package seedu.address.logic.parser.jobs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.person.Person;

public class ImportDeliveryJobCommandParserTest {

    @Test
    public void parse_success() throws FileNotFoundException, ParseException, IOException {

        File validFile = new File("docs/testimportfile.csv");
        File fileEmpty = new File("fileEmpty.csv");
        fileEmpty.createNewFile();
        File missingElementsFile = new File("docs/missingelements.csv");
        File someNa = new File("docs/some_na.csv");

        List<Person> listOfCustomers = new ArrayList<>();

        final List<DeliveryJob> importCommandValid =
                ImportDeliveryJobCommandParser.parse(validFile, listOfCustomers);
        final List<DeliveryJob> importCommandNa =
                ImportDeliveryJobCommandParser.parse(someNa, listOfCustomers);

        assertTrue(importCommandValid.equals(importCommandValid));
        assertTrue(importCommandNa.equals(importCommandNa));
    }

}
