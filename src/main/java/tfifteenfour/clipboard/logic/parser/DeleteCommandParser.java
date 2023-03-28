package tfifteenfour.clipboard.logic.parser;

import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.commands.deletecommand.DeleteCommand;
import tfifteenfour.clipboard.logic.commands.deletecommand.DeleteCourseCommand;
import tfifteenfour.clipboard.logic.commands.deletecommand.DeleteGroupCommand;
import tfifteenfour.clipboard.logic.commands.deletecommand.DeleteSessionCommand;
import tfifteenfour.clipboard.logic.commands.deletecommand.DeleteStudentCommand;
import tfifteenfour.clipboard.logic.commands.deletecommand.DeleteTaskCommand;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    private static final String MESSAGE_USAGE = "delete: Deletes the item at index specified in parameter. "
            + "Parameters: ITEM_TYPE INDEX\n"
            + "Note: INDEX must be a positive integer.\n"
            + "Examples: delete course 1, delete session 3";
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns an DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {

        CommandTargetType deleteCommandType;

        try {
            deleteCommandType = CommandTargetType.fromString(ArgumentTokenizer.tokenizeString(args)[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException("Delete type missing! Please enter a valid delete command.\n"
                    + "Available delete commands are: "
                    + "delete course, delete group, delete session, delete task, delete student");
        }

        Index index;
        try {
            index = parseDeleteCommandIndex(args);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), pe);
        }

        switch (deleteCommandType) {
        case MODULE:
            return new DeleteCourseCommand(index);
        case GROUP:
            return new DeleteGroupCommand(index);
        case SESSION:
            return new DeleteSessionCommand(index);
        case TASK:
            return new DeleteTaskCommand(index);
        case STUDENT:
            return new DeleteStudentCommand(index);
        default:
            throw new ParseException("Invalid argument for delete command");
        }
    }

    private Index parseDeleteCommandIndex(String args) throws ParseException {
        String[] tokens = ArgumentTokenizer.tokenizeString(args);
        if (tokens.length != 3) {
            throw new ParseException("Invalid number of arguments");
        }
        return ParserUtil.parseIndex(tokens[2]);
    }
}
