package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ELDERLY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ELDERLY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DESC_PERSON_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestUtil.getTypicalModelManager;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.util.EditElderlyDescriptor;
import seedu.address.logic.commands.util.EditPersonDescriptor;
import seedu.address.logic.commands.util.EditVolunteerDescriptor;
import seedu.address.model.Model;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Nric;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.ElderlyBuilder;
import seedu.address.testutil.ModelManagerBuilder;
import seedu.address.testutil.TypicalElderly;
import seedu.address.testutil.TypicalVolunteers;
import seedu.address.testutil.VolunteerBuilder;

public class EditCommandTest {
    private final Model model = getTypicalModelManager();

    @Test
    public void execute_validElderlyNric_success() {
        Elderly selectedElderly = TypicalElderly.getTypicalElderly().get(0);
        Nric selectedNric = selectedElderly.getNric();
        Elderly editedElderly = new ElderlyBuilder().build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedElderly).build();
        Elderly resultantElderly = EditElderlyDescriptor.createEditedElderly(selectedElderly, descriptor);
        EditCommand editCommand = new EditCommand(selectedNric, descriptor);

        String expectedMessage = String.format(EditElderlyCommand.MESSAGE_EDIT_ELDERLY_SUCCESS,
                resultantElderly);

        Model expectedModel = new ModelManagerBuilder()
                .withFriendlyLink(model.getFriendlyLink())
                .build();
        expectedModel.setElderly(model.getElderly(selectedNric), resultantElderly);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validVolunteerNric_success() {
        Volunteer selectedVolunteer = TypicalVolunteers.getTypicalVolunteers().get(0);
        Nric selectedNric = selectedVolunteer.getNric();
        Volunteer editedVolunteer = new VolunteerBuilder().build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedVolunteer).build();
        Volunteer resultantVolunteer = EditVolunteerDescriptor.createEditedVolunteer(selectedVolunteer, descriptor);
        EditCommand editCommand = new EditCommand(selectedNric, descriptor);

        String expectedMessage = String.format(EditVolunteerCommand.MESSAGE_EDIT_VOLUNTEER_SUCCESS,
                resultantVolunteer);

        Model expectedModel = new ModelManagerBuilder()
                .withFriendlyLink(model.getFriendlyLink())
                .build();

        expectedModel.setVolunteer(model.getVolunteer(selectedNric), resultantVolunteer);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateElderly_throwsCommandException() {
        Elderly selectedElderly = TypicalElderly.getTypicalElderly().get(1);
        Nric selectedNric = TypicalElderly.getTypicalElderly().get(0).getNric();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(selectedElderly).build();
        EditCommand editCommand = new EditCommand(selectedNric, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_DUPLICATE_ELDERLY);
    }

    @Test
    public void execute_duplicateVolunteer_throwsCommandException() {
        Volunteer selectedVolunteer = TypicalVolunteers.getTypicalVolunteers().get(1);
        Nric selectedNric = TypicalVolunteers.getTypicalVolunteers().get(0).getNric();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(selectedVolunteer).build();
        EditCommand editCommand = new EditCommand(selectedNric, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_DUPLICATE_VOLUNTEER);
    }

    @Test
    public void execute_emptyDescriptor_throwsCommandException() {
        Nric validNric = TypicalVolunteers.getTypicalVolunteers().get(0).getNric();
        EditCommand editCommand = new EditCommand(validNric, new EditPersonDescriptor());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_NOT_EDITED);
    }
    @Test
    public void execute_invalidNric_throwsCommandException() {
        Nric invalidNric = new Nric("T9999999I");
        EditCommand editCommand = new EditCommand(
                invalidNric,
                new EditPersonDescriptorBuilder(new VolunteerBuilder().build()).build()
        );

        assertCommandFailure(editCommand, model, Messages.MESSAGE_NRIC_NOT_EXIST);
    }
    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(new Nric(VALID_NRIC_AMY), DESC_PERSON_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_PERSON_AMY);
        EditCommand commandWithSameValues = new EditCommand(new Nric(VALID_NRIC_AMY), copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different nric -> returns false
        assertNotEquals(standardCommand, new EditCommand(new Nric(VALID_NRIC_BOB), DESC_ELDERLY_AMY));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditCommand(new Nric(VALID_NRIC_AMY), DESC_ELDERLY_BOB));
    }
}
