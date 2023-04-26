package seedu.careflow.logic.commands.drugcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.commons.core.Messages.MESSAGE_DRUGS_LISTED_OVERVIEW;
import static seedu.careflow.logic.commands.drugcommands.DrugCommandTestUtil.assertCommandSuccess;
import static seedu.careflow.testutil.TypicalDrugs.METFORMIN;
import static seedu.careflow.testutil.TypicalDrugs.ROBITUSSIN;
import static seedu.careflow.testutil.TypicalDrugs.SUDAFED;
import static seedu.careflow.testutil.TypicalDrugs.getTypicalDrugInventory;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.CareFlowModelManager;
import seedu.careflow.model.PatientRecord;
import seedu.careflow.model.UserPrefs;
import seedu.careflow.model.drug.TradeNameContainsKeywordsPredicate;

class FindCommandTest {
    private CareFlowModel model = new CareFlowModelManager(new PatientRecord(),
            getTypicalDrugInventory(), new UserPrefs());
    private CareFlowModel expectedModel = new CareFlowModelManager(new PatientRecord(),
            getTypicalDrugInventory(), new UserPrefs());

    @Test
    public void equals() {
        TradeNameContainsKeywordsPredicate firstPredicate =
                new TradeNameContainsKeywordsPredicate(Collections.singletonList("first"));
        TradeNameContainsKeywordsPredicate secondPredicate =
                new TradeNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different drugs -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noDrugFound() {
        String expectedMessage = String.format(MESSAGE_DRUGS_LISTED_OVERVIEW, 0);
        TradeNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredDrugList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredDrugList());
    }

    @Test
    public void execute_multipleKeywords_multipleDrugsFound() {
        String expectedMessage = String.format(MESSAGE_DRUGS_LISTED_OVERVIEW, 3);
        TradeNameContainsKeywordsPredicate predicate = preparePredicate("Metformin Robitussin Sudafed");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredDrugList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ROBITUSSIN, SUDAFED, METFORMIN), model.getFilteredDrugList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private TradeNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TradeNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
