package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BACK;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.APPLE;
import static seedu.address.testutil.TypicalInternships.getTypicalInternBuddy;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.exceptions.DuplicateInternshipException;
import seedu.address.testutil.InternshipBuilder;

public class InternBuddyTest {

    private final InternBuddy internBuddy = new InternBuddy();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), internBuddy.getInternshipList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internBuddy.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyInternBuddy_replacesData() {
        InternBuddy newData = getTypicalInternBuddy();
        internBuddy.resetData(newData);
        assertEquals(newData, internBuddy);
    }

    @Test
    public void resetData_withDuplicateInternships_throwsDuplicateInternshipException() {
        // Two internships with the same identity fields
        Internship editedApple = new InternshipBuilder(APPLE).withDate(VALID_DATE_GOOGLE).withTags(VALID_TAG_BACK)
                .build();
        List<Internship> newInternships = Arrays.asList(APPLE, editedApple);
        InternBuddyStub newData = new InternBuddyStub(newInternships);

        assertThrows(DuplicateInternshipException.class, () -> internBuddy.resetData(newData));
    }

    @Test
    public void hasInternship_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internBuddy.hasInternship(null));
    }

    @Test
    public void hasInternship_internshipNotInInternBuddy_returnsFalse() {
        assertFalse(internBuddy.hasInternship(APPLE));
    }

    @Test
    public void hasInternship_internshipInInternBuddy_returnsTrue() {
        internBuddy.addInternship(APPLE);
        assertTrue(internBuddy.hasInternship(APPLE));
    }

    @Test
    public void hasInternship_internshipWithSameIdentityFieldsInInternBuddy_returnsTrue() {
        internBuddy.addInternship(APPLE);
        Internship editedApple = new InternshipBuilder(APPLE).withDate(VALID_DATE_GOOGLE).withTags(VALID_TAG_BACK)
                .build();
        assertTrue(internBuddy.hasInternship(editedApple));
    }

    @Test
    public void getInternshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> internBuddy.getInternshipList().remove(0));
    }

    /**
     * A stub ReadOnlyInternBuddy whose internships list can violate interface constraints.
     */
    private static class InternBuddyStub implements ReadOnlyInternBuddy {
        private final ObservableList<Internship> internships = FXCollections.observableArrayList();

        InternBuddyStub(Collection<Internship> internships) {
            this.internships.setAll(internships);
        }

        @Override
        public ObservableList<Internship> getInternshipList() {
            return internships;
        }
    }

}
