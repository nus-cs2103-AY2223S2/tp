package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MedicalConditionTagTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->new MedicalConditionTag(
                null, "Requires metaformin after meals",
                true, MedicalConditionTag.Priority.HIGH));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidMedicalConditionTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new MedicalConditionTag(
                invalidMedicalConditionTagName, "Requires metaformin after meals",
                true, MedicalConditionTag.Priority.HIGH));
    }
    @Test
    public void getNotes() {
        MedicalConditionTag medicalConditionTag = new MedicalConditionTag(
                "Diabetes", "Requires metaformin after meals",
                true, MedicalConditionTag.Priority.HIGH);

        String notes = "Requires metaformin after meals";
        assertEquals(notes, medicalConditionTag.getNotes());
    }

    @Test
    public void isRequiresAttention() {
        MedicalConditionTag medicalConditionTag = new MedicalConditionTag(
                "Diabetes", "Requires metaformin after meals",
                true, MedicalConditionTag.Priority.HIGH);

        assertTrue(medicalConditionTag.isRequiresAttention());
    }

    @Test
    public void getPriority() {
        MedicalConditionTag medicalConditionTag = new MedicalConditionTag(
                "Diabetes", "Requires metaformin after meals",
                true, MedicalConditionTag.Priority.HIGH);

        assertEquals(MedicalConditionTag.Priority.HIGH, medicalConditionTag.getPriority());
    }

    @Test
    public void changeNotes() {
        MedicalConditionTag medicalConditionTag = new MedicalConditionTag(
                "Diabetes", "Requires metaformin after meals",
                true, MedicalConditionTag.Priority.HIGH);

        String newNotes = "Monthly visit to the hospital";
        medicalConditionTag.setNotes(newNotes);
        assertEquals(newNotes, medicalConditionTag.getNotes());
    }

    @Test
    public void changeAttentionNotRequired() {
        MedicalConditionTag medicalConditionTag = new MedicalConditionTag(
                "Diabetes", "Requires metaformin after meals",
                true, MedicalConditionTag.Priority.HIGH);

        medicalConditionTag.setAttentionRequired();
        assertTrue(medicalConditionTag.isRequiresAttention());
    }

    @Test
    public void changeAttentionRequired() {
        MedicalConditionTag medicalConditionTag = new MedicalConditionTag(
                "Diabetes", "Requires metaformin after meals",
                false, MedicalConditionTag.Priority.HIGH);

        medicalConditionTag.setAttentionNotRequired();
        assertFalse(medicalConditionTag.isRequiresAttention());
    }

    @Test
    public void changePriority() {
        MedicalConditionTag medicalConditionTag = new MedicalConditionTag(
                "Diabetes", "Requires metaformin after meals",
                true, MedicalConditionTag.Priority.HIGH);

        medicalConditionTag.setPriority(MedicalConditionTag.Priority.LOW);
        assertEquals(MedicalConditionTag.Priority.LOW, medicalConditionTag.getPriority());
    }

    @Test
    public void getNotes_noNotes_noNotesMessage() {
        MedicalConditionTag medicalConditionTag = new MedicalConditionTag(
                "Diabetes", null, true, MedicalConditionTag.Priority.HIGH);
        String noNotesMessage = "No notes added.";
        assertEquals(noNotesMessage, medicalConditionTag.getNotes());
    }

    @Test
    public void testFullStringConversion() {
        MedicalConditionTag medicalConditionTag = new MedicalConditionTag(
                "Diabetes", "Requires metaformin after meals",
                true, MedicalConditionTag.Priority.HIGH);

        String fullString = "[Diabetes] true HIGH Requires metaformin after meals";
        assertEquals(fullString, medicalConditionTag.toFullString());
    }
}
