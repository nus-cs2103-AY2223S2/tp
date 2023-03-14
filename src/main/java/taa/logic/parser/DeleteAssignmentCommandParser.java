package taa.logic.parser;

import taa.logic.commands.DeleteAssignmentCommand;
import taa.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteStudentCommand object
 */
public class DeleteAssignmentCommandParser implements Parser<DeleteAssignmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteAssignmentCommand
     * and returns a DeleteAssignmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteAssignmentCommand parse(String args) throws ParseException {
        try {
            String assignmentName = ParserUtil.parseName(args).toString();
            return new DeleteAssignmentCommand(assignmentName);
        } catch (ParseException pe) {
            throw new ParseException("Assignment name not found.");
        }
    }
}
