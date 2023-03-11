package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.model.group.exceptions.DuplicateGroupException;
import seedu.address.model.group.exceptions.GroupNotFoundException;
import seedu.address.model.person.Person;

public class UniqueGroupListTest {

    @Test
    public void addGroupToList() {
        Group group1 = new Group("2103T");
        UniqueGroupList ugl = new UniqueGroupList();
        ugl.add(group1);
        assertTrue(ugl.contains(group1));
        Assertions.assertThrows(NullPointerException.class, () -> {
            ugl.add(null);
        });
        Assertions.assertThrows(DuplicateGroupException.class, () -> {
            ugl.add(group1);
        });
    }

    @Test
    public void removeGroupFromList() {
        Group group1 = new Group("2103T");
        UniqueGroupList ugl = new UniqueGroupList();
        ugl.add(group1);
        ugl.delete(group1, new HashSet<Person>());
        assertFalse(ugl.contains(group1));
        Assertions.assertThrows(NullPointerException.class, () -> {
            ugl.delete(null, new HashSet<Person>());
        });
        Assertions.assertThrows(GroupNotFoundException.class, () -> {
            ugl.delete(group1, new HashSet<Person>());
        });
    }

}
