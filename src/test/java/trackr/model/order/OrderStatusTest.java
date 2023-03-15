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
    public void isValidTaskStatus() {
        // null task status
        assertThrows(NullPointerException.class, () -> OrderStatus.isValidOrderStatus(null));

        // invalid task status
        assertFalse(OrderStatus.isValidOrderStatus("")); // empty string
        assertFalse(OrderStatus.isValidOrderStatus(" ")); // spaces only
        assertFalse(OrderStatus.isValidOrderStatus("^")); // only non-alphanumeric characters
        assertFalse(OrderStatus.isValidOrderStatus("N*")); // contains non-alphanumeric characters
        assertFalse(OrderStatus.isValidOrderStatus("M")); // invalid status
        assertFalse(OrderStatus.isValidOrderStatus("DN")); // both D and N
        assertFalse(OrderStatus.isValidOrderStatus("N N")); // more than one character
        assertFalse(OrderStatus.isValidOrderStatus("12345")); // numbers only
        assertFalse(OrderStatus.isValidOrderStatus("D ")); // white spaces at the back

        // valid task status
        assertTrue(OrderStatus.isValidOrderStatus("N")); // not done
        assertTrue(OrderStatus.isValidOrderStatus("I")); // in progress
        assertTrue(OrderStatus.isValidOrderStatus("D")); // done
        assertTrue(OrderStatus.isValidOrderStatus("n")); // small letter
        assertTrue(OrderStatus.isValidOrderStatus("i")); // small letter
        assertTrue(OrderStatus.isValidOrderStatus("d")); // small letter
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

}
