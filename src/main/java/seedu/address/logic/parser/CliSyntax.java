package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    // Compulsory Fields
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_TIMESLOT = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");

    // Optional fields
    public static final Prefix PREFIX_REMARK = new Prefix("r/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("d/");
    public static final Prefix PREFIX_TEACHER = new Prefix("s/");

    // no use for this for now
    public static final Prefix PREFIX_TYPE = new Prefix("p/");


}
