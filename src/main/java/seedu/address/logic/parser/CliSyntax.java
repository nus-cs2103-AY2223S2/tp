package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Fish prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_LAST_FED_DATE = new Prefix("lfd/");
    public static final Prefix PREFIX_SPECIES = new Prefix("s/");
    public static final Prefix PREFIX_FEEDING_INTERVAL = new Prefix("fi/");
    public static final Prefix PREFIX_TAG = new Prefix("tg/");
    public static final Prefix PREFIX_TANK = new Prefix("tk/");
    public static final Prefix PREFIX_SORT_BY = new Prefix("by/");

    /* Task prefixes */
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_PRIORITY = new Prefix("p/");

    /* Tank reading prefixes */
    public static final Prefix PREFIX_AMMONIA_LEVEL = new Prefix("al/");
    public static final Prefix PREFIX_PH = new Prefix("ph/");
    public static final Prefix PREFIX_TEMPERATURE = new Prefix("tp/");

}
