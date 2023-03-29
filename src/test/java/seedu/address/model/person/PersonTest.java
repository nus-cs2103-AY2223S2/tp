package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_BEN;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_BEN;
import static seedu.address.logic.commands.CommandTestUtil.NAME_BEN;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_BEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_1;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2101_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.MA2104_RU;
import static seedu.address.model.timetable.util.TypicalLesson.CS2101_MON_8AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2101_THU_8AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2104_FRI_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2104_TUE_12PM_2HR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALBERT;
import static seedu.address.testutil.TypicalPersons.BEN;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getImmutableGroupTags().remove(0));
        assertThrows(UnsupportedOperationException.class, () -> person.getImmutableModuleTags().remove(0));
        assertThrows(UnsupportedOperationException.class, () -> person.getImmutableCommonModuleTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALBERT.isSamePerson(ALBERT));

        // null -> returns false
        assertFalse(ALBERT.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlbert = new PersonBuilder(ALBERT).withPhone(PHONE_BEN).withEmail(EMAIL_BEN)
                .withAddress(ADDRESS_BEN).withGroupTags(VALID_GROUP_1).build();
        assertTrue(ALBERT.isSamePerson(editedAlbert));

        // different name, all other attributes same -> returns false
        editedAlbert = new PersonBuilder(ALBERT).withName(NAME_BEN).build();
        assertFalse(ALBERT.isSamePerson(editedAlbert));

        // name differs in case, all other attributes same -> returns false
        Person editedBart = new PersonBuilder(BEN).withName(NAME_BEN.toLowerCase()).build();
        assertFalse(BEN.isSamePerson(editedBart));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = NAME_BEN + " ";
        editedBart = new PersonBuilder(BEN).withName(nameWithTrailingSpaces).build();
        assertFalse(BEN.isSamePerson(editedBart));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALBERT).build();
        assertTrue(ALBERT.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALBERT.equals(ALBERT));

        // null -> returns false
        assertFalse(ALBERT.equals(null));

        // different type -> returns false
        assertFalse(ALBERT.equals(5));

        // different person -> returns false
        assertFalse(ALBERT.equals(BEN));

        // different name -> returns false
        Person editedAlbert = new PersonBuilder(ALBERT).withName(NAME_BEN).build();
        assertFalse(ALBERT.equals(editedAlbert));

        // different phone -> returns false
        editedAlbert = new PersonBuilder(ALBERT).withPhone(PHONE_BEN).build();
        assertFalse(ALBERT.equals(editedAlbert));

        // different email -> returns false
        editedAlbert = new PersonBuilder(ALBERT).withEmail(EMAIL_BEN).build();
        assertFalse(ALBERT.equals(editedAlbert));

        // different address -> returns false
        editedAlbert = new PersonBuilder(ALBERT).withAddress(ADDRESS_BEN).build();
        assertFalse(ALBERT.equals(editedAlbert));

        // different tags -> returns false
        editedAlbert = new PersonBuilder(ALBERT).withGroupTags(VALID_GROUP_1).build();
        assertFalse(ALBERT.equals(editedAlbert));
    }

    @Test
    public void hashCode_validPerson_success() {
        Person albert = ALBERT;
        assertEquals(albert.hashCode(), Objects.hash(ALBERT.getName(),
                ALBERT.getPhone(), ALBERT.getEmail(), ALBERT.getAddress(),
                ALBERT.getTelegramHandle(), ALBERT.getGroupTags(),
                ALBERT.getModuleTags()));
    }

    @Test
    public void getCommitments_validModuleTags_success() {
        Person person = new PersonBuilder()
                .withModuleTags(CS2101_HA, MA2104_RU)
                .build();

        assertTrue(person.getCommitments().contains(CS2101_MON_8AM_2HR));
        assertTrue(person.getCommitments().contains(CS2101_THU_8AM_2HR));
        assertTrue(person.getCommitments().contains(MA2104_TUE_12PM_2HR));
        assertTrue(person.getCommitments().contains(MA2104_FRI_12PM_2HR));
    }
}
