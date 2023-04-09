package seedu.library.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.library.model.Tags;
import seedu.library.model.tag.Tag;

/**
 * A utility class containing a list of {@code Tag} objects to be used in tests.
 */
public class TypicalTags {
    public static final Tag MALE_PROTAGONIST = new Tag("MaleProtagonist");

    public static final Tag MANHWA = new Tag("Manhwa");

    public static final Tag HUNTERS = new Tag("Hunters");

    public static final Tag CHEATS = new Tag("Cheats");

    public static final Tag SYSTEM = new Tag("System");

    public static final Tag ANTIHERO = new Tag("Antihero");

    public static final Tag LITERATURE = new Tag("Literature");

    public static final Tag SCHOOL = new Tag("School");

    public static final Tag GORE = new Tag("Gore");

    public static final Tag OCEAN = new Tag("Ocean");

    public static final Tag PLANT = new Tag("Plant");

    public static Tags getTypicalTags() {
        Tags tags = new Tags();
        for (Tag tag : getTypicalTag()) {
            tags.addTag(tag);
        }
        return tags;
    }

    public static List<Tag> getTypicalTag() {
        return new ArrayList<>(Arrays.asList(MALE_PROTAGONIST, MANHWA, HUNTERS, CHEATS, SYSTEM, ANTIHERO,
                LITERATURE, SCHOOL, GORE));
    }
}
