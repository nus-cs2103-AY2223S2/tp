package seedu.address.model.util;

import java.util.HashSet;
import java.util.List;

import seedu.address.model.ReadOnlyReroll;
import seedu.address.model.Reroll;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Inventory;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Mob;
import seedu.address.model.entity.Name;
import seedu.address.model.entity.Progression;
import seedu.address.model.entity.Stats;
import seedu.address.model.tag.Tag;

/***
 * Contains utility methods for populating {@code Reroll} with sample data.
 */
public class SampleDataUtil {

    private static Character[] sampleCharacter;
    private static Mob[] sampleMob;
    private static Item[] sampleItem;

    /** Sample Data */
    public static void getSampleEntities() {
        Item sword = new Item(new Name("Sword"), 2, 3, new HashSet<>());
        Item bow = new Item(new Name("Bow"), 4, 3, new HashSet<>());
        Inventory i1 = new Inventory(List.of(sword));
        Inventory i2 = new Inventory(List.of(bow));
        Character c = new Character.CharacterBuilder(new Name("Mike"))
                .setStats(new Stats(3, 3, 3))
                .setProgression(new Progression(3, 2))
                .setInventory(i2)
                .build();
        Mob m1 = new Mob(new Name("Skeleton Archer"), new Stats(300, 300, 300), 2, true, i2, new HashSet<Tag>());
        Mob m2 = new Mob(new Name("Skeleton Warrior"), new Stats(300, 300, 300), 2, true, i1, new HashSet<Tag>());
        sampleCharacter = new Character[] {c};
        sampleMob = new Mob[] {m1, m2};
        sampleItem = new Item[] {sword, bow};
    }

    /** Initialize Reroll with sample data */
    public static ReadOnlyReroll getSampleReroll() {
        Reroll sampleRr = new Reroll();
        getSampleEntities();
        for (Item item : sampleItem) {
            sampleRr.addEntity(item);
        }
        for (Mob mob : sampleMob) {
            sampleRr.addEntity(mob);
        }
        for (Character character : sampleCharacter) {
            sampleRr.addEntity(character);
        }

        return sampleRr;
    }
}
