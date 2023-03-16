package seedu.address.experimental.model.util;

import java.util.HashSet;

import seedu.address.experimental.model.ReadOnlyReroll;
import seedu.address.experimental.model.Reroll;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Mob;
import seedu.address.model.entity.Name;
import seedu.address.model.entity.Stats;
import seedu.address.model.tag.Tag;
/***/
public class SampleDataUtil {

    private static Character[] sampleCharacter;
    private static Mob[] sampleMob;
    private static Item[] sampleItem;

    /***/
    public static void getSampleEntities() {
        Character c = new Character(new Name("Item1"), new Stats(0, 0, 0), 3, 2, new HashSet<Tag>());
        Mob m1 = new Mob(new Name("Mob1"), new Stats(300, 300, 300), 2, true, new HashSet<Tag>());
        Mob m2 = new Mob(new Name("Mob2"), new Stats(300, 300, 300), 2, true, new HashSet<Tag>());
        sampleCharacter = new Character[] {c};
        sampleMob = new Mob[] {m1, m2};
        sampleItem = new Item[] {};
    }

    /***/
    public static ReadOnlyReroll getSampleReroll() {
        Reroll sampleRr = new Reroll();
        getSampleEntities();
        for (Item item : sampleItem) {
            sampleRr.addItem(item);
        }
        for (Mob mob : sampleMob) {
            sampleRr.addMob(mob);
        }
        for (Character character : sampleCharacter) {
            sampleRr.addCharacter(character);
        }
        return sampleRr;
    }
}
