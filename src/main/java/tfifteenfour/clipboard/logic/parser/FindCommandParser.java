package tfifteenfour.clipboard.logic.parser;

import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.commands.findcommand.FindCommand;
import tfifteenfour.clipboard.logic.commands.findcommand.FindCourseCommand;
import tfifteenfour.clipboard.logic.commands.findcommand.FindGroupCommand;
import tfifteenfour.clipboard.logic.commands.findcommand.FindSessionCommand;
import tfifteenfour.clipboard.logic.commands.findcommand.FindStudentCommand;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;
import tfifteenfour.clipboard.logic.predicates.CourseNameContainsPredicate;
import tfifteenfour.clipboard.logic.predicates.GroupNameContainsPredicate;
import tfifteenfour.clipboard.logic.predicates.SessionNameContainsPredicate;
import tfifteenfour.clipboard.logic.predicates.StudentNameContainsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private final CurrentSelection currentSelection;

    public FindCommandParser(CurrentSelection currentSelection) {
        this.currentSelection = currentSelection;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        CommandTargetType findCommandType;
        try {
            findCommandType = CommandTargetType.fromString(ArgumentTokenizer.tokenizeString(args)[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException("Find type missing!");
        }

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format("No search keywords entered"));
        }

        String[] keywords = parseFindKeywords(trimmedArgs);

        switch (findCommandType) {
            case MODULE:
                return new FindCourseCommand(new CourseNameContainsPredicate(keywords));
            case GROUP:
                return new FindGroupCommand(new GroupNameContainsPredicate(keywords), currentSelection);
            case SESSION:
                return new FindSessionCommand(new SessionNameContainsPredicate(keywords), currentSelection);
            case STUDENT:
                return new FindStudentCommand(new StudentNameContainsPredicate(keywords), currentSelection);
            default:
                throw new ParseException("Invalid type for edit command");
            }
    }

    public String[] parseFindKeywords(String args) throws ParseException {
        String[] tokens = ArgumentTokenizer.tokenizeString(args);

        if (tokens.length < 2) {
            System.out.println("token length " + tokens.length);
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCourseCommand.MESSAGE_USAGE));
        }
        String[] result = new String[tokens.length - 1];
        for (int i = 0; i < tokens.length - 1; i++) {
            result[i] = tokens[i + 1];
        }
        return result;
    }

}
