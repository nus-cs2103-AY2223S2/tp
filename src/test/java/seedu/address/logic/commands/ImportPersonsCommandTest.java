package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.storage.JsonAdaptedPerson;
import seedu.address.testutil.TypicalAddressBooks;
import seedu.address.testutil.TypicalPersons;

public class ImportPersonsCommandTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "ImportExportPersonsTest");
    private static final Path VALID_PERSONS_FILE = TEST_DATA_FOLDER.resolve("validPersons.json");

    @Test
    public void success() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = Files.newBufferedReader(VALID_PERSONS_FILE);
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }

        ImportPersonsCommand command = new ImportPersonsCommand(stringBuilder.toString(), false);
        Model expectedModel = new ModelManager(TypicalAddressBooks.getTypicalAddressBookPersonsOnly(), new UserPrefs());
        assertCommandSuccess(command, model, ImportPersonsCommand.SUCCESS, expectedModel);
    }

    @Test
    public void duplicateForced_success() {
        Model model = new ModelManager(TypicalAddressBooks.getTypicalAddressBookPersonsOnly(),
                new UserPrefs());
        JsonAdaptedPerson firstPerson = new JsonAdaptedPerson(TypicalPersons.getTypicalPersons().get(0));
        JsonAdaptedPerson secondPerson = new JsonAdaptedPerson(TypicalPersons.getTypicalPersons().get(1));
        String json;
        try {
            json = JsonUtil.toJsonString(new JsonAdaptedPerson[]{firstPerson, secondPerson});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        model.deletePerson(TypicalPersons.getTypicalPersons().get(1));
        ImportPersonsCommand command = new ImportPersonsCommand(json, true);
        Model expectedModel = new ModelManager(TypicalAddressBooks.getTypicalAddressBookPersonsOnly(), new UserPrefs());
        expectedModel.deletePerson(TypicalPersons.getTypicalPersons().get(1));
        expectedModel.addPerson(TypicalPersons.getTypicalPersons().get(1));
        assertCommandSuccess(command, model, ImportPersonsCommand.SUCCESS, expectedModel);
    }

    @Test
    public void duplicate_failure() {
        Model model = new ModelManager(TypicalAddressBooks.getTypicalAddressBookPersonsOnly(), new UserPrefs());
        JsonAdaptedPerson firstPerson = new JsonAdaptedPerson(TypicalPersons.getTypicalPersons().get(0));
        String json;
        try {
            json = JsonUtil.toJsonString(new JsonAdaptedPerson[]{firstPerson});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        ImportPersonsCommand command = new ImportPersonsCommand(json, false);
        assertCommandFailure(command, model, ImportPersonsCommand.DUPLICATE_PERSON);
    }

    @Test
    public void malformedJson_failure() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        String json = "abc";
        ImportPersonsCommand command = new ImportPersonsCommand(json, false);
        assertCommandFailure(command, model, ImportPersonsCommand.MALFORMED_JSON);
    }


}
