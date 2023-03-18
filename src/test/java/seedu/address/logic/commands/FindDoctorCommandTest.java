package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_DOCTORS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDoctors.ALICE;
import static seedu.address.testutil.TypicalDoctors.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.doctor.DoctorContainsKeywordsPredicate;
import seedu.address.model.person.doctor.DoctorFilter;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindDoctorCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {

        Set<String> bensonTags = BENSON.getTags().stream()
                .map(t -> t.tagName).collect(Collectors.toSet());

        DoctorFilter emptyFilter = new DoctorFilter("", "",
                "", "", "", new HashSet<>());

        DoctorFilter aliceFilter = new DoctorFilter(ALICE.getName().fullName,
                ALICE.getPhone().value,
                ALICE.getEmail().value,
                ALICE.getSpecialty().specialty,
                ALICE.getYoe().value,
                new HashSet<>());

        DoctorFilter bensonFilter = new DoctorFilter(BENSON.getName().fullName,
                BENSON.getPhone().value,
                BENSON.getEmail().value,
                BENSON.getSpecialty().specialty,
                BENSON.getYoe().value,
                bensonTags);

        DoctorContainsKeywordsPredicate firstPredicate =
                new DoctorContainsKeywordsPredicate(emptyFilter);
        DoctorContainsKeywordsPredicate secondPredicate =
                new DoctorContainsKeywordsPredicate(bensonFilter);
        DoctorContainsKeywordsPredicate thirdPredicate =
                new DoctorContainsKeywordsPredicate(aliceFilter);

        FindDoctorCommand findFirstCommand = new FindDoctorCommand(firstPredicate);
        FindDoctorCommand findSecondCommand = new FindDoctorCommand(secondPredicate);
        FindDoctorCommand findThirdCommand = new FindDoctorCommand(thirdPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindDoctorCommand findFirstCommandCopy = new FindDoctorCommand(firstPredicate);
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

        Set<String> bensonTags = BENSON.getTags().stream()
                .map(t -> t.tagName).collect(Collectors.toSet());
        DoctorFilter bensonFilter = new DoctorFilter(BENSON.getName().fullName,
                BENSON.getPhone().value,
                BENSON.getEmail().value,
                BENSON.getSpecialty().specialty,
                BENSON.getYoe().value,
                bensonTags);

        String expectedMessage = String.format(MESSAGE_DOCTORS_LISTED_OVERVIEW, 1);
        DoctorContainsKeywordsPredicate predicate = new DoctorContainsKeywordsPredicate(bensonFilter);
        FindDoctorCommand command = new FindDoctorCommand(predicate);
        expectedModel.updateFilteredDoctorList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(BENSON), model.getFilteredDoctorList());
    }
}
