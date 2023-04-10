package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.util.SampleDataUtil.getMedicalTagSet;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.MedicalQualificationTag;

class SampleDataUtilTest {

    @Test
    public void getMedicalTagTest() {
        try {
            MedicalQualificationTag tag1 = ParserUtil.parseMedicalTag("CPR, ADVANCED");
            MedicalQualificationTag tag2 = ParserUtil.parseMedicalTag("CPR, ADVANCED");
            MedicalQualificationTag tag3 = ParserUtil.parseMedicalTag("CPR, ADVANCED");
        } catch (ParseException ex) {
            System.out.println(ex);
        }
    }
    @Test
    public void getMedicalTagSetTest() {
        Set<MedicalQualificationTag> medicalTags =
                getMedicalTagSet("CPR,BASIC", "AED,ADVANCED", "BLS,INTERMEDIATE");
        Set<MedicalQualificationTag> standardSet = new HashSet<>();
        MedicalQualificationTag tag1 = new MedicalQualificationTag("CPR", "BASIC");
        MedicalQualificationTag tag2 = new MedicalQualificationTag("AED", "ADVANCED");
        MedicalQualificationTag tag3 = new MedicalQualificationTag("BLS", "INTERMEDIATE");
        standardSet.add(tag1);
        standardSet.add(tag2);
        standardSet.add(tag3);
        assertEquals(standardSet, medicalTags);
    }


}
