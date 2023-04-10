package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalElister;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exceptions.ModifyFrozenStateException;
import seedu.address.model.person.FieldsMatchRegexPredicate;
import seedu.address.model.person.ParticularPersonsPredicate;
import seedu.address.model.person.Tag;

public class FreezeCommandTest {

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
        expectedModel.updateFilteredPersonList(tagPredicate);
        try {
            expectedModel.freezeFilteredPersonList();
        } catch (ModifyFrozenStateException ex) {
            throw new AssertionError("Freezing failed on new model", ex);
        }
        FreezeCommand freezeCommand = new FreezeCommand();
        assertCommandSuccess(freezeCommand, model,
                new CommandResult(FreezeCommand.MESSAGE_SUCCESS, true, true), expectedModel);
        model.deleteTag(BENSON, new Tag("owesMoney"));
        expectedModel.deleteTag(BENSON, new Tag("owesMoney"));
        assertEquals(model.getFilteredPersonList(), expectedModel.getFilteredPersonList());
        try {
            expectedModel.unfreezeFilteredPersonList();
        } catch (ModifyFrozenStateException ex) {
            throw new AssertionError("Unfreezing failed on valid model", ex);
        }
        expectedModel.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        expectedModel.updateFilteredPersonList(
                new ParticularPersonsPredicate(
                        Collections.singletonList(expectedModel.getFilteredPersonList().get(1))));
        assertEquals(model.getFilteredPersonList(), expectedModel.getFilteredPersonList());
    }

    @Test
    public void execute_alreadyFrozen_throwsException() {
        Model model = new ModelManager(getTypicalElister(), new UserPrefs());
        FreezeCommand freezeCommand = new FreezeCommand();
        try {
            model.freezeFilteredPersonList();
        } catch (ModifyFrozenStateException ex) {
            throw new AssertionError("Freezing failed on new model", ex);
        }
        assertCommandFailure(freezeCommand, model, FreezeCommand.MESSAGE_FAILURE);
    }

}
