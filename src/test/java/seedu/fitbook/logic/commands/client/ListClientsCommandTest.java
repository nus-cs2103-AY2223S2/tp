package seedu.fitbook.logic.commands.client;

import static seedu.fitbook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.fitbook.testutil.client.TypicalClients.getTypicalFitBook;
import static seedu.fitbook.testutil.routine.TypicalRoutines.getTypicalFitBookExerciseRoutine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.fitbook.logic.commands.CommandTestUtil;
import seedu.fitbook.logic.commands.ListClientsCommand;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.FitBookModelManager;
import seedu.fitbook.model.UserPrefs;

/**
 * Contains integration tests (interaction with the FitBookModel) and unit tests for ListCommand.
 */
public class ListClientsCommandTest {

    private FitBookModel model;
    private FitBookModel expectedFitBookModel;

    @BeforeEach
    public void setUp() {
        model = new FitBookModelManager(getTypicalFitBook(),
                getTypicalFitBookExerciseRoutine(), new UserPrefs());
        expectedFitBookModel = new FitBookModelManager(model.getFitBook(),
                model.getFitBookExerciseRoutine(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        CommandTestUtil.assertCommandSuccess(new ListClientsCommand(),
                model, ListClientsCommand.MESSAGE_SUCCESS, expectedFitBookModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        CommandTestUtil.showClientAtIndex(model, INDEX_FIRST_PERSON);
        CommandTestUtil.assertCommandSuccess(new ListClientsCommand(), model, ListClientsCommand
                .MESSAGE_SUCCESS, expectedFitBookModel);
    }
}
