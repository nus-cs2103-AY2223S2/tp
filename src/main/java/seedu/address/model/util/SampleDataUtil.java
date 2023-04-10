package seedu.address.model.util;

import static seedu.address.model.entity.Character.CharacterBuilder;
import static seedu.address.model.entity.Item.ItemBuilder;
import static seedu.address.model.entity.Mob.MobBuilder;

import java.util.HashSet;
import java.util.List;

import seedu.address.model.ReadOnlyReroll;
import seedu.address.model.Reroll;
import seedu.address.model.entity.ChallengeRating;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Cost;
import seedu.address.model.entity.Inventory;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Legend;
import seedu.address.model.entity.Mob;
import seedu.address.model.entity.Name;
import seedu.address.model.entity.Progression;
import seedu.address.model.entity.Stats;
import seedu.address.model.entity.Weight;
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
        Tag rustyTag = new Tag("rusty");
        Tag eliteTag = new Tag("elite");
        Tag undeadTag = new Tag("undead");

        Item sword = new ItemBuilder(new Name("Sword"))
                .setCost(new Cost(2))
                .setWeight(new Weight(3.0))
                .setTags(new HashSet<>(List.of(rustyTag)))
                .build();

        Item bow = new ItemBuilder(new Name("Bow"))
                .setCost(new Cost(4))
                .setWeight(new Weight(3.0))
                .setTags(new HashSet<>(List.of(rustyTag)))
                .build();

        Item staff = new ItemBuilder(new Name("Gilded staff"))
                .setCost(new Cost(15))
                .setWeight(new Weight(4.0))
                .build();

        Item book = new ItemBuilder(new Name("Spellbook"))
                .setCost(new Cost(6))
                .setWeight(new Weight(0.3))
                .build();

        Inventory i1 = new Inventory(List.of(sword));
        Inventory i2 = new Inventory(List.of(bow));
        Inventory i3 = new Inventory(List.of(staff, book));

        Character c = new CharacterBuilder(new Name("Mike"))
                .setStats(new Stats(3, 3, 3))
                .setProgression(new Progression(3, 2))
                .setInventory(i2)
                .build();

        Mob m1 = new MobBuilder(new Name("Skeleton Archer"))
                .setStats(new Stats(20, 20, 20))
                .setChallengeRating(new ChallengeRating(2.0))
                .setLegend(new Legend(true))
                .setInventory(i2)
                .setTags(new HashSet<>(List.of(undeadTag)))
                .build();

        Mob m2 = new MobBuilder(new Name("Skeleton Warrior"))
                .setStats(new Stats(20, 20, 20))
                .setChallengeRating(new ChallengeRating(2.0))
                .setInventory(i1)
                .setTags(new HashSet<>(List.of(undeadTag)))
                .build();

        Mob m3 = new MobBuilder(new Name("Necromancer"))
                .setStats(new Stats(300, 300, 300))
                .setChallengeRating(new ChallengeRating(5.0))
                .setLegend(new Legend(true))
                .setInventory(i3)
                .setTags(new HashSet<>(List.of(undeadTag, eliteTag)))
                .build();

        sampleCharacter = new Character[] {c};
        sampleMob = new Mob[] {m1, m2, m3};
        sampleItem = new Item[] {sword, bow, staff, book};
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
