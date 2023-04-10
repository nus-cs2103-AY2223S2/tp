package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TransactionCountTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
         assertEquals(new TransactionCount("0"), new TransactionCount(null));
    }

    @Test
    public void constructor_invalidTransactionCounts_throwsIllegalArgumentException() {
        String invalidTransactionCount = "0.1";
        assertThrows(IllegalArgumentException.class, () -> new TransactionCount(invalidTransactionCount));
    }

    @Test
    public void isValidTransactionCount() {


        // invalid TransactionCount
        assertFalse(TransactionCount.isValidTransactionCount("")); // empty string
        assertFalse(TransactionCount.isValidTransactionCount("9223372036854775811"));//exceeds long value
        assertFalse(TransactionCount.isValidTransactionCount("-1"));//negative number
        assertFalse(TransactionCount.isValidTransactionCount("0.1"));//floatingpoint
        assertFalse(TransactionCount.isValidTransactionCount("kjdcsbjk"));//alphabets



        // valid transaction counts
        assertTrue(TransactionCount.isValidTransactionCount("0"));
        assertTrue(TransactionCount.isValidTransactionCount("1"));
        assertTrue(TransactionCount.isValidTransactionCount("922337203685477580"));

    }
}