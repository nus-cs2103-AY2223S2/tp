package seedu.address.ui;

/**
 * Provides HelpWindow class with long strings to be displayed on screen.
 */
public class HelpStrings {
    public static final String HELP_MESSAGE = "For more help, Refer to the user guide: " + HelpStrings.USERGUIDE_URL;

    public static final String USERGUIDE_URL = "\nhttps://ay2223s2-cs2103-f10-1.github.io/tp/UserGuide.html";
    public static final String ADDMETHODS_HELP = "Nested within addmethods are the methods that you "
            + "can use for adding tasks, and people.";
    public static final String ADDPERSON_HELP = "What it does: Adds a person to OfficeConnect. \n\n"
            + "Format: addperson n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…";
    public static final String ADDTASK_HELP = "What it does: Adds a task to OfficeConnect. \n\n"
            + "Format: addtask n/NAME";
    public static final String DELETEMETHODS_HELP = "Nested within deletemethods are the methods that you "
            + "can use for deleting tasks, and people.";
    public static final String DELETEPERSON_HELP = "What it does: Deletes the person at the specified ID.\n\n"
            + "The index refers to the INDEX shown in the displayed person list."
            + "The INDEX must be a positive integer (eg. 1, 2, 3...) \n\n"
            + "Format: deleteperson INDEX";
    public static final String DELETETASK_HELP = "What it does: Deletes the task at the specified INDEX.\n\n"
            + "The index refers to the INDEX shown in the displayed task list"
            + "The INDEX must be a positive integer (eg. 1, 2, 3...) \n\n"
            + "Format: deletetask INDEX";
    public static final String LISTMETHODS_HELP = "Nested within are the commands related"
            + " to listing out persons, and tasks.";
    public static final String LISTPERSON_HELP = "What it does: Lists all persons in OfficeConnect. \n\n"
            + "Format: listpersons";
    public static final String LISTTASK_HELP = "What it does: Lists all the tasks in OfficeConnect. \n\n"
            + "Format: listtask";
    public static final String FINDMETHODS_HELP = "Nested within are the commands related to"
            + " finding persons, and tasks.";
    public static final String FINDTASK_HELP = "What it does: Finds the task based on given keyword. \n\n"
            + "Format: findtask KEYWORD";
    public static final String FINDPERSON_HELP = "What it does: Returns a person whose"
            + " name matches the input keyword. \n\n"
            + "The search is case-sensitive, and partial inputs are also accepted. \n\n"
            + "Format: findperson KEYWORD";
    public static final String ASSIGNMETHODS_HELP = "Nested within are the commands"
            + " related to the assigning of tasks,"
            + " to a person.";
    public static final String ASSIGN_HELP = "What it does: Assigns the task at"
            + " specified index to person at specified"
            + "index. The index refers to the index number shown in the displayed persons/task list. \n\n"
            + "Format: assign /task INDEX /person INDEX";

}
