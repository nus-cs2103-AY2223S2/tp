package seedu.address.model.util;

import static seedu.address.model.entity.Character.CharacterBuilder;
import static seedu.address.model.entity.Mob.MobBuilder;

import java.util.HashSet;
import java.util.List;

import seedu.address.model.ReadOnlyReroll;
import seedu.address.model.Reroll;
import seedu.address.model.entity.ChallengeRating;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Inventory;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Legend;
import seedu.address.model.entity.Mob;
import seedu.address.model.entity.Name;
import seedu.address.model.entity.Progression;
import seedu.address.model.entity.Stats;

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
        Character c = new CharacterBuilder(new Name("Mike"))
                .setStats(new Stats(3, 3, 3))
                .setProgression(new Progression(3, 2))
                .setInventory(i2)
                .build();
        Mob m1 = new MobBuilder(new Name("Skeleton Archer"))
                .setStats(new Stats(300, 300, 300))
                .setChallengeRating(new ChallengeRating(2.0))
                .setLegend(new Legend(true))
                .setInventory(i2)
                .build();

        Mob m2 = new MobBuilder(new Name("Skeleton Warrior"))
                .setStats(new Stats(300, 300, 300))
                .setChallengeRating(new ChallengeRating(2.0))
                .setLegend(new Legend(true))
                .setInventory(i1)
                .build();

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
