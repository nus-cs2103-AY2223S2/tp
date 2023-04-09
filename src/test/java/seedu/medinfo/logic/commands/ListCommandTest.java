package seedu.medinfo.logic.commands;

import static seedu.medinfo.commons.core.Messages.MESSAGE_ALL_PATIENTS_LISTED_OVERVIEW;
import static seedu.medinfo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.medinfo.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.medinfo.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.medinfo.testutil.TypicalPatients.getTypicalMedInfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.medinfo.model.Model;
import seedu.medinfo.model.ModelManager;
import seedu.medinfo.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMedInfo(), new UserPrefs());
        expectedModel = new ModelManager(model.getMedInfo(), new UserPrefs());
    }

    //    @Test
    //    public void execute_listIsNotFiltered_showsSameList() {
    //        String expectedMessage = String.format(MESSAGE_ALL_PATIENTS_LISTED_OVERVIEW, 7);
    //        assertCommandSuccess(new ListCommand(), model, expectedMessage, expectedModel);
    //    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        String expectedMessage = String.format(MESSAGE_ALL_PATIENTS_LISTED_OVERVIEW, 7);
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, expectedMessage, expectedModel);
    }
}
