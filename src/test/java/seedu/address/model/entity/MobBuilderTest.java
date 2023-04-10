package seedu.address.model.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.model.entity.Mob.MobBuilder;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.InventoryStub;

/**
 * Test class for Mob.MobBuilder
 */
public class MobBuilderTest {
    private static final Name DEFAULT_NAME = new Name("TEST");
    private static final Stats DEFAULT_STATS = new Stats();
    private static final Inventory DEFAULT_INVENTORY = new Inventory();
    private static final ChallengeRating DEFAULT_CHALLENGE_RATING = new ChallengeRating();
    private static final Legend DEFAULT_LEGEND = new Legend();
    private static final Set<Tag> DEFAULT_TAGS = new HashSet<>();

    /**
     * Test if the optional setters of the builder produce the default values
     */
    @Test
    public void build_useDefaultFields_allDefaultFieldsPresent() {
        MobBuilder baseBuilder = new MobBuilder(DEFAULT_NAME);
        Mob baseMob =  baseBuilder.build();
        assertNotNull(baseMob);
        assertEquals(DEFAULT_STATS, baseMob.getStats());
        assertEquals(DEFAULT_INVENTORY, baseMob.getInventory());
        assertEquals(DEFAULT_CHALLENGE_RATING, baseMob.getChallengeRating());
        assertEquals(DEFAULT_LEGEND, baseMob.getLegend());
        assertEquals(DEFAULT_TAGS, baseMob.getTags());
    }

    /**
     * Test if the optional setters of the builder sets the fields to the given values
     */
    @Test
    public void build_setAllFields_allFieldsSetCorrectly() {
        final Stats CUSTOM_STATS = new Stats(1, 1, 1);
        final Inventory CUSTOM_INVENTORY = new InventoryStub();
        final ChallengeRating CUSTOM_CHALLENGE_RATING = new ChallengeRating(1.4);
        final Legend CUSTOM_LEGEND = new Legend(true);
        final Set<Tag> CUSTOM_TAGS = new HashSet<>();
        CUSTOM_TAGS.add(new Tag("Testing"));

        MobBuilder customBuilder = new MobBuilder(DEFAULT_NAME)
                .setStats(CUSTOM_STATS)
                .setInventory(CUSTOM_INVENTORY)
                .setChallengeRating(CUSTOM_CHALLENGE_RATING)
                .setLegend(CUSTOM_LEGEND)
                .setTags(CUSTOM_TAGS);

        Mob customMob = customBuilder.build();
        assertNotNull(customMob);
        assertEquals(CUSTOM_STATS, customMob.getStats());
        assertEquals(CUSTOM_INVENTORY, customMob.getInventory());
        assertEquals(CUSTOM_CHALLENGE_RATING, customMob.getChallengeRating());
        assertEquals(CUSTOM_LEGEND, customMob.getLegend());
        assertEquals(CUSTOM_TAGS, customMob.getTags());
    }
}
