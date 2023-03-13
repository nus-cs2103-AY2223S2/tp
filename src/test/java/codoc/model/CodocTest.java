package codoc.model;

import static codoc.testutil.Assert.assertThrows;
import static codoc.testutil.TypicalPersons.ALICE;
import static codoc.testutil.TypicalPersons.getTypicalCodoc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import codoc.logic.commands.CommandTestUtil;
import codoc.model.person.Person;
import codoc.model.person.exceptions.DuplicatePersonException;
import codoc.testutil.PersonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CodocTest {

    private final Codoc codoc = new Codoc();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), codoc.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> codoc.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyCodoc_replacesData() {
        Codoc newData = getTypicalCodoc();
        codoc.resetData(newData);
        assertEquals(newData, codoc);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice =
                new PersonBuilder(ALICE).withAddress(CommandTestUtil.VALID_ADDRESS_BOB).withSkills(
                        CommandTestUtil.VALID_SKILL_JAVA)
                        .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        CodocStub newData = new CodocStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> codoc.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> codoc.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInCodoc_returnsFalse() {
        assertFalse(codoc.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInCodoc_returnsTrue() {
        codoc.addPerson(ALICE);
        assertTrue(codoc.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInCodoc_returnsTrue() {
        codoc.addPerson(ALICE);
        Person editedAlice =
                new PersonBuilder(ALICE).withAddress(CommandTestUtil.VALID_ADDRESS_BOB).withSkills(
                        CommandTestUtil.VALID_SKILL_JAVA)
                        .build();
        assertTrue(codoc.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> codoc.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyCodoc whose persons list can violate interface constraints.
     */
    private static class CodocStub implements ReadOnlyCodoc {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        CodocStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
