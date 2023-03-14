package tfifteenfour.clipboard.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.VALID_MODULE_CS2105;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;
import static tfifteenfour.clipboard.testutil.Assert.assertThrows;
import static tfifteenfour.clipboard.testutil.TypicalStudents.ALICE;
import static tfifteenfour.clipboard.testutil.TypicalStudents.getTypicalRoster;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.student.exceptions.DuplicateStudentException;
import tfifteenfour.clipboard.testutil.StudentBuilder;

public class RosterTest {

    private final Roster roster = new Roster();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), roster.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roster.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyRoster_replacesData() {
        Roster newData = getTypicalRoster();
        roster.resetData(newData);
        assertEquals(newData, roster);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two students with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).withStudentId(VALID_STUDENTID_BOB).withTags(VALID_MODULE_CS2105)
                .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        RosterStub newData = new RosterStub(newStudents);

        assertThrows(DuplicateStudentException.class, () -> roster.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roster.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInRoster_returnsFalse() {
        assertFalse(roster.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInRoster_returnsTrue() {
        roster.addStudent(ALICE);
        assertTrue(roster.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInRoster_returnsTrue() {
        roster.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withStudentId(VALID_STUDENTID_BOB).withTags(VALID_MODULE_CS2105)
                .build();
        assertTrue(roster.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> roster.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlyRoster whose students list can violate interface constraints.
     */
    private static class RosterStub implements ReadOnlyRoster {
        private final ObservableList<Student> students = FXCollections.observableArrayList();

        RosterStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }
    }

}
