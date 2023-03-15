package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GroupTest {
    private final Group group1 = new Group("2103T");
    private final Group group2 = new Group("2101");
    private final Group group1Duplicate = new Group("2103T");

    @Test
    public void equals() {
        assertFalse(group1.equals(group2));
        assertTrue(group1.equals(group1Duplicate));

    }

    @Test
    public void validName() {
        assertTrue(Group.isValidGroupName("2103T"));
        assertFalse(Group.isValidGroupName("2103T*"));
    }

    @Test
    public void isSameGroup() {
        assertTrue(group1.isSameGroup(group1));
        assertTrue(group1.isSameGroup(group1Duplicate));
        assertFalse(group1.isSameGroup(group2));
    }

    @Test
    public void output() {
        Group group1 = new Group("2103T");
        assertTrue(group1.toString().equals("[2103T]"));
        assertFalse(group1.toString().equals("[2101]"));
    }
}
