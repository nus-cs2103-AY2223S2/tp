package seedu.connectus.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_CCA_ICS;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_MAJOR_COMPUTER_SCIENCE;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_MODULE_CS2101;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_REMARK_HUSBAND;
import static seedu.connectus.testutil.Assert.assertThrows;
import static seedu.connectus.testutil.TypicalPersons.ALICE;
import static seedu.connectus.testutil.TypicalPersons.getTypicalConnectUs;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.connectus.model.person.Person;
import seedu.connectus.model.person.exceptions.DuplicatePersonException;
import seedu.connectus.testutil.PersonBuilder;

public class ConnectUsTest {

    private final ConnectUs connectUs = new ConnectUs();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), connectUs.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> connectUs.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyConnectUs_replacesData() {
        ConnectUs newData = getTypicalConnectUs();
        connectUs.resetData(newData);
        assertEquals(newData, connectUs);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withRemarks(VALID_REMARK_HUSBAND)
                .withModules(VALID_MODULE_CS2101).withCcas(VALID_CCA_ICS)
                .withMajors(VALID_MAJOR_COMPUTER_SCIENCE).build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        ConnectUsStub newData = new ConnectUsStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> connectUs.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> connectUs.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInConnectUs_returnsFalse() {
        assertFalse(connectUs.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInConnectUs_returnsTrue() {
        connectUs.addPerson(ALICE);
        assertTrue(connectUs.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        connectUs.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withRemarks(VALID_REMARK_HUSBAND)
                .withModules(VALID_MODULE_CS2101).withCcas(VALID_CCA_ICS)
                .withMajors(VALID_MAJOR_COMPUTER_SCIENCE).build();
        assertTrue(connectUs.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> connectUs.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class ConnectUsStub implements ReadOnlyConnectUs {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        ConnectUsStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
