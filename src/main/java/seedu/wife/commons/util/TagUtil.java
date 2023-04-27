package seedu.wife.commons.util;

import java.util.HashSet;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.wife.model.food.Food;
import seedu.wife.model.tag.Tag;

/**
 * Helper functions for handling tags.
 */
public class TagUtil {

    /**
     * Returns the match status of condition tags.
     * @param foodlist
     * @param conditionTags
     * @param matchMessage
     * @param noMatchMessage
     * @return a string representation of the condition tag's match status
     */
    public static String getMatchStatus(
        ObservableList<Food> foodlist,
        Set<Tag> conditionTags,
        String matchMessage,
        String noMatchMessage
    ) {
        // Collects all unique tags in the filtered food list
        Set<Tag> filteredFoodTags = foodlist
            .stream()
            .map(food -> food.getTags())
            .reduce((tagX, tagY) -> {
                HashSet<Tag> result = new HashSet<Tag>();
                result.addAll(tagX);
                result.addAll(tagY);
                return result;
            }).orElse(new HashSet<Tag>());

        // Constructs string representation of the match status
        HashSet<Tag> matchTag = new HashSet<Tag>();
        HashSet<Tag> noMatchTag = new HashSet<Tag>();
        for (Tag tag : conditionTags) {
            if (filteredFoodTags.contains(tag)) {
                matchTag.add(tag);
            } else if (!filteredFoodTags.contains(tag)) {
                noMatchTag.add(tag);
            }
        }

        String result = "";
        if (matchTag.size() > 0) {
            result += matchMessage + "\n" + tagsToString(matchTag) + "\n";
        }

        if (noMatchTag.size() > 0) {
            result += noMatchMessage + "\n" + tagsToString(noMatchTag);
        }

        return result;
    }

    /**
     * Returns a string representation for a set of tags.
     * @param tags
     * @return a string representation for a set of tags.
     */
    public static String tagsToString(Set<Tag> tags) {
        String setString = "";

        for (Tag tag : tags) {
            setString += tag + "\n";
        }

        return setString;
    }
}
