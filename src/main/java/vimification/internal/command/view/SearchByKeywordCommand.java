package vimification.internal.command.view;

public class SearchByKeywordCommand extends SearchCommand {
    public static final String COMMAND_WORD = "s -t";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": search for tasks that has title matching with input keyword.\n"
            + "Parameters: KEYWORD\n"
            + "Conditions: Keyword cannot be empty.\n"
            + "Example: " + COMMAND_WORD + " quiz";

    public SearchByKeywordCommand(String keyword) {
        super(task -> task.containsKeyword(keyword));
    }

    /*
     * @Override public CommandResult execute(LogicTaskList taskList) { ObservableList<Task>
     * viewTaskList = FXCollections.observableList(taskList.filter(getPredicate()));
     * setViewTaskList(viewTaskList); return new CommandResult(SUCCESS_MESSAGE_FORMAT); }
     */
}
