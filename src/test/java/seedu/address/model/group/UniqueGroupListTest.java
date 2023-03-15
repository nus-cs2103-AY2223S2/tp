package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.group.exceptions.DuplicateGroupException;
import seedu.address.model.group.exceptions.GroupNotFoundException;
import seedu.address.model.person.Person;

public class UniqueGroupListTest {
    private final UniqueGroupList emptyGroupList = new UniqueGroupList();
    private final Group group = new Group("2103T");
    private final Group groupWithSameName = new Group("2103T");
    private final Group anotherGroup = new Group("2101");

    @Test
    public void addGroupToList() {
        UniqueGroupList ugl = new UniqueGroupList();
        assertFalse(ugl.contains(group));
        ugl.add(group);
        assertTrue(ugl.contains(group));
        assertTrue(ugl.contains(groupWithSameName));
        assertThrows(NullPointerException.class, () -> ugl.add(null));
        assertThrows(DuplicateGroupException.class, () -> ugl.add(group));
    }

    @Test
    public void removeGroupFromList() {
        UniqueGroupList ugl = new UniqueGroupList();
        ugl.add(group);
        ugl.delete(group, new HashSet<Person>());
        assertFalse(ugl.contains(group));
        assertThrows(NullPointerException.class, () -> {
            ugl.delete(null, new HashSet<Person>());
        });
        assertThrows(GroupNotFoundException.class, () -> {
            ugl.delete(group, new HashSet<Person>());
        });
    }

    @Test
    public void add_duplicateGroup_throwsDuplicateGroupException() {
        UniqueGroupList ugl = new UniqueGroupList();
        ugl.add(group);
        assertThrows(DuplicateGroupException.class, () -> ugl.add(groupWithSameName));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        UniqueGroupList startUniqueGroupList = new UniqueGroupList();
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        expectedUniqueGroupList.add(group);
        startUniqueGroupList.setGroups(expectedUniqueGroupList);
        assertEquals(expectedUniqueGroupList, startUniqueGroupList);
    }

    @Test
    public void setGroups_list_replacesOwnListWithProvidedList() {
        UniqueGroupList startUniqueGroupList = new UniqueGroupList();
        startUniqueGroupList.add(group);
        List<Group> groupList = Collections.singletonList(anotherGroup);
        startUniqueGroupList.setGroups(groupList);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        expectedUniqueGroupList.add(anotherGroup);
        assertEquals(expectedUniqueGroupList, startUniqueGroupList);
    }

    @Test
    public void setGroups_listWithDuplicateGroups_throwsDuplicateGroupException() {
        List<Group> listWithDuplicateGroups = Arrays.asList(group, groupWithSameName);
        assertThrows(DuplicateGroupException.class, () -> emptyGroupList.setGroups(listWithDuplicateGroups));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        UniqueGroupList ugl = new UniqueGroupList();
        assertThrows(UnsupportedOperationException.class, ()
                -> ugl.asUnmodifiableObservableList().remove(0));
    }
}
