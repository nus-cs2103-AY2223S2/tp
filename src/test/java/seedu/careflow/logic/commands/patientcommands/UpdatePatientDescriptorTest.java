package seedu.careflow.logic.commands.patientcommands;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.DESC_AMY;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.DESC_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_BIRTHDATE_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_DRUG_ALLERGY_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_EMAIL_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_EMERGENCY_CONTACT_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_GENDER_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_IC_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_NAME_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_PHONE_BOB;

import org.junit.jupiter.api.Test;

import seedu.careflow.logic.commands.patientcommands.UpdateCommand.EditPatientDescriptor;
import seedu.careflow.testutil.EditPatientDescriptorBuilder;

public class UpdatePatientDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPatientDescriptor descriptorWithSameValues = new EditPatientDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditPatientDescriptor editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different birthday -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withDob(VALID_BIRTHDATE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different gender -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withGender(VALID_GENDER_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different ic -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withIc(VALID_IC_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different drug allergy -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withDrugAllergy(VALID_DRUG_ALLERGY_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different emergency contact -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withEmergencyContact(VALID_EMERGENCY_CONTACT_BOB)
                .build();
        assertFalse(DESC_AMY.equals(editedAmy));

    }
}
