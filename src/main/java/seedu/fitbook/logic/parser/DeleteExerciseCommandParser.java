package seedu.fitbook.logic.parser;

import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.logic.commands.DeleteExerciseCommand;
import seedu.fitbook.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteRoutineCommand object
 */
public class DeleteExerciseCommandParser implements Parser<DeleteExerciseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteRoutineCommand
     * and returns a DeleteRoutineCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteExerciseCommand parse(String args) throws ParseException {
        try {
            String[] argSplit = args.split(" ");
            Index index = ParserUtil.parseIndex(argSplit[1]);
            Index index2 = ParserUtil.parseIndex(argSplit[2]);
            return new DeleteExerciseCommand(index, index2);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteExerciseCommand.MESSAGE_USAGE), pe);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteExerciseCommand.MESSAGE_USAGE), e);

        }
    }

}
