package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;
import static seedu.address.testutil.TypicalPatients.YANNIE;
import static seedu.address.testutil.TypicalPatients.ZAYDEN;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientsOnlyAddressBook;

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
    private Model model = new ModelManager(getTypicalPatientsOnlyAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalPatientsOnlyAddressBook(), new UserPrefs());

    @Test
    public void equals() {

        Set<String> zaydenTags = ZAYDEN.getTags().stream()
                .map(t -> t.getTagName()).collect(Collectors.toSet());

        PatientFilter emptyFilter = new PatientFilter("", "",
                "", "", "", "", "", "", new HashSet<>());

        PatientFilter yannieFilter = new PatientFilter(YANNIE.getName().getValue(),
                YANNIE.getPhone().getValue(),
                YANNIE.getEmail().getValue(),
                YANNIE.getHeight().getValue(),
                YANNIE.getWeight().getValue(),
                YANNIE.getDiagnosis().getValue(),
                YANNIE.getStatus().getValue(),
                YANNIE.getRemark().getValue(),
                new HashSet<>());

        PatientFilter zaydenFilter = new PatientFilter(ZAYDEN.getName().getValue(),
                ZAYDEN.getPhone().getValue(),
                ZAYDEN.getEmail().getValue(),
                ZAYDEN.getHeight().getValue(),
                ZAYDEN.getWeight().getValue(),
                ZAYDEN.getDiagnosis().getValue(),
                ZAYDEN.getStatus().getValue(),
                ZAYDEN.getRemark().getValue(),
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
                .map(t -> t.getTagName()).collect(Collectors.toSet());
        PatientFilter zaydenFilter = new PatientFilter(ZAYDEN.getName().getValue(),
                ZAYDEN.getPhone().getValue(),
                ZAYDEN.getEmail().getValue(),
                ZAYDEN.getHeight().getValue(),
                ZAYDEN.getWeight().getValue(),
                ZAYDEN.getDiagnosis().getValue(),
                ZAYDEN.getStatus().getValue(),
                ZAYDEN.getRemark().getValue(),
                zaydenTags);

        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 1);
        PatientContainsKeywordsPredicate predicate = new PatientContainsKeywordsPredicate(zaydenFilter);
        FindPatientCommand command = new FindPatientCommand(predicate);
        expectedModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(ZAYDEN), model.getFilteredPatientList());
    }

    @Test
    public void execute_getCommandUsageSuccessful() {
        String messageUsage = FindPatientCommand.COMMAND_WORD + " (short form: "
                + FindPatientCommand.SHORTHAND_COMMAND_WORD + ")"
                + ": Finds a patient in the address book. "
                + "Parameters: "
                + "[" + PREFIX_NAME + "NAME] "
                + "[" + PREFIX_PHONE + "PHONE] "
                + "[" + PREFIX_EMAIL + "EMAIL] "
                + "[" + PREFIX_HEIGHT + "HEIGHT] "
                + "[" + PREFIX_WEIGHT + "WEIGHT] "
                + "[" + PREFIX_DIAGNOSIS + "DIAGNOSIS] "
                + "[" + PREFIX_STATUS + "STATUS] "
                + "[" + PREFIX_REMARK + "REMARK] "
                + "[" + PREFIX_TAG + "TAG]...\n"
                + "At least one of the parameters must be present. \n"
                + "Only one of each parameter (excluding " + PREFIX_TAG + "TAG) may be present. \n"
                + "Example: " + FindPatientCommand.COMMAND_WORD + " "
                + PREFIX_NAME + "John Doe";
        assertEquals(messageUsage, FindPatientCommand.getCommandUsage());
    }

    @Test
    public void execute_getMessageSuccessSuccessful() {
        String messageSuccess = "%1$d patient(s) listed!";
        assertEquals(messageSuccess, FindPatientCommand.getMessageSuccess());
    }
}
