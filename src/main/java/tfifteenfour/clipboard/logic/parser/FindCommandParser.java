package tfifteenfour.clipboard.logic.parser;

import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.logic.commands.findcommand.FindCommand;
import tfifteenfour.clipboard.logic.commands.findcommand.FindCourseCommand;
import tfifteenfour.clipboard.logic.commands.findcommand.FindGroupCommand;
import tfifteenfour.clipboard.logic.commands.findcommand.FindSessionCommand;
import tfifteenfour.clipboard.logic.commands.findcommand.FindStudentCommand;
import tfifteenfour.clipboard.logic.commands.findcommand.FindTaskCommand;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;
import tfifteenfour.clipboard.logic.predicates.CourseNameContainsPredicate;
import tfifteenfour.clipboard.logic.predicates.GroupNameContainsPredicate;
import tfifteenfour.clipboard.logic.predicates.SessionNameContainsPredicate;
import tfifteenfour.clipboard.logic.predicates.StudentParticularsContainsPredicate;
import tfifteenfour.clipboard.logic.predicates.TaskNameContainsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    private static final String WRONG_PAGE_MESSAGE = "Wrong page. Navigate to %1$s page to find %1$s";

    private final CurrentSelection currentSelection;


    public FindCommandParser(CurrentSelection currentSelection) {
        this.currentSelection = currentSelection;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     * @throws CommandException if command was performed on wrong page.
     */
    public FindCommand parse(String args) throws ParseException, CommandException {
        CommandTargetType findCommandType;

        try {
            findCommandType = CommandTargetType.fromString(ArgumentTokenizer.tokenizeString(args)[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException("Find type missing!"
                    + "Available find commands are: find course, find group, find session, find task, find student");
        }

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format("No search keywords entered"));
        }

        String[] keywords = parseFindKeywords(trimmedArgs, findCommandType);

        switch (findCommandType) {
        case MODULE:
            if (currentSelection.getCurrentPage() != PageType.COURSE_PAGE) {
                throw new CommandException(String.format(WRONG_PAGE_MESSAGE, "course"));
            }
            return new FindCourseCommand(new CourseNameContainsPredicate(keywords));
        case GROUP:
            if (currentSelection.getCurrentPage() != PageType.GROUP_PAGE) {
                throw new CommandException(String.format(WRONG_PAGE_MESSAGE, "group"));
            }
            return new FindGroupCommand(new GroupNameContainsPredicate(keywords), currentSelection);
        case SESSION:
            if (currentSelection.getCurrentPage() != PageType.SESSION_PAGE) {
                throw new CommandException(String.format(WRONG_PAGE_MESSAGE, "session"));
            }
            return new FindSessionCommand(new SessionNameContainsPredicate(keywords), currentSelection);
        case STUDENT:
            if (currentSelection.getCurrentPage() != PageType.STUDENT_PAGE) {
                throw new CommandException(String.format(WRONG_PAGE_MESSAGE, "student"));
            }
            return new FindStudentCommand(new StudentParticularsContainsPredicate(keywords), currentSelection);
        case TASK:
            if (currentSelection.getCurrentPage() != PageType.TASK_PAGE) {
                throw new CommandException(String.format(WRONG_PAGE_MESSAGE, "task"));
            }
            return new FindTaskCommand(new TaskNameContainsPredicate(keywords), currentSelection);
        default:
            throw new ParseException("Invalid type for find command");
        }
    }

    /**
     * Parses the find command arguments and returns the search keywords as a String array.
     *
     * @param args User input arguments.
     * @return String array of search keywords.
     * @throws ParseException If user input does not meet expected format.
     */
    public String[] parseFindKeywords(String args, CommandTargetType findCommandType) throws ParseException {
        String[] tokens = ArgumentTokenizer.tokenizeString(args);

        if (tokens.length < 2) {
            switch (findCommandType) {
            case MODULE:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindCourseCommand.MESSAGE_USAGE));
            case GROUP:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindGroupCommand.MESSAGE_USAGE));
            case SESSION:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindSessionCommand.MESSAGE_USAGE));
            case STUDENT:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindStudentCommand.MESSAGE_USAGE));
            case TASK:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindTaskCommand.MESSAGE_USAGE));
            default:
                throw new ParseException("Invalid type for find command");
            }
        }
        String[] result = new String[tokens.length - 1];
        for (int i = 0; i < tokens.length - 1; i++) {
            result[i] = tokens[i + 1];
        }
        return result;
    }

}
