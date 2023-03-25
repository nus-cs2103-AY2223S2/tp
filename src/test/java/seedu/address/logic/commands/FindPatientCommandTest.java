package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.YANNIE;
import static seedu.address.testutil.TypicalPatients.ZAYDEN;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.patient.PatientContainsKeywordsPredicate;
import seedu.address.model.person.patient.PatientFilter;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindPatientCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {

        Set<String> zaydenTags = ZAYDEN.getTags().stream()
                .map(t -> t.tagName).collect(Collectors.toSet());

        PatientFilter emptyFilter = new PatientFilter("", "",
                "", "", "", "", "", "", new HashSet<>());

        PatientFilter yannieFilter = new PatientFilter(YANNIE.getName().fullName,
                YANNIE.getPhone().value,
                YANNIE.getEmail().value,
                YANNIE.getHeight().value,
                YANNIE.getWeight().value,
                YANNIE.getDiagnosis().diagnosis,
                YANNIE.getStatus().status,
                YANNIE.getRemark().remark,
                new HashSet<>());

        PatientFilter zaydenFilter = new PatientFilter(ZAYDEN.getName().fullName,
                ZAYDEN.getPhone().value,
                ZAYDEN.getEmail().value,
                ZAYDEN.getHeight().value,
                ZAYDEN.getWeight().value,
                ZAYDEN.getDiagnosis().diagnosis,
                ZAYDEN.getStatus().status,
                ZAYDEN.getRemark().remark,
                zaydenTags);

        PatientContainsKeywordsPredicate firstPredicate =
                new PatientContainsKeywordsPredicate(emptyFilter);
        PatientContainsKeywordsPredicate secondPredicate =
                new PatientContainsKeywordsPredicate(zaydenFilter);
        PatientContainsKeywordsPredicate thirdPredicate =
                new PatientContainsKeywordsPredicate(yannieFilter);

        FindPatientCommand findFirstCommand = new FindPatientCommand(firstPredicate);
        FindPatientCommand findSecondCommand = new FindPatientCommand(secondPredicate);
        FindPatientCommand findThirdCommand = new FindPatientCommand(thirdPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindPatientCommand findFirstCommandCopy = new FindPatientCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
        assertFalse(findSecondCommand.equals(findThirdCommand));
        assertFalse(findFirstCommand.equals(findThirdCommand));
    }

    @Test
    public void execute_exactKeywords_onePersonFound() {

        Set<String> zaydenTags = ZAYDEN.getTags().stream()
                .map(t -> t.tagName).collect(Collectors.toSet());
        PatientFilter zaydenFilter = new PatientFilter(ZAYDEN.getName().fullName,
                ZAYDEN.getPhone().value,
                ZAYDEN.getEmail().value,
                ZAYDEN.getHeight().value,
                ZAYDEN.getWeight().value,
                ZAYDEN.getDiagnosis().diagnosis,
                ZAYDEN.getStatus().status,
                ZAYDEN.getRemark().remark,
                zaydenTags);

        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 1);
        PatientContainsKeywordsPredicate predicate = new PatientContainsKeywordsPredicate(zaydenFilter);
        FindPatientCommand command = new FindPatientCommand(predicate);
        expectedModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(ZAYDEN), model.getFilteredPatientList());
    }
}
