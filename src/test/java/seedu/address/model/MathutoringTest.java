package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MathutoringTest {

    private final Mathutoring mathutoring = new Mathutoring();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), mathutoring.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mathutoring.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        Mathutoring newData = getTypicalAddressBook();
        mathutoring.resetData(newData);
        assertEquals(newData, mathutoring);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two students with the same identity fields
        seedu.address.model.student.Student editedAlice = new seedu.address.testutil.StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<seedu.address.model.student.Student> newStudents = Arrays.asList(ALICE, editedAlice);
        seedu.address.model.MathutoringTest.MathutoringStub newData = new seedu.address.model.MathutoringTest.MathutoringStub(newStudents);

        assertThrows(seedu.address.model.student.exceptions.DuplicateStudentException.class, () -> mathutoring.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mathutoring.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(mathutoring.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        mathutoring.addPerson(ALICE);
        assertTrue(mathutoring.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        mathutoring.addPerson(ALICE);
        seedu.address.model.student.Student editedAlice = new seedu.address.testutil.StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(mathutoring.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> mathutoring.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyMathutoring whose students list can violate interface constraints.
     */
    private static class MathutoringStub implements ReadOnlyMathutoring {
        private final ObservableList<seedu.address.model.student.Student> students = FXCollections.observableArrayList();

        MathutoringStub(Collection<seedu.address.model.student.Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<seedu.address.model.student.Student> getPersonList() {
            return students;
        }
    }

}
