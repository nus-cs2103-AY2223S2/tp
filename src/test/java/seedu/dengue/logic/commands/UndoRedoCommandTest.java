package seedu.dengue.logic.commands;

import static seedu.dengue.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dengue.testutil.Assert.assertThrows;
import static seedu.dengue.testutil.TypicalPersons.getTypicalDengueHotspotTracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.dengue.logic.commands.exceptions.CommandException;
import seedu.dengue.model.Model;
import seedu.dengue.model.ModelManager;
import seedu.dengue.model.UserPrefs;
import seedu.dengue.model.person.Person;
import seedu.dengue.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code UndoCommand} and {@code RedoCommand}.
 */
public class UndoRedoCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        this.model = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());
    }
    @Test
    public void execute_undoOnNoSaves_throwsCommandException() throws CommandException {
        UndoCommand undo = new UndoCommand();
        assertThrows(CommandException.class, () -> undo.execute(model));
    }

    @Test
    public void execute_moreUndosThanSavesAvailable_success() throws CommandException {
        UndoCommand undo = new UndoCommand(20);
        Person toAdd = new PersonBuilder().withName("Bob Turner").build();
        model.addPerson(toAdd);

        ModelManager expectedModel = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());
        expectedModel.addPerson(toAdd.getCopy());
        expectedModel.undo();

        String expectedMessage = String.format(UndoCommand.MESSAGE_SUCCESS, 1);
        assertCommandSuccess(undo, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_redoWhenImpossible_throwsCommandException() throws CommandException {
        RedoCommand redo = new RedoCommand();
        assertThrows(CommandException.class, () -> redo.execute(model));
    }

    @Test
    public void execute_setAddAndDeleteOperations_success() throws CommandException {
        UndoCommand undo = new UndoCommand();
        RedoCommand redo = new RedoCommand();

        Person bobby = new PersonBuilder().withName("Bobby").build();
        Person timmy = new PersonBuilder().withName("Tim").build();

        ModelManager expectedModel = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());
        ModelManager finalModel = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());
        model.addPerson(bobby);
        finalModel.addPerson(bobby);

        assertCommandSuccess(undo, model, String.format(UndoCommand.MESSAGE_SUCCESS, 1), expectedModel);
        assertCommandSuccess(redo, model, String.format(RedoCommand.MESSAGE_SUCCESS, 1), finalModel);
        model.setPerson(bobby, timmy);

        finalModel.setPerson(bobby, timmy);

        ModelManager expectedModelSecond = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());
        expectedModelSecond.addPerson(bobby);
        assertCommandSuccess(undo, model, String.format(UndoCommand.MESSAGE_SUCCESS, 1), expectedModelSecond);
        assertCommandSuccess(redo, model, String.format(RedoCommand.MESSAGE_SUCCESS, 1), finalModel);

        model.deletePerson(timmy);
        assertCommandSuccess(undo, model, String.format(UndoCommand.MESSAGE_SUCCESS, 1), finalModel);

    }

    @Test
    public void execute_undoAndRedo_success() throws CommandException {
        RedoCommand redoOnce = new RedoCommand();
        RedoCommand redoMany = new RedoCommand(20);
        UndoCommand undoOnce = new UndoCommand();
        UndoCommand undoMany = new UndoCommand(20);
        ModelManager firstModel = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());

        Person toAdd = new PersonBuilder().withName("Bob Turner").build();
        model.addPerson(toAdd);
        ModelManager secondModel = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());
        secondModel.addPerson(toAdd);

        Person toAddSecond = new PersonBuilder().withName("Tob Burner").build();
        model.addPerson(toAddSecond);
        ModelManager thirdModel = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());
        thirdModel.addPerson(toAdd);
        thirdModel.addPerson(toAddSecond);

        Person toRemove = toAdd;
        model.deletePerson(toRemove);
        ModelManager fourthModel = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());
        fourthModel.addPerson(toAdd);
        fourthModel.addPerson(toAddSecond);
        fourthModel.deletePerson(toRemove);

        // There are now four saved iterations of the model.
        assertCommandSuccess(undoOnce, model, String.format(UndoCommand.MESSAGE_SUCCESS, 1), thirdModel);
        // undoMany performs undo 20 times, but there are only 2 iterations to undo.
        assertCommandSuccess(undoMany, model, String.format(UndoCommand.MESSAGE_SUCCESS, 2), firstModel);
        assertCommandSuccess(redoMany, model, String.format(RedoCommand.MESSAGE_SUCCESS, 3), fourthModel);
        assertCommandSuccess(undoOnce, model, String.format(UndoCommand.MESSAGE_SUCCESS, 1), thirdModel);
        assertCommandSuccess(undoOnce, model, String.format(UndoCommand.MESSAGE_SUCCESS, 1), secondModel);
        assertCommandSuccess(redoOnce, model, String.format(RedoCommand.MESSAGE_SUCCESS, 1), thirdModel);
        assertCommandSuccess(redoMany, model, String.format(RedoCommand.MESSAGE_SUCCESS, 1), fourthModel);
        assertThrows(CommandException.class, () -> redoOnce.execute(model));

    }

    @Test
    public void execute_moreThanTenSaves_undoTenTimes() {
        ModelManager firstModel = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());
        for (int i = 0; i < 25; i++) {
            model.addPerson(new PersonBuilder().buildRandom());
        }

        UndoCommand undoNine = new UndoCommand(9);
        assertCommandSuccess(undoNine, model, String.format(UndoCommand.MESSAGE_SUCCESS, 9), model);

        for (int i = 0; i < 25; i++) {
            model.addPerson(new PersonBuilder().buildRandom());
        }

        UndoCommand undoMany = new UndoCommand(25);
        assertCommandSuccess(undoMany, model, String.format(UndoCommand.MESSAGE_SUCCESS, 9), model);
    }

    @Test
    public void executeRedo_undoThenAddOperation_redoThrowsCommandException() {
        ModelManager expectedModel = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());
        model.addPerson(new PersonBuilder().withName("BENSONNY").build());

        UndoCommand undo = new UndoCommand();
        assertCommandSuccess(undo, model, String.format(UndoCommand.MESSAGE_SUCCESS, 1), expectedModel);

        model.addPerson(new PersonBuilder().withName("AMISHA").build());
        RedoCommand redo = new RedoCommand();
        assertThrows(CommandException.class, () -> model.redo());

    }
}
