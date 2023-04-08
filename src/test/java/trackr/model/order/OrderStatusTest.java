package trackr.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class OrderStatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OrderStatus(null));
    }

    @Test
    public void constructor_blankStatus_throwsIllegalArgumentException() {
        String blankOrderStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new OrderStatus(blankOrderStatus));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        String invalidTaskStatus = "Z";
        assertThrows(IllegalArgumentException.class, () -> new OrderStatus(invalidTaskStatus));
    }

    @Test
    public void constructor_noArguments_success() {
        OrderStatus expectedStatus = new OrderStatus("N");
        assertEquals(expectedStatus, new OrderStatus());
    }

    @Test
    public void isValidOrderStatus() {
        // null order status
        assertThrows(NullPointerException.class, () -> OrderStatus.isValidStatus(null, OrderStatus.STATUSES));

        // invalid order status
        assertFalse(OrderStatus.isValidStatus("", OrderStatus.STATUSES)); // empty string
        assertFalse(OrderStatus.isValidStatus(" ", OrderStatus.STATUSES)); // spaces only
        assertFalse(OrderStatus.isValidStatus("^", OrderStatus.STATUSES)); // only non-alphanumeric characters
        assertFalse(OrderStatus.isValidStatus("N*", OrderStatus.STATUSES)); // contains non-alphanumeric characters
        assertFalse(OrderStatus.isValidStatus("M", OrderStatus.STATUSES)); // invalid status
        assertFalse(OrderStatus.isValidStatus("DN", OrderStatus.STATUSES)); // both D and N
        assertFalse(OrderStatus.isValidStatus("N N", OrderStatus.STATUSES)); // more than one character
        assertFalse(OrderStatus.isValidStatus("12345", OrderStatus.STATUSES)); // numbers only
        assertFalse(OrderStatus.isValidStatus("D ", OrderStatus.STATUSES)); // white spaces at the back

        // valid order status
        assertTrue(OrderStatus.isValidStatus("N", OrderStatus.STATUSES)); // not done
        assertTrue(OrderStatus.isValidStatus("I", OrderStatus.STATUSES)); // in progress
        assertTrue(OrderStatus.isValidStatus("D", OrderStatus.STATUSES)); // done
        assertTrue(OrderStatus.isValidStatus("n", OrderStatus.STATUSES)); // small letter
        assertTrue(OrderStatus.isValidStatus("i", OrderStatus.STATUSES)); // small letter
        assertTrue(OrderStatus.isValidStatus("d", OrderStatus.STATUSES)); // small letter
    }

    @Test
    public void toStringTest() {
        OrderStatus done = new OrderStatus("D");
        assertEquals("Delivered", done.toString());

        OrderStatus notDone = new OrderStatus("N");
        assertEquals("Not Delivered", notDone.toString());

        OrderStatus inProgress = new OrderStatus("I");
        assertEquals("In Progress", inProgress.toString());
    }

    @Test
    public void toJsonString() {
        OrderStatus done = new OrderStatus("D");
        assertEquals("D", done.toJsonString());

        OrderStatus notDone = new OrderStatus("N");
        assertEquals("N", notDone.toJsonString());

        OrderStatus inProgress = new OrderStatus("I");
        assertEquals("I", inProgress.toJsonString());
    }

    @Test
    public void equals() {
        OrderStatus done = new OrderStatus("D");
        OrderStatus notDone = new OrderStatus("N");
        OrderStatus inProgress = new OrderStatus("I");

        assertTrue(done.equals(done)); //same object
        assertTrue(done.equals(new OrderStatus("d"))); //both are done
        assertTrue(inProgress.equals(inProgress)); //same object
        assertTrue(inProgress.equals(new OrderStatus("i"))); //both are in progress

        assertFalse(done.equals(null)); //null
        assertFalse(done.equals(notDone)); //done vs not done
        assertFalse(done.equals(inProgress)); //done vs in progress
        assertFalse(done.equals("N")); //different types
    }

    @Test
    public void compare() {
        OrderStatus done = new OrderStatus("D");
        OrderStatus doneCopy = new OrderStatus("d");
        OrderStatus notDone = new OrderStatus("N");
        OrderStatus inProgress = new OrderStatus("I");

        // same value -> 0
        assertEquals(0, done.compare(done));
        assertEquals(0, done.compare(doneCopy));

        // higher priority
        assertEquals(1, done.compare(notDone));
        assertEquals(1, done.compare(inProgress));
        assertEquals(1, inProgress.compare(notDone));

        // lower priority
        assertEquals(-1, notDone.compare(done));
        assertEquals(-1, inProgress.compare(done));
        assertEquals(-1, notDone.compare(inProgress));
    }

}
