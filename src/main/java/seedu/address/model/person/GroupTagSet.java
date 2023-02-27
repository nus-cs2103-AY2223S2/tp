package seedu.address.model.person;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

/**
 * This class was added to facilitate the Sort Command.
 * We implement the comparator here so that the Sort Command is cleaner.
 */
public class GroupTagSet implements Comparable<GroupTagSet> {
    /**
     * How many groups you want to display in the toString() method
     * before it comes out as ellipses.
     */
    private static final int DISPLAY_LIMIT = 10;
    private final Set<GroupTag> groups;

    public GroupTagSet() {
        groups = new HashSet<>();
    }

    /**
     * Adds the group tag to the set of modules.
     * Gives access from outside classes to this set.
     */
    public void add(GroupTag groupTag) {
        groups.add(groupTag);
    }

    /**
     * Adds all group tags to the set of groups.
     * Gives access from outside classes to this set.
     */
    public void addAll(Collection<? extends GroupTag> groupTags) {
        groups.addAll(groupTags);
    }

    /**
     * Removes the group tag from the set of groups.
     * Gives access from outside classes to this set.
     */
    public void remove(GroupTag groupTag) {
        groups.remove(groupTag);
    }

    /**
     * Returns an immutable group tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<GroupTag> getImmutableGroups() {
        return Collections.unmodifiableSet(groups);
    }

    @Override
    public String toString() {
        String groupsString = groups.stream().sorted().map(GroupTag::toString)
                .limit(DISPLAY_LIMIT).collect(Collectors.joining(" | "));

        // whether the size exceeds the display limit
        String ellipsis = groups.size() > DISPLAY_LIMIT ? "..." : "";

        return String.format("%s%s", groupsString, ellipsis);
    }

    /**
     * Compares the sizes of the sets.
     * We do this because we want the size of the set to be the basis of comparison.
     * In other words, if the person shares more relationships with the user,
     * the person is likely to be closer to the user.
     */
    @Override
    public int compareTo(GroupTagSet otherGroupTagSet) {
        return Integer.compare(groups.size(), otherGroupTagSet.groups.size());
    }
}
