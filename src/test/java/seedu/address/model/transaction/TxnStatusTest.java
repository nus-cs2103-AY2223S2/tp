package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TxnStatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TxnStatus(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTxnStatusName = "";
        assertThrows(IllegalArgumentException.class, () -> new TxnStatus(invalidTxnStatusName));
    }

    @Test
    public void isValidTxnStatus() {
        assertFalse(() -> TxnStatus.isValidTxnStatus(""));
    }
}
