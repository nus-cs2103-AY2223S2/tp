package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBooks.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonAdaptedPerson;

public class ExportPersonsCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void index_inRange_success() {
        List<Index> indexList = List.of(new Index[]{INDEX_FIRST_PERSON, INDEX_SECOND_PERSON});
        ExportPersonsCommand exportPersonsCommand = new ExportPersonsCommand(indexList);
        ObservableList<Person> persons = model.getFilteredPersonList();
        String expectedMessage;
        try {
            expectedMessage = JsonUtil.toJsonString(new JsonAdaptedPerson[]{
                new JsonAdaptedPerson(persons.get(INDEX_FIRST_PERSON.getZeroBased())),
                new JsonAdaptedPerson(persons.get(INDEX_SECOND_PERSON.getZeroBased()))
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        assertCommandSuccess(exportPersonsCommand, model, expectedMessage, model);
    }

    @Test
    public void index_outOfRange_failure() {
        ObservableList<Person> persons = model.getFilteredPersonList();
        List<Index> indexList = List.of(new Index[]{Index.fromZeroBased(persons.size())});
        ExportPersonsCommand exportPersonsCommand = new ExportPersonsCommand(indexList);

        String expectedMessage = ExportPersonsCommand.INDEX_NOT_FOUND;


        assertCommandFailure(exportPersonsCommand, model, expectedMessage);
    }

}
