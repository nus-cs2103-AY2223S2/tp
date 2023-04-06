package seedu.address.model.person.fields;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


class ModulesTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Modules(null));
    }
}
