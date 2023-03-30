package vimification.internal.command.view;

public class SearchByLabelCommand extends SearchCommand {

    public static final String COMMAND_WORD = "s -l";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": search for tasks with label matching the input label.\n"
            + "Example: " + COMMAND_WORD + " meeting";

    public SearchByLabelCommand(String label) {
        super(task -> task.containsLabel(label));
    }
}
