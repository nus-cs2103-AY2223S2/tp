package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.DESC_ONE;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.DESC_TWO;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.VALID_COMPANY_EMAIL_GRAB;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.VALID_COMPANY_NAME_GRAB;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.VALID_ROLE_GRAB;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.VALID_STATUS_GRAB;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.showApplicationAtIndex;
import static seedu.address.testutil.TypicalApplicationIndexes.INDEX_FIRST_APPLICATION;
import static seedu.address.testutil.TypicalApplicationIndexes.INDEX_SECOND_APPLICATION;
import static seedu.address.testutil.TypicalApplications.getTypicalInternshipBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.ApplicationModel;
import seedu.address.model.ApplicationModelManager;
import seedu.address.model.InternshipBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.Application;
import seedu.address.testutil.ApplicationBuilder;
import seedu.address.testutil.EditApplicationDescriptorBuilder;


public class EditApplicationCommandTest {


    private ApplicationModel model =
            new ApplicationModelManager(getTypicalInternshipBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Application editedApplication = new ApplicationBuilder().build();
        EditApplicationCommand.EditApplicationDescriptor descriptor =
                new EditApplicationDescriptorBuilder(editedApplication).build();
        EditApplicationCommand editCommand =
                new EditApplicationCommand(INDEX_FIRST_APPLICATION, descriptor);

        String expectedMessage =
                String.format(EditApplicationCommand.MESSAGE_EDIT_APPLICATION_SUCCESS, editedApplication);

        ApplicationModel expectedModel = new ApplicationModelManager(
                new InternshipBook(model.getInternshipBook()), new UserPrefs());
        expectedModel.setApplication(model.getFilteredApplicationList().get(0), editedApplication);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredApplicationList().size());
        Application lastApplication = model.getFilteredApplicationList()
                .get(indexLastPerson.getZeroBased());

        ApplicationBuilder appInList = new ApplicationBuilder(lastApplication);
        Application editedApplication = appInList.withCompanyName(VALID_COMPANY_NAME_GRAB)
                .withCompanyEmail(VALID_COMPANY_EMAIL_GRAB)
                .withRole(VALID_ROLE_GRAB).withStatus(VALID_STATUS_GRAB).build();

        EditApplicationCommand.EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_GRAB).withCompanyEmail(VALID_COMPANY_EMAIL_GRAB)
                .withRole(VALID_ROLE_GRAB).withStatus(VALID_STATUS_GRAB).build();
        EditApplicationCommand editCommand = new EditApplicationCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(
                EditApplicationCommand.MESSAGE_EDIT_APPLICATION_SUCCESS, editedApplication);

        ApplicationModel expectedModel = new ApplicationModelManager(
                new InternshipBook(model.getInternshipBook()), new UserPrefs());
        expectedModel.setApplication(lastApplication, editedApplication);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditApplicationCommand editCommand = new EditApplicationCommand(INDEX_FIRST_APPLICATION,
                new EditApplicationCommand.EditApplicationDescriptor());
        Application editedApplication = model.getFilteredApplicationList()
                .get(INDEX_FIRST_APPLICATION.getZeroBased());

        String expectedMessage = String.format(EditApplicationCommand
                .MESSAGE_EDIT_APPLICATION_SUCCESS, editedApplication);

        ApplicationModel expectedModel = new ApplicationModelManager(
                new InternshipBook(model.getInternshipBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);

        Application appInFilteredList = model.getFilteredApplicationList()
                .get(INDEX_FIRST_APPLICATION.getZeroBased());
        Application editedApplication = new ApplicationBuilder(
                appInFilteredList).withCompanyName(VALID_COMPANY_NAME_GRAB).build();
        EditApplicationCommand editCommand = new EditApplicationCommand(INDEX_FIRST_APPLICATION,
                new EditApplicationDescriptorBuilder()
                        .withCompanyName(VALID_COMPANY_NAME_GRAB).build());

        String expectedMessage = String.format(
                EditApplicationCommand.MESSAGE_EDIT_APPLICATION_SUCCESS, editedApplication);

        ApplicationModel expectedModel = new ApplicationModelManager(
                new InternshipBook(model.getInternshipBook()),
                new UserPrefs());
        expectedModel.setApplication(model.getFilteredApplicationList().get(0), editedApplication);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Application firstPerson =
                model.getFilteredApplicationList().get(INDEX_FIRST_APPLICATION.getZeroBased());
        EditApplicationCommand.EditApplicationDescriptor descriptor =
                new EditApplicationDescriptorBuilder(firstPerson).build();
        EditApplicationCommand editCommand =
                new EditApplicationCommand(INDEX_SECOND_APPLICATION, descriptor);

        assertCommandFailure(editCommand, model,
                EditApplicationCommand.MESSAGE_DUPLICATE_APPLICATION);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);

        // edit person in filtered list into a duplicate in address book
        Application appInList = model.getInternshipBook().getApplicationList()
                .get(INDEX_SECOND_APPLICATION.getZeroBased());
        EditApplicationCommand editCommand = new EditApplicationCommand(INDEX_FIRST_APPLICATION,
                new EditApplicationDescriptorBuilder(appInList).build());

        assertCommandFailure(editCommand, model,
                EditApplicationCommand.MESSAGE_DUPLICATE_APPLICATION);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicationList().size() + 1);
        EditApplicationCommand.EditApplicationDescriptor descriptor =
                new EditApplicationDescriptorBuilder()
                        .withCompanyName(VALID_COMPANY_NAME_GRAB).build();
        EditApplicationCommand editCommand = new EditApplicationCommand(outOfBoundIndex,
                descriptor);

        assertCommandFailure(editCommand, model,
                Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);
        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased()
                < model.getInternshipBook().getApplicationList().size());

        EditApplicationCommand editCommand = new EditApplicationCommand(outOfBoundIndex,
                new EditApplicationDescriptorBuilder()
                        .withCompanyName(VALID_COMPANY_NAME_GRAB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditApplicationCommand standardCommand =
                new EditApplicationCommand(INDEX_FIRST_APPLICATION, DESC_ONE);

        // same values -> returns true
        EditApplicationCommand.EditApplicationDescriptor copyDescriptor =
                new EditApplicationCommand.EditApplicationDescriptor(DESC_ONE);
        EditApplicationCommand commandWithSameValues =
                new EditApplicationCommand(INDEX_FIRST_APPLICATION, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(
                new EditApplicationCommand(INDEX_SECOND_APPLICATION, DESC_ONE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(
                new EditApplicationCommand(INDEX_FIRST_APPLICATION, DESC_TWO)));
    }

}
