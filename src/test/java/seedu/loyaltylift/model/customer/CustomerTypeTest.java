package seedu.loyaltylift.model.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.loyaltylift.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CustomerTypeTest {

    @Test
    public void fromUserFriendlyString() {
        assertEquals(CustomerType.fromUserFriendlyString("ind"), CustomerType.INDIVIDUAL);
        assertEquals(CustomerType.fromUserFriendlyString("IND"), CustomerType.INDIVIDUAL);

        assertEquals(CustomerType.fromUserFriendlyString("individual"), CustomerType.INDIVIDUAL);
        assertEquals(CustomerType.fromUserFriendlyString("INDIVIDUAL"), CustomerType.INDIVIDUAL);

        assertEquals(CustomerType.fromUserFriendlyString("ent"), CustomerType.ENTERPRISE);
        assertEquals(CustomerType.fromUserFriendlyString("ENT"), CustomerType.ENTERPRISE);

        assertEquals(CustomerType.fromUserFriendlyString("enterprise"), CustomerType.ENTERPRISE);
        assertEquals(CustomerType.fromUserFriendlyString("ENTERPRISE"), CustomerType.ENTERPRISE);

        assertThrows(IllegalArgumentException.class, () -> CustomerType.fromUserFriendlyString(""));
        assertThrows(NullPointerException.class, () -> CustomerType.fromUserFriendlyString(null));
    }
}
