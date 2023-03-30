package wingman.model.pilot;

import wingman.model.pilot.exceptions.InvalidGenderException;

/**
 * The gender.
 */
public enum Gender {
    MALE,
    FEMALE,
    OTHER;

    /**
     * Converts the gender to index for Json storage.
     *
     * @return the gender.
     */
    public int toIndex() {
        switch (this) {
        case MALE:
            return 0;
        case FEMALE:
            return 1;
        case OTHER:
            return 2;
        default:
            throw new InvalidGenderException();
        }
    }

    /**
     * Converts the gender from index.
     *
     * @param index the index
     * @return gender.
     */
    public static Gender fromIndex(int index) {
        switch (index) {
        case 0:
            return MALE;
        case 1:
            return FEMALE;
        case 2:
            return OTHER;
        default:
            throw new InvalidGenderException();
        }
    }
}
