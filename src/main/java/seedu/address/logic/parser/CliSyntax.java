package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("telegram/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_REMARK = new Prefix("r/");
    public static final Prefix PREFIX_PERFORMANCE = new Prefix("score/");
    public static final Prefix PREFIX_PHOTO = new Prefix("photo/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_TUTORIAL = new Prefix("Tutorial/");
    public static final Prefix PREFIX_LAB = new Prefix("Lab/");
    public static final Prefix PREFIX_CONSULTATION = new Prefix("Consultation/");
    public static final Prefix PREFIX_RECUR = new Prefix("Recur/");
    public static final Prefix PREFIX_SORT_STUDENT = new Prefix("sort student/");
    public static final Prefix PREFIX_FILTER = new Prefix("Filter/");
    public static final Prefix PREFIX_COUNT = new Prefix("-n ");
    public static final Prefix PREFIX_DATE = new Prefix("-date ");
    public static final Prefix PREFIX_FILE = new Prefix("-file ");
    public static final Prefix PREFIX_NOTE = new Prefix("-note ");

    // Add note directly instead of depending on events
    public static final Prefix PREFIX_NOTE_EXTERNAL = new Prefix("Note/");
    public static final Prefix PREFIX_NOTE_CONTENT = new Prefix("content/");
    public static final Prefix PREFIX_NOTE_EVENT_TYPE = new Prefix("type/");
    public static final Prefix PREFIX_NOTE_EVENT_NAME = new Prefix("name/");
    public static final Prefix PREFIX_NOTE_INDEX = new Prefix("index/");
}
