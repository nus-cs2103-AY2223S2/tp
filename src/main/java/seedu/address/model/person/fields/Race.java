package seedu.address.model.person.fields;

public class Race {

    public static final String MESSAGE_CONSTRAINTS = "stonks";
    String race;

    public Race(String race) {
        this.race = race;
    }

    public static boolean isValidRace(String trimmedRace) {
        return true;
    }
}
