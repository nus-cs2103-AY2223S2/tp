package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_CONTEXT_USAGE = "/mod and /lec parameters are automatically inserted depending "
            + "on the current context.\n"
            + "The user can overwrite the values by specifying them explicitly.\n"
            + "The user can also specify /r to prevent any of these insertions.\n"
            + "Refer to the User Guide for more information.";

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command\n";
    public static final String MESSAGE_KNOWN_COMMANDS = "The known commands are:\n"
            + "nav, add, edit, delete, tag, list, find, mark, unmark, help and exit";

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";

    public static final String MESSAGE_MODULES_LISTED_OVERVIEW = "%1$d modules listed!";
    public static final String MESSAGE_LECTURES_LISTED_OVERVIEW = "%1$d lectures listed!";
    public static final String MESSAGE_VIDEOS_LISTED_OVERVIEW = "%1$d videos listed!";

    public static final String MESSAGE_DOES_NOT_EXIST = "%1$s does not exist";
    public static final String MESSAGE_MODULE_DOES_NOT_EXIST = "Module "
            + String.format(MESSAGE_DOES_NOT_EXIST, "%1$s");
    public static final String MESSAGE_LECTURE_DOES_NOT_EXIST = "Lecture %1$s of "
            + String.format(MESSAGE_MODULE_DOES_NOT_EXIST, "%2$s");
    public static final String MESSAGE_VIDEO_DOES_NOT_EXIST = "Video %1$s of "
            + String.format(MESSAGE_LECTURE_DOES_NOT_EXIST, "%2$s", "%3$s");

    public static final String MESSAGE_DO_NOT_EXIST = "%1$s do not exist";
    public static final String MESSAGE_MODULES_DONT_EXIST = "Modules "
            + String.format(MESSAGE_DO_NOT_EXIST, "%1$s");
    public static final String MESSAGE_LECTURES_DO_NOT_EXIST = "Lectures "
            + String.format(MESSAGE_DO_NOT_EXIST, "%1$s")
            + " in Module %2$s";
    public static final String MESSAGE_VIDEOS_DO_NOT_EXIST = "Videos "
            + String.format(MESSAGE_DO_NOT_EXIST, "%1$s")
            + " in Lecture %3$s of Module %2$s";

    public static final String MESSAGE_MODULE_TAG_DOES_NOT_EXIST = "Tag %1$s of "
            + String.format(MESSAGE_MODULE_DOES_NOT_EXIST, "%2$s");
    public static final String MESSAGE_LECTURE_TAG_DOES_NOT_EXIST = "Tag %1$s of "
            + String.format(MESSAGE_LECTURE_DOES_NOT_EXIST, "%2$s", "%3$s");
    public static final String MESSAGE_VIDEO_TAG_DOES_NOT_EXIST = "Tag %1$s of "
            + String.format(MESSAGE_VIDEO_DOES_NOT_EXIST, "%2$s", "%3$s", "%4$s");

    public static final String MESSAGE_EMPTY_TAGS = "No tag is provided";

    public static final String MESSAGE_CONFLICTING_ARGS = "The arguments %1$s and %2$s cannot both be specified";
}
