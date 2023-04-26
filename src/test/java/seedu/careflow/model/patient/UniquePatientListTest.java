package seedu.careflow.model.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_IC_AMY;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_IC_BOB;
import static seedu.careflow.testutil.Assert.assertThrows;
import static seedu.careflow.testutil.TypicalPatients.ALICE;
import static seedu.careflow.testutil.TypicalPatients.AMY;
import static seedu.careflow.testutil.TypicalPatients.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.careflow.model.patient.exceptions.DuplicatePatientException;
import seedu.careflow.model.patient.exceptions.DuplicatePatientIcException;
import seedu.careflow.model.patient.exceptions.PatientNotFoundException;
import seedu.careflow.testutil.PatientBuilder;

public class UniquePatientListTest {

    private final UniquePatientList uniquePatientList = new UniquePatientList();

    @Test
    public void containName_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.containName(null));
    }

    @Test
    public void containName_patientNotInList_returnsFalse() {
        assertFalse(uniquePatientList.containName(ALICE));
    }

    @Test
    public void containName_patientInList_returnsTrue() {
        uniquePatientList.add(ALICE);
        assertTrue(uniquePatientList.containName(ALICE));
    }

    @Test
    public void containName_patientWithSameIdentityFieldsInList_returnsTrue() {
        uniquePatientList.add(ALICE);
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withIc(VALID_IC_BOB).build();
        assertTrue(uniquePatientList.containName(editedAlice));
    }

    @Test
    public void containIc_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.containIc(null));
    }

    @Test
    public void containIc_icNotInList_returnsFalse() {
        assertFalse(uniquePatientList.containIc(ALICE));
    }

    @Test
    public void containIc_icInList_returnsTrue() {
        uniquePatientList.add(ALICE);
        Patient patient = new PatientBuilder().withIc(ALICE.getIc().value).build();
        assertTrue(uniquePatientList.containIc(patient));
    }

    @Test
    public void add_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.add(null));
    }

    @Test
    public void add_duplicatePatient_throwsDuplicatePersonException() {
        uniquePatientList.add(ALICE);
        assertThrows(DuplicatePatientException.class, () -> uniquePatientList.add(ALICE));
    }

    @Test
    public void add_duplicateIcPatient_throwsDuplicatePersonException() {
        uniquePatientList.add(ALICE);
        Patient patient = new PatientBuilder().withIc(ALICE.getIc().value).build();
        assertThrows(DuplicatePatientIcException.class, () -> uniquePatientList.add(patient));
    }

    @Test
    public void setPatient_nullTargetPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPatient(null, ALICE));
    }

    @Test
    public void setPatient_nullEditedPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPatient(ALICE, null));
    }

    @Test
    public void setPatient_targetPatientNotInList_throwsPersonNotFoundException() {
        assertThrows(PatientNotFoundException.class, () -> uniquePatientList.setPatient(ALICE, ALICE));
    }

    @Test
    public void setPatient_editedPatientIsSamePatient_success() {
        uniquePatientList.add(ALICE);
        uniquePatientList.setPatient(ALICE, ALICE);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(ALICE);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPatient_editedPatientHasSameIdentity_success() {
        uniquePatientList.add(ALICE);
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withIc(VALID_IC_BOB)
                .build();
        uniquePatientList.setPatient(ALICE, editedAlice);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(editedAlice);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPatient_editedPatientHasDifferentIdentity_success() {
        uniquePatientList.add(ALICE);
        uniquePatientList.setPatient(ALICE, BOB);
        UniquePatientList expectedUniquePersonList = new UniquePatientList();
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, uniquePatientList);
    }

    @Test
    public void setPatient_editedPatientHasNonUniqueIdentity_throwsDuplicatePatientException() {
        uniquePatientList.add(ALICE);
        uniquePatientList.add(BOB);
        assertThrows(DuplicatePatientException.class, () -> uniquePatientList.setPatient(ALICE, BOB));
    }

    @Test
    public void setPatient_editedPatientHasDuplicateIc_success() {
        uniquePatientList.add(AMY);
        Patient editedBob = new PatientBuilder(BOB).withIc(VALID_IC_AMY).build();
        uniquePatientList.setPatient(AMY, editedBob);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(editedBob);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPatient_editedPatientWithDuplicateIc_throwsDuplicatePatientIcException() {
        uniquePatientList.add(AMY);
        uniquePatientList.add(BOB);
        Patient editedBob = new PatientBuilder(BOB).withIc(VALID_IC_AMY).build();
        assertThrows(DuplicatePatientIcException.class, () -> uniquePatientList.setPatient(BOB, editedBob));
    }

    @Test
    public void remove_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.remove(null));
    }

    @Test
    public void remove_patientDoesNotExist_throwsPatientNotFoundException() {
        assertThrows(PatientNotFoundException.class, () -> uniquePatientList.remove(ALICE));
    }

    @Test
    public void remove_existingPatient_removesPatient() {
        uniquePatientList.add(ALICE);
        uniquePatientList.remove(ALICE);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPatients((UniquePatientList) null));
    }

    @Test
    public void setPatients_uniquePatientList_replacesOwnListWithProvidedUniquePatientList() {
        uniquePatientList.add(ALICE);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(BOB);
        uniquePatientList.setPatients(expectedUniquePatientList);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPatients_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPatients((List<Patient>) null));
    }

    @Test
    public void setPatients_list_replacesOwnListWithProvidedList() {
        uniquePatientList.add(ALICE);
        List<Patient> patientList = Collections.singletonList(BOB);
        uniquePatientList.setPatients(patientList);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(BOB);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPatients_listWithDuplicatePatients_throwsDuplicatePersonException() {
        List<Patient> listWithDuplicatePatients = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePatientException.class, () -> uniquePatientList.setPatients(listWithDuplicatePatients));
    }

    @Test
    public void setPatients_listWithDuplicatePatientsIc_throwsDuplicatePersonException() {
        Patient editedBob = new PatientBuilder(BOB).withIc(VALID_IC_AMY).build();
        List<Patient> listWithDuplicatePatientsIc = Arrays.asList(AMY, editedBob);
        assertThrows(
                DuplicatePatientIcException.class, () -> uniquePatientList.setPatients(listWithDuplicatePatientsIc));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniquePatientList.asUnmodifiableObservableList().remove(0));
    }
}
