package seedu.address.testutil;

import seedu.address.model.event.Consultation;
import seedu.address.model.event.Lab;
import seedu.address.model.event.Tutorial;

/**
 * A utility class containing a list of {@code Tutorial}, {@code Lab}, {@code Consultation}
 * objects to be used in tests.
 */
public class TypicalEvents {
    public static final Tutorial SAMPLE_TUTORIAL = new Tutorial("tutorial");
    public static final Lab SAMPLE_LAB = new Lab("lab");
    public static final Consultation SAMPLE_CONSULTATION = new Consultation("consultation");
}
