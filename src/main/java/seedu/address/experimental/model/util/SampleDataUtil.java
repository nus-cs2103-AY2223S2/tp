package seedu.address.experimental.model.util;

import java.util.HashSet;

import seedu.address.experimental.model.ReadOnlyReroll;
import seedu.address.experimental.model.Reroll;
import seedu.address.model.entity.*;
import seedu.address.model.entity.Character;
import seedu.address.model.tag.Tag;

/***/
public class SampleDataUtil {
    /***/
    public static Entity[] getSampleEntities() {
        Character c = new Character(new Name("Item1"), new Stats(0, 0, 0), 3, 2, new HashSet<Tag>());
        Mob m = new Mob(new Name("Mob1"), new Stats(300, 300, 300), 2, true, new HashSet<Tag>());
        Mob m2 = new Mob(new Name("Mob2"), new Stats(300, 300, 300), 2, true, new HashSet<Tag>());
        Entity[] entities = {c, m, m2};
        return entities;
    }
    /***/
    public static ReadOnlyReroll getSampleReroll() {
        Reroll sampleRr = new Reroll();
        for (Entity sampleEntity : getSampleEntities()) {
            sampleRr.addEntity(sampleEntity);
        }
        return sampleRr;
    }
}
