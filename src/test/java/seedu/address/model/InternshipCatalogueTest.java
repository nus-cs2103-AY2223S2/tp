package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.ML2;
import static seedu.address.testutil.TypicalInternships.getTypicalInternshipCataloue;

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

public class InternshipCatalogueTest {

    private final InternshipCatalogue internshipCatalogue = new InternshipCatalogue();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), internshipCatalogue.getinternshipList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internshipCatalogue.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyInternshipCatalogue_replacesData() {
        InternshipCatalogue newData = getTypicalInternshipCatalogue();
        internshipCatalogue.resetData(newData);
        assertEquals(newData, internshipCatalogue);
    }

    @Test
    public void resetData_withDuplicateInternships_throwsDuplicateInternshipException() {
        // Two internships with the same identity fields
        //Needs to be changed when we have valid internship feilds
        internship editedML2 = new internshipBuilder(ML2).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();

        List<Internship> newInternships = Arrays.asList(ML2, editedML2);
        InternshipCatalogueStub newData = new InternshipCatalogueStub(newInternships);

        assertThrows(DuplicateInternshipException.class, () -> internshipCatalogue.resetData(newData));
    }

    @Test
    public void hasinternship_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internshipCatalogue.hasInternship(null));
    }

    @Test
    public void hasInternship_internshipNotInInternshipCatalogue_returnsFalse() {
        assertFalse(internshipCatalogue.hasinternship(ML2));
    }

    @Test
    public void hasInternship_internshipInInternshipCatalogue_returnsTrue() {
        internshipCatalogue.addInternship(ML2);
        assertTrue(internshipCatalogue.hasInternship(ML2));
    }

    @Test
    public void hasInternship_internshipWithSameIdentityFieldsInInternshipCatalogue_returnsTrue() {
        internshipCatalogue.addInternship(ML2);
        // Needs to be edited when we have Valid Internship Feilds
        internship editedML2 = new internshipBuilder(ML2).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(internshipCatalogue.hasInternship(editedML2));
    }

    @Test
    public void getInternshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> internshipCatalogue.getInternshipList().remove(0));
    }

    /**
     * A stub ReadOnlyInternshipCatalogue whose internships list can violate interface constraints.
     */
    private static class InternshipCatalogueStub implements ReadOnlyInternshipCatalogue {
        private final ObservableList<Internship> internships = FXCollections.observableArrayList();

        InternshipCatalogueStub(Collection<Internship> internships) {
            this.internships.setAll(internships);
        }

        @Override
        public ObservableList<Internship> getinternshipList() {
            return internships;
        }
    }

}
