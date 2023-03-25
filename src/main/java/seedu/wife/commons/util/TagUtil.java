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
        // Collects all unqiue tags in the filtered food list
        Set<Tag> filteredFoodtags = foodlist
            .stream()
            .map(food -> food.getTags())
            .reduce((tagX, tagY) -> {
                HashSet<Tag> result = new HashSet<Tag>();
                result.addAll(tagX);
                result.addAll(tagY);
                return result;
            }).orElse(new HashSet<Tag>());

        // Constructs string representation of the match status
        String messageFoodWithTag = matchMessage;
        String messageNoFoodWithTag = noMatchMessage;
        for (Tag tag : conditionTags) {
            if (filteredFoodtags.contains(tag)) {
                messageFoodWithTag += "\n" + tag;
            } else if (!filteredFoodtags.contains(tag)) {
                messageNoFoodWithTag += "\n" + tag;
            }
        }

        String result = "";
        if (!messageFoodWithTag.equals(matchMessage)) {
            result += messageFoodWithTag + "\n\n";
        }

        if (!messageNoFoodWithTag.equals(noMatchMessage)) {
            result += messageNoFoodWithTag;
        }

        return result;
    }
}
