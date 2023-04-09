package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalElister;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exceptions.ModifyFrozenStateException;
import seedu.address.model.person.FieldsMatchRegexPredicate;
import seedu.address.model.person.ParticularPersonsPredicate;
import seedu.address.model.person.Tag;

public class UnfreezeCommandTest {

    @Test
    public void execute_predicateMatchesChanged_success() {
        Model model = new ModelManager(getTypicalElister(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalElister(), new UserPrefs());

        FieldsMatchRegexPredicate tagPredicate = new FieldsMatchRegexPredicate(
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                Collections.singletonList("owesMoney")
        );
        model.updateFilteredPersonList(tagPredicate);
        expectedModel.updateFilteredPersonList(
                new ParticularPersonsPredicate(
                        Collections.singletonList(expectedModel.getFilteredPersonList().get(1))));
        try {
            model.freezeFilteredPersonList();
        } catch (ModifyFrozenStateException ex) {
            throw new AssertionError("Freezing failed on new model", ex);
        }
        model.deleteTag(BENSON, new Tag("owesMoney"));
        expectedModel.deleteTag(BENSON, new Tag("owesMoney"));
        assertEquals(model.getFilteredPersonList(), expectedModel.getFilteredPersonList());

        UnfreezeCommand unfreezeCommand = new UnfreezeCommand();
        expectedModel.updateFilteredPersonList(tagPredicate);
        assertCommandSuccess(unfreezeCommand, model,
                new CommandResult(UnfreezeCommand.MESSAGE_SUCCESS, true, true), expectedModel);
    }

    @Test
    public void execute_notFrozen_throwsException() {
        Model model = new ModelManager(getTypicalElister(), new UserPrefs());
        UnfreezeCommand unfreezeCommand = new UnfreezeCommand();
        assertCommandFailure(unfreezeCommand, model, UnfreezeCommand.MESSAGE_FAILURE);
        try {
            model.freezeFilteredPersonList();
        } catch (ModifyFrozenStateException ex) {
            throw new AssertionError("Freezing failed on new model", ex);
        }
        try {
            unfreezeCommand.execute(model);
        } catch (CommandException ex) {
            throw new AssertionError("Command failed on valid model", ex);
        }
        assertCommandFailure(unfreezeCommand, model, UnfreezeCommand.MESSAGE_FAILURE);
    }

}
