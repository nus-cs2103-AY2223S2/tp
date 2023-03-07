package arb.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void getStatus() {
        Status s1 = new Status(); // Default
        assertFalse(s1.getStatus());

        Status s2 = new Status(true); // True
        assertTrue(s2.getStatus());
    }

    @Test
    public void setTrue() {
        Status initFalse = new Status();
        Status initTrue = new Status(true);
        initFalse.setTrue();
        initTrue.setTrue();

        assertTrue(initFalse.getStatus());
        assertTrue(initTrue.getStatus());
    }

    @Test
    public void setFalse() {
        Status initFalse = new Status();
        Status initTrue = new Status(true);
        initFalse.setFalse();
        initTrue.setFalse();

        assertFalse(initFalse.getStatus());
        assertFalse(initTrue.getStatus());
    }

    @Test
    public void equals() {
        Status falseStatus = new Status();
        Status trueStatus = new Status(true);

        assertFalse(falseStatus.equals(trueStatus)); // Check opposites
        assertTrue(falseStatus.equals(new Status(false))); // Check against new

        falseStatus.setTrue();
        assertTrue(trueStatus.equals(falseStatus)); // Changed
    }

}
