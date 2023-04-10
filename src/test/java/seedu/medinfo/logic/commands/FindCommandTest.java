package seedu.medinfo.logic.commands;

import static seedu.medinfo.testutil.TypicalPatients.getTypicalMedInfo;

import java.util.Arrays;

import seedu.medinfo.model.Model;
import seedu.medinfo.model.ModelManager;
import seedu.medinfo.model.UserPrefs;
import seedu.medinfo.model.patient.NameContainsKeywordsPredicate;
import seedu.medinfo.model.patient.NricContainsKeywordsPredicate;
import seedu.medinfo.model.patient.StatusContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalMedInfo(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalMedInfo(), new UserPrefs());

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    private NricContainsKeywordsPredicate prepareNricPredicate(String userInput) {
        return new NricContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    private StatusContainsKeywordsPredicate prepareStatusPredicate(String userInput) {
        return new StatusContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
