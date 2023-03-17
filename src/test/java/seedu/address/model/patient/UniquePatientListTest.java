package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.ALEX;
import static seedu.address.testutil.TypicalPatients.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.patient.exceptions.DuplicatePatientException;
import seedu.address.model.patient.exceptions.PatientNotFoundException;
import seedu.address.testutil.PatientBuilder;

public class UniquePatientListTest {

    private final UniquePatientList uniquePatientList = new UniquePatientList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniquePatientList.contains(ALEX));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniquePatientList.add(ALEX);
        assertTrue(uniquePatientList.contains(ALEX));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniquePatientList.add(ALEX);
        Patient editedAlice = new PatientBuilder(ALEX)
                .build();
        assertTrue(uniquePatientList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniquePatientList.add(ALEX);
        assertThrows(DuplicatePatientException.class, () -> uniquePatientList.add(ALEX));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPatient(null, ALEX));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPatient(ALEX, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PatientNotFoundException.class, () -> uniquePatientList.setPatient(ALEX, ALEX));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniquePatientList.add(ALEX);
        uniquePatientList.setPatient(ALEX, ALEX);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(ALEX);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniquePatientList.add(ALEX);
        Patient editedAlice = new PatientBuilder(ALEX)
                .build();
        uniquePatientList.setPatient(ALEX, editedAlice);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(editedAlice);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniquePatientList.add(ALEX);
        uniquePatientList.setPatient(ALEX, BOB);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(BOB);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniquePatientList.add(ALEX);
        uniquePatientList.add(BOB);
        assertThrows(DuplicatePatientException.class, () -> uniquePatientList.setPatient(ALEX, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PatientNotFoundException.class, () -> uniquePatientList.remove(ALEX));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniquePatientList.add(ALEX);
        uniquePatientList.remove(ALEX);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPatients((UniquePatientList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniquePatientList.add(ALEX);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(BOB);
        uniquePatientList.setPatients(expectedUniquePatientList);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPatients((List<Patient>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniquePatientList.add(ALEX);
        List<Patient> patientList = Collections.singletonList(BOB);
        uniquePatientList.setPatients(patientList);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(BOB);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Patient> listWithDuplicatePatients = Arrays.asList(ALEX, ALEX);
        assertThrows(DuplicatePatientException.class, () -> uniquePatientList.setPatients(listWithDuplicatePatients));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniquePatientList.asUnmodifiableObservableList().remove(0));
    }
}
