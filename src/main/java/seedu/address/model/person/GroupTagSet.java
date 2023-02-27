package seedu.address.model.person;

import java.util.HashSet;
import java.util.stream.Collectors;

import seedu.address.model.tag.GroupTag;

/**
 * This class was added to facilitate the Sort Command.
 * We implement the comparator here so that the Sort Command is cleaner.
 */
public class GroupTagSet extends HashSet<GroupTag> implements Comparable<GroupTagSet> {
    private static final int DISPLAY_LIMIT = 10;

    @Override
    public String toString() {
        String groupsString = stream().sorted().map(GroupTag::toString)
                .limit(DISPLAY_LIMIT).collect(Collectors.joining(" | "));

        // whether the size exceeds the display limit
        String ellipsis = size() > DISPLAY_LIMIT ? "..." : "";

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
        return Integer.compare(size(), otherGroupTagSet.size());
    }
}
