package codoc.model.module;

import static codoc.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
public class ModuleTest {
    @Test public void constructor_invalidModuleName_throwsIllegalArgumentException() {
        String invalidModuleName = "ay1111s1 cs1231";
        assertThrows(IllegalArgumentException.class, () -> new Module(invalidModuleName));
    }
    @Test public void test_isValidModuleYear_return_false() {
        String invalidModuleName = "ay1111s1 cs1231";
        assertFalse(Module.isValidModuleYear(invalidModuleName));
    }
}
