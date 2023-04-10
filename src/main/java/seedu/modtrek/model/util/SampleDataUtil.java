package seedu.modtrek.model.util;

import java.util.HashSet;
import java.util.Set;

import seedu.modtrek.model.DegreeProgression;
import seedu.modtrek.model.ReadOnlyDegreeProgression;
import seedu.modtrek.model.tag.Tag;

/**
 * Contains utility methods for populating {@code DegreeProgression} with sample data.
 */
public class SampleDataUtil {
    public static Module[] getSampleModules() {
        return null;
    }

    public static ReadOnlyDegreeProgression getSampleDegreeProgression() {
        return new DegreeProgression();
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        Set<Tag> tagSet = new HashSet<>();
        for (String s : strings) {
            tagSet.add(new Tag(s));
        }
        return tagSet;
    }

}
