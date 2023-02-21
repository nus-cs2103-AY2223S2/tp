package seedu.task.logic.parser;

import static seedu.task.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.task.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.task.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.task.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.task.logic.commands.AddCommand;
import seedu.task.logic.parser.exceptions.ParseException;
import seedu.task.model.tag.Tag;
import seedu.task.model.task.Description;
import seedu.task.model.task.Name;
import seedu.task.model.task.Task;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Set<Name> nameList = ParserUtil.parseNames(argMultimap.getAllValues(PREFIX_NAME));
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        List<Task> taskList = addTasks(nameList, description, tagList);

        return new AddCommand(taskList);
    }

    /**
     * Adds tasks to the task list
     * @param nameList All the names of tasks
     * @param description A single description belonging to all tasks
     * @param tagList A single tag belonging to all tasks
     * @return
     */
    public List<Task> addTasks(Set<Name> nameList, Description description, Set<Tag> tagList) {
        List<Task> taskList = new ArrayList<>();
        for (Name cur: nameList) {
            taskList.add(new Task(cur, description, tagList));
        }
        return taskList;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
