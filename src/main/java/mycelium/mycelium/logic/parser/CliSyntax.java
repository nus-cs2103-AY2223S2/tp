package mycelium.mycelium.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    public static final Prefix PREFIX_CLIENT_NAME = new Prefix("-cn");
    public static final Prefix PREFIX_CLIENT_EMAIL = new Prefix("-e");
    public static final Prefix PREFIX_CLIENT_YEAR_OF_BIRTH = new Prefix("-y");
    public static final Prefix PREFIX_SOURCE = new Prefix("-src");
    public static final Prefix PREFIX_CLIENT_MOBILE_NUMBER = new Prefix("-mn");
    public static final Prefix PREFIX_PROJECT_NAME = new Prefix("-pn");
    public static final Prefix PREFIX_PROJECT_DESCRIPTION = new Prefix("-d");
    public static final Prefix PREFIX_ACCEPTED_DATE = new Prefix("-ad");
    public static final Prefix PREFIX_DEADLINE_DATE = new Prefix("-dd");
    public static final Prefix PREFIX_PROJECT_STATUS = new Prefix("-s");

}
