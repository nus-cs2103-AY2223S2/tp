package seedu.socket.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.socket.testutil.Assert.assertThrows;
import static seedu.socket.testutil.TypicalPersons.ALICE;
import static seedu.socket.testutil.TypicalPersons.getTypicalSocket;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.socket.model.person.Person;
import seedu.socket.model.person.exceptions.DuplicatePersonException;
import seedu.socket.testutil.PersonBuilder;

public class SocketTest {

    private final Socket socket = new Socket();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), socket.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> socket.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlySocket_replacesData() {
        Socket newData = getTypicalSocket();
        socket.resetData(newData);
        assertEquals(newData, socket);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        SocketStub newData = new SocketStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> socket.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> socket.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInSocket_returnsFalse() {
        assertFalse(socket.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInSocket_returnsTrue() {
        socket.addPerson(ALICE);
        assertTrue(socket.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInSocket_returnsTrue() {
        socket.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(socket.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> socket.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlySocket whose persons list can violate interface constraints.
     */
    private static class SocketStub implements ReadOnlySocket {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        SocketStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
