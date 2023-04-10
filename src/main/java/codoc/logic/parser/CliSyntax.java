package codoc.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_GITHUB = new Prefix("g/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_COURSE = new Prefix("c/");
    public static final Prefix PREFIX_YEAR = new Prefix("y/");
    public static final Prefix PREFIX_LINKEDIN = new Prefix("l/");
    public static final Prefix PREFIX_SKILL = new Prefix("s/");
    public static final Prefix PREFIX_SKILL_ADD = new Prefix("s+/");
    public static final Prefix PREFIX_SKILL_DELETE = new Prefix("s-/");
    public static final Prefix PREFIX_MOD = new Prefix("m/");
    public static final Prefix PREFIX_MOD_ADD = new Prefix("m+/");
    public static final Prefix PREFIX_MOD_DELETE = new Prefix("m-/");

}
