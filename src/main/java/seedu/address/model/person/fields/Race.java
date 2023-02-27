package seedu.address.model.person.fields;

public class Race {

    public static final String MESSAGE_CONSTRAINTS = "Race must be a string of alphabets";
    public String race;

    public Race(String race) {
        this.race = race;
    }

    public static boolean isValidRace(String trimmedRace) {
        return true;
    }

    @Override
    public String toString() {
        return this.race;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.race.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return this.race.hashCode();
    }

}
