package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        assertThrows(IllegalArgumentException.class, () ->
                new MedicalQualificationTag("CPR", invalidMedicalQualification1));
        assertThrows(IllegalArgumentException.class, () ->
                new MedicalQualificationTag("AED", invalidMedicalQualification2));
    }

    @Test
    public void isValidQualification() {
        // invalid
        assertFalse(MedicalQualificationTag.isValidQualification(""));
        assertFalse(MedicalQualificationTag.isValidQualification("something"));

        // valid
        assertTrue(MedicalQualificationTag.isValidQualification("BASIC"));
        assertTrue(MedicalQualificationTag.isValidQualification("basic"));
        assertTrue(MedicalQualificationTag.isValidQualification("INTERmediate"));
        assertTrue(MedicalQualificationTag.isValidQualification("adVanCed"));
    }


    @Test
    public void changeQualificationLevel() {
        MedicalQualificationTag medicalQualificationTag1 = new MedicalQualificationTag("CPR", "ADVANCED");
        MedicalQualificationTag medicalQualificationTag2 = new MedicalQualificationTag("AED", "basic");
        MedicalQualificationTag medicalQualificationTag3 = new MedicalQualificationTag("BLS", "interMEDIATE");

        medicalQualificationTag1.setQualificationLevel("BASIC");
        assertEquals("BASIC",
                medicalQualificationTag1.getQualificationLevel());

        medicalQualificationTag2.setQualificationLevel("intermediate");
        assertEquals("INTERMEDIATE",
                medicalQualificationTag2.getQualificationLevel());

        medicalQualificationTag1.setQualificationLevel("adVANCED");
        assertEquals("ADVANCED",
                medicalQualificationTag1.getQualificationLevel());
    }


    @Test
    public void testFullStringConversion() {
        MedicalQualificationTag medicalQualificationTag1 = new MedicalQualificationTag("AED", "ADVANCED");
        MedicalQualificationTag medicalQualificationTag2 = new MedicalQualificationTag("CPR", "basic");
        MedicalQualificationTag medicalQualificationTag3 = new MedicalQualificationTag("BLS", "interMEDIATE");
        String fullString1 = "[AED] ADVANCED";
        String fullString2 = "[CPR] BASIC";
        String fullString3 = "[BLS] INTERMEDIATE";

        assertEquals(fullString1, medicalQualificationTag1.toFullString());
        assertEquals(fullString2, medicalQualificationTag2.toFullString());
        assertEquals(fullString3, medicalQualificationTag3.toFullString());
    }
}
