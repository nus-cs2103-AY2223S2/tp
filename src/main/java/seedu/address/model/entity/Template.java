package seedu.address.model.entity;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * A template class to create a new Character.
 */
public class Template {

    private static final int BASE_LEVEL = 1;
    private static final int BASE_XP = 0;
    private static final int BASE_POINTS = 10;

    private final Name name;
    private final float strWeight;
    private final float dexWeight;
    private final float intWeight;

    private final Set<Tag> tags = new HashSet<>();

    // May have default inventory.

    /**
     * Constructor for Template
     */
    public Template(Name name, float strWeight, float dexWeight, float intWeight, Set<Tag> tags) {
        requireAllNonNull(name, tags);
        this.name = name;
        this.dexWeight = dexWeight;
        this.strWeight = strWeight;
        this.intWeight = intWeight;
        this.tags.addAll(tags);
        // The template always tags its own name
        // this.tags.add(new Tag(name.toString()));
    }

    /**
     * Returns name
     */
    public Name getName() {
        return name;
    }

    public float getDexWeight() {
        return dexWeight;
    }

    public float getStrWeight() {
        return strWeight;
    }

    public float getIntWeight() {
        return intWeight;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Primitive version of generating character from template.
     */
    public Character generateCharacter(Name characterName) {
        int dexterity = (int) (BASE_POINTS * dexWeight);
        int strength = (int) (BASE_POINTS * intWeight);
        int intelligence = (int) (BASE_POINTS * dexWeight);
        if (dexterity + strength + intelligence < BASE_POINTS) {
            // just allocate somewhere...
            dexterity += BASE_POINTS - dexterity - strength - intelligence;
        }
        Stats stats = new Stats(strength, dexterity, intelligence);

        return new Character(characterName, stats, BASE_LEVEL, BASE_XP, tags);
    }

    /**
     * Returns true if other template has the same name.
     */
    public boolean isSameTemplate(Template other) {
        return other == this
                || other.getName() == this.getName();
    }

}
