package seedu.internship.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.testutil.Assert.assertThrows;
import static seedu.internship.testutil.TypicalInternships.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.internship.model.internship.Internship;
import seedu.internship.model.internship.exceptions.DuplicateInternshipException;
import seedu.internship.testutil.InternshipBuilder;

public class InternshipCatalogueTest {

    private final InternshipCatalogue internshipCatalogue = new InternshipCatalogue();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), internshipCatalogue.getInternshipList());
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
        Internship editedML1 = new InternshipBuilder().withPosition(VALID_POSITION_ML1)
                .withCompany(VALID_COMPANY_ML1).withId(VALID_ID_ML1).withDescription(VALID_DESCRIPTION_ML1)
                .withStatus(VALID_STATUS_ML1).withTags(VALID_TAG_IMPORTANT).build();

        List<Internship> newInternships = Arrays.asList(ML1, editedML1);
        InternshipCatalogueStub newData = new InternshipCatalogueStub(newInternships);

        assertThrows(DuplicateInternshipException.class, () -> internshipCatalogue.resetData(newData));
    }

    @Test
    public void hasinternship_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internshipCatalogue.hasInternship(null));
    }

    @Test
    public void hasInternship_internshipNotInInternshipCatalogue_returnsFalse() {
        assertFalse(internshipCatalogue.hasInternship(ML2));
    }

    @Test
    public void hasInternship_internshipInInternshipCatalogue_returnsTrue() {
        internshipCatalogue.addInternship(ML2);
        assertTrue(internshipCatalogue.hasInternship(ML2));
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
        public ObservableList<Internship> getInternshipList() {
            return internships;
        }
    }

}
