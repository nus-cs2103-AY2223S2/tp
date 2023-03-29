package seedu.careflow.model.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.careflow.logic.commands.CommandTestUtil.VALID_IC_BOB;
import static seedu.careflow.testutil.Assert.assertThrows;
import static seedu.careflow.testutil.TypicalPatients.ALICE;
import static seedu.careflow.testutil.TypicalPatients.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.careflow.model.patient.exceptions.DuplicatePatientException;
import seedu.careflow.model.patient.exceptions.PatientNotFoundException;
import seedu.careflow.testutil.PatientBuilder;

public class UniquePatientListTest {

    private final UniquePatientList uniquePatientList = new UniquePatientList();

    @Test
    public void contains_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.containName(null));
    }

    @Test
    public void contains_patientNotInList_returnsFalse() {
        assertFalse(uniquePatientList.containName(ALICE));
    }

    @Test
    public void contains_patientInList_returnsTrue() {
        uniquePatientList.add(ALICE);
        assertTrue(uniquePatientList.containName(ALICE));
    }

    @Test
    public void contains_patientWithSameIdentityFieldsInList_returnsTrue() {
        uniquePatientList.add(ALICE);
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withIc(VALID_IC_BOB).build();
        assertTrue(uniquePatientList.containName(editedAlice));
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
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPatient(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPatient(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PatientNotFoundException.class, () -> uniquePatientList.setPatient(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniquePatientList.add(ALICE);
        uniquePatientList.setPatient(ALICE, ALICE);
        UniquePatientList expectedUniquePersonList = new UniquePatientList();
        expectedUniquePersonList.add(ALICE);
        assertEquals(expectedUniquePersonList, uniquePatientList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniquePatientList.add(ALICE);
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withIc(VALID_IC_BOB)
                .build();
        uniquePatientList.setPatient(ALICE, editedAlice);
        UniquePatientList expectedUniquePersonList = new UniquePatientList();
        expectedUniquePersonList.add(editedAlice);
        assertEquals(expectedUniquePersonList, uniquePatientList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniquePatientList.add(ALICE);
        uniquePatientList.setPatient(ALICE, BOB);
        UniquePatientList expectedUniquePersonList = new UniquePatientList();
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, uniquePatientList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniquePatientList.add(ALICE);
        uniquePatientList.add(BOB);
        assertThrows(DuplicatePatientException.class, () -> uniquePatientList.setPatient(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PatientNotFoundException.class, () -> uniquePatientList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniquePatientList.add(ALICE);
        uniquePatientList.remove(ALICE);
        UniquePatientList expectedUniquePersonList = new UniquePatientList();
        assertEquals(expectedUniquePersonList, uniquePatientList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPatients((UniquePatientList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniquePatientList.add(ALICE);
        UniquePatientList expectedUniquePersonList = new UniquePatientList();
        expectedUniquePersonList.add(BOB);
        uniquePatientList.setPatients(expectedUniquePersonList);
        assertEquals(expectedUniquePersonList, uniquePatientList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPatients((List<Patient>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniquePatientList.add(ALICE);
        List<Patient> personList = Collections.singletonList(BOB);
        uniquePatientList.setPatients(personList);
        UniquePatientList expectedUniquePersonList = new UniquePatientList();
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, uniquePatientList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Patient> listWithDuplicatePersons = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePatientException.class, () -> uniquePatientList.setPatients(listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniquePatientList.asUnmodifiableObservableList().remove(0));
    }
}
