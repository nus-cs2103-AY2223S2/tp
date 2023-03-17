package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDoctors.ALICE;
import static seedu.address.testutil.TypicalDoctors.BENSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.doctor.UniqueDoctorList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.DoctorBuilder;

public class UniqueDoctorListTest {

    private final UniqueDoctorList uniqueDoctorList = new UniqueDoctorList();

    @Test
    public void contains_nullDoctor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDoctorList.contains(null));
    }

    @Test
    public void contains_doctorNotInList_returnsFalse() {
        assertFalse(uniqueDoctorList.contains(ALICE));
    }

    @Test
    public void contains_doctorInList_returnsTrue() {
        uniqueDoctorList.add(ALICE);
        assertTrue(uniqueDoctorList.contains(ALICE));
    }

    @Test
    public void contains_doctorWithSameIdentityFieldsInList_returnsTrue() {
        uniqueDoctorList.add(ALICE);
        Doctor editedAlice = new DoctorBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueDoctorList.contains(editedAlice));
    }

    @Test
    public void add_nullDoctor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDoctorList.add(null));
    }

    @Test
    public void add_duplicateDoctor_throwsDuplicatePersonException() {
        uniqueDoctorList.add(ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueDoctorList.add(ALICE));
    }

    @Test
    public void setDoctor_nullTargetDoctor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDoctorList.setDoctor(null, ALICE));
    }

    @Test
    public void setDoctor_nullEditedDoctor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDoctorList.setDoctor(ALICE, null));
    }

    @Test
    public void setDoctor_targetDoctorNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueDoctorList.setDoctor(ALICE, ALICE));
    }

    @Test
    public void setDoctor_editedDoctorIsSameDoctor_success() {
        uniqueDoctorList.add(ALICE);
        uniqueDoctorList.setDoctor(ALICE, ALICE);
        UniqueDoctorList expectedUniqueDoctorList = new UniqueDoctorList();
        expectedUniqueDoctorList.add(ALICE);
        assertEquals(expectedUniqueDoctorList, uniqueDoctorList);
    }

    @Test
    public void setDoctor_editedDoctorHasSameIdentity_success() {
        uniqueDoctorList.add(ALICE);
        Doctor editedAlice = new DoctorBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        uniqueDoctorList.setDoctor(ALICE, editedAlice);
        UniqueDoctorList expectedUniqueDoctorList = new UniqueDoctorList();
        expectedUniqueDoctorList.add(editedAlice);
        assertEquals(expectedUniqueDoctorList, uniqueDoctorList);
    }

    @Test
    public void setDoctor_editedDoctorHasDifferentIdentity_success() {
        uniqueDoctorList.add(ALICE);
        uniqueDoctorList.setDoctor(ALICE, BENSON);
        UniqueDoctorList expectedUniqueDoctorList = new UniqueDoctorList();
        expectedUniqueDoctorList.add(BENSON);
        assertEquals(expectedUniqueDoctorList, uniqueDoctorList);
    }

    @Test
    public void setDoctor_editedDoctorHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueDoctorList.add(ALICE);
        uniqueDoctorList.add(BENSON);
        assertThrows(DuplicatePersonException.class, () -> uniqueDoctorList.setDoctor(ALICE, BENSON));
    }

    @Test
    public void remove_nullDoctor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDoctorList.remove(null));
    }

    @Test
    public void remove_doctorDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueDoctorList.remove(ALICE));
    }

    @Test
    public void remove_existingDoctor_removesDoctor() {
        uniqueDoctorList.add(ALICE);
        uniqueDoctorList.remove(ALICE);
        UniqueDoctorList expectedUniqueDoctorList = new UniqueDoctorList();
        assertEquals(expectedUniqueDoctorList, uniqueDoctorList);
    }

    @Test
    public void setDoctors_nullUniqueDoctorList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDoctorList.setDoctors((UniqueDoctorList) null));
    }

    @Test
    public void setDoctors_uniqueDoctorList_replacesOwnListWithProvidedUniqueDoctorList() {
        uniqueDoctorList.add(ALICE);
        UniqueDoctorList expectedUniqueDoctorList = new UniqueDoctorList();
        expectedUniqueDoctorList.add(BENSON);
        uniqueDoctorList.setDoctors(expectedUniqueDoctorList);
        assertEquals(expectedUniqueDoctorList, uniqueDoctorList);
    }

    @Test
    public void setDoctors_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDoctorList.setDoctors((List<Doctor>) null));
    }

    @Test
    public void setDoctors_list_replacesOwnListWithProvidedList() {
        uniqueDoctorList.add(ALICE);
        List<Doctor> doctorList = Collections.singletonList(BENSON);
        uniqueDoctorList.setDoctors(doctorList);
        UniqueDoctorList expectedUniqueDoctorList = new UniqueDoctorList();
        expectedUniqueDoctorList.add(BENSON);
        assertEquals(expectedUniqueDoctorList, uniqueDoctorList);
    }

    @Test
    public void setDoctors_listWithDuplicateDoctors_throwsDuplicatePersonException() {
        List<Doctor> listWithDuplicateDoctors = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueDoctorList.setDoctors(listWithDuplicateDoctors));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueDoctorList.asUnmodifiableObservableList().remove(0));
    }
}
