package seedu.internship.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.logic.commands.CommandTestUtil.DESC_ML1;
import static seedu.internship.logic.commands.CommandTestUtil.DESC_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_COMPANY_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_POSITION_DA1;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_POSITION_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_TAG_FUN;
import static seedu.internship.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.internship.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internship.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.internship.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.internship.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.internship.testutil.TypicalInternships.getTypicalInternshipCatalogue;

import org.junit.jupiter.api.Test;

import seedu.internship.commons.core.Messages;
import seedu.internship.commons.core.index.Index;
import seedu.internship.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.internship.model.InternshipCatalogue;
import seedu.internship.model.Model;
import seedu.internship.model.ModelManager;
import seedu.internship.model.UserPrefs;
import seedu.internship.model.internship.Internship;
import seedu.internship.testutil.EditInternshipDescriptorBuilder;
import seedu.internship.testutil.InternshipBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalInternshipCatalogue(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Internship editedInternship = new InternshipBuilder().build();
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder(editedInternship).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_INTERNSHIP, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(
                new InternshipCatalogue(model.getInternshipCatalogue()), new UserPrefs());
        expectedModel.setInternship(model.getFilteredInternshipList().get(0), editedInternship);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastInternship = Index.fromOneBased(model.getFilteredInternshipList().size());
        Internship lastInternship = model.getFilteredInternshipList().get(indexLastInternship.getZeroBased());

        InternshipBuilder internshipInList = new InternshipBuilder(lastInternship);
        Internship editedInternship = internshipInList.withPosition(VALID_POSITION_SE1).withCompany(VALID_COMPANY_SE1)
                .withTags(VALID_TAG_FUN).build();

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withPosition(VALID_POSITION_SE1)
                .withCompany(VALID_COMPANY_SE1).withTags(VALID_TAG_FUN).build();
        EditCommand editCommand = new EditCommand(indexLastInternship, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(
                new InternshipCatalogue(model.getInternshipCatalogue()), new UserPrefs());
        expectedModel.setInternship(lastInternship, editedInternship);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_INTERNSHIP, new EditInternshipDescriptor());
        Internship editedInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(
                new InternshipCatalogue(model.getInternshipCatalogue()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Internship internshipInFilteredList = model.getFilteredInternshipList()
                .get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        Internship editedInternship = new InternshipBuilder(internshipInFilteredList)
                .withPosition(VALID_POSITION_DA1).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_INTERNSHIP,
                new EditInternshipDescriptorBuilder().withPosition(VALID_POSITION_DA1).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(
                new InternshipCatalogue(model.getInternshipCatalogue()), new UserPrefs());
        expectedModel.setInternship(model.getFilteredInternshipList().get(0), editedInternship);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateInternshipUnfilteredList_failure() {
        Internship firstInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder(firstInternship).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_INTERNSHIP, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_INTERNSHIP);
    }

    @Test
    public void execute_duplicateInternshipFilteredList_failure() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        // edit internship in filtered list into a duplicate in internship catalogue
        Internship internshipInList = model.getInternshipCatalogue()
                .getInternshipList().get(INDEX_SECOND_INTERNSHIP.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_INTERNSHIP,
                new EditInternshipDescriptorBuilder(internshipInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_INTERNSHIP);
    }

    @Test
    public void execute_invalidInternshipIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipList().size() + 1);
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withPosition(VALID_POSITION_SE1).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of internship catalogue
     */
    @Test
    public void execute_invalidInternshipIndexFilteredList_failure() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);
        Index outOfBoundIndex = INDEX_SECOND_INTERNSHIP;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternshipCatalogue().getInternshipList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditInternshipDescriptorBuilder().withPosition(VALID_POSITION_SE1).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_INTERNSHIP, DESC_ML1);

        // same values -> returns true
        EditInternshipDescriptor copyDescriptor = new EditInternshipDescriptor(DESC_ML1);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_INTERNSHIP, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        // uncomment when clear command is implemented.
        //assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_INTERNSHIP, DESC_ML1)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_INTERNSHIP, DESC_SE1)));
    }

}
