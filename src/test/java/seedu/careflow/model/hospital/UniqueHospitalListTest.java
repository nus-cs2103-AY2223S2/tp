package seedu.careflow.model.hospital;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.testutil.Assert.assertThrows;
import static seedu.careflow.testutil.TypicalHospitals.ALEXANDRA_HOSPITAL;
import static seedu.careflow.testutil.TypicalHospitals.TAN_TOCK_SENG_HOSPITAL;
import static seedu.careflow.testutil.TypicalHospitals.VALID_TAN_TOCK_SENG_HOSPITAL_HOTLINE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.careflow.model.hospital.exceptions.DuplicateHospitalException;
import seedu.careflow.model.hospital.exceptions.HospitalNotFoundException;
import seedu.careflow.testutil.HospitalBuilder;


public class UniqueHospitalListTest {
    private final UniqueHospitalList uniqueHospitalList = new UniqueHospitalList();

    @Test
    public void contains_nullHospital_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueHospitalList.contains(null));
    }

    @Test
    public void contains_hospitalNotInList_returnsFalse() {
        assertFalse(uniqueHospitalList.contains(ALEXANDRA_HOSPITAL));
    }

    @Test
    public void contains_hospitalInList_returnsTrue() {
        uniqueHospitalList.add(ALEXANDRA_HOSPITAL);
        assertTrue(uniqueHospitalList.contains(ALEXANDRA_HOSPITAL));
    }

    @Test
    public void contains_hospitalWithSameIdentityFieldsInList_returnsTrue() {
        uniqueHospitalList.add(ALEXANDRA_HOSPITAL);
        Hospital editedAlexandraHospital =
                new HospitalBuilder(ALEXANDRA_HOSPITAL).withHotline(VALID_TAN_TOCK_SENG_HOSPITAL_HOTLINE).build();
        assertTrue(uniqueHospitalList.contains(editedAlexandraHospital));
    }

    @Test
    public void add_nullHospital_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueHospitalList.add(null));
    }

    @Test
    public void add_duplicateHospital_throwsDuplicateHospitalException() {
        uniqueHospitalList.add(ALEXANDRA_HOSPITAL);
        assertThrows(DuplicateHospitalException.class, () -> uniqueHospitalList.add(ALEXANDRA_HOSPITAL));
    }

    @Test
    public void setHospital_nullTargetHospital_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueHospitalList.setHospital(null, ALEXANDRA_HOSPITAL));
    }

    @Test
    public void setHospital_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueHospitalList.setHospital(ALEXANDRA_HOSPITAL, null));
    }

    @Test
    public void setHospital_targetHospitalNotInList_throwsHospitalNotFoundException() {
        assertThrows(HospitalNotFoundException.class, () -> uniqueHospitalList.setHospital(ALEXANDRA_HOSPITAL,
                ALEXANDRA_HOSPITAL));
    }

    @Test
    public void setHospital_editedHospitalIsSameHospital_success() {
        uniqueHospitalList.add(ALEXANDRA_HOSPITAL);
        uniqueHospitalList.setHospital(ALEXANDRA_HOSPITAL, ALEXANDRA_HOSPITAL);
        UniqueHospitalList expectedUniqueHospitalList = new UniqueHospitalList();
        expectedUniqueHospitalList.add(ALEXANDRA_HOSPITAL);
        assertEquals(expectedUniqueHospitalList, uniqueHospitalList);
    }

    @Test
    public void setHospital_editedHospitalHasSameIdentity_success() {
        uniqueHospitalList.add(ALEXANDRA_HOSPITAL);
        Hospital editedAlexandraHospital = new HospitalBuilder(ALEXANDRA_HOSPITAL)
                .withHotline(VALID_TAN_TOCK_SENG_HOSPITAL_HOTLINE).build();
        uniqueHospitalList.setHospital(ALEXANDRA_HOSPITAL, editedAlexandraHospital);
        UniqueHospitalList expectedUniqueHospitalList = new UniqueHospitalList();
        expectedUniqueHospitalList.add(editedAlexandraHospital);
        assertEquals(expectedUniqueHospitalList, uniqueHospitalList);
    }

    @Test
    public void setHospital_editedPersonHasDifferentIdentity_success() {
        uniqueHospitalList.add(ALEXANDRA_HOSPITAL);
        uniqueHospitalList.setHospital(ALEXANDRA_HOSPITAL, TAN_TOCK_SENG_HOSPITAL);
        UniqueHospitalList expectedUniqueHospitalList = new UniqueHospitalList();
        expectedUniqueHospitalList.add(TAN_TOCK_SENG_HOSPITAL);
        assertEquals(expectedUniqueHospitalList, uniqueHospitalList);
    }

    @Test
    public void setHospital_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueHospitalList.add(ALEXANDRA_HOSPITAL);
        uniqueHospitalList.add(TAN_TOCK_SENG_HOSPITAL);
        assertThrows(DuplicateHospitalException.class, () -> uniqueHospitalList.setHospital(ALEXANDRA_HOSPITAL,
                TAN_TOCK_SENG_HOSPITAL));
    }

    @Test
    public void remove_nullHospital_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueHospitalList.remove(null));
    }

    @Test
    public void remove_hospitalDoesNotExist_throwsHospitalNotFoundException() {
        assertThrows(HospitalNotFoundException.class, () -> uniqueHospitalList.remove(ALEXANDRA_HOSPITAL));
    }

    @Test
    public void remove_existingHospital_removesHospital() {
        uniqueHospitalList.add(ALEXANDRA_HOSPITAL);
        uniqueHospitalList.remove(ALEXANDRA_HOSPITAL);
        UniqueHospitalList expectedUniqueHospitalList = new UniqueHospitalList();
        assertEquals(expectedUniqueHospitalList, uniqueHospitalList);
    }

    @Test
    public void setHospitals_nullUniqueHospitalList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueHospitalList.setHospitals((UniqueHospitalList) null));
    }

    @Test
    public void setHospitals_uniqueHospitalList_replacesOwnListWithProvidedUniqueHospitalList() {
        uniqueHospitalList.add(ALEXANDRA_HOSPITAL);
        UniqueHospitalList expectedUniqueHospitalList = new UniqueHospitalList();
        expectedUniqueHospitalList.add(TAN_TOCK_SENG_HOSPITAL);
        uniqueHospitalList.setHospitals(expectedUniqueHospitalList);
        assertEquals(expectedUniqueHospitalList, uniqueHospitalList);
    }

    @Test
    public void setHospitals_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueHospitalList.setHospitals((List<Hospital>) null));
    }

    @Test
    public void setHospitals_list_replacesOwnListWithProvidedList() {
        uniqueHospitalList.add(ALEXANDRA_HOSPITAL);
        List<Hospital> hospitalList = Collections.singletonList(TAN_TOCK_SENG_HOSPITAL);
        uniqueHospitalList.setHospitals(hospitalList);
        UniqueHospitalList expectedUniquePersonList = new UniqueHospitalList();
        expectedUniquePersonList.add(TAN_TOCK_SENG_HOSPITAL);
        assertEquals(expectedUniquePersonList, uniqueHospitalList);
    }

    @Test
    public void setHospitals_listWithDuplicateHospitals_throwsDuplicateHospitalException() {
        List<Hospital> listWithDuplicateHospitals = Arrays.asList(ALEXANDRA_HOSPITAL, ALEXANDRA_HOSPITAL);
        assertThrows(DuplicateHospitalException.class, () ->
                uniqueHospitalList.setHospitals(listWithDuplicateHospitals));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueHospitalList.asUnmodifiableObservableList().remove(0));
    }
}
