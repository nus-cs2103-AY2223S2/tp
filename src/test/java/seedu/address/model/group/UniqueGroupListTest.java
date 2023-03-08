package seedu.address.model.group;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.testutil.Assert.assertThrows;

public class UniqueGroupListTest {

    @Test
    public void addGroupToList(){
        Group group1 = new Group("2103T");
        UniqueGroupList ugl = new UniqueGroupList();
        ugl.add(group1);
        assertTrue(ugl.contains(group1));
    }

    @Test
    public void removeGroupFromList(){
        Group group1 = new Group("2103T");
        UniqueGroupList ugl = new UniqueGroupList();
        ugl.add(group1);
        ugl.delete(group1, new HashSet<Person>());
        assertFalse(ugl.contains(group1));
    }
}
