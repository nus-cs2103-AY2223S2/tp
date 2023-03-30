package taa.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");

    public static final Prefix PREFIX_TIME = new Prefix("t/");

    public static final Prefix PREFIX_COMMENT = new Prefix("c/");

    public static final Prefix PREFIX_CLASS_TAG = new Prefix("cl/");
    public static final Prefix PREFIX_WEEK = new Prefix("w/");

    public static final Prefix PREFIX_STUDENT_ID = new Prefix("i/");
    public static final Prefix PREFIX_ASSIGNMENT_NAME = new Prefix("as/");

    public static final Prefix PREFIX_MARK = new Prefix("m/");

    public static final Prefix PREFIX_LATE = new Prefix("late/");

    public static final Prefix PREFIX_PARTICIPATION_POINTS = new Prefix("pp/");
    public static final Prefix PREFIX_STAT_TYPE = new Prefix("st/");

    private CliSyntax() {
    }

}
