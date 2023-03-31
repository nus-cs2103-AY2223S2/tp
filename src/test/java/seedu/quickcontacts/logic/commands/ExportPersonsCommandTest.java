package seedu.quickcontacts.logic.commands;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import javafx.collections.ObservableList;
import seedu.quickcontacts.commons.core.index.Index;
import seedu.quickcontacts.commons.util.JsonUtil;
import seedu.quickcontacts.model.Model;
import seedu.quickcontacts.model.ModelManager;
import seedu.quickcontacts.model.UserPrefs;
import seedu.quickcontacts.model.person.Person;
import seedu.quickcontacts.storage.JsonAdaptedPerson;
import seedu.quickcontacts.testutil.TypicalIndexes;
import seedu.quickcontacts.testutil.TypicalQuickBooks;

public class ExportPersonsCommandTest {
    private final Model model = new ModelManager(TypicalQuickBooks.getTypicalQuickBook(), new UserPrefs());

    @Test
    public void index_inRange_success() {
        List<Index> indexList = List.of(new Index[]{
            TypicalIndexes.INDEX_FIRST_PERSON, TypicalIndexes.INDEX_SECOND_PERSON});
        ExportPersonsCommand exportPersonsCommand = new ExportPersonsCommand(indexList);
        ObservableList<Person> persons = model.getFilteredPersonList();
        String expectedMessage;
        try {
            expectedMessage = JsonUtil.toJsonString(new JsonAdaptedPerson[]{
                new JsonAdaptedPerson(persons.get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased())),
                new JsonAdaptedPerson(persons.get(TypicalIndexes.INDEX_SECOND_PERSON.getZeroBased()))
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        CommandTestUtil.assertCommandSuccess(exportPersonsCommand, model, expectedMessage, model);
    }

    @Test
    public void index_outOfRange_failure() {
        ObservableList<Person> persons = model.getFilteredPersonList();
        List<Index> indexList = List.of(new Index[]{Index.fromZeroBased(persons.size())});
        ExportPersonsCommand exportPersonsCommand = new ExportPersonsCommand(indexList);

        String expectedMessage = ExportPersonsCommand.INDEX_NOT_FOUND;


        CommandTestUtil.assertCommandFailure(exportPersonsCommand, model, expectedMessage);
    }

}
