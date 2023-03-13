package taa.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static taa.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import taa.model.student.Student;
import taa.model.student.exceptions.DuplicateStudentException;
import taa.testutil.Assert;
import taa.testutil.PersonBuilder;
import taa.testutil.TypicalPersons;

public class ClassListTest {

    private final ClassList classList = new ClassList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), classList.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> classList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        ClassList newData = TypicalPersons.getTypicalAddressBook();
        classList.resetData(newData);
        assertEquals(newData, classList);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two students with the same identity fields
        Student editedAlice = new PersonBuilder(TypicalPersons.ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Student> newStudents = Arrays.asList(TypicalPersons.ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newStudents);

        Assert.assertThrows(DuplicateStudentException.class, () -> classList.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> classList.hasStudent(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(classList.hasStudent(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        classList.addStudent(TypicalPersons.ALICE);
        assertTrue(classList.hasStudent(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        classList.addStudent(TypicalPersons.ALICE);
        Student editedAlice = new PersonBuilder(TypicalPersons.ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(classList.hasStudent(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> classList.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose students list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Student> students = FXCollections.observableArrayList();

        AddressBookStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }
    }

}
