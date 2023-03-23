package seedu.vms.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {
    public static final String DELIMITER = "--";

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n");
    public static final Prefix PREFIX_PHONE = new Prefix("p");
    public static final Prefix PREFIX_DOB = new Prefix("d");
    public static final Prefix PREFIX_BLOODTYPE = new Prefix("b");
    public static final Prefix PREFIX_ALLERGY = new Prefix("a");
    public static final Prefix PREFIX_VACCINATION = new Prefix("v");

    /*
     * ========================================================================
     * Vaccination
     * ========================================================================
     */

    public static final Prefix PREFIX_VAX_GROUPS = new Prefix("g");
    public static final Prefix PREFIX_MIN_AGE = new Prefix("lal");
    public static final Prefix PREFIX_MAX_AGE = new Prefix("ual");
    public static final Prefix PREFIX_INGREDIENTS = new Prefix("i");
    public static final Prefix PREFIX_HISTORY_REQ = new Prefix("h");

    /*
     * ========================================================================
     * Appointment
     * ========================================================================
     */

    public static final Prefix PREFIX_PATIENT = new Prefix("p");
    public static final Prefix PREFIX_STARTTIME = new Prefix("s");
    public static final Prefix PREFIX_ENDTIME = new Prefix("e");

}
