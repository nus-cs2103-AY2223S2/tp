package seedu.address.model.entity;

/**
 * Represents the stats of a single character/mob
 */
public class Stats {

    private final int strength;
    private final int dexterity;
    private final int intelligence;

    /**
     * Every field should be present and non-null.
     *
     * @param str   Strength of the character/mob
     * @param dex   Dexterity of the character/mob
     * @param intel Intelligence of the character/mob
     */
    public Stats(int str, int dex, int intel) {
        this.strength = str;
        this.dexterity = dex;
        this.intelligence = intel;
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
