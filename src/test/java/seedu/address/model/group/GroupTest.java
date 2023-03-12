package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GroupTest {

    @Test
    public void equals() {
        Group group1 = new Group("2103T");
        Group group2 = new Group("2101");
        Group group1Duplicate = new Group("2103T");
        assertFalse(group1.equals(group2));
        assertTrue(group1.equals(group1Duplicate));

    }

    @Test
    public void validName() {
        assertTrue(Group.isValidGroupName("2103T"));
        assertFalse(Group.isValidGroupName("2103T*"));
    }

    @Test
    public void output() {
        Group group1 = new Group("2103T");
        assertTrue(group1.toString().equals("[2103T]"));
        assertFalse(group1.toString().equals("[2101]"));
    }

    // TODO: More tests
}
