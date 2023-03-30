package seedu.modtrek.model.module;

import java.util.HashSet;
import java.util.Set;

import seedu.modtrek.model.tag.Tag;
import seedu.modtrek.model.tag.ValidTag;

/**
 * Tags class
 */
public class Tags implements Comparable<Tags> {

    private Set<String> tagStrings = new HashSet<>();
    public Tags(Set<Tag> tags) {
        tags.forEach(x -> this.tagStrings.add(ValidTag.getShortForm(x.toString()).toString()));
    }
    @Override
    public int compareTo(Tags o) {
        return this.toString().compareTo(o.toString());
    }

    @Override
    public String toString() {
        if (tagStrings.isEmpty()) {
            return "NO TAGS";
        }
        return tagStrings.toString().replace("[", "").replace("]", "");
    }
}
