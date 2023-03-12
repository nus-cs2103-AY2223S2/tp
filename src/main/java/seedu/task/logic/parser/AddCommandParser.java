package seedu.task.logic.parser;

import static seedu.task.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.task.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.task.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.task.logic.parser.CliSyntax.PREFIX_EFFORT;
import static seedu.task.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.task.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.task.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.task.logic.parser.CliSyntax.PREFIX_TO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.task.logic.commands.AddCommand;
import seedu.task.logic.parser.exceptions.ParseException;
import seedu.task.model.tag.Tag;
import seedu.task.model.task.*;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution with one or more tasks.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DESCRIPTION,
                            PREFIX_TAG, PREFIX_DEADLINE, PREFIX_TO, PREFIX_FROM, PREFIX_EFFORT);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Set<Name> nameList = ParserUtil.parseNames(argMultimap.getAllValues(PREFIX_NAME));
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Effort effort = ParserUtil.parseEffort(argMultimap.getValue(PREFIX_EFFORT).get());

        List<Task> taskList;
        // auto-convert to deadline if deadline prefix exists.
        if (arePrefixesPresent(argMultimap, PREFIX_DEADLINE)) {
            Date deadline = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DEADLINE).get());
            taskList = addDeadlines(nameList, description, tagList, deadline, effort);
            return new AddCommand(taskList);
        }
        // auto-convert to event if from and to exist.
        if (arePrefixesPresent(argMultimap, PREFIX_FROM, PREFIX_TO)) {
            Date from = ParserUtil.parseDate(argMultimap.getValue(PREFIX_FROM).get());
            Date to = ParserUtil.parseDate(argMultimap.getValue(PREFIX_TO).get());
            taskList = addEvents(nameList, description, tagList, from, to, effort);
            return new AddCommand(taskList);
        }

        taskList = addSimpleTasks(nameList, description, tagList, effort);

        return new AddCommand(taskList);
    }

    /**
     * Adds simple tasks to the task list
     * @param nameList All the names of tasks
     * @param description A single description belonging to all tasks
     * @param tagList A single tag belonging to all tasks
     * @param effort An effort estimating the amount of work required to complete task
     * @return
     */
    public List<Task> addSimpleTasks(Set<Name> nameList, Description description, Set<Tag> tagList, Effort effort) {
        List<Task> taskList = new ArrayList<>();
        for (Name cur: nameList) {
            taskList.add(new SimpleTask(cur, description, tagList, effort));
        }
        return taskList;
    }

    /**
     * Adds deadline tasks to the task list
     * @param nameList All the names of deadlines
     * @param description A single description belonging to all deadlines
     * @param tagList A single tag belonging to all deadlines
     * @param deadline A date representing the deadline of all tasks here
     * @param effort An effort estimating the amount of work required to complete task
     * @return
     */
    public List<Task> addDeadlines(Set<Name> nameList, Description description, Set<Tag> tagList, Date deadline, Effort effort) {
        List<Task> taskList = new ArrayList<>();
        for (Name cur: nameList) {
            taskList.add(new Deadline(cur, description, tagList, deadline, effort));
        }
        return taskList;
    }

    /**
     * Adds event tasks to the task list
     * @param nameList All the names of events
     * @param description A single description belonging to all events
     * @param tagList A single tag belonging to all events
     * @param from A date representing the start date of all events here
     * @param to A date representing the end date of all events here
     * @param effort An effort estimating the amount of work required to complete task
     * @return
     */
    public List<Task> addEvents(Set<Name> nameList, Description description, Set<Tag> tagList, Date from, Date to, Effort effort) {
        List<Task> taskList = new ArrayList<>();
        for (Name cur: nameList) {
            taskList.add(new Event(cur, description, tagList, from, to, effort));
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
