package seedu.address.model.person.fields.subfields;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class NusModTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NusMod(null));
    }

    @Test
    public void constructor_invalidNusMod_throwsIllegalArgumentException() {
        String invalidMod = "selfmod123";
        assertThrows(IllegalArgumentException.class, () -> new NusMod(invalidMod));
    }

    @Test
    public void isValidModule() {
        // null tag name
        assertFalse(NusMod.isModuleCodeValid(null));

        // valid module code
        assertTrue(NusMod.isModuleCodeValid("CHC5342"));

        //invalid module code
        assertFalse(NusMod.isModuleCodeValid("AWA000"));
    }

}
