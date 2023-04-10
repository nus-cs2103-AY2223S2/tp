package seedu.modtrek.model.module;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

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

    /**
     * Returns Set of All possible ValidTags as a Tags object
     * @return Set of ValidTags as a Tags object
     */
    public static Set<Tags> getAllShortFormTags() {
        Set<Tags> result = new HashSet<>();
        Stream.of(ValidTag.values()).forEach(x -> result.add(new Tags(Set.of(new Tag(x.name())))));
        result.add(new Tags(new HashSet<>()));
        return result;
    }

    @Override
    public int compareTo(Tags o) {
        if (o.tagStrings.isEmpty()) {
            if (this.tagStrings.isEmpty()) {
                return 0;
            }
            return -1;
        }
        if (this.tagStrings.isEmpty()) {
            return 1;
        }
        return this.toString().compareTo(o.toString());
    }

    @Override
    public String toString() {
        if (tagStrings.isEmpty()) {
            return "NO TAGS";
        }
        return tagStrings.toString().replace("[", "").replace("]", "");
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Tags)) {
            return false;
        }
        return this.tagStrings.equals(((Tags) o).tagStrings);
    }

    /**
     * Check whether another Tags object has any overlapping tag or both this and t have no tags
     * @param t another Tags object
     * @return boolean which is true when t has any overlapping tag or both this and t have no tags
     */
    public boolean contains(Tags t) {
        return (this.tagStrings.isEmpty() && t.tagStrings.isEmpty())
                || !Collections.disjoint(t.tagStrings, this.tagStrings);
    }
}
