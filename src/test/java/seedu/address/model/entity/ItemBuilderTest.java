package seedu.address.model.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.model.entity.Item.ItemBuilder;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import seedu.address.model.tag.Tag;

/**
 * Test class for Item.ItemBuilder
 */
public class ItemBuilderTest {
    private static final Name DEFAULT_NAME = new Name("TEST");
    private static final Cost DEFAULT_COST = new Cost();
    private static final Weight DEFAULT_WEIGHT = new Weight();
    private static final Set<Tag> DEFAULT_TAGS = new HashSet<>();

    /**
     * Test if the optional setters of the builder produce the default values
     */
    @Test
    public void build_useDefaultFields_allDefaultFieldsPresent() {
        ItemBuilder baseBuilder = new ItemBuilder(DEFAULT_NAME);
        Item baseItem =  baseBuilder.build();
        assertNotNull(baseItem);
        assertEquals(DEFAULT_COST, baseItem.getCost());
        assertEquals(DEFAULT_WEIGHT, baseItem.getWeight());
        assertEquals(DEFAULT_TAGS, baseItem.getTags());
    }

    /**
     * Test if the optional setters of the builder sets the fields to the given values
     */
    @Test
    public void build_setAllFields_allFieldsSetCorrectly() {
        final Cost CUSTOM_COST = new Cost();
        final Weight CUSTOM_WEIGHT = new Weight(2.0);
        final Set<Tag> CUSTOM_TAGS = new HashSet<>();
        CUSTOM_TAGS.add(new Tag("Testing"));

        ItemBuilder customBuilder = new ItemBuilder(DEFAULT_NAME)
                .setCost(CUSTOM_COST)
                .setWeight(CUSTOM_WEIGHT)
                .setTags(CUSTOM_TAGS);

        Item customItem = customBuilder.build();
        assertNotNull(customItem);
        assertEquals(CUSTOM_COST, customItem.getCost());
        assertEquals(CUSTOM_WEIGHT, customItem.getWeight());
        assertEquals(CUSTOM_TAGS, customItem.getTags());
    }
}
