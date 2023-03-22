package seedu.modtrek.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ValidTagTest {

    @Test
    public void getShortForm_invalidTagName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            ValidTag.getShortForm("test");
        });
    }

    @Test
    public void getShortForm_validTagName_returnsTrue() {
        assertEquals(ValidTag.CSF, ValidTag.getShortForm("Computer Science Foundation"));
        assertEquals(ValidTag.UE, ValidTag.getShortForm("Unrestricted Electives"));
        assertEquals(ValidTag.ULR, ValidTag.getShortForm("University Level Requirements"));
        assertEquals(ValidTag.CSBD, ValidTag.getShortForm("Computer Science Breadth and Depth"));
        assertEquals(ValidTag.ITP, ValidTag.getShortForm("IT Professionalism"));
        assertEquals(ValidTag.MS, ValidTag.getShortForm("Mathematics and Sciences"));
    }

    @Test
    public void getShortForm_alreadyShortForm_returnsTrue() {
        assertEquals(ValidTag.UE, ValidTag.getShortForm("ue"));
    }

    @Test
    public void getTotalCredit_validTag_returnsTrue() {
        assertEquals(40, ValidTag.getTotalCredit("Unrestricted Electives"));
    }

}
