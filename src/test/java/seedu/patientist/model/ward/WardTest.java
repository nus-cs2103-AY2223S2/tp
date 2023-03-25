package seedu.patientist.model.ward;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.patientist.testutil.Assert.assertThrows;
import static seedu.patientist.testutil.TypicalPatients.ADAM;
import static seedu.patientist.testutil.TypicalPatients.AMY;
import static seedu.patientist.testutil.TypicalPatients.BOB;
import static seedu.patientist.testutil.TypicalPatients.CHARLIE;
import static seedu.patientist.testutil.TypicalStaff.CHARLES;
import static seedu.patientist.testutil.TypicalStaff.DACIA;
import static seedu.patientist.testutil.TypicalWards.getBlockAWard1;
import static seedu.patientist.testutil.TypicalWards.getBlockAWard2;

import org.junit.jupiter.api.Test;

import seedu.patientist.model.person.exceptions.DuplicatePersonException;
import seedu.patientist.model.person.exceptions.PersonNotFoundException;
import seedu.patientist.testutil.WardBuilder;

public class WardTest {

    @Test
    public void constructor_nullWardName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Ward(null));
    }

    @Test
    public void constructor_invalidWardName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Ward(""));
    }

    @Test
    public void contains_personInWard_true() {
        Ward blockAWard1Copy = new WardBuilder(getBlockAWard1()).build();
        assertTrue(blockAWard1Copy.containsStaff(CHARLES));
        assertTrue(blockAWard1Copy.containsPatient(AMY));
        assertTrue(blockAWard1Copy.containsPerson(CHARLIE));
    }

    @Test
    public void contains_personNotInWard_false() {
        Ward blockAWard1Copy = new WardBuilder(getBlockAWard1()).build();

        assertFalse(getBlockAWard2().containsStaff(CHARLES));
        assertFalse(blockAWard1Copy.containsPatient(BOB));
        assertFalse(getBlockAWard2().containsPerson(CHARLIE));

        Ward emptyWard = new Ward("empty test ward");
        assertFalse(emptyWard.containsPerson(DACIA));
        assertFalse(emptyWard.containsPatient(ADAM));
        assertFalse(emptyWard.containsStaff(CHARLES));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        Ward blockAWard1Copy = new WardBuilder(getBlockAWard1()).build();

        Ward emptyWard = new Ward("empty test ward");
        assertThrows(NullPointerException.class, () -> emptyWard.addPatient(null));
        assertThrows(NullPointerException.class, () -> blockAWard1Copy.addPatient(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        Ward blockAWard1Copy = new WardBuilder(getBlockAWard1()).build();

        Ward emptyWard = new Ward("empty test ward");
        emptyWard.addStaff(DACIA);
        emptyWard.addPatient(ADAM);
        assertThrows(DuplicatePersonException.class, () -> emptyWard.addStaff(DACIA));
        assertThrows(DuplicatePersonException.class, () -> emptyWard.addPatient(ADAM));
        assertThrows(DuplicatePersonException.class, () -> blockAWard1Copy.addPatient(AMY));
    }

    @Test
    public void add_success() {
        Ward mock = new Ward("empty test ward");
        mock.addPatient(AMY);
        mock.addStaff(DACIA);
        assertTrue(mock.containsPatient(AMY));
        assertTrue(mock.containsStaff(DACIA));
    }

    @Test
    public void delete_nullPerson_throwsNullPointerException() {
        Ward blockAWard2Copy = new WardBuilder(getBlockAWard1()).build();

        assertThrows(NullPointerException.class, () -> blockAWard2Copy.deletePatient(null));
        assertThrows(NullPointerException.class, () -> blockAWard2Copy.deleteStaff(null));
        assertThrows(NullPointerException.class, () -> blockAWard2Copy.deletePerson(null));

        Ward emptyWard = new Ward("empty test ward");
        assertThrows(NullPointerException.class, () -> emptyWard.deletePerson(null));
    }

    @Test
    public void delete_personNotInList_throwsPersonNotFoundException() {
        Ward blockAWard1Copy = new WardBuilder(getBlockAWard1()).build();

        assertThrows(PersonNotFoundException.class, () -> blockAWard1Copy.deletePatient(BOB));
        assertThrows(PersonNotFoundException.class, () -> blockAWard1Copy.deleteStaff(DACIA));
        assertThrows(PersonNotFoundException.class, () -> blockAWard1Copy.deletePerson(BOB));

        Ward emptyWard = new Ward("empty test ward");
        assertThrows(PersonNotFoundException.class, () -> emptyWard.deletePerson(ADAM));
    }

    @Test
    public void delete_personInList_success() {
        Ward blockAWard1Copy = new WardBuilder(getBlockAWard1()).build();

        assertTrue(blockAWard1Copy.containsPerson(AMY));
        blockAWard1Copy.deletePerson(AMY);
        assertFalse(blockAWard1Copy.containsPerson(AMY));
        assertTrue(blockAWard1Copy.containsPerson(CHARLIE));
        blockAWard1Copy.deletePatient(CHARLIE);
        assertFalse(blockAWard1Copy.containsPerson(CHARLIE));
        assertTrue(blockAWard1Copy.containsPerson(CHARLES));
        blockAWard1Copy.deleteStaff(CHARLES);
        assertFalse(blockAWard1Copy.containsPerson(CHARLES));
    }

    @Test
    public void set_nullPerson_throwsNullPointerException() {
        Ward blockAWard1Copy = new WardBuilder(getBlockAWard1()).build();

        assertThrows(NullPointerException.class, () -> blockAWard1Copy.setPatient(null, AMY));
        assertThrows(NullPointerException.class, () -> blockAWard1Copy.setStaff(null, CHARLES));
        assertThrows(NullPointerException.class, () -> blockAWard1Copy.setPatient(ADAM, null));
        assertThrows(NullPointerException.class, () -> blockAWard1Copy.setStaff(DACIA, null));
    }

    @Test
    public void set_targetNotInList_throwsPersonNotFoundException() {
        Ward blockAWard1Copy = new WardBuilder(getBlockAWard1()).build();

        assertThrows(PersonNotFoundException.class, () -> blockAWard1Copy.setStaff(DACIA, DACIA));
        assertThrows(PersonNotFoundException.class, () -> blockAWard1Copy.setPatient(ADAM, BOB));
    }

    @Test
    public void set_success() {
        Ward blockAWard1Copy = new WardBuilder(getBlockAWard1()).build();

        blockAWard1Copy.setStaff(CHARLES, DACIA);
        assertTrue(blockAWard1Copy.containsPerson(DACIA));
        blockAWard1Copy.setPatient(CHARLIE, BOB);
        assertTrue(blockAWard1Copy.containsPerson(BOB));
    }

    @Test
    public void equals() {
        assertEquals(getBlockAWard1(), getBlockAWard1());
        assertNotEquals(getBlockAWard1(), getBlockAWard2());

        Ward blockAWard1Copy = new WardBuilder(getBlockAWard1()).build();
        assertEquals(getBlockAWard1(), blockAWard1Copy);

        blockAWard1Copy.deleteStaff(CHARLES);
        assertEquals(getBlockAWard1(), blockAWard1Copy);
    }
}
