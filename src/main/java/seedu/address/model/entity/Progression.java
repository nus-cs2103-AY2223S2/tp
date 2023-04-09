package seedu.address.model.entity;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a character's level in Reroll.
 * Guaranteed to be an integer.
 */
public class Progression {
    public static final String CONSTRAINTS = "Levels and experience points should be integers";

    private static final int BASE_LEVEL = 1;
    private static final int BASE_XP = 0;

    private final Integer level;
    private final Integer xp;

    /**
     * Constructs a {@code Progression}
     *
     * @param level A valid character level
     * @param xp A valid amount of xp
     */
    public Progression(Integer level, Integer xp) {
        requireAllNonNull(level, xp);
        this.level = level;
        this.xp = xp;
    }

    public Progression(Integer level) {
        this(level, BASE_XP);
    }

    public Progression() {
        this(BASE_LEVEL, BASE_XP);
    }

    public int getLevel() {
        return this.level;
    }

    public int getXP() {
        return this.xp;
    }

    @Override
    public String toString() {
        return level.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Progression // instanceof handles nulls
                && level.equals(((Progression) other).level)); // state check
    }
}
