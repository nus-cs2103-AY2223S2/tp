package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */

    // Generic commands
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    // Doctor specific commands
    public static final Prefix PREFIX_DOCTOR = new Prefix("doc/");
    public static final Prefix PREFIX_SPECIALTY = new Prefix("s/");
    public static final Prefix PREFIX_YOE = new Prefix("y/");

    // Patient specific commands
    public static final Prefix PREFIX_PATIENT = new Prefix("ptn/");
    public static final Prefix PREFIX_HEIGHT = new Prefix("h/");
    public static final Prefix PREFIX_WEIGHT = new Prefix("w/");
    public static final Prefix PREFIX_DIAGNOSIS = new Prefix("d/");
    public static final Prefix PREFIX_REMARK = new Prefix("r/");
    public static final Prefix PREFIX_STATUS = new Prefix("st/");

}
