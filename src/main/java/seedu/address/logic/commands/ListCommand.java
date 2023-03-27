package seedu.address.logic.commands;

import seedu.address.model.Model;

public class ListCommand extends Command{
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all students or parents\n"
            + "Example: " + COMMAND_WORD + " student" + " or " + COMMAND_WORD + " parent";

    public static final String SHOWING_LIST_MESSAGE = "Listed all ";

    public String trimmedType;
    public ListCommand(String type) {
        String trimmedType = type.trim();
        this.trimmedType = trimmedType;
    }
    @Override
    public CommandResult execute(Model model) {
        if (this.trimmedType.equals("student")) {
            model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
            return new CommandResult(SHOWING_LIST_MESSAGE + "students!", false, false);
        } else if (this.trimmedType.equals("parent")) {
            model.updateFilteredParentList(Model.PREDICATE_SHOW_ALL_PARENTS);
            return new CommandResult(SHOWING_LIST_MESSAGE + "parents!", false, false);
        } else {
            return new CommandResult("Invalid command! Try `list student` or `list parent`",
                    false, false);
        }

    }
}
