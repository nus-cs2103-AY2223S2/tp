package seedu.patientist.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.patientist.testutil.Assert.assertThrows;
import static seedu.patientist.testutil.TypicalPatients.AMY;
import static seedu.patientist.testutil.TypicalStaff.BOB;
import static seedu.patientist.testutil.TypicalStaff.CHARLES;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.patientist.model.person.exceptions.DuplicatePersonException;
import seedu.patientist.model.person.exceptions.PersonNotFoundException;
import seedu.patientist.model.person.staff.Staff;
import seedu.patientist.testutil.PatientBuilder;
import seedu.patientist.testutil.StaffBuilder;

public class UniquePersonListTest {

    private static final String DUMMY_WARD_NAME = "DUMMY WARDNAME";
    private final UniquePersonList uniquePersonList = new UniquePersonList(DUMMY_WARD_NAME);

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniquePersonList.contains(AMY));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniquePersonList.add(AMY);
        assertTrue(uniquePersonList.contains(AMY));
    }

    @Test
    public void contains_personWithSameIdDifferentAttrsInList_returnsTrue() {
        uniquePersonList.add(AMY);
        Person editedAmy = new PatientBuilder(AMY).withAddress("different addr")
                .withEmail("exampledifferentemail@email.com").withTags("extratag")
                .build();
        assertTrue(uniquePersonList.contains(editedAmy));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniquePersonList.add(AMY);
        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.add(AMY));
    }

    @Test
    public void add_differentPersonSameId_throwsDuplicatePersonException() {
        uniquePersonList.add(BOB);
        Staff editedCharles = new StaffBuilder(CHARLES).withIdNumber(BOB.getIdNumber().idNumber).build();
        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.add(editedCharles));
    }

    @Test
    public void add_sameAttributesDifferentId_success() {
        uniquePersonList.add(BOB);
        Staff editedBob = new StaffBuilder(BOB).withIdNumber("D8439555H").build();
        uniquePersonList.add(editedBob);
        assertTrue(uniquePersonList.contains(editedBob));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setPerson(null, AMY));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setPerson(AMY, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniquePersonList.setPerson(AMY, AMY));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniquePersonList.add(AMY);
        uniquePersonList.setPerson(AMY, AMY);
        UniquePersonList expectedUniquePersonList = new UniquePersonList(DUMMY_WARD_NAME);
        expectedUniquePersonList.add(AMY);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniquePersonList.add(AMY);
        Person editedAmy = new PatientBuilder(AMY).withAddress("different test address").build();
        uniquePersonList.setPerson(AMY, editedAmy);
        UniquePersonList expectedUniquePersonList = new UniquePersonList(DUMMY_WARD_NAME);
        expectedUniquePersonList.add(editedAmy);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniquePersonList.add(AMY);
        uniquePersonList.setPerson(AMY, BOB);
        UniquePersonList expectedUniquePersonList = new UniquePersonList(DUMMY_WARD_NAME);
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniquePersonList.add(AMY);
        uniquePersonList.add(BOB);
        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.setPerson(AMY, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniquePersonList.remove(AMY));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniquePersonList.add(AMY);
        uniquePersonList.remove(AMY);
        UniquePersonList expectedUniquePersonList = new UniquePersonList(DUMMY_WARD_NAME);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setPersons((UniquePersonList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniquePersonList.add(AMY);
        UniquePersonList expectedUniquePersonList = new UniquePersonList(DUMMY_WARD_NAME);
        expectedUniquePersonList.add(BOB);
        uniquePersonList.setPersons(expectedUniquePersonList);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setPersons((List<Person>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniquePersonList.add(AMY);
        List<Person> personList = Collections.singletonList(BOB);
        uniquePersonList.setPersons(personList);
        UniquePersonList expectedUniquePersonList = new UniquePersonList(DUMMY_WARD_NAME);
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Person> listWithDuplicatePersons = Arrays.asList(AMY, AMY);
        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.setPersons(listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniquePersonList.asUnmodifiableObservableList().remove(0));
    }
}
