package seedu.task.logic.commands;

import static java.util.Objects.requireNonNull;
import java.util.Set;

import seedu.task.logic.commands.exceptions.CommandException;
import seedu.task.model.Model;
import seedu.task.model.tag.Tag;


public class FilterCommand {

    public static final String COMMAND_WORD = "";
    public static String MESSAGE_SUCCESS = "Tasks found with tags: %1$s";
    public static String MESSAGE_FAILURE = "There is no task with all the tags in you input"
        + "\n" + "You may want to search for each individual tag separately";
    public Set<Tag> tags;

    public FilterCommand(Set<Tag> tags) {
        this.tags = tags;
    }


    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);



        model.updateFilteredTaskList(task -> (task.hasTags(tags)));
        return new CommandResult(String.format(MESSAGE_SUCCESS, formatTags(tags)));
    }

    public String formatTags(Set<Tag> tags) {
        requireNonNull(tags);
        String output = "";
        for (Tag cur: tags) {
            output += cur.toString() + ", ";
        }
        output = output.trim();
        return output.substring(0,output.length() - 1);
    }



}