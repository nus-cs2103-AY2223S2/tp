package seedu.address.model.entity;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents the stats of a single character/mob
 */
public class Stats {
    private static final int BASE_STRENGTH = 1;
    private static final int BASE_DEXTERITY = 1;
    private static final int BASE_INTELLIGENCE = 1;

    private final Integer strength;
    private final Integer dexterity;
    private final Integer intelligence;

    /**
     * Every field should be present and non-null.
     *
     * @param str   Strength of the character/mob
     * @param dex   Dexterity of the character/mob
     * @param intel Intelligence of the character/mob
     */
    public Stats(Integer str, Integer dex, Integer intel) {
        requireAllNonNull(str, dex, intel);
        this.strength = str;
        this.dexterity = dex;
        this.intelligence = intel;
    }

    public Stats() {
        this(BASE_STRENGTH, BASE_DEXTERITY, BASE_INTELLIGENCE);
    }

    public int getStrength() {
        return this.strength;
    }

    public int getDexterity() {
        return this.dexterity;
    }

    public int getIntelligence() {
        return this.intelligence;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Stats)) {
            return false;
        }

        Stats s = (Stats) other;

        return getDexterity() == s.getDexterity()
            && getIntelligence() == s.getIntelligence()
            && getStrength() == s.getStrength();
    }
}
