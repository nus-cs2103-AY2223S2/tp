package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalEduMate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.User;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;

public class EduMateTest {

    private final EduMate eduMate = new EduMate();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), eduMate.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eduMate.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyEduMate_replacesData() {
        EduMate newData = getTypicalEduMate();
        eduMate.resetData(newData);
        assertEquals(newData, eduMate);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        User validUser = TypicalPersons.getTypicalUser();
        EduMateStub newData = new EduMateStub(newPersons, validUser);

        assertThrows(DuplicatePersonException.class, () -> eduMate.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eduMate.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInEduMate_returnsFalse() {
        assertFalse(eduMate.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInEduMate_returnsTrue() {
        eduMate.addPerson(ALICE);
        assertTrue(eduMate.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInEduMate_returnsTrue() {
        eduMate.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(eduMate.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> eduMate.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyEduMate whose persons list or user can violate interface constraints.
     */
    private static class EduMateStub implements ReadOnlyEduMate {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private User user;

        EduMateStub(Collection<Person> persons, User user) {
            this.persons.setAll(persons);
            this.user = user;
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public User getUser() {
            return user;
        }
    }

}
