package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands.
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
<<<<<<< Updated upstream
    public static final Prefix PREFIX_NRIC_ELDERLY = new Prefix("enr/");
    public static final Prefix PREFIX_NRIC_VOLUNTEER = new Prefix("vnr/");
    public static final Prefix PREFIX_NRIC = new Prefix("nr/");
    public static final Prefix PREFIX_AGE = new Prefix("ag/");
=======
    public static final Prefix PREFIX_NRIC_ELDERLY = new Prefix("eic/");
    public static final Prefix PREFIX_NRIC_VOLUNTEER = new Prefix("vic/");
    public static final Prefix PREFIX_NRIC = new Prefix("ic/");
>>>>>>> Stashed changes
    public static final Prefix PREFIX_RISK = new Prefix("r/");
    public static final Prefix PREFIX_REGION = new Prefix("re/");
    public static final Prefix PREFIX_MEDICAL_TAG = new Prefix("mt/");

    public static final Prefix PREFIX_AVAILABILITY = new Prefix("dr/");
}
