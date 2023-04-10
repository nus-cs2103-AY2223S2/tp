package seedu.address.model.person.information;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.Parser.FIELD_NOT_SPECIFIED;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class RiskLevelTest {

    @Test
    public void constructor_invalidRisk_throwsIllegalArgumentException() {
        String invalidRisk = "abc";
        assertThrows(IllegalArgumentException.class, () -> new RiskLevel(invalidRisk));
    }

    @Test
    public void isValidRisk() {
        // null address
        assertFalse(RiskLevel.isValidRisk(null));

        // invalid risk
        assertFalse(RiskLevel.isValidRisk(" ")); // spaces only
        assertFalse(RiskLevel.isValidRisk("hello")); // random letters

        // valid risk
        assertTrue(RiskLevel.isValidRisk(FIELD_NOT_SPECIFIED));
        assertTrue(RiskLevel.isValidRisk("low"));
        assertTrue(RiskLevel.isValidRisk("HIGH"));
        assertTrue(RiskLevel.isValidRisk("meDIum")); // low, high or medium
    }
}
