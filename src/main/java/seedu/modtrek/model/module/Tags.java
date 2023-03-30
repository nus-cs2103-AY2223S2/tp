package seedu.modtrek.model.module;

import seedu.modtrek.model.tag.Tag;
import seedu.modtrek.model.tag.ValidTag;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Tags implements Comparable<Tags> {

    public Set<String> tagStrings = new HashSet<>();
    public Tags(Set<Tag> tags) {
        tags.forEach(x -> this.tagStrings.add(ValidTag.getShortForm(x.toString()).toString()));
    }
    @Override
    public int compareTo(Tags o) {
        if (Collections.disjoint(o.tagStrings, this.tagStrings)) {
            return 0;
        }
        return 1;
    }
    @Override
    public String toString() {
        if (tagStrings.isEmpty()) {
            return "No tags";
        }
        return tagStrings.toString().replace("[", "").replace("]", "");
    }
}
