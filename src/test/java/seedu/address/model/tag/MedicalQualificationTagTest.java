package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MedicalQualificationTagTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MedicalQualificationTag(null, null));
    }

    @Test
    public void constructor_invalidQualification_throwsIllegalArgumentException() {
        String invalidMedicalQualification1 = "";
        String invalidMedicalQualification2 = "random";
        assertThrows(IllegalArgumentException.class, () -> new MedicalQualificationTag("CPR", invalidMedicalQualification1));
        assertThrows(IllegalArgumentException.class, () -> new MedicalQualificationTag("AED", invalidMedicalQualification2));
    }

    @Test
    public void isValidQualification() {
        // invalid
        assertFalse(MedicalQualificationTag.isValidQualification(""));
        assertFalse(MedicalQualificationTag.isValidQualification("something"));

        // valid
        assertTrue(MedicalQualificationTag.isValidQualification("BASIC"));
        assertTrue(MedicalQualificationTag.isValidQualification("adVanCed"));
    }


    @Test
    public void changeQualificationLevel() {
        MedicalQualificationTag medicalQualificationTag = new MedicalQualificationTag("CPR", "ADVANCED");

        medicalQualificationTag.setQualificationLevel("BASIC");
        assertEquals("BASIC",
                medicalQualificationTag.getQualificationLevel());
    }


    @Test
    public void testFullStringConversion() {
        MedicalQualificationTag medicalQualificationTag = new MedicalQualificationTag("AED", "ADVANCED");

        String fullString = "AED ADVANCED";
        assertEquals(fullString, medicalQualificationTag.toFullString());
    }
}
