package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_2;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.GroupTag;

public class GroupTagSetTest {
    private static final GroupTag GROUP_TAG_1 = new GroupTag(VALID_GROUP_1);
    private static final GroupTag GROUP_TAG_2 = new GroupTag(VALID_GROUP_2);

    @Test
    public void add() {
        GroupTagSet groupTagSet = new GroupTagSet();
        assertEquals(0, groupTagSet.size());

        groupTagSet.add(GROUP_TAG_1);
        assertEquals(1, groupTagSet.size());
    }

    @Test
    public void remove() {
        GroupTagSet groupTagSet = new GroupTagSet();
        groupTagSet.add(GROUP_TAG_1);
        assertEquals(1, groupTagSet.size());

        groupTagSet.remove(GROUP_TAG_1);
        assertEquals(0, groupTagSet.size());
    }

    @Test
    public void toString_validGroupTagSet_success() {
        GroupTagSet groupTagSet = new GroupTagSet();
        groupTagSet.add(GROUP_TAG_1);
        groupTagSet.add(GROUP_TAG_2);

        assertEquals(groupTagSet.toString(),
                String.format("%s | %s", VALID_GROUP_1, VALID_GROUP_2));
    }

    @Test
    public void getImmutableGroups_tryEdit_throwsUnsupportedOperationException() {
        GroupTagSet groupTagSet = new GroupTagSet();
        Set<GroupTag> immutableSet = groupTagSet.getImmutableGroups();

        assertThrows(UnsupportedOperationException.class, () -> immutableSet.add(GROUP_TAG_1));
    }

    @Test
    public void compareTo() {
        GroupTagSet groupTagSet = new GroupTagSet();
        groupTagSet.add(GROUP_TAG_1);

        GroupTagSet biggerGroupTagSet = new GroupTagSet();
        biggerGroupTagSet.add(GROUP_TAG_1);
        biggerGroupTagSet.add(GROUP_TAG_2);

        assertEquals(-1, groupTagSet.compareTo(biggerGroupTagSet));
        assertEquals(1, biggerGroupTagSet.compareTo(groupTagSet));
    }
}
