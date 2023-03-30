package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_BEN;
import static seedu.address.logic.commands.CommandTestUtil.NAME_BEN;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_BEN;
import static seedu.address.logic.commands.CommandTestUtil.STATION_BEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_1;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2030S_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2040S_HA;
import static seedu.address.model.timetable.util.TypicalLesson.CS2030S_MON_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2030S_THU_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2030S_THU_1PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2040S_FRI_10AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2040S_MON_4PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2040S_TUE_9AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2040S_WED_2PM_1HR;
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
                .withStation(STATION_BEN).withGroupTags(VALID_GROUP_1).build();
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

        // different station -> returns false
        editedAlbert = new PersonBuilder(ALBERT).withStation(STATION_BEN).build();
        assertFalse(ALBERT.equals(editedAlbert));

        // different tags -> returns false
        editedAlbert = new PersonBuilder(ALBERT).withGroupTags(VALID_GROUP_1).build();
        assertFalse(ALBERT.equals(editedAlbert));
    }

    @Test
    public void hashCode_validPerson_success() {
        Person albert = ALBERT;
        assertEquals(albert.hashCode(), Objects.hash(ALBERT.getName(),
                ALBERT.getPhone(), ALBERT.getEmail(), ALBERT.getStation(),
                ALBERT.getTelegramHandle(), ALBERT.getGroupTags(),
                ALBERT.getModuleTags()));
    }

    @Test
    public void getCommitments_validModuleTags_success() {
        Person person = new PersonBuilder()
                .withModuleTags(CS2030S_HA, CS2040S_HA)
                .build();

        assertTrue(person.getCommitments().contains(CS2030S_MON_12PM_2HR));
        assertTrue(person.getCommitments().contains(CS2030S_THU_10AM_2HR));
        assertTrue(person.getCommitments().contains(CS2030S_THU_1PM_1HR));
        assertTrue(person.getCommitments().contains(CS2040S_MON_4PM_2HR));
        assertTrue(person.getCommitments().contains(CS2040S_TUE_9AM_2HR));
        assertTrue(person.getCommitments().contains(CS2040S_WED_2PM_1HR));
        assertTrue(person.getCommitments().contains(CS2040S_FRI_10AM_1HR));
    }
}
